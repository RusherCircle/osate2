/**
 * AADL-BA-FrontEnd
 * 
 * Copyright © 2013 TELECOM ParisTech and CNRS
 * 
 * TELECOM ParisTech/LTCI
 * 
 * Authors: see AUTHORS
 * 
 * This program is free software: you can redistribute it and/or modify 
 * it under the terms of the Eclipse Public License as published by Eclipse,
 * either version 1.0 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Eclipse Public License for more details.
 * You should have received a copy of the Eclipse Public License
 * along with this program.  If not, see 
 * http://www.eclipse.org/org/documents/epl-v10.php
 */

package org.osate.ba.declarative.impl ;

import org.eclipse.emf.ecore.EAttribute ;
import org.eclipse.emf.ecore.EClass ;
import org.eclipse.emf.ecore.EPackage ;
import org.eclipse.emf.ecore.EReference ;

import org.eclipse.emf.ecore.impl.EPackageImpl ;

import org.osate.aadl2.Aadl2Package ;
import org.osate.ba.aadlba.AadlBaPackage ;
import org.osate.ba.declarative.ArrayableIdentifier ;
import org.osate.ba.declarative.CommAction ;
import org.osate.ba.declarative.DeclarativeArrayDimension ;
import org.osate.ba.declarative.DeclarativeBehaviorElement ;
import org.osate.ba.declarative.DeclarativeBehaviorTransition ;
import org.osate.ba.declarative.DeclarativeFactory ;
import org.osate.ba.declarative.DeclarativePackage ;
import org.osate.ba.declarative.DeclarativePropertyName ;
import org.osate.ba.declarative.DeclarativePropertyReference ;
import org.osate.ba.declarative.DeclarativeTime ;
import org.osate.ba.declarative.Enumeration ;
import org.osate.ba.declarative.Identifier ;
import org.osate.ba.declarative.NamedValue ;
import org.osate.ba.declarative.QualifiedNamedElement ;
import org.osate.ba.declarative.Reference ;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DeclarativePackageImpl extends EPackageImpl implements
                                                        DeclarativePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass arrayableIdentifierEClass = null ;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass commActionEClass = null ;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass declarativeArrayDimensionEClass = null ;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass declarativeBehaviorElementEClass = null ;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass declarativeBehaviorTransitionEClass = null ;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass declarativePropertyNameEClass = null ;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass declarativePropertyReferenceEClass = null ;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass declarativeTimeEClass = null ;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass identifierEClass = null ;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass namedValueEClass = null ;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass qualifiedNamedElementEClass = null ;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass referenceEClass = null ;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.osate.ba.declarative.DeclarativePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private DeclarativePackageImpl()
  {
    super(eNS_URI, DeclarativeFactory.eINSTANCE) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false ;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link DeclarativePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static DeclarativePackage init()
  {
    if(isInited)
      return (DeclarativePackage) EPackage.Registry.INSTANCE
            .getEPackage(DeclarativePackage.eNS_URI) ;

    // Obtain or create and register package
    DeclarativePackageImpl theDeclarativePackage =
          (DeclarativePackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DeclarativePackageImpl
                                                                                                             ? EPackage.Registry.INSTANCE
                                                                                                                   .get(eNS_URI)
                                                                                                             : new DeclarativePackageImpl()) ;

    isInited = true ;

    // Initialize simple dependencies
    AadlBaPackage.eINSTANCE.eClass() ;

    // Create package meta-data objects
    theDeclarativePackage.createPackageContents() ;

    // Initialize created meta-data
    theDeclarativePackage.initializePackageContents() ;

    // Mark meta-data to indicate it can't be changed
    theDeclarativePackage.freeze() ;

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(DeclarativePackage.eNS_URI,
                                   theDeclarativePackage) ;
    return theDeclarativePackage ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getArrayableIdentifier()
  {
    return arrayableIdentifierEClass ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getArrayableIdentifier_ArrayIndexes()
  {
    return (EReference) arrayableIdentifierEClass.getEStructuralFeatures()
          .get(0) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCommAction()
  {
    return commActionEClass ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCommAction_Parameters()
  {
    return (EReference) commActionEClass.getEStructuralFeatures().get(0) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCommAction_PortFreeze()
  {
    return (EAttribute) commActionEClass.getEStructuralFeatures().get(1) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCommAction_PortDequeue()
  {
    return (EAttribute) commActionEClass.getEStructuralFeatures().get(2) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCommAction_Lock()
  {
    return (EAttribute) commActionEClass.getEStructuralFeatures().get(3) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCommAction_Unlock()
  {
    return (EAttribute) commActionEClass.getEStructuralFeatures().get(4) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCommAction_Target()
  {
    return (EReference) commActionEClass.getEStructuralFeatures().get(5) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCommAction_QualifiedName()
  {
    return (EReference) commActionEClass.getEStructuralFeatures().get(6) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCommAction_Reference()
  {
    return (EReference) commActionEClass.getEStructuralFeatures().get(7) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDeclarativeArrayDimension()
  {
    return declarativeArrayDimensionEClass ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDeclarativeArrayDimension_Dimension()
  {
    return (EReference) declarativeArrayDimensionEClass
          .getEStructuralFeatures().get(0) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDeclarativeBehaviorElement()
  {
    return declarativeBehaviorElementEClass ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDeclarativeBehaviorElement_OsateRef()
  {
    return (EReference) declarativeBehaviorElementEClass
          .getEStructuralFeatures().get(0) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDeclarativeBehaviorElement_BaRef()
  {
    return (EReference) declarativeBehaviorElementEClass
          .getEStructuralFeatures().get(1) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDeclarativeBehaviorTransition()
  {
    return declarativeBehaviorTransitionEClass ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDeclarativeBehaviorTransition_SrcStates()
  {
    return (EReference) declarativeBehaviorTransitionEClass
          .getEStructuralFeatures().get(0) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDeclarativeBehaviorTransition_DestState()
  {
    return (EReference) declarativeBehaviorTransitionEClass
          .getEStructuralFeatures().get(1) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDeclarativePropertyName()
  {
    return declarativePropertyNameEClass ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDeclarativePropertyName_Field()
  {
    return (EReference) declarativePropertyNameEClass.getEStructuralFeatures()
          .get(0) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDeclarativePropertyName_PropertyName()
  {
    return (EReference) declarativePropertyNameEClass.getEStructuralFeatures()
          .get(1) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDeclarativePropertyReference()
  {
    return declarativePropertyReferenceEClass ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDeclarativePropertyReference_QualifiedName()
  {
    return (EReference) declarativePropertyReferenceEClass
          .getEStructuralFeatures().get(0) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDeclarativePropertyReference_Reference()
  {
    return (EReference) declarativePropertyReferenceEClass
          .getEStructuralFeatures().get(1) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDeclarativePropertyReference_PropertyNames()
  {
    return (EReference) declarativePropertyReferenceEClass
          .getEStructuralFeatures().get(2) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDeclarativeTime()
  {
    return declarativeTimeEClass ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDeclarativeTime_UnitId()
  {
    return (EReference) declarativeTimeEClass.getEStructuralFeatures().get(0) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIdentifier()
  {
    return identifierEClass ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIdentifier_Id()
  {
    return (EAttribute) identifierEClass.getEStructuralFeatures().get(0) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNamedValue()
  {
    return namedValueEClass ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNamedValue_Reference()
  {
    return (EReference) namedValueEClass.getEStructuralFeatures().get(0) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNamedValue_Dequeue()
  {
    return (EAttribute) namedValueEClass.getEStructuralFeatures().get(1) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNamedValue_Fresh()
  {
    return (EAttribute) namedValueEClass.getEStructuralFeatures().get(2) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNamedValue_Count()
  {
    return (EAttribute) namedValueEClass.getEStructuralFeatures().get(3) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getQualifiedNamedElement()
  {
    return qualifiedNamedElementEClass ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getQualifiedNamedElement_BaNamespace()
  {
    return (EReference) qualifiedNamedElementEClass.getEStructuralFeatures()
          .get(0) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getQualifiedNamedElement_BaName()
  {
    return (EReference) qualifiedNamedElementEClass.getEStructuralFeatures()
          .get(1) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getReference()
  {
    return referenceEClass ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getReference_Ids()
  {
    return (EReference) referenceEClass.getEStructuralFeatures().get(0) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DeclarativeFactory getDeclarativeFactory()
  {
    return (DeclarativeFactory) getEFactoryInstance() ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false ;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if(isCreated)
      return ;
    isCreated = true ;

    // Create classes and their features
    arrayableIdentifierEClass = createEClass(ARRAYABLE_IDENTIFIER) ;
    createEReference(arrayableIdentifierEClass,
                     ARRAYABLE_IDENTIFIER__ARRAY_INDEXES) ;

    commActionEClass = createEClass(COMM_ACTION) ;
    createEReference(commActionEClass, COMM_ACTION__PARAMETERS) ;
    createEAttribute(commActionEClass, COMM_ACTION__PORT_FREEZE) ;
    createEAttribute(commActionEClass, COMM_ACTION__PORT_DEQUEUE) ;
    createEAttribute(commActionEClass, COMM_ACTION__LOCK) ;
    createEAttribute(commActionEClass, COMM_ACTION__UNLOCK) ;
    createEReference(commActionEClass, COMM_ACTION__TARGET) ;
    createEReference(commActionEClass, COMM_ACTION__QUALIFIED_NAME) ;
    createEReference(commActionEClass, COMM_ACTION__REFERENCE) ;

    declarativeArrayDimensionEClass = createEClass(DECLARATIVE_ARRAY_DIMENSION) ;
    createEReference(declarativeArrayDimensionEClass,
                     DECLARATIVE_ARRAY_DIMENSION__DIMENSION) ;

    declarativeBehaviorElementEClass =
          createEClass(DECLARATIVE_BEHAVIOR_ELEMENT) ;
    createEReference(declarativeBehaviorElementEClass,
                     DECLARATIVE_BEHAVIOR_ELEMENT__OSATE_REF) ;
    createEReference(declarativeBehaviorElementEClass,
                     DECLARATIVE_BEHAVIOR_ELEMENT__BA_REF) ;

    declarativeBehaviorTransitionEClass =
          createEClass(DECLARATIVE_BEHAVIOR_TRANSITION) ;
    createEReference(declarativeBehaviorTransitionEClass,
                     DECLARATIVE_BEHAVIOR_TRANSITION__SRC_STATES) ;
    createEReference(declarativeBehaviorTransitionEClass,
                     DECLARATIVE_BEHAVIOR_TRANSITION__DEST_STATE) ;

    declarativePropertyNameEClass = createEClass(DECLARATIVE_PROPERTY_NAME) ;
    createEReference(declarativePropertyNameEClass,
                     DECLARATIVE_PROPERTY_NAME__FIELD) ;
    createEReference(declarativePropertyNameEClass,
                     DECLARATIVE_PROPERTY_NAME__PROPERTY_NAME) ;

    declarativePropertyReferenceEClass =
          createEClass(DECLARATIVE_PROPERTY_REFERENCE) ;
    createEReference(declarativePropertyReferenceEClass,
                     DECLARATIVE_PROPERTY_REFERENCE__QUALIFIED_NAME) ;
    createEReference(declarativePropertyReferenceEClass,
                     DECLARATIVE_PROPERTY_REFERENCE__REFERENCE) ;
    createEReference(declarativePropertyReferenceEClass,
                     DECLARATIVE_PROPERTY_REFERENCE__PROPERTY_NAMES) ;

    declarativeTimeEClass = createEClass(DECLARATIVE_TIME) ;
    createEReference(declarativeTimeEClass, DECLARATIVE_TIME__UNIT_ID) ;

    identifierEClass = createEClass(IDENTIFIER) ;
    createEAttribute(identifierEClass, IDENTIFIER__ID) ;

    namedValueEClass = createEClass(NAMED_VALUE) ;
    createEReference(namedValueEClass, NAMED_VALUE__REFERENCE) ;
    createEAttribute(namedValueEClass, NAMED_VALUE__DEQUEUE) ;
    createEAttribute(namedValueEClass, NAMED_VALUE__FRESH) ;
    createEAttribute(namedValueEClass, NAMED_VALUE__COUNT) ;

    qualifiedNamedElementEClass = createEClass(QUALIFIED_NAMED_ELEMENT) ;
    createEReference(qualifiedNamedElementEClass,
                     QUALIFIED_NAMED_ELEMENT__BA_NAMESPACE) ;
    createEReference(qualifiedNamedElementEClass,
                     QUALIFIED_NAMED_ELEMENT__BA_NAME) ;

    referenceEClass = createEClass(REFERENCE) ;
    createEReference(referenceEClass, REFERENCE__IDS) ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false ;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if(isInitialized)
      return ;
    isInitialized = true ;

    // Initialize package
    setName(eNAME) ;
    setNsPrefix(eNS_PREFIX) ;
    setNsURI(eNS_URI) ;

    // Obtain other dependent packages
    AadlBaPackage theAadlBaPackage =
          (AadlBaPackage) EPackage.Registry.INSTANCE
                .getEPackage(AadlBaPackage.eNS_URI) ;
    Aadl2Package theAadl2Package =
          (Aadl2Package) EPackage.Registry.INSTANCE
                .getEPackage(Aadl2Package.eNS_URI) ;

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    arrayableIdentifierEClass.getESuperTypes().add(this.getIdentifier()) ;
    commActionEClass.getESuperTypes().add(theAadlBaPackage
                                                .getCommunicationAction()) ;
    commActionEClass.getESuperTypes().add(this.getDeclarativeBehaviorElement()) ;
    declarativeArrayDimensionEClass.getESuperTypes()
          .add(theAadl2Package.getArrayDimension()) ;
    declarativeArrayDimensionEClass.getESuperTypes()
          .add(this.getDeclarativeBehaviorElement()) ;
    declarativeBehaviorElementEClass.getESuperTypes()
          .add(theAadlBaPackage.getBehaviorElement()) ;
    declarativeBehaviorTransitionEClass.getESuperTypes()
          .add(theAadlBaPackage.getBehaviorTransition()) ;
    declarativeBehaviorTransitionEClass.getESuperTypes()
          .add(this.getDeclarativeBehaviorElement()) ;
    declarativePropertyNameEClass.getESuperTypes()
          .add(this.getDeclarativeBehaviorElement()) ;
    declarativePropertyReferenceEClass.getESuperTypes()
          .add(this.getDeclarativeBehaviorElement()) ;
    declarativePropertyReferenceEClass.getESuperTypes()
          .add(theAadlBaPackage.getIntegerValueConstant()) ;
    declarativePropertyReferenceEClass.getESuperTypes()
          .add(theAadlBaPackage.getValueConstant()) ;
    declarativeTimeEClass.getESuperTypes().add(theAadlBaPackage
                                                     .getBehaviorTime()) ;
    declarativeTimeEClass.getESuperTypes()
          .add(theAadlBaPackage.getCompletionRelativeTimeout()) ;
    declarativeTimeEClass.getESuperTypes()
          .add(this.getDeclarativeBehaviorElement()) ;
    identifierEClass.getESuperTypes().add(theAadlBaPackage.getBehaviorState()) ;
    identifierEClass.getESuperTypes().add(this.getDeclarativeBehaviorElement()) ;
    identifierEClass.getESuperTypes().add(theAadlBaPackage.getPropertyField()) ;
    namedValueEClass.getESuperTypes().add(theAadlBaPackage
                                                .getIntegerValueVariable()) ;
    namedValueEClass.getESuperTypes().add(theAadlBaPackage.getValueVariable()) ;
    namedValueEClass.getESuperTypes().add(this.getDeclarativeBehaviorElement()) ;
    qualifiedNamedElementEClass.getESuperTypes()
          .add(theAadl2Package.getDataClassifier()) ;
    qualifiedNamedElementEClass.getESuperTypes()
          .add(this.getDeclarativeBehaviorElement()) ;
    qualifiedNamedElementEClass.getESuperTypes().add(theAadlBaPackage
                                                           .getValueConstant()) ;
    qualifiedNamedElementEClass.getESuperTypes()
          .add(theAadlBaPackage.getIntegerValueConstant()) ;
    qualifiedNamedElementEClass.getESuperTypes()
          .add(theAadl2Package.getProcessorClassifier()) ;
    referenceEClass.getESuperTypes()
          .add(theAadlBaPackage.getActualPortHolder()) ;
    referenceEClass.getESuperTypes().add(this.getDeclarativeBehaviorElement()) ;
    referenceEClass.getESuperTypes().add(theAadlBaPackage
                                               .getIntegerValueVariable()) ;
    referenceEClass.getESuperTypes().add(theAadlBaPackage.getValueVariable()) ;
    referenceEClass.getESuperTypes().add(theAadlBaPackage.getElementValues()) ;
    referenceEClass.getESuperTypes().add(theAadlBaPackage.getTarget()) ;
    referenceEClass.getESuperTypes().add(theAadlBaPackage
                                               .getDispatchTriggerCondition()) ;
    referenceEClass.getESuperTypes().add(theAadlBaPackage.getDispatchTrigger()) ;

    // Initialize classes and features; add operations and parameters
    initEClass(arrayableIdentifierEClass, ArrayableIdentifier.class,
               "ArrayableIdentifier", !IS_ABSTRACT, !IS_INTERFACE,
               IS_GENERATED_INSTANCE_CLASS) ;
    initEReference(getArrayableIdentifier_ArrayIndexes(), theAadlBaPackage
                         .getIntegerValue(), null, "arrayIndexes", null, 0, -1,
                   ArrayableIdentifier.class, !IS_TRANSIENT, !IS_VOLATILE,
                   IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                   IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED) ;

    initEClass(commActionEClass, CommAction.class, "CommAction", !IS_ABSTRACT,
               !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS) ;
    initEReference(getCommAction_Parameters(), theAadlBaPackage
                         .getParameterLabel(), null, "parameters", null, 0, -1,
                   CommAction.class, !IS_TRANSIENT, !IS_VOLATILE,
                   IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                   IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED) ;
    initEAttribute(getCommAction_PortFreeze(), theAadlBaPackage.getBoolean(),
                   "portFreeze", "false", 0, 1, CommAction.class,
                   !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                   !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED) ;
    initEAttribute(getCommAction_PortDequeue(), theAadlBaPackage.getBoolean(),
                   "portDequeue", "false", 0, 1, CommAction.class,
                   !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                   !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED) ;
    initEAttribute(getCommAction_Lock(), theAadlBaPackage.getBoolean(), "lock",
                   "false", 0, 1, CommAction.class, !IS_TRANSIENT,
                   !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                   IS_UNIQUE, !IS_DERIVED, IS_ORDERED) ;
    initEAttribute(getCommAction_Unlock(), theAadlBaPackage.getBoolean(),
                   "unlock", "false", 0, 1, CommAction.class, !IS_TRANSIENT,
                   !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                   IS_UNIQUE, !IS_DERIVED, IS_ORDERED) ;
    initEReference(getCommAction_Target(), theAadlBaPackage.getTarget(), null,
                   "target", null, 0, 1, CommAction.class, !IS_TRANSIENT,
                   !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                   !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                   IS_ORDERED) ;
    initEReference(getCommAction_QualifiedName(), this
                         .getQualifiedNamedElement(), null, "qualifiedName",
                   null, 0, 1, CommAction.class, !IS_TRANSIENT, !IS_VOLATILE,
                   IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                   !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED) ;
    initEReference(getCommAction_Reference(), this.getReference(), null,
                   "reference", null, 0, 1, CommAction.class, !IS_TRANSIENT,
                   !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                   !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                   IS_ORDERED) ;

    initEClass(declarativeArrayDimensionEClass,
               DeclarativeArrayDimension.class, "DeclarativeArrayDimension",
               !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS) ;
    initEReference(getDeclarativeArrayDimension_Dimension(), theAadlBaPackage
                         .getIntegerValueConstant(), null, "dimension", null,
                   1, 1, DeclarativeArrayDimension.class, !IS_TRANSIENT,
                   !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                   !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                   IS_ORDERED) ;

    initEClass(declarativeBehaviorElementEClass,
               DeclarativeBehaviorElement.class, "DeclarativeBehaviorElement",
               IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS) ;
    initEReference(getDeclarativeBehaviorElement_OsateRef(), theAadl2Package
                         .getElement(), null, "osateRef", null, 0, 1,
                   DeclarativeBehaviorElement.class, !IS_TRANSIENT,
                   !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                   IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED,
                   IS_ORDERED) ;
    initEReference(getDeclarativeBehaviorElement_BaRef(), theAadlBaPackage
                         .getBehaviorElement(), null, "baRef", null, 0, 1,
                   DeclarativeBehaviorElement.class, !IS_TRANSIENT,
                   !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                   IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED,
                   IS_ORDERED) ;

    initEClass(declarativeBehaviorTransitionEClass,
               DeclarativeBehaviorTransition.class,
               "DeclarativeBehaviorTransition", !IS_ABSTRACT, !IS_INTERFACE,
               IS_GENERATED_INSTANCE_CLASS) ;
    initEReference(getDeclarativeBehaviorTransition_SrcStates(), this
                         .getIdentifier(), null, "srcStates", null, 1, -1,
                   DeclarativeBehaviorTransition.class, !IS_TRANSIENT,
                   !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                   !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                   IS_ORDERED) ;
    initEReference(getDeclarativeBehaviorTransition_DestState(), this
                         .getIdentifier(), null, "destState", null, 1, 1,
                   DeclarativeBehaviorTransition.class, !IS_TRANSIENT,
                   !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                   !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                   IS_ORDERED) ;

    initEClass(declarativePropertyNameEClass, DeclarativePropertyName.class,
               "DeclarativePropertyName", !IS_ABSTRACT, !IS_INTERFACE,
               IS_GENERATED_INSTANCE_CLASS) ;
    initEReference(getDeclarativePropertyName_Field(), theAadlBaPackage
                         .getPropertyField(), null, "field", null, 0, 1,
                   DeclarativePropertyName.class, !IS_TRANSIENT, !IS_VOLATILE,
                   IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                   !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED) ;
    initEReference(getDeclarativePropertyName_PropertyName(), this
                         .getIdentifier(), null, "propertyName", null, 1, 1,
                   DeclarativePropertyName.class, !IS_TRANSIENT, !IS_VOLATILE,
                   IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                   !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED) ;

    initEClass(declarativePropertyReferenceEClass,
               DeclarativePropertyReference.class,
               "DeclarativePropertyReference", !IS_ABSTRACT, !IS_INTERFACE,
               IS_GENERATED_INSTANCE_CLASS) ;
    initEReference(getDeclarativePropertyReference_QualifiedName(), this
                         .getQualifiedNamedElement(), null, "qualifiedName",
                   null, 0, 1, DeclarativePropertyReference.class,
                   !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                   !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                   IS_ORDERED) ;
    initEReference(getDeclarativePropertyReference_Reference(), this
                         .getReference(), null, "reference", null, 0, 1,
                   DeclarativePropertyReference.class, !IS_TRANSIENT,
                   !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                   !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                   IS_ORDERED) ;
    initEReference(getDeclarativePropertyReference_PropertyNames(), this
                         .getDeclarativePropertyName(), null, "propertyNames",
                   null, 1, -1, DeclarativePropertyReference.class,
                   !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                   !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                   IS_ORDERED) ;

    initEClass(declarativeTimeEClass, DeclarativeTime.class, "DeclarativeTime",
               !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS) ;
    initEReference(getDeclarativeTime_UnitId(), this.getIdentifier(), null,
                   "unitId", null, 1, 1, DeclarativeTime.class, !IS_TRANSIENT,
                   !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                   !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                   IS_ORDERED) ;

    initEClass(identifierEClass, Identifier.class, "Identifier", !IS_ABSTRACT,
               !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS) ;
    initEAttribute(getIdentifier_Id(), theAadlBaPackage.getString(), "id",
                   null, 1, 1, Identifier.class, !IS_TRANSIENT, !IS_VOLATILE,
                   IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                   !IS_DERIVED, IS_ORDERED) ;

    initEClass(namedValueEClass, NamedValue.class, "NamedValue", !IS_ABSTRACT,
               !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS) ;
    initEReference(getNamedValue_Reference(), this.getReference(), null,
                   "reference", null, 1, 1, NamedValue.class, !IS_TRANSIENT,
                   !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                   !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                   IS_ORDERED) ;
    initEAttribute(getNamedValue_Dequeue(), theAadlBaPackage.getBoolean(),
                   "dequeue", "false", 0, 1, NamedValue.class, !IS_TRANSIENT,
                   !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                   IS_UNIQUE, !IS_DERIVED, IS_ORDERED) ;
    initEAttribute(getNamedValue_Fresh(), theAadlBaPackage.getBoolean(),
                   "fresh", "false", 0, 1, NamedValue.class, !IS_TRANSIENT,
                   !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                   IS_UNIQUE, !IS_DERIVED, IS_ORDERED) ;
    initEAttribute(getNamedValue_Count(), theAadlBaPackage.getBoolean(),
                   "count", "false", 0, 1, NamedValue.class, !IS_TRANSIENT,
                   !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                   IS_UNIQUE, !IS_DERIVED, IS_ORDERED) ;

    initEClass(qualifiedNamedElementEClass, QualifiedNamedElement.class,
               "QualifiedNamedElement", !IS_ABSTRACT, !IS_INTERFACE,
               IS_GENERATED_INSTANCE_CLASS) ;
    initEReference(getQualifiedNamedElement_BaNamespace(),
                   this.getIdentifier(), null, "baNamespace", null, 0, 1,
                   QualifiedNamedElement.class, !IS_TRANSIENT, !IS_VOLATILE,
                   IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                   !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED) ;
    initEReference(getQualifiedNamedElement_BaName(), this.getIdentifier(),
                   null, "baName", null, 1, 1, QualifiedNamedElement.class,
                   !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                   !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                   IS_ORDERED) ;

    initEClass(referenceEClass, Reference.class, "Reference", !IS_ABSTRACT,
               !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS) ;
    initEReference(getReference_Ids(), this.getArrayableIdentifier(), null,
                   "ids", null, 1, -1, Reference.class, !IS_TRANSIENT,
                   !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                   !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                   IS_ORDERED) ;

    // Create resource
    createResource(eNS_URI) ;
  }

} //DeclarativePackageImpl
