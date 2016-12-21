package org.osate.ge.internal.graphiti.features;

import java.util.Objects;

import javax.inject.Inject;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.osate.ge.internal.DockArea;
import org.osate.ge.internal.graphiti.graphics.AgeGraphitiGraphicsUtil;
import org.osate.ge.internal.services.BusinessObjectResolutionService;
import org.osate.ge.internal.services.LayoutService;
import org.osate.ge.internal.services.PropertyService;

public class AgeMoveShapeFeature extends DefaultMoveShapeFeature {
	private final BusinessObjectResolutionService bor;
	private final PropertyService propertyService;
	private final LayoutService layoutService;
	
	@Inject
	public AgeMoveShapeFeature(final BusinessObjectResolutionService bor, final PropertyService propertyService, final LayoutService layoutService, final IFeatureProvider fp) {
		super(fp);
		this.bor = Objects.requireNonNull(bor, "bor must not be null");
		this.propertyService = Objects.requireNonNull(propertyService, "propertyService must not be null");		
		this.layoutService = Objects.requireNonNull(layoutService, "layoutService must not be null");
	}
	
	@Override
	protected boolean avoidNegativeCoordinates() {
		return true;
	}
	
	@Override
	public boolean canMoveShape(final IMoveShapeContext ctx) {
		final Shape shape = ctx.getShape();
		
		// Don't allow moving of transient shapes
		if(propertyService.isTransient(shape)) {
			return false;
		}
		
		final String dockArea = propertyService.getDockArea(ctx.getShape());
		final Shape container = shape.getContainer();
		if(container instanceof Diagram) {
			if(bor.getBusinessObjectForPictogramElement(shape) == bor.getBusinessObjectForPictogramElement(shape.getContainer())) {
				return false;
			}
		} else {
			if(dockArea == null) {
				final GraphicsAlgorithm containerInnerGa = AgeGraphitiGraphicsUtil.getInnerGraphicsAlgorithm(container.getGraphicsAlgorithm());
				if(ctx.getX() < containerInnerGa.getX() || 
						ctx.getY() < containerInnerGa.getY() ||
						ctx.getX() >= containerInnerGa.getX() + containerInnerGa.getWidth() ||
						ctx.getY() >= containerInnerGa.getY() + containerInnerGa.getHeight()) {
					return false;
				}
			}
		}
		
		return super.canMoveShape(ctx);
	}
	
	@Override 
	protected void postMoveShape(final IMoveShapeContext context) {	
		super.postMoveShape(context);
		
		final ContainerShape shape = (ContainerShape)context.getShape();
		final String currentDockArea = propertyService.getDockArea(shape);
		if(currentDockArea != null && shape.getContainer() != null) {
			if(!DockArea.GROUP.id.equals(currentDockArea)) {
				final String newDockArea = getDockArea(shape).id;
				propertyService.setDockArea(shape, newDockArea);
	        
				// Relayout if the dock area has changed
				if(!currentDockArea.equals(newDockArea)) {
					// Layout the shapes and its descendants. Depth first.
					layoutDepthFirst(shape);
				}
			}
			
	        // Relayout ancestors
			if(!layoutService.checkShapeBoundsWithAncestors(shape)) {
				// If the ancestor was not relayed out, relayout ancestors until one is found which is not docked. 
				// This is needed to allow docked shapes which are automatically sized to shrink.
				ContainerShape tmpShape = shape.getContainer();
				do {
					layoutPictogramElement(tmpShape);
					tmpShape = tmpShape.getContainer();					
				} while(tmpShape != null && propertyService.getDockArea(tmpShape) != null);
			}
		} else {
			layoutService.checkShapeBoundsWithAncestors(shape);
		}
				
        // TODO: Update connection anchors when they are supported in business object handlers
		//connectionService.updateConnectionAnchors(shape);
	}
	
	private void layoutDepthFirst(final Shape shape) {
		if(shape instanceof ContainerShape) {
			for(final Shape child : ((ContainerShape)shape).getChildren()) {
				layoutDepthFirst(child);				
			}
		}
		
		layoutPictogramElement(shape);
	}
	
	private DockArea getDockArea(final ContainerShape shape) {
		final GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
		final ContainerShape container = shape.getContainer();
		final GraphicsAlgorithm containerInnerGa = AgeGraphitiGraphicsUtil.getInnerGraphicsAlgorithm(container.getGraphicsAlgorithm());
		
		final int distanceToLeft = Math.max(0, ga.getX() - containerInnerGa.getX());
		final int distanceToRight = containerInnerGa.getWidth() - Math.min(ga.getX() + ga.getWidth() - containerInnerGa.getX(), containerInnerGa.getWidth());
		final int distanceToTop = Math.max(0, ga.getY() - containerInnerGa.getY());
		final int distanceToBottom = containerInnerGa.getHeight() - Math.min(ga.getY() + ga.getHeight() - containerInnerGa.getY(), containerInnerGa.getHeight());

		// Find the closest dock area while giving priority to the left, right, top, and bottom.
		if(distanceToLeft <= distanceToRight && distanceToLeft <= distanceToTop && distanceToLeft <= distanceToBottom) {
			return DockArea.LEFT;
		} else if(distanceToRight <= distanceToTop && distanceToRight <= distanceToBottom) {
			return DockArea.RIGHT;
		} else if(distanceToTop <= distanceToBottom) {
			return DockArea.TOP;
		} else {
			return DockArea.BOTTOM;
		}
	}
}
