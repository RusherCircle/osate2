package org.osate.ge.internal.graphiti.features;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.graphiti.features.ICustomUndoRedoFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.osate.ge.di.GetGraphic;
import org.osate.ge.di.Names;
import org.osate.ge.internal.di.GetNameLabelConfiguration;
import org.osate.ge.internal.di.InternalNames;
import org.osate.ge.internal.graphics.FeatureGraphic;
import org.osate.ge.internal.graphiti.PictogramElementProxy;
import org.osate.ge.internal.graphiti.graphics.AgeGraphitiGraphicsUtil;
import org.osate.ge.internal.labels.AgeLabelConfiguration;
import org.osate.ge.internal.labels.LabelConfiguration;
import org.osate.ge.internal.labels.LabelConfigurationBuilder;
import org.osate.ge.internal.labels.LabelPosition;
import org.osate.ge.internal.services.BusinessObjectResolutionService;
import org.osate.ge.internal.services.ExtensionService;
import org.osate.ge.internal.services.PropertyService;
import org.osate.ge.internal.services.ShapeService;
import org.osate.ge.internal.DockArea;

// ILayoutFeature implementation for shapes associated with a BO with a business object handler
public class BoHandlerLayoutFeature extends AbstractLayoutFeature implements ICustomUndoRedoFeature {
	private static final int labelPadding = 2;
	private final static LabelConfiguration defaultLabelConfiguration = LabelConfigurationBuilder.create().build();
	private final BusinessObjectResolutionService bor;
	private final ExtensionService extService;
	private final ShapeService shapeService;
	private final PropertyService propertyService;
	
	@Inject
	public BoHandlerLayoutFeature(final IFeatureProvider fp, final BusinessObjectResolutionService bor, 
			final ExtensionService extService, final ShapeService shapeService, final PropertyService propertyService) {
		super(fp);
		this.bor = Objects.requireNonNull(bor, "bor must not be null");
		this.extService = Objects.requireNonNull(extService, "extService must not be null");
		this.shapeService = Objects.requireNonNull(shapeService, "shapeService must not be null");
		this.propertyService = Objects.requireNonNull(propertyService, "propertyService must not be null");
	}

	@Override
	public boolean canLayout(final ILayoutContext context) {
		final Object bo = bor.getBusinessObjectForPictogramElement(context.getPictogramElement());
		return context.getPictogramElement() instanceof ContainerShape && 
				!(context.getPictogramElement() instanceof Diagram) && 
				extService.getApplicableBusinessObjectHandler(bo) != null;
	}

	@Override
	public boolean layout(final ILayoutContext context) {
		final ContainerShape shape = (ContainerShape)context.getPictogramElement();
		if(shape.getGraphicsAlgorithm() == null) {
			return false;
		}
			
		final Object bo = bor.getBusinessObjectForPictogramElement(context.getPictogramElement());
		final Object handler = extService.getApplicableBusinessObjectHandler(bo);
		
		final IEclipseContext eclipseCtx = extService.createChildContext();
		try {
			final Object diagramBo = bor.getBusinessObjectForPictogramElement(getDiagram());
			eclipseCtx.set(Names.BUSINESS_OBJECT, bo);
			eclipseCtx.set(InternalNames.INTERNAL_DIAGRAM_BO, diagramBo);
			eclipseCtx.set(InternalNames.DIAGRAM_ELEMENT_PROXY, new PictogramElementProxy(context.getPictogramElement()));	
			final AgeLabelConfiguration labelConfiguration = (AgeLabelConfiguration)ContextInjectionFactory.invoke(handler, GetNameLabelConfiguration.class, eclipseCtx, defaultLabelConfiguration);
			final GraphicsAlgorithm shapeGa = shape.getGraphicsAlgorithm();

			final Object gr = ContextInjectionFactory.invoke(handler, GetGraphic.class, eclipseCtx, null);
			if(gr == null) {	
				return false;
			}

			// Determine the space needed for each area
			final int dockedShapeOffset = AgeGraphitiGraphicsUtil.featureGroupDefaultSymbolWidth; // For now, only graphics which represent feature groups may have shape docked at the GROUP dock area.
			int innerWidth = 10;
			int innerHeight = 10;
			int leftOuterPadding = 0;
			int rightOuterPadding = 0;
			int topOuterPadding = 0;
			int bottomOuterPadding = 0;

			final Shape nameShape = shapeService.getChildShapeByName(shape, BoHandlerFeatureHelper.nameShapeName);
			final GraphicsAlgorithm nameGa = nameShape == null ? null : nameShape.getGraphicsAlgorithm();
			final DockArea shapeDockArea = getNonGroupDockArea(shape);
			
			// Adjust label position if the shape is docked
			LabelPosition nameHorizontalPosition = getHorizontalLabelPosition(labelConfiguration.horizontalPosition, labelConfiguration.verticalPosition, shapeDockArea);
			LabelPosition nameVerticalPosition = getVerticalLabelPosition(labelConfiguration.horizontalPosition, labelConfiguration.verticalPosition, shapeDockArea);
			
			// The minimum inner width and height are the minimum size for the graphics algorithm for the inner area.
			// In the case of fixed size graphics algorithms such as features, the actual size of the inner graphics algorithm's symbol will be less than
			// the minimum after creation. In that case, these values are used to adjust the bounds of the inner graphics algorithm after creation.
			int minInnerWidth = 0;
			int minInnerHeight = 0;
			
			// Handle label configuration
			if(nameGa != null) {
				switch(nameHorizontalPosition) {
				case BEFORE_GRAPHIC:
					leftOuterPadding = Math.max(leftOuterPadding, nameGa.getWidth());
					break;	

				case DEFAULT:
				case GRAPHIC_BEGINNING:
				case GRAPHIC_CENTER:
				case GRAPHIC_END:
				default:
					minInnerWidth = Math.max(minInnerWidth, nameGa.getWidth() + labelPadding); // Add additional padding to avoid label background from overlapping shape border for simple shapes
					break;

				case AFTER_GRAPHIC:
					rightOuterPadding = Math.max(rightOuterPadding, nameGa.getWidth());
					break;			
				}					
				
				// Set Y value based on the label configuration
				switch(nameVerticalPosition) {
				case BEFORE_GRAPHIC:
					topOuterPadding = Math.max(topOuterPadding, nameGa.getHeight());
					break;	
					
				case DEFAULT:					
				case GRAPHIC_BEGINNING:
				case GRAPHIC_CENTER:
				case GRAPHIC_END:
				default:
					minInnerHeight = Math.max(minInnerHeight, nameGa.getHeight() + labelPadding);
					break;
					
				case AFTER_GRAPHIC:
					bottomOuterPadding = Math.max(bottomOuterPadding, nameGa.getHeight());
					break;			
				}
			}
					
			// Shrink features to the smallest required size
			if(!(gr instanceof FeatureGraphic)) {
				// Adjust inner width and height based on padding and current size
				innerWidth = Math.max(innerWidth, shapeGa.getWidth() - leftOuterPadding - rightOuterPadding);
				innerHeight = Math.max(innerHeight, shapeGa.getHeight() - topOuterPadding - bottomOuterPadding);
			}
			
			// Prevent children from being positioned outside of the inner area
			for(final Shape childShape : shapeService.getNonGhostChildren(shape)) {
				if(!propertyService.isManuallyPositioned(childShape)) {
					final GraphicsAlgorithm childGa = childShape.getGraphicsAlgorithm();
					if(childGa != null) {
						if(childGa.getX() < leftOuterPadding) {
							childGa.setX(leftOuterPadding);
						}
						
						if(childGa.getY() < topOuterPadding) {
							childGa.setY(topOuterPadding);
						}
					}
				}
			}			
			
			// Group feature shapes based on docking area
			final Map<DockArea, List<Shape>> dockAreaToShapesMap = buildDockAreaToChildrenMap(shape);
			
			// Adjust shapes so they do not overlap
			cleanupOverlappingDockedShapes(shapeDockArea, dockAreaToShapesMap);
			
			// Handle positioning of group docked shapes
			for(final Entry<DockArea, List<Shape>> dockAreaToShapesEntry : dockAreaToShapesMap.entrySet()) {
				for(final Shape childShape : dockAreaToShapesEntry.getValue()) {
					if(dockAreaToShapesEntry.getKey() == DockArea.GROUP) {
						switch(shapeDockArea) {
						case LEFT:
						case RIGHT:
							minInnerWidth = Math.max(minInnerWidth, childShape.getGraphicsAlgorithm().getWidth() + dockedShapeOffset);
							break;
							
						case TOP:
						case BOTTOM:
							minInnerHeight = Math.max(minInnerHeight, childShape.getGraphicsAlgorithm().getHeight() + dockedShapeOffset);
							break;
							
						default:
							break;						
						}						
					}
				}
			}
			innerWidth = Math.max(innerWidth, minInnerWidth);
			innerHeight = Math.max(innerHeight, minInnerHeight);
			
			// Consider children when determining size
			final int[] minSizeForChildren = getMinimumSizeForChildren(shape, leftOuterPadding, topOuterPadding);
			innerWidth = Math.max(innerWidth, minSizeForChildren[0]);
			innerHeight = Math.max(innerHeight, minSizeForChildren[1]);

			// Create the graphics algorithm					
			shapeGa.getGraphicsAlgorithmChildren().clear();
			final boolean filled = diagramBo != bo;
			
			final GraphicsAlgorithm innerGa;
			// Adjust the size of the graphics algorithm based on the rotation implied by the dock area
			if(shapeDockArea == DockArea.TOP || shapeDockArea == DockArea.BOTTOM) {
				innerGa = AgeGraphitiGraphicsUtil.createGraphicsAlgorithm(getDiagram(), shapeGa, gr, innerHeight, innerWidth, filled);	
			} else {
				innerGa = AgeGraphitiGraphicsUtil.createGraphicsAlgorithm(getDiagram(), shapeGa, gr, innerWidth, innerHeight, filled);
			}

			// Rotate shape
			if(shapeDockArea != null) {
				AgeGraphitiGraphicsUtil.rotate(innerGa, shapeDockArea);
			}

			// Update variables using actual size of inner graphics algorithm
			innerWidth = Math.max(innerGa.getWidth(), minInnerWidth);
			innerHeight = Math.max(innerGa.getHeight(), minInnerHeight);

			// Update the shape's graphics algorithm based on required size
			final int innerRight = leftOuterPadding + innerWidth;
			final int innerBottom = topOuterPadding + innerHeight;
			shapeGa.setWidth(innerRight + rightOuterPadding);
			shapeGa.setHeight(innerBottom + bottomOuterPadding);			

			// Position docked shapes
			for(final Entry<DockArea, List<Shape>> dockAreaToShapesEntry : dockAreaToShapesMap.entrySet()) {
				for(final Shape childShape : dockAreaToShapesEntry.getValue()) {
					if(dockAreaToShapesEntry.getKey() == DockArea.LEFT) {
						childShape.getGraphicsAlgorithm().setX(leftOuterPadding);
					} else if(dockAreaToShapesEntry.getKey() == DockArea.RIGHT) {
						childShape.getGraphicsAlgorithm().setX(innerRight-childShape.getGraphicsAlgorithm().getWidth());
					} else if(dockAreaToShapesEntry.getKey() == DockArea.TOP) {
						childShape.getGraphicsAlgorithm().setY(topOuterPadding);
					} else if(dockAreaToShapesEntry.getKey() == DockArea.BOTTOM) {
						childShape.getGraphicsAlgorithm().setY(innerBottom-childShape.getGraphicsAlgorithm().getHeight());
					} else if(dockAreaToShapesEntry.getKey() == DockArea.GROUP) {
						final GraphicsAlgorithm childGa = childShape.getGraphicsAlgorithm();
						switch(shapeDockArea) {
						case LEFT:
						default:
							childGa.setX(dockedShapeOffset);
							break;
							
						case RIGHT:
							childGa.setX(innerWidth - dockedShapeOffset - childGa.getWidth());
							break;

						case TOP:
							childGa.setY(dockedShapeOffset);
							break;
							
						case BOTTOM:
							childGa.setY(innerBottom - dockedShapeOffset - childGa.getHeight());
							break;
						}
						
					}
				}
			}
			
			// Position the inner graphics algorithm
			if(shapeDockArea == DockArea.RIGHT) {
				innerGa.setX(innerRight-innerGa.getWidth());
				innerGa.setY(topOuterPadding);
			} else if(shapeDockArea == DockArea.BOTTOM) {
				innerGa.setX(leftOuterPadding);
				innerGa.setY(innerBottom-innerGa.getHeight());
			} else {
				innerGa.setX(leftOuterPadding);
				innerGa.setY(topOuterPadding);
			}
			
			if(nameGa != null) {
				// Position the labels
				switch(nameHorizontalPosition) {
				case BEFORE_GRAPHIC:
					nameGa.setX(0);
					break;	

				case DEFAULT:
				case GRAPHIC_BEGINNING:
				default:
					nameGa.setX(leftOuterPadding);
					break;
					
				case GRAPHIC_CENTER:
					nameGa.setX(leftOuterPadding + (innerWidth - nameGa.getWidth())/2);
					break;
					
				case GRAPHIC_END:						
					nameGa.setX(innerRight - nameGa.getWidth());
					break;

				case AFTER_GRAPHIC:
					nameGa.setX(innerRight);
					break;			
				}					
				
				// Set Y value based on the label configuration
				switch(nameVerticalPosition) {
				case BEFORE_GRAPHIC:
					nameGa.setY(0);
					break;	

				case DEFAULT:
				case GRAPHIC_BEGINNING:
				default:
					nameGa.setY(topOuterPadding + labelPadding);
					break;
					
				case GRAPHIC_CENTER:
					nameGa.setY(topOuterPadding + (innerHeight - nameGa.getHeight())/2);
					break;
					
				case GRAPHIC_END:						
					nameGa.setY(innerBottom - nameGa.getHeight());
					break;

				case AFTER_GRAPHIC:
					nameGa.setY(innerBottom);
					break;			
				}
			}
		} finally {
			eclipseCtx.dispose();
		}		
				
		return false;
	}
	
	// Determines the horizontal label position. Transforms the positions specified by the business object handler based on the dock area.
	private LabelPosition getHorizontalLabelPosition(final LabelPosition horizontalPosition, final LabelPosition verticalPosition, final DockArea shapeDockArea) {
		if(shapeDockArea == null) {
			return horizontalPosition;			
		}
		
		switch(shapeDockArea) {
		case LEFT:
			return horizontalPosition;

		case RIGHT:
			return horizontalPosition.mirror();
			
		case TOP:
			return verticalPosition;
			
		case BOTTOM:
			return verticalPosition;
			
		default:
			return horizontalPosition;		
		}
	}

	// Determines the vertical label position. Transforms the positions specified by the business object handler based on the dock area.
	private LabelPosition getVerticalLabelPosition(final LabelPosition horizontalPosition, final LabelPosition verticalPosition, final DockArea shapeDockArea) {
		if(shapeDockArea == null) {
			return verticalPosition;			
		}
		
		switch(shapeDockArea) {
		case LEFT:
			return verticalPosition;

		case RIGHT:
			return verticalPosition;
			
		case TOP:
			return horizontalPosition;
			
		case BOTTOM:
			return horizontalPosition.mirror();
			
		default:
			return verticalPosition;		
		}
	}
	
	private int[] getMinimumSizeForChildren(final ContainerShape shape, final int innerLeftX, final int innerTopY) {
		// Calculate max right and bottom
		int maxRight = 0;
		int maxBottom = 0;
		for(final Shape childShape :  shapeService.getNonGhostChildren(shape)) {
			if(childShape.isVisible()) {
				if(!propertyService.isManuallyPositioned(childShape) && propertyService.isLayedOut(childShape)) {
					final GraphicsAlgorithm childGa = childShape.getGraphicsAlgorithm();
					final DockArea childDockArea = getDockArea(childShape);
					if(childDockArea == null || childDockArea == DockArea.GROUP) {	
						maxRight = Math.max(maxRight, childGa.getX() + childGa.getWidth());
						maxBottom = Math.max(maxBottom, childGa.getY() + childGa.getHeight());
					} else {
						switch(childDockArea) {
						case LEFT:
						case RIGHT:
							maxRight = Math.max(maxRight, innerLeftX + childGa.getWidth());
							maxBottom = Math.max(maxBottom, childGa.getY() + childGa.getHeight());						
							break;
						case TOP:
						case BOTTOM:
							maxRight = Math.max(maxRight, childGa.getX() + childGa.getWidth());
							maxBottom = Math.max(maxBottom, innerTopY + childGa.getHeight());
							break;
						default:
							break;						
						}
					}
				}
			}
		}

		return new int[] { maxRight - innerLeftX, maxBottom - innerTopY};
	}
	
	private DockArea getNonGroupDockArea(ContainerShape shape) {
		DockArea result = null;
		do {
			result = getDockArea(shape);
			shape = shape.getContainer();
		} while(result != null && result == DockArea.GROUP);

		return result;
	}
	
	private final Comparator<Shape> xComparator = new Comparator<Shape>() {
		@Override
		public int compare(final Shape s1, final Shape s2) {
			if(s1.getGraphicsAlgorithm() == null) {
				return -1;
			} else if(s2.getGraphicsAlgorithm() == null) {
				return 1;
			}
			
			return Integer.compare(s1.getGraphicsAlgorithm().getX(), s2.getGraphicsAlgorithm().getX());
		}		
	};
	private final Comparator<Shape> yComparator = new Comparator<Shape>() {
		@Override
		public int compare(final Shape s1, final Shape s2) {
			if(s1.getGraphicsAlgorithm() == null) {
				return -1;
			} else if(s2.getGraphicsAlgorithm() == null) {
				return 1;
			}
			
			return Integer.compare(s1.getGraphicsAlgorithm().getY(), s2.getGraphicsAlgorithm().getY());
		}		
	};
	private void cleanupOverlappingDockedShapes(final DockArea parentNonGroupDockArea, final Map<DockArea, List<Shape>> dockAreaToShapesMap) {
		for(final Entry<DockArea, List<Shape>> dockAreaToShapesEntry : dockAreaToShapesMap.entrySet()) {
			if(dockAreaToShapesEntry.getKey() != null) {
				// Determine the orientation of the docked shapes
				DockArea dockArea = dockAreaToShapesEntry.getKey();
				if(dockArea == DockArea.GROUP) {
					dockArea = parentNonGroupDockArea;
				}
				
				final boolean vertical;
				if(dockArea == DockArea.LEFT || dockArea == DockArea.RIGHT) {
					vertical = true;
				} else if(dockArea == DockArea.TOP || dockArea == DockArea.BOTTOM) {
					vertical = false;
				} else {
					vertical = true;
				}
				
				// Sort shapes by order
				final List<Shape> sortedShapes = new ArrayList<Shape>(dockAreaToShapesEntry.getValue());
				
				if(vertical) {
					Collections.sort(sortedShapes, yComparator);
					int minY = 0;
					for(Shape shape : sortedShapes) {
						if(shape.getGraphicsAlgorithm() != null) {
							final int newY = Math.max(shape.getGraphicsAlgorithm().getY(), minY);
							shape.getGraphicsAlgorithm().setY(newY);
							minY = newY + shape.getGraphicsAlgorithm().getHeight() + 5;
						}
					}
				} else {
					Collections.sort(sortedShapes, xComparator);
					int minX = 0;
					for(Shape shape : sortedShapes) {
						if(shape.getGraphicsAlgorithm() != null) {
							final int newX = Math.max(shape.getGraphicsAlgorithm().getX(), minX);
							shape.getGraphicsAlgorithm().setX(newX);
							minX = newX + shape.getGraphicsAlgorithm().getWidth() + 5;
						}
					}
				}
			}
		}		
	}
	
	private DockArea getDockArea(final Shape shape) {
		return DockArea.getById(propertyService.getDockArea(shape));
	}
	
	/**
	 * Builds a mapping between DockArea as returned by getDockArea(String) and a list of child shapes.
	 * @param shape
	 * @return
	 */
	private Map<DockArea, List<Shape>> buildDockAreaToChildrenMap(final ContainerShape shape) {
		// Build mapping from dock area to shapes
		final Map<DockArea, List<Shape>> result = new HashMap<DockArea, List<Shape>>();
		for(final Shape child : shapeService.getNonGhostChildren(shape)) {
			final DockArea dockArea = getDockArea(child);
			if(dockArea != null) {
				List<Shape> dockAreaShapes = result.get(dockArea);
				
				// Create a new list if the shape is the first shape in the dock area
				if(dockAreaShapes == null) {
					dockAreaShapes = new ArrayList<Shape>();
					result.put(dockArea, dockAreaShapes);
				}
				
				dockAreaShapes.add(child);
			}
		}
		
		return result;
	}
	
	// ICustomUndoRedoFeature
	@Override
	public boolean canUndo(final IContext context) {
		return false;
	}
	
	@Override
	public void preUndo(IContext context) {
	}

	@Override
	public void postUndo(IContext context) {
	}

	@Override
	public boolean canRedo(IContext context) {
		return false;
	}

	@Override
	public void preRedo(IContext context) {
	}

	@Override
	public void postRedo(IContext context) {
	}
}
