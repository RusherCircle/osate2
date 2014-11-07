/*******************************************************************************
 * Copyright (C) 2013 University of Alabama in Huntsville (UAH)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * The US Government has unlimited rights in this work in accordance with W31P4Q-10-D-0092 DO 0073.
 *******************************************************************************/
package org.osate.ge.diagrams.common.patterns;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentImplementationReference;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Element;
import org.osate.aadl2.FeatureGroupType;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.SubcomponentType;
import org.osate.ge.diagrams.common.AadlElementWrapper;
import org.osate.ge.services.AadlArrayService;
import org.osate.ge.services.AadlFeatureService;
import org.osate.ge.services.AnchorService;
import org.osate.ge.services.BusinessObjectResolutionService;
import org.osate.ge.services.ConnectionCreationService;
import org.osate.ge.services.GraphicsAlgorithmCreationService;
import org.osate.ge.services.HighlightingService;
import org.osate.ge.services.LayoutService;
import org.osate.ge.services.PropertyService;
import org.osate.ge.services.ShapeCreationService;
import org.osate.ge.services.ShapeService;
import org.osate.ge.services.StyleService;
import org.osate.ge.services.SubcomponentService;
import org.osate.ge.services.VisibilityService;
import org.osate.ge.ui.util.ComponentImplementationHelper;

/**
 * A pattern for top level classifier shapes as well as subcomponents.
 * @author philip.alldredge
 */
public class ClassifierPattern extends AgePattern {
	
	private static final String labelShapeName = "label";
	private static final String subcomponentTypeLabelShapeName = "subcomponent_type_label";
	private final VisibilityService visibilityHelper;
	private final LayoutService layoutService;
	private final ShapeService shapeService;
	private final ShapeCreationService shapeCreationService;
	private final AadlFeatureService featureService;
	private final SubcomponentService subcomponentService;
	private final ConnectionCreationService connectionCreationService;
	private final StyleService styleUtil;
	private final GraphicsAlgorithmCreationService graphicsAlgorithmCreator;
	private final PropertyService propertyService;
	private final AadlArrayService arrayService;
	private final HighlightingService highlightingService;
	private final AnchorService anchorService;
	private final BusinessObjectResolutionService bor;
	
	@Inject
	public ClassifierPattern(final VisibilityService visibilityHelper, final LayoutService layoutService, final ShapeService shapeService, final ShapeCreationService shapeCreationService,
			final AadlFeatureService featureService, final SubcomponentService subcomponentService, final ConnectionCreationService connectionCreationService, final StyleService styleUtil,
			final GraphicsAlgorithmCreationService graphicsAlgorithmCreator, final PropertyService propertyService, final AadlArrayService arrayService,
			final HighlightingService highlightingService, final AnchorService anchorService, final BusinessObjectResolutionService bor) {
		this.visibilityHelper = visibilityHelper;
		this.layoutService = layoutService;
		this.shapeService = shapeService;
		this.shapeCreationService = shapeCreationService;
		this.featureService = featureService;
		this.subcomponentService = subcomponentService;
		this.connectionCreationService = connectionCreationService;
		this.styleUtil = styleUtil;
		this.graphicsAlgorithmCreator = graphicsAlgorithmCreator;
		this.propertyService = propertyService;
		this.arrayService = arrayService;
		this.highlightingService = highlightingService;
		this.anchorService = anchorService;
		this.bor = bor;
	}
	
	@Override
	public boolean isMainBusinessObjectApplicable(final Object mainBusinessObject) {
		final Object bo = AadlElementWrapper.unwrap(mainBusinessObject);
		return bo instanceof Classifier || bo instanceof Subcomponent;
	}

	@Override
	public boolean isPaletteApplicable() {
		return false;
	}
	
	@Override
	public boolean canAdd(final IAddContext context) {
		if(isMainBusinessObjectApplicable(context.getNewObject())) {
			final Object targetBo = bor.getBusinessObjectForPictogramElement(context.getTargetContainer());
			return targetBo instanceof Classifier || targetBo instanceof Subcomponent;
		}
		
		return false;
	}
	
	@Override
	public boolean canMoveShape(final IMoveShapeContext context) {
		if(bor.getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof Subcomponent) {
			return super.canMoveShape(context);
		} else {
			return false;
		}
	}
	
	@Override 
	protected void postMoveShape(final IMoveShapeContext context) {
		if(bor.getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof Subcomponent) {
			if(layoutService.checkContainerSize((ContainerShape)context.getPictogramElement())) {
				getFeatureProvider().getDiagramTypeProvider().getDiagramBehavior().refresh();
			}
		}
	}
	
	@Override
	public boolean canResizeShape(final IResizeShapeContext context) {
		return true;
	}
	
	@Override
	public void resizeShape(final IResizeShapeContext context) {
		final ContainerShape shape = (ContainerShape)context.getPictogramElement();			
		super.resizeShape(context);
		layoutService.checkContainerSize(shape);
		getFeatureProvider().getDiagramTypeProvider().getDiagramBehavior().refresh();
			
		// When the graphics algorithm is recreated, the selection is lost. This triggers the selection to be restored on the next editor refresh 
		getFeatureProvider().getDiagramTypeProvider().getDiagramBehavior().getDiagramContainer().setPictogramElementsForSelection(getFeatureProvider().getDiagramTypeProvider().getDiagramBehavior().getDiagramContainer().getSelectedPictogramElements());		
	}
	
	@Override
	public final PictogramElement add(final IAddContext context) {
		final Element bo = (Element)AadlElementWrapper.unwrap(context.getNewObject());
		final IPeCreateService peCreateService = Graphiti.getPeCreateService();
		
        // Create the container shape
        final ContainerShape shape = peCreateService.createContainerShape(context.getTargetContainer(), true);
        link(shape, new AadlElementWrapper(bo));       
        
        // Finish creating
        refresh(shape, bo, context.getX(), context.getY(), 0, 0);
        
        return shape;
	}

	@Override
	public final boolean update(final IUpdateContext context) {
		final PictogramElement pe = context.getPictogramElement();
		final Object bo = AadlElementWrapper.unwrap(getBusinessObjectForPictogramElement(pe));
		
		if(pe instanceof ContainerShape) {
			final GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
			final int x, y, width, height;
			if(ga == null) {
				x = 25;
				y = 25;
				width = height = 0;
			} else {
				x = ga.getX();
				y = ga.getY();
				width = ga.getWidth();
				height = ga.getHeight();
			}
			this.refresh((ContainerShape)pe, bo, x, y, width, height);
		}
		return true;
	}	
	
	private Classifier getClassifier(final ContainerShape shape) {
		final Object bo = bor.getBusinessObjectForPictogramElement(shape);
		final Classifier classifier;
		if(bo instanceof Classifier) {
			classifier = (Classifier)bo;
		} else if(bo instanceof Subcomponent) {
			classifier = (Classifier)subcomponentService.getComponentClassifier(shape,  (Subcomponent)bo);
		} else {
			throw new RuntimeException("Unexpected case. Business object is of unexpected type: " + bo.getClass());
		}	
		
		return classifier;
	}
	
	private void refresh(final ContainerShape shape, final Object bo, final int x, final int y, final int minWidth, final int minHeight) {
		visibilityHelper.setIsGhost(shape, false);
		
		// Remove invalid connections from the diagram
		visibilityHelper.ghostInvalidConnections(null);
		
		if(bo instanceof Classifier) {
			visibilityHelper.ghostInvalidConnections(ModeTransitionPattern.MODE_TRANSITION_TRIGGER_CONNECTION_TYPE);
			visibilityHelper.ghostInvalidConnections(ModePattern.INITIAL_MODE_CONNECTION_TYPE);
		}
		
		// Remove invalid features
		visibilityHelper.ghostInvalidShapes(shape);
		
		final Classifier classifier = getClassifier(shape);
		final Set<Shape> childShapesToGhost = new HashSet<Shape>();
		childShapesToGhost.addAll(visibilityHelper.getNonGhostChildren(shape));

		final List<Shape> touchedShapes = new ArrayList<Shape>();
		if(classifier != null) {
			shapeCreationService.createUpdateFeatureShapes(shape, featureService.getAllOwnedFeatures(classifier), touchedShapes);
		}
		
		// Create component implementation specific shapes
		if(classifier instanceof ComponentImplementation) {
			final ComponentImplementation ci = (ComponentImplementation)classifier;
			shapeCreationService.createUpdateFeatureShapes(shape, ComponentImplementationHelper.getAllInternalFeatures(ci), touchedShapes);
			shapeCreationService.createUpdateFeatureShapes(shape, ComponentImplementationHelper.getAllProcessorFeatures(ci), touchedShapes);
			shapeCreationService.createUpdateShapesForElements(shape, ci.getAllSubcomponents(), 25, true, 30, 25, true, 20, touchedShapes);
		}
		
		// Create/Update Modes and Mode Transitions
		if(classifier instanceof ComponentClassifier) {
			final ComponentClassifier cc = (ComponentClassifier)classifier;			
			shapeCreationService.createUpdateModeShapes(shape, cc.getAllModes(), touchedShapes);
			connectionCreationService.createUpdateConnections(shape, cc.getAllModeTransitions());	
		}
		
		// Create connections
		if(classifier instanceof ComponentImplementation) {
			final ComponentImplementation ci = (ComponentImplementation)classifier;
			connectionCreationService.createUpdateConnections(shape, ci.getAllConnections());
		}

		// Create/Update Flow Specifications
		final ComponentType componentType;
		if(classifier instanceof ComponentType) {
			componentType = (ComponentType)classifier;
		} else if(classifier instanceof ComponentImplementation) {
			componentType = ((ComponentImplementation)classifier).getType();
		} else {
			componentType = null;
		}
		if(componentType != null) {
			connectionCreationService.createUpdateConnections(shape, componentType.getAllFlowSpecifications());
		}
		
		// Ghost child shapes that were not updated
		childShapesToGhost.removeAll(touchedShapes);
		for(final Shape child : childShapesToGhost) {
			visibilityHelper.setIsGhost(child, true);
		}			

		// Update label and graphics algorithms
		if(bo instanceof Subcomponent) {	
			final IPeCreateService peCreateService = Graphiti.getPeCreateService();
			final Subcomponent sc = (Subcomponent)bo;
			
			// Create label
	        final Shape labelShape = peCreateService.createShape(shape, false);
	        propertyService.setName(labelShape, labelShapeName);
	        propertyService.setIsManuallyPositioned(labelShape, true);
	        link(labelShape, new AadlElementWrapper(sc));
	        final String name = getLabelText(sc);
	        final GraphicsAlgorithm labelBackground = graphicsAlgorithmCreator.createTextBackground(labelShape);		
	        graphicsAlgorithmCreator.createLabelGraphicsAlgorithm(labelBackground, name);
	        
			// Create Subcomponent Type Indicator
	        final Shape subcomponentTypeIndicatorShape = peCreateService.createShape(shape, false);
	        propertyService.setName(subcomponentTypeIndicatorShape, subcomponentTypeLabelShapeName);
	        propertyService.setIsManuallyPositioned(subcomponentTypeIndicatorShape, true);
	        final String subcomponentTypeName = getTypeText(sc);
	        final GraphicsAlgorithm subcomponentTypeLabelBackground = graphicsAlgorithmCreator.createTextBackground(subcomponentTypeIndicatorShape);
	        graphicsAlgorithmCreator.createMultiLineLabelGraphicsAlgorithm(subcomponentTypeLabelBackground, subcomponentTypeName);
		}	

		layout(shape, bo, x, y, minWidth, minHeight);

		// Create/update the chopbox anchor
		anchorService.createOrUpdateChopboxAnchor(shape, chopboxAnchorName);
	}	
	
	private GraphicsAlgorithm getBackgroundFromLabelShape(final Shape labelShape) {
		final GraphicsAlgorithm bg = labelShape.getGraphicsAlgorithm();
		if(bg == null || bg.getGraphicsAlgorithmChildren().size() < 1) {
			return null;
		}
		
		return bg;
	}
	
	private AbstractText getTextFromLabelShape(final Shape labelShape) {
		final GraphicsAlgorithm bg = getBackgroundFromLabelShape(labelShape);
		if(bg == null) {
			return null;
		}
		
		final GraphicsAlgorithm txtGa = bg.getGraphicsAlgorithmChildren().get(0);
		if(txtGa instanceof AbstractText) {
			return (AbstractText)txtGa;
		}
		
		return null;
	}
	
	private Shape getLabelShape(final ContainerShape shape) {
		return shapeService.getChildShapeByName(shape, labelShapeName);
	}
	
	private Shape getSubcomponentTypeLabelShape(final ContainerShape shape) {
		return shapeService.getChildShapeByName(shape, subcomponentTypeLabelShapeName);
	}

	public boolean canLayout(ILayoutContext context) {
		return isMainBusinessObjectApplicable(getBusinessObjectForPictogramElement(context.getPictogramElement())) && context.getPictogramElement() instanceof ContainerShape;
	}
	
	@Override
	public boolean layout(ILayoutContext context) {
		final ContainerShape shape = (ContainerShape)context.getPictogramElement();
		final int x = shape.getGraphicsAlgorithm().getX();
		final int y = shape.getGraphicsAlgorithm().getY();
		final int minWidth = shape.getGraphicsAlgorithm().getWidth();
		final int minHeight = shape.getGraphicsAlgorithm().getHeight();
		return layout(shape, bor.getBusinessObjectForPictogramElement(context.getPictogramElement()), x , y, minWidth, minHeight);
	}
	
	private boolean layout(final ContainerShape shape, final Object bo, final int x, final int y, int minWidth, int minHeight) {
		final IGaService gaService = Graphiti.getGaService();
		final GraphicsAlgorithm ga;			
		
		if(bo instanceof Subcomponent) {	
	        // Determine text sizing
	        // Currently adding padding to work around incorrect size being returned by calculateTextSize().
			final Shape labelShape = getLabelShape(shape);
			final GraphicsAlgorithm labelBackground = getBackgroundFromLabelShape(labelShape);
			final AbstractText labelText = getTextFromLabelShape(labelShape);
			
			final Shape subcomponentTypeLabelShape = getSubcomponentTypeLabelShape(shape);
			final GraphicsAlgorithm subcomponentTypeLabelBackground = getBackgroundFromLabelShape(subcomponentTypeLabelShape);
			final AbstractText subcomponentTypeText = getTextFromLabelShape(subcomponentTypeLabelShape);
			final IDimension textSize = GraphitiUi.getUiLayoutService().calculateTextSize(labelText.getValue(), labelText.getStyle().getFont());
	        final IDimension unpaddedSubcomponentTypeTextSize = GraphitiUi.getUiLayoutService().calculateTextSize(subcomponentTypeText.getValue(), subcomponentTypeText.getStyle().getFont(), true);
	        final int paddedLabelTextWidth = textSize.getWidth() + 15;
	        final int paddedLabelTextHeight = textSize.getHeight() + 5;
	        final int paddedTypeTextWidth = unpaddedSubcomponentTypeTextSize.getWidth() + 15;
	        final int paddedTypeTextHeight = unpaddedSubcomponentTypeTextSize.getHeight() + 5;
	        
	        // Sets initialize position and size of text. Centering will be done when the final size of the shape is determined
	        gaService.setLocationAndSize(labelText, 0, 0, paddedLabelTextWidth, paddedLabelTextHeight);
	        gaService.setLocationAndSize(labelBackground, 0, 0, paddedLabelTextWidth, paddedLabelTextHeight);
	        gaService.setLocationAndSize(subcomponentTypeText, 0, 0, paddedTypeTextWidth, paddedTypeTextHeight);
			gaService.setLocationAndSize(subcomponentTypeLabelBackground, 0, 0, paddedTypeTextWidth, paddedTypeTextHeight);

	        final int newSize[] = layoutService.adjustChildShapePositions(shape);
	        ga = graphicsAlgorithmCreator.createClassifierGraphicsAlgorithm(shape, (Subcomponent)bo, newSize[0], newSize[1]);	                
			gaService.setLocation(ga, x, y);
	        
			// Set the position and size of the text	        
			gaService.setLocation(labelBackground, (ga.getWidth() - paddedLabelTextWidth) / 2, 2);
			gaService.setLocation(subcomponentTypeLabelBackground, (ga.getWidth() - paddedTypeTextWidth) / 2, labelText.getY()+paddedLabelTextHeight);
			
			// Set color based on current mode
			highlightingService.highlight((Subcomponent)bo, null, ga);
		} else {
			final Classifier classifier = getClassifier(shape);
			final int newSize[] = layoutService.adjustChildShapePositions(shape);
			
			// Enforce a minimum size for classifiers
			newSize[0] = Math.max(newSize[0], 300);
			newSize[1] = Math.max(newSize[1], 325);
			
			if(classifier instanceof FeatureGroupType) { // Use a rectangle for feature group types because the feature group shape is not ideal as a container for features.
				ga = gaService.createRectangle(shape);
				ga.setStyle(styleUtil.getSystemStyle(false));
				gaService.setLocationAndSize(ga, x, y, newSize[0], newSize[1]);
			} else {
				ga = graphicsAlgorithmCreator.createClassifierGraphicsAlgorithm(shape, classifier, newSize[0], newSize[1]);
				gaService.setLocation(ga, x, y);
			}
		}
		
		ga.setFilled(false);
		return true;
	}

	// Labels
	private String getSubcomponentName(final Subcomponent sc) {
		return sc.getName() == null ? "" : sc.getName();
	}
	
	private String getLabelText(final Subcomponent sc) {
		return getSubcomponentName(sc) + arrayService.getDimensionUserString(sc);
	}
	
	private String getTypeText(final Subcomponent sc) {
		String retVal = "";
        final SubcomponentType scType = subcomponentService.getAllSubcomponentType(sc);
        
		if(scType != null) {
			retVal += scType.getQualifiedName();
		}

		// TODO: Show types of all subcomponents if collapsed?
		// Add text for each of the implementation references (for arrays)
		final List<ComponentImplementationReference> implRefs = subcomponentService.getArrayComponentImplementationReferences(sc);
		if(implRefs.size() != 0) {
			retVal += "\n(";			
			for(int i = 0; i < implRefs.size(); i++) {
				final ComponentImplementationReference ref = implRefs.get(i);
				if(ref.getImplementation() != null) {
					if(ref.getImplementation().eIsProxy()) {
						retVal += "<unresolved>";
					} else {
						retVal += ref.getImplementation().getQualifiedName();
					}
				}
				
				if(i == (implRefs.size() - 1)) {
					retVal += ")";
				} else {
					retVal += ",\n";					
				}
				
			}
		}
		
		return retVal;
	}

}
