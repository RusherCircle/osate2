/**
 * Copyright 2015 Carnegie Mellon University. All Rights Reserved.
 * 
 * NO WARRANTY. THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING INSTITUTE
 * MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER INCLUDING,
 * BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR MERCHANTABILITY,
 * EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL. CARNEGIE MELLON
 * UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH RESPECT TO FREEDOM FROM
 * PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 * 
 * Released under the Eclipse Public License (http://www.eclipse.org/org/documents/epl-v10.php)
 * 
 * See COPYRIGHT file for full details.
 */
package org.osate.reqspec.reqSpec.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.osate.aadl2.ComponentClassifier;

import org.osate.alisa.common.common.AVariableDeclaration;
import org.osate.alisa.common.common.Description;

import org.osate.reqspec.reqSpec.ExternalDocument;
import org.osate.reqspec.reqSpec.GlobalConstants;
import org.osate.reqspec.reqSpec.ReqRoot;
import org.osate.reqspec.reqSpec.ReqSpecPackage;
import org.osate.reqspec.reqSpec.Requirement;
import org.osate.reqspec.reqSpec.SystemRequirements;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Requirements</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.osate.reqspec.reqSpec.impl.SystemRequirementsImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.impl.SystemRequirementsImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.impl.SystemRequirementsImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.impl.SystemRequirementsImpl#getImportConstants <em>Import Constants</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.impl.SystemRequirementsImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.impl.SystemRequirementsImpl#getConstants <em>Constants</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.impl.SystemRequirementsImpl#getComputes <em>Computes</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.impl.SystemRequirementsImpl#getContent <em>Content</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.impl.SystemRequirementsImpl#getDocReference <em>Doc Reference</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.impl.SystemRequirementsImpl#getStakeholderGoals <em>Stakeholder Goals</em>}</li>
 *   <li>{@link org.osate.reqspec.reqSpec.impl.SystemRequirementsImpl#getIssues <em>Issues</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SystemRequirementsImpl extends ReqSpecContainerImpl implements SystemRequirements
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTitle()
   * @generated
   * @ordered
   */
  protected static final String TITLE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTitle()
   * @generated
   * @ordered
   */
  protected String title = TITLE_EDEFAULT;

  /**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected ComponentClassifier target;

  /**
   * The cached value of the '{@link #getImportConstants() <em>Import Constants</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImportConstants()
   * @generated
   * @ordered
   */
  protected EList<GlobalConstants> importConstants;

  /**
   * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
  protected Description description;

  /**
   * The cached value of the '{@link #getConstants() <em>Constants</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstants()
   * @generated
   * @ordered
   */
  protected EList<AVariableDeclaration> constants;

  /**
   * The cached value of the '{@link #getComputes() <em>Computes</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComputes()
   * @generated
   * @ordered
   */
  protected EList<AVariableDeclaration> computes;

  /**
   * The cached value of the '{@link #getContent() <em>Content</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContent()
   * @generated
   * @ordered
   */
  protected EList<Requirement> content;

  /**
   * The cached value of the '{@link #getDocReference() <em>Doc Reference</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDocReference()
   * @generated
   * @ordered
   */
  protected EList<ExternalDocument> docReference;

  /**
   * The cached value of the '{@link #getStakeholderGoals() <em>Stakeholder Goals</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStakeholderGoals()
   * @generated
   * @ordered
   */
  protected EList<ReqRoot> stakeholderGoals;

  /**
   * The cached value of the '{@link #getIssues() <em>Issues</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIssues()
   * @generated
   * @ordered
   */
  protected EList<String> issues;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SystemRequirementsImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ReqSpecPackage.Literals.SYSTEM_REQUIREMENTS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReqSpecPackage.SYSTEM_REQUIREMENTS__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTitle()
  {
    return title;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTitle(String newTitle)
  {
    String oldTitle = title;
    title = newTitle;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReqSpecPackage.SYSTEM_REQUIREMENTS__TITLE, oldTitle, title));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComponentClassifier getTarget()
  {
    if (target != null && ((EObject)target).eIsProxy())
    {
      InternalEObject oldTarget = (InternalEObject)target;
      target = (ComponentClassifier)eResolveProxy(oldTarget);
      if (target != oldTarget)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReqSpecPackage.SYSTEM_REQUIREMENTS__TARGET, oldTarget, target));
      }
    }
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComponentClassifier basicGetTarget()
  {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTarget(ComponentClassifier newTarget)
  {
    ComponentClassifier oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReqSpecPackage.SYSTEM_REQUIREMENTS__TARGET, oldTarget, target));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GlobalConstants> getImportConstants()
  {
    if (importConstants == null)
    {
      importConstants = new EObjectResolvingEList<GlobalConstants>(GlobalConstants.class, this, ReqSpecPackage.SYSTEM_REQUIREMENTS__IMPORT_CONSTANTS);
    }
    return importConstants;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Description getDescription()
  {
    return description;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDescription(Description newDescription, NotificationChain msgs)
  {
    Description oldDescription = description;
    description = newDescription;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReqSpecPackage.SYSTEM_REQUIREMENTS__DESCRIPTION, oldDescription, newDescription);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDescription(Description newDescription)
  {
    if (newDescription != description)
    {
      NotificationChain msgs = null;
      if (description != null)
        msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReqSpecPackage.SYSTEM_REQUIREMENTS__DESCRIPTION, null, msgs);
      if (newDescription != null)
        msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReqSpecPackage.SYSTEM_REQUIREMENTS__DESCRIPTION, null, msgs);
      msgs = basicSetDescription(newDescription, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReqSpecPackage.SYSTEM_REQUIREMENTS__DESCRIPTION, newDescription, newDescription));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AVariableDeclaration> getConstants()
  {
    if (constants == null)
    {
      constants = new EObjectContainmentEList<AVariableDeclaration>(AVariableDeclaration.class, this, ReqSpecPackage.SYSTEM_REQUIREMENTS__CONSTANTS);
    }
    return constants;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AVariableDeclaration> getComputes()
  {
    if (computes == null)
    {
      computes = new EObjectContainmentEList<AVariableDeclaration>(AVariableDeclaration.class, this, ReqSpecPackage.SYSTEM_REQUIREMENTS__COMPUTES);
    }
    return computes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Requirement> getContent()
  {
    if (content == null)
    {
      content = new EObjectContainmentEList<Requirement>(Requirement.class, this, ReqSpecPackage.SYSTEM_REQUIREMENTS__CONTENT);
    }
    return content;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ExternalDocument> getDocReference()
  {
    if (docReference == null)
    {
      docReference = new EObjectContainmentEList<ExternalDocument>(ExternalDocument.class, this, ReqSpecPackage.SYSTEM_REQUIREMENTS__DOC_REFERENCE);
    }
    return docReference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ReqRoot> getStakeholderGoals()
  {
    if (stakeholderGoals == null)
    {
      stakeholderGoals = new EObjectResolvingEList<ReqRoot>(ReqRoot.class, this, ReqSpecPackage.SYSTEM_REQUIREMENTS__STAKEHOLDER_GOALS);
    }
    return stakeholderGoals;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getIssues()
  {
    if (issues == null)
    {
      issues = new EDataTypeEList<String>(String.class, this, ReqSpecPackage.SYSTEM_REQUIREMENTS__ISSUES);
    }
    return issues;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__DESCRIPTION:
        return basicSetDescription(null, msgs);
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__CONSTANTS:
        return ((InternalEList<?>)getConstants()).basicRemove(otherEnd, msgs);
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__COMPUTES:
        return ((InternalEList<?>)getComputes()).basicRemove(otherEnd, msgs);
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__CONTENT:
        return ((InternalEList<?>)getContent()).basicRemove(otherEnd, msgs);
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__DOC_REFERENCE:
        return ((InternalEList<?>)getDocReference()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__NAME:
        return getName();
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__TITLE:
        return getTitle();
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__TARGET:
        if (resolve) return getTarget();
        return basicGetTarget();
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__IMPORT_CONSTANTS:
        return getImportConstants();
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__DESCRIPTION:
        return getDescription();
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__CONSTANTS:
        return getConstants();
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__COMPUTES:
        return getComputes();
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__CONTENT:
        return getContent();
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__DOC_REFERENCE:
        return getDocReference();
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__STAKEHOLDER_GOALS:
        return getStakeholderGoals();
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__ISSUES:
        return getIssues();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__NAME:
        setName((String)newValue);
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__TITLE:
        setTitle((String)newValue);
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__TARGET:
        setTarget((ComponentClassifier)newValue);
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__IMPORT_CONSTANTS:
        getImportConstants().clear();
        getImportConstants().addAll((Collection<? extends GlobalConstants>)newValue);
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__DESCRIPTION:
        setDescription((Description)newValue);
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__CONSTANTS:
        getConstants().clear();
        getConstants().addAll((Collection<? extends AVariableDeclaration>)newValue);
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__COMPUTES:
        getComputes().clear();
        getComputes().addAll((Collection<? extends AVariableDeclaration>)newValue);
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__CONTENT:
        getContent().clear();
        getContent().addAll((Collection<? extends Requirement>)newValue);
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__DOC_REFERENCE:
        getDocReference().clear();
        getDocReference().addAll((Collection<? extends ExternalDocument>)newValue);
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__STAKEHOLDER_GOALS:
        getStakeholderGoals().clear();
        getStakeholderGoals().addAll((Collection<? extends ReqRoot>)newValue);
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__ISSUES:
        getIssues().clear();
        getIssues().addAll((Collection<? extends String>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__TITLE:
        setTitle(TITLE_EDEFAULT);
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__TARGET:
        setTarget((ComponentClassifier)null);
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__IMPORT_CONSTANTS:
        getImportConstants().clear();
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__DESCRIPTION:
        setDescription((Description)null);
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__CONSTANTS:
        getConstants().clear();
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__COMPUTES:
        getComputes().clear();
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__CONTENT:
        getContent().clear();
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__DOC_REFERENCE:
        getDocReference().clear();
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__STAKEHOLDER_GOALS:
        getStakeholderGoals().clear();
        return;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__ISSUES:
        getIssues().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__TITLE:
        return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__TARGET:
        return target != null;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__IMPORT_CONSTANTS:
        return importConstants != null && !importConstants.isEmpty();
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__DESCRIPTION:
        return description != null;
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__CONSTANTS:
        return constants != null && !constants.isEmpty();
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__COMPUTES:
        return computes != null && !computes.isEmpty();
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__CONTENT:
        return content != null && !content.isEmpty();
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__DOC_REFERENCE:
        return docReference != null && !docReference.isEmpty();
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__STAKEHOLDER_GOALS:
        return stakeholderGoals != null && !stakeholderGoals.isEmpty();
      case ReqSpecPackage.SYSTEM_REQUIREMENTS__ISSUES:
        return issues != null && !issues.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(", title: ");
    result.append(title);
    result.append(", issues: ");
    result.append(issues);
    result.append(')');
    return result.toString();
  }

} //SystemRequirementsImpl
