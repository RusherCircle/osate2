/*******************************************************************************
 * Copyright (C) 2013 University of Alabama in Huntsville (UAH)
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * The US Government has unlimited rights in this work in accordance with W31P4Q-10-D-0092 DO 0073.
 *******************************************************************************/
package edu.uah.rsesc.aadl.age.services.impl;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.Feature;
import org.osate.aadl2.FeatureGroup;
import org.osate.aadl2.FeatureGroupType;
import org.osate.aadl2.util.Aadl2Util;

import edu.uah.rsesc.aadl.age.services.AadlFeatureService;
import edu.uah.rsesc.aadl.age.services.BusinessObjectResolutionService;
import edu.uah.rsesc.aadl.age.services.PrototypeService;

public class DefaultAadlFeatureService implements AadlFeatureService {
	private final PrototypeService prototypeService;
	private final BusinessObjectResolutionService bor;
	
	public DefaultAadlFeatureService(final PrototypeService prototypeService, final BusinessObjectResolutionService bor) {
		this.prototypeService = prototypeService;
		this.bor = bor;
	}

	/**
	 * Returns all the features owned by the feature group type or the type it extends. It does not return features from the inverse and in the case of refined features, 
	 * only returns the refined feature.
	 * @param fgt
	 * @return
	 */
	private EList<Feature> getAllOwnedFeatures(final FeatureGroupType fgt) {
		final EList<Feature> features = new BasicEList<Feature>();
		FeatureGroupType temp = fgt;
		while(temp != null) {
			boolean wasRefined = false;
			for(final Feature newFeature : temp.getOwnedFeatures()) {
				for(final Feature existingFeature : features) {
					if(existingFeature.getRefined() == newFeature) {
						wasRefined = true;
					}
				}
				
				if(!wasRefined) {
					features.add(newFeature);
				}
			}
			temp = temp.getExtended();
		}

		return features;
	}
	
	/**
	 * Returns all the features owned by the feature group type or the type it extends. It does not return features from the inverse and in the case of refined features, 
	 * only returns the refined feature.
	 * @param fgt
	 * @return
	 */
	/*
	private EList<Feature> getAllOwnedFeatures(final ComponentType ct) {
		final EList<Feature> features = new BasicEList<Feature>();
		ComponentType temp = ct;
		while(temp != null) {
			boolean wasRefined = false;
			for(final Feature newFeature : temp.getOwnedFeatures()) {
				for(final Feature existingFeature : features) {
					if(existingFeature.getRefined() == newFeature) {
						wasRefined = true;
					}
				}
				
				if(!wasRefined) {
					features.add(newFeature);
				}
			}
			temp = temp.getExtended();
		}

		return features;
	}
	*/
	
	/* (non-Javadoc)
	 * @see edu.uah.rsesc.aadl.age.diagrams.common.util.ClassifierService#getAllOwnedFeatures(org.osate.aadl2.Classifier)
	 */
	@Override
	public EList<Feature> getAllOwnedFeatures(final Classifier classifier) {
		if(classifier instanceof FeatureGroupType) {
			return getAllOwnedFeatures((FeatureGroupType)classifier);
		} /*
		else if(classifier instanceof ComponentType) { 
			return getAllOwnedFeatures((ComponentType)classifier);
		} else if(classifier instanceof ComponentImplementation) {
			return getAllOwnedFeatures(((ComponentImplementation) classifier).getType());
		} else {
			throw new RuntimeException("Unhandled case for classifier:" + classifier);
		}*/
		return classifier.getAllFeatures();
	}
	
	/* (non-Javadoc)
	 * @see edu.uah.rsesc.aadl.age.diagrams.common.util.ClassifierService#getAllFeatures(org.osate.aadl2.FeatureGroupType)
	 */
	@Override
	public EList<Feature> getAllFeatures(final FeatureGroupType fgt) {
		final EList<Feature> owned = getAllOwnedFeatures(fgt);
		final FeatureGroupType inverseFgt = fgt.getInverse();
		if (owned.isEmpty() && !Aadl2Util.isNull(inverseFgt)) {
			return getAllOwnedFeatures(inverseFgt);
		}
		
		return owned;
	}
	
	/* (non-Javadoc)
	 * @see edu.uah.rsesc.aadl.age.diagrams.common.util.ClassifierService#isFeatureInverted(org.eclipse.graphiti.mm.pictograms.Shape)
	 */
	@Override
	public boolean isFeatureInverted(final Shape featureShape) {
		boolean isInverted = false;
		
		Shape container = featureShape.getContainer();
		while(!(container instanceof Diagram)) {
			final Object containerBo = bor.getBusinessObjectForPictogramElement(container);
			if(containerBo instanceof FeatureGroup) {
				final FeatureGroup fg = (FeatureGroup)containerBo;
				isInverted ^= fg.isInverse();
				
				// This feature group type is not necessarily the one that owned the feature... Could be inverse.. Could be refined, etc..
				// Check if the feature group type was implicitly defined via an inverse
				final FeatureGroupType fgt = getFeatureGroupType(container, fg);
				if(getAllOwnedFeatures(fgt).isEmpty() && !Aadl2Util.isNull(fgt.getInverse())) {
					isInverted = !isInverted;
				}
			}

			container = container.getContainer();
		}
		
		return isInverted;
	}
	
	// Prototype Related Methods
	// CLEAN-UP: Consider moving to another class
	/* (non-Javadoc)
	 * @see edu.uah.rsesc.aadl.age.diagrams.common.util.ClassifierService#getFeatureGroupType(org.eclipse.graphiti.mm.pictograms.Shape, org.osate.aadl2.FeatureGroup)
	 */
	@Override
	public FeatureGroupType getFeatureGroupType(final Shape shape, final FeatureGroup fg) {
		if(fg.getFeatureGroupPrototype() == null) {
			return fg.getAllFeatureGroupType();
		} else {
			return prototypeService.getFeatureGroupType(prototypeService.getPrototypeBindingContext(shape), fg);		
		}
	}
}
