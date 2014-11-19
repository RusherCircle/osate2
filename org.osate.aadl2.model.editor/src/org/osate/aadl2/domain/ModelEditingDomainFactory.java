/**
 * <copyright>
 * Copyright  2008 by Carnegie Mellon University, all rights reserved.
 *
 * Use of the Open Source AADL Tool Environment (OSATE) is subject to the terms of the license set forth
 * at http://www.eclipse.org/org/documents/epl-v10.html.
 *
 * NO WARRANTY
 *
 * ANY INFORMATION, MATERIALS, SERVICES, INTELLECTUAL PROPERTY OR OTHER PROPERTY OR RIGHTS GRANTED OR PROVIDED BY
 * CARNEGIE MELLON UNIVERSITY PURSUANT TO THIS LICENSE (HEREINAFTER THE ''DELIVERABLES'') ARE ON AN ''AS-IS'' BASIS.
 * CARNEGIE MELLON UNIVERSITY MAKES NO WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED AS TO ANY MATTER INCLUDING,
 * BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR A PARTICULAR PURPOSE, MERCHANTABILITY, INFORMATIONAL CONTENT,
 * NONINFRINGEMENT, OR ERROR-FREE OPERATION. CARNEGIE MELLON UNIVERSITY SHALL NOT BE LIABLE FOR INDIRECT, SPECIAL OR
 * CONSEQUENTIAL DAMAGES, SUCH AS LOSS OF PROFITS OR INABILITY TO USE SAID INTELLECTUAL PROPERTY, UNDER THIS LICENSE,
 * REGARDLESS OF WHETHER SUCH PARTY WAS AWARE OF THE POSSIBILITY OF SUCH DAMAGES. LICENSEE AGREES THAT IT WILL NOT
 * MAKE ANY WARRANTY ON BEHALF OF CARNEGIE MELLON UNIVERSITY, EXPRESS OR IMPLIED, TO ANY PERSON CONCERNING THE
 * APPLICATION OF OR THE RESULTS TO BE OBTAINED WITH THE DELIVERABLES UNDER THIS LICENSE.
 *
 * Licensee hereby agrees to defend, indemnify, and hold harmless Carnegie Mellon University, its trustees, officers,
 * employees, and agents from all claims or demands made against them (and any related losses, expenses, or
 * attorney's fees) arising out of, or relating to Licensee's and/or its sub licensees' negligent use or willful
 * misuse of or negligent conduct or willful misconduct regarding the Software, facilities, or other rights or
 * assistance granted by Carnegie Mellon University under this License, including, but not limited to, any claims of
 * product liability, personal injury, death, damage to property, or violation of any laws or regulations.
 *
 * Carnegie Mellon University Software Engineering Institute authored documents are sponsored by the U.S. Department
 * of Defense under Contract F19628-00-C-0003. Carnegie Mellon University retains copyrights in all material produced
 * under this contract. The U.S. Government retains a non-exclusive, royalty-free license to publish or reproduce these
 * documents, or allow others to do so, for U.S. Government purposes only pursuant to the copyright license
 * under the contract clause at 252.227.7013.
 * </copyright>
 *
 *
 * $Id: ModelEditingDomainFactory.java,v 1.1 2009-12-01 15:31:10 lwrage Exp $
 */
package org.osate.aadl2.domain;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.DefaultOptions;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.emf.workspace.util.WorkspaceValidateEditSupport;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.xtext.aadl2.properties.ui.internal.PropertiesActivator;

/**
 */
public class ModelEditingDomainFactory implements TransactionalEditingDomain.Factory {

	@Override
	public TransactionalEditingDomain createEditingDomain() {
		// create an editing domain with an Osate resource set
		// and delegating command execution to the default (workbench)
		// operation history
		PropertiesActivator.getInstance();
		ResourceSet rset = OsateResourceUtil.createResourceSet();
		if (rset == null) {
			return null;
		}
		TransactionalEditingDomain result = WorkspaceEditingDomainFactory.INSTANCE.createEditingDomain(rset);

		// add an exception handler to the editing domain's command stack
		((TransactionalCommandStack) result.getCommandStack()).setExceptionHandler(new CommandStackExceptionHandler());

		DefaultOptions defaults = TransactionUtil.getAdapter(result, DefaultOptions.class);
		if (defaults != null) {
			Map<Object, Object> options = new java.util.HashMap<Object, Object>();

			options.put(Transaction.OPTION_VALIDATE_EDIT, new WorkspaceValidateEditSupport() {
				@Override
				protected IStatus doValidateEdit(Transaction transaction, Collection<? extends Resource> resources,
						Object context) {
					if ((context == null) && (Display.getCurrent() != null)) {
						// get the active shell for the context
						// when validating on the UI thread
						IWorkbench wb = PlatformUI.getWorkbench();
						if (wb.getActiveWorkbenchWindow() != null) {
							context = wb.getActiveWorkbenchWindow().getShell();
						}
					}

					return super.doValidateEdit(transaction, resources, context);
				}
			});

			defaults.setDefaultTransactionOptions(options);
		}

		return result;
	}

	@Override
	public TransactionalEditingDomain createEditingDomain(ResourceSet rset) {
		// not used when initializing editing domain from extension point
		return null;
	}

	@Override
	public TransactionalEditingDomain getEditingDomain(ResourceSet rset) {
		// not used when initializing editing domain from extension point
		return null;
	}

}