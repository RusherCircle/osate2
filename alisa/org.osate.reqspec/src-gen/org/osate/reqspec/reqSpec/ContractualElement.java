/**
 */
package org.osate.reqspec.reqSpec;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.osate.aadl2.NamedElement;

import org.osate.categories.categories.RequirementCategory;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contractual Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.osate.reqspec.reqSpec.ContractualElement#getName <em>Name</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.ContractualElement#getTitle <em>Title</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.ContractualElement#getTarget <em>Target</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.ContractualElement#getTargetDescription <em>Target Description</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.ContractualElement#getCategory <em>Category</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.ContractualElement#getRationale <em>Rationale</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.ContractualElement#getDocumentRequirement <em>Document Requirement</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.ContractualElement#getDocReference <em>Doc Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.osate.reqspec.reqSpec.ReqSpecPackage#getContractualElement()
 * @model
 * @generated
 */
public interface ContractualElement extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.osate.reqspec.reqSpec.ReqSpecPackage#getContractualElement_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.osate.reqspec.reqSpec.ContractualElement#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Title</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Title</em>' attribute.
   * @see #setTitle(String)
   * @see org.osate.reqspec.reqSpec.ReqSpecPackage#getContractualElement_Title()
   * @model
   * @generated
   */
  String getTitle();

  /**
   * Sets the value of the '{@link org.osate.reqspec.reqSpec.ContractualElement#getTitle <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Title</em>' attribute.
   * @see #getTitle()
   * @generated
   */
  void setTitle(String value);

  /**
   * Returns the value of the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' reference.
   * @see #setTarget(NamedElement)
   * @see org.osate.reqspec.reqSpec.ReqSpecPackage#getContractualElement_Target()
   * @model
   * @generated
   */
  NamedElement getTarget();

  /**
   * Sets the value of the '{@link org.osate.reqspec.reqSpec.ContractualElement#getTarget <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' reference.
   * @see #getTarget()
   * @generated
   */
  void setTarget(NamedElement value);

  /**
   * Returns the value of the '<em><b>Target Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Description</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Description</em>' attribute.
   * @see #setTargetDescription(String)
   * @see org.osate.reqspec.reqSpec.ReqSpecPackage#getContractualElement_TargetDescription()
   * @model
   * @generated
   */
  String getTargetDescription();

  /**
   * Sets the value of the '{@link org.osate.reqspec.reqSpec.ContractualElement#getTargetDescription <em>Target Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Description</em>' attribute.
   * @see #getTargetDescription()
   * @generated
   */
  void setTargetDescription(String value);

  /**
   * Returns the value of the '<em><b>Category</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Category</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Category</em>' reference.
   * @see #setCategory(RequirementCategory)
   * @see org.osate.reqspec.reqSpec.ReqSpecPackage#getContractualElement_Category()
   * @model
   * @generated
   */
  RequirementCategory getCategory();

  /**
   * Sets the value of the '{@link org.osate.reqspec.reqSpec.ContractualElement#getCategory <em>Category</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Category</em>' reference.
   * @see #getCategory()
   * @generated
   */
  void setCategory(RequirementCategory value);

  /**
   * Returns the value of the '<em><b>Rationale</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rationale</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rationale</em>' attribute.
   * @see #setRationale(String)
   * @see org.osate.reqspec.reqSpec.ReqSpecPackage#getContractualElement_Rationale()
   * @model
   * @generated
   */
  String getRationale();

  /**
   * Sets the value of the '{@link org.osate.reqspec.reqSpec.ContractualElement#getRationale <em>Rationale</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rationale</em>' attribute.
   * @see #getRationale()
   * @generated
   */
  void setRationale(String value);

  /**
   * Returns the value of the '<em><b>Document Requirement</b></em>' reference list.
   * The list contents are of type {@link org.osate.reqspec.reqSpec.ContractualElement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Document Requirement</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Document Requirement</em>' reference list.
   * @see org.osate.reqspec.reqSpec.ReqSpecPackage#getContractualElement_DocumentRequirement()
   * @model
   * @generated
   */
  EList<ContractualElement> getDocumentRequirement();

  /**
   * Returns the value of the '<em><b>Doc Reference</b></em>' containment reference list.
   * The list contents are of type {@link org.osate.reqspec.reqSpec.ExternalDocument}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Doc Reference</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Doc Reference</em>' containment reference list.
   * @see org.osate.reqspec.reqSpec.ReqSpecPackage#getContractualElement_DocReference()
   * @model containment="true"
   * @generated
   */
  EList<ExternalDocument> getDocReference();

} // ContractualElement