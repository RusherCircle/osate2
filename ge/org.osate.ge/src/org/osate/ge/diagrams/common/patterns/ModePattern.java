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
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.impl.MoveShapeContext;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.ui.services.GraphitiUi;
import org.osate.aadl2.Aadl2Factory;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.Mode;
import org.osate.aadl2.ModeTransition;
import org.osate.aadl2.NamedElement;
import org.osate.ge.diagrams.common.AadlElementWrapper;
import org.osate.ge.diagrams.common.AgeImageProvider;
import org.osate.ge.services.AadlModificationService;
import org.osate.ge.services.AnchorService;
import org.osate.ge.services.BusinessObjectResolutionService;
import org.osate.ge.services.DiagramModificationService;
import org.osate.ge.services.GraphicsAlgorithmCreationService;
import org.osate.ge.services.LayoutService;
import org.osate.ge.services.NamingService;
import org.osate.ge.services.PropertyService;
import org.osate.ge.services.RefactoringService;
import org.osate.ge.services.SerializableReferenceService;
import org.osate.ge.services.ShapeCreationService;
import org.osate.ge.services.ShapeService;
import org.osate.ge.services.StyleService;
import org.osate.ge.services.UserInputService;
import org.osate.ge.services.VisibilityService;
import org.osate.ge.services.AadlModificationService.AbstractModifier;

public class ModePattern extends AgeLeafShapePattern {
	public static String INITIAL_MODE_CONNECTION_TYPE = "initial_mode";
	public static String innerModeShapeName = "inner_mode";
	public static String initialModeShapeName = "initial_mode";
	private final AnchorService anchorService;
	private final LayoutService resizeHelper;
	private final ShapeService shapeHelper;
	private final PropertyService propertyService;
	private final GraphicsAlgorithmCreationService graphicsAlgorithmCreator;
	private final StyleService styleUtil;
	private final ShapeCreationService shapeCreationService;
	private final DiagramModificationService diagramModService;
	private final AadlModificationService modificationService;
	private final UserInputService userInputService;
	private final NamingService namingService;
	private final SerializableReferenceService referenceService;
	private final BusinessObjectResolutionService bor;
	private final RefactoringService refactoringService;
	
	@Inject
	public ModePattern(final AnchorService anchorUtil, final VisibilityService visibilityHelper, final LayoutService resizeHelper, final ShapeService shapeHelper, 
			final PropertyService propertyUtil, final GraphicsAlgorithmCreationService graphicsAlgorithmCreator, final StyleService styleUtil, 
			final ShapeCreationService shapeCreationService, DiagramModificationService diagramModService, final AadlModificationService modificationService, 
			final UserInputService userInputService, final NamingService namingService, final RefactoringService refactoringService, 
			final SerializableReferenceService referenceService, final BusinessObjectResolutionService bor) {
		super(anchorUtil, visibilityHelper);
		this.anchorService = anchorUtil;
		this.resizeHelper = resizeHelper;
		this.shapeHelper = shapeHelper;
		this.propertyService = propertyUtil;
		this.graphicsAlgorithmCreator = graphicsAlgorithmCreator;
		this.styleUtil = styleUtil;
		this.shapeCreationService = shapeCreationService;
		this.diagramModService = diagramModService;
		this.modificationService = modificationService;
		this.userInputService = userInputService;
		this.namingService = namingService;
		this.refactoringService = refactoringService;
		this.referenceService = referenceService;
		this.bor = bor;
	}

	@Override
	public boolean isMainBusinessObjectApplicable(final Object mainBusinessObject) {
		return AadlElementWrapper.unwrap(mainBusinessObject) instanceof Mode;
	}	
		
	@Override
	public boolean canAdd(final IAddContext context) {
		if(isMainBusinessObjectApplicable(context.getNewObject())) {
			if(AadlElementWrapper.unwrap(getBusinessObjectForPictogramElement(context.getTargetContainer())) instanceof ComponentClassifier) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean canResizeShape(final IResizeShapeContext ctx) {
		return false;
	}
	
	/** Method to transform a move shape context into one that can be used. Typically the "root" shapes are moved. However, due to an issue that prevents direct editing
	 * when the label is not an active shape, the label is active and is used in the call to the move shape methods. This method transforms the move shape context
	 * to point to the root shape instead of the label shape.
	 * @param context
	 * @return
	 */
	private IMoveShapeContext transformMoveShapeContext(final IMoveShapeContext context) {
		final Shape shape = context.getShape();
		final Shape newShape;
		final ContainerShape sourceContainer;
		if(shape.getGraphicsAlgorithm() instanceof Text) {
			newShape = shapeHelper.getClosestAncestorWithBusinessObjectType(shape.getContainer(), Mode.class);
			sourceContainer = newShape.getContainer();
		} else {
			newShape = shape;
			sourceContainer = context.getSourceContainer();
		}
		final MoveShapeContext newCtx = new MoveShapeContext(newShape);
		newCtx.setSourceContainer(sourceContainer);
		newCtx.setTargetContainer(context.getTargetContainer());
		newCtx.setDeltaX(context.getDeltaX());
		newCtx.setDeltaY(context.getDeltaY());
		newCtx.setX(context.getX());
		newCtx.setY(context.getY());
		return newCtx;
	}
	
	@Override
	public boolean canMoveShape(final IMoveShapeContext context) {
		return super.canMoveShape(transformMoveShapeContext(context));
	}

	@Override
	public void moveShape(final IMoveShapeContext context) {
		super.moveShape(transformMoveShapeContext(context));
	}
	
	@Override 
	protected void postMoveShape(final IMoveShapeContext untransformedContext) {
		final IMoveShapeContext newContext = transformMoveShapeContext(untransformedContext);
		final Anchor anchor = anchorService.getAnchorByName(getInnerModeShape((ContainerShape)newContext.getPictogramElement()), chopboxAnchorName);
		if(anchor != null) {
			updateModeTransition(anchor.getIncomingConnections());
			updateModeTransition(anchor.getOutgoingConnections());
		}
		
		if(resizeHelper.checkContainerSize((ContainerShape)newContext.getPictogramElement())) {
			getFeatureProvider().getDiagramTypeProvider().getDiagramBehavior().refresh();
		}
	}
	
	// Updates the control points for mode transition connections. Also update the mode transition triggers. 
	// It does not update all aspects of the connection because doing so will cause problems due to issues
	// caused by creating graphics algorithms during postMoveShape()
	public void updateModeTransition(final List<Connection> connections) {
		for(Connection connection : connections) {
			final Object connectionBo = AadlElementWrapper.unwrap(this.getBusinessObjectForPictogramElement(connection));
			if(connectionBo instanceof ModeTransition) {
				final ModeTransition mt = (ModeTransition)connectionBo;
				ModeTransitionPattern.updateControlPoints(connection);
				ModeTransitionPattern.updateAnchors(referenceService, connection, mt, anchorService);
			}
		}
	}
	
	@Override
	protected void createGaAndInnerShapes(final ContainerShape shape, final Object bo, final int x, final int y) {
		final Mode mode = (Mode)bo;
		final IGaService gaService = Graphiti.getGaService();
		final IPeCreateService peCreateService = Graphiti.getPeCreateService();
        
        // Remove connections related to the initial shape
		{
	        final Shape initialModeShape = shapeHelper.getChildShapeByName(shape, initialModeShapeName);
	        if(initialModeShape != null) {
	        	removeConnectionsByAnchorParent(initialModeShape);
	        }
		}
        
		// Remove child shapes
		// Clear all shapes except for the inner mode shape
		final Iterator<Shape> it = shape.getChildren().iterator();
		while(it.hasNext()) {
			final Shape child = it.next();
			if(!innerModeShapeName.equals(propertyService.getName(child))) {
				it.remove();
			}
		}		
		
		final GraphicsAlgorithm ga = gaService.createInvisibleRectangle(shape);		
		final int initialModeHeight = 20;
		final int innerModeHeight = 50;
		final int totalHeight = mode.isInitial() ? innerModeHeight + initialModeHeight : innerModeHeight;
		
		// Get/create inner mode shape
		ContainerShape innerModeShape = getInnerModeShape(shape);
		if(innerModeShape == null) {
			innerModeShape = peCreateService.createContainerShape(shape, true);
			propertyService.setName(innerModeShape, innerModeShapeName);
		}
		
		// Ensure the inner mode shope has a chopbox anchor
		anchorService.createOrUpdateChopboxAnchor(innerModeShape, chopboxAnchorName);
		
		// Clear the inner mode shape's children
		innerModeShape.getChildren().clear();
		
		// Determine the label text
        final String labelTxt = mode.getName();
        
		// Create label
        final Shape labelShape = peCreateService.createShape(innerModeShape, true);
        propertyService.setIsManuallyPositioned(labelShape, true);
        link(labelShape, new AadlElementWrapper(mode));
        final Text text = graphicsAlgorithmCreator.createLabelGraphicsAlgorithm(labelShape, labelTxt);
        
        // Create an anchor for the label otherwise there are issues creating mode transitions
        anchorService.createOrUpdateChopboxAnchor(labelShape, chopboxAnchorName);
        
        // Set the size        
        final IDimension textSize = GraphitiUi.getUiLayoutService().calculateTextSize(labelTxt, text.getStyle().getFont());
		final int width = Math.max(100, textSize == null ? 0 : textSize.getWidth() + 30); 
		gaService.setLocationAndSize(text, 0, 0, width, innerModeHeight);
				
		// Create the graphics algorithm
        final GraphicsAlgorithm modeGa = graphicsAlgorithmCreator.createModeGraphicsAlgorithm(innerModeShape, width, innerModeHeight);        
        gaService.setLocation(modeGa, 0, totalHeight - innerModeHeight);
        gaService.setLocationAndSize(ga, x, y, width, totalHeight);

        if(mode.isInitial()) {
			// Create the shape for the initial mode
			final ContainerShape initialModeShape = peCreateService.createContainerShape(shape, true);
			propertyService.setName(initialModeShape, initialModeShapeName);
			graphicsAlgorithmCreator.createInitialModeGraphicsAlgorithm(initialModeShape, 10);			
			final Anchor initialModeAnchor = peCreateService.createChopboxAnchor(initialModeShape);
			
			// Create a line between the initial mode symbol and the actual mode symbol
			final Connection initialModeConnection = peCreateService.createCurvedConnection(new double[] {0.0, -10.0}, getDiagram());
			initialModeConnection.setStart(initialModeAnchor);
			initialModeConnection.setEnd(anchorService.getAnchorByName(innerModeShape, chopboxAnchorName));
			propertyService.setConnectionType(initialModeConnection, INITIAL_MODE_CONNECTION_TYPE);
			
			// Create the line
			final Polyline polyline = gaService.createPlainPolyline(initialModeConnection);
			final Style style = styleUtil.getDecoratorStyle();
			polyline.setStyle(style);
			
			// Create the arrow decorator
			final ConnectionDecorator arrowDecorator = peCreateService.createConnectionDecorator(initialModeConnection, false, 1.0, true);    
	        createArrow(arrowDecorator, styleUtil.getDecoratorStyle());		
		}
	}
	
	private ContainerShape getInnerModeShape(final ContainerShape shape) {
		return (ContainerShape)shapeHelper.getChildShapeByName(shape, innerModeShapeName);
	}
	
	private ComponentClassifier getComponentClassifier(final Shape modeShape) {
		return shapeHelper.getClosestBusinessObjectOfType(modeShape, ComponentClassifier.class);
	}
	
	private GraphicsAlgorithm createArrow(final GraphicsAlgorithmContainer gaContainer, final Style style) {
	    final IGaService gaService = Graphiti.getGaService();
	    final GraphicsAlgorithm ga = gaService.createPlainPolygon(gaContainer, new int[] {
	    		-6, 4, 
	    		2, 0, 
	    		-6, -4});
	    ga.setStyle(style);
	    return ga;
	}
	
	protected void updateAnchors(final ContainerShape shape) {
		// Remove orphan anchors. Usually created by mode transition triggers
		anchorService.removeAnchorsWithoutConnections(shape);
		
		super.updateAnchors(shape);
	}
	
	private void removeConnectionsByAnchorParent(final PictogramElement anchorParent) {
		final List<Connection> connectionsToRemove = new ArrayList<Connection>();
		
		for(final Connection connection : this.getDiagram().getConnections()) {
			boolean remove = false;
			
			if((connection.getStart() != null && connection.getStart().getParent() == anchorParent) ||
					(connection.getEnd() != null && connection.getEnd().getParent() == anchorParent)) {
				remove = true;
				break;
			}

			if(remove) {
				connectionsToRemove.add(connection);
			}
		}
		
		// Remove the connections
		for(final Connection connection : connectionsToRemove) {
			EcoreUtil.delete(connection, true);
		}		
	}

	@Override
	public boolean isPaletteApplicable() {
		return bor.getBusinessObjectForPictogramElement(getDiagram()) instanceof ComponentClassifier;
	}
	
	@Override
	public boolean canCreate(final ICreateContext context) {
		return !(context.getTargetContainer() instanceof Diagram) && bor.getBusinessObjectForPictogramElement(context.getTargetContainer()) instanceof ComponentClassifier;
	}
	@Override
	public String getCreateImageId(){
		final Aadl2Package p = Aadl2Factory.eINSTANCE.getAadl2Package();
		return AgeImageProvider.getImage(p.getMode());
	}
	
	@Override
	public String getCreateName() {
		return "Mode";
	}
	
	@Override
	public Object[] create(final ICreateContext context) {
		// Get the classifier
		final ComponentClassifier classifier = (ComponentClassifier)bor.getBusinessObjectForPictogramElement(context.getTargetContainer());
		final Mode newMode;
		if(classifier == null) {
			newMode = null;
		} else {
			final String newModeName = namingService.buildUniqueIdentifier(classifier, "new_mode");
			
			// Make the modification
			newMode = modificationService.modify(classifier, new AbstractModifier<ComponentClassifier, Mode>() {
				private DiagramModificationService.Modification diagramMod;
				
				@Override
				public Mode modify(final Resource resource, final ComponentClassifier classifier) {
					// Handle diagram updates
		 			diagramMod = diagramModService.startModification();
		 			diagramMod.markRelatedDiagramsAsDirty(classifier);
		 			
					final Mode newMode = classifier.createOwnedMode();
					newMode.setInitial(false);
					newMode.setName(newModeName);
					
					// Clear the no modes flag
					classifier.setNoModes(false);
					
					return newMode;
				}
				
				@Override
				public void beforeCommit(final Resource resource, final ComponentClassifier classifier, final Mode newMode) {
					diagramMod.commit();
					shapeCreationService.createShape(context.getTargetContainer(), newMode, context.getX(), context.getY());							
				}
			});
		}
		
		// Return the new mode if it was created
		return newMode == null ? EMPTY : new Object[] {newMode};
	}
	
	@Override
	public boolean canDelete(final IDeleteContext context) {
		final Mode mode = (Mode)bor.getBusinessObjectForPictogramElement(((Shape)context.getPictogramElement()));
		return mode.getContainingClassifier() == getComponentClassifier((Shape)context.getPictogramElement());
	}

	@Override
	public void delete(final IDeleteContext context) {
		if(!userInputService.confirmDelete(context)) {
			return;
		}
		
		final Mode mode = (Mode)bor.getBusinessObjectForPictogramElement(context.getPictogramElement());
		modificationService.modify(mode, new AbstractModifier<Mode, Object>() {
			private DiagramModificationService.Modification diagramMod;
			
			@Override
			public Object modify(final Resource resource, final Mode mode) {
				// Handle diagram updates
	 			diagramMod = diagramModService.startModification();
	 			final Classifier classifier = mode.getContainingClassifier();
	 			diagramMod.markRelatedDiagramsAsDirty(classifier);	 			
	 			
				// Just remove the mode. In the future it would be helpful to offer options for refactoring the model so that it does not
				// cause errors.
				EcoreUtil.remove(mode);
				
				return null;
			}		
			
	 		@Override
			public void beforeCommit(final Resource resource, final Mode mode, final Object modificationResult) {
				diagramMod.commit();
			}
		});
				
		// Clear selection
		getFeatureProvider().getDiagramTypeProvider().getDiagramBehavior().getDiagramContainer().selectPictogramElements(new PictogramElement[0]);
	}
	
	// Renaming
	@Override
	public int getEditingType() {
        return TYPE_TEXT;
    }
	
	@Override
	public boolean stretchFieldToFitText() {
		return true;
	}
	
    @Override
    public String checkValueValid(final String value, final IDirectEditingContext context) {
    	return namingService.checkNameValidity((NamedElement)bor.getBusinessObjectForPictogramElement(context.getPictogramElement()), value);
    }
 
    @Override
    public boolean canDirectEdit(final IDirectEditingContext context) {
        final GraphicsAlgorithm ga = context.getGraphicsAlgorithm();
        final Mode mode = (Mode)bor.getBusinessObjectForPictogramElement(((Shape)context.getPictogramElement()));
		return ga instanceof Text && mode.getContainingClassifier() == getComponentClassifier((Shape)context.getPictogramElement());
    }
    
    public String getInitialValue(final IDirectEditingContext context) {
    	final Mode mode = (Mode)bor.getBusinessObjectForPictogramElement(context.getPictogramElement());
    	return mode.getName();
    }
    
    public void setValue(final String value, final IDirectEditingContext context) {
    	final PictogramElement pe = context.getPictogramElement();
    	final Mode mode = (Mode)bor.getBusinessObjectForPictogramElement(pe);
    	refactoringService.renameElement(mode, value);
    }
}
