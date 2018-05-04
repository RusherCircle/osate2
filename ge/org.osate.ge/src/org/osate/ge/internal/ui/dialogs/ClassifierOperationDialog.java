package org.osate.ge.internal.ui.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.osate.aadl2.ComponentCategory;
import org.osate.ge.internal.util.classifiers.ClassifierOperation;
import org.osate.ge.internal.util.classifiers.ClassifierOperationPartType;

/**
 * Dialog used for creating or selecting a classifier.
 * If a new implementation is being created, the dialog can also prompt to create a new type.
 *
 */
public class ClassifierOperationDialog {
	public static final String primaryPartIdentifier = "org.osate.ge.PrimaryPartIdentifier";
	public static final String baseValueIdentifier = "org.osate.ge.BaseValueIdentifier";
	static String NOT_SELECTED_LABEL = "<Not Selected>";

	public static interface Model {
		String getTitle();

		String getMessage(final ClassifierOperation value);

		Collection<?> getPackageOptions();

		String getPrimarySelectTitle();

		String getPrimarySelectMessage();

		Collection<?> getPrimarySelectOptions();

		Collection<?> getUnfilteredPrimarySelectOptions();

		Collection<?> getBaseSelectOptions(final ClassifierOperationPartType primaryOperation);

		Collection<?> getUnfilteredBaseSelectOptions(final ClassifierOperationPartType primaryOperation);

		String validate(ClassifierOperation value);
	}

	private static class Arguments {
		private Arguments(final Model model, final EnumSet<ClassifierOperationPartType> allowedOperations, final Object defaultPackage,
				final Object defaultSelection, final boolean showPrimaryPackageSelector,
				final ComponentCategory componentCategory) {
			this.model = Objects.requireNonNull(model, "model must not be null");
			this.allowedOperations = Objects.requireNonNull(allowedOperations, "allowedOperations must not be null");
			this.defaultPackage = defaultPackage;
			this.defaultSelection = defaultSelection;
			this.showPrimaryPackageSelector = showPrimaryPackageSelector;
			this.componentCategory = componentCategory;

			if(componentCategory == null && allowedOperations.stream().anyMatch(op -> op == ClassifierOperationPartType.NEW_COMPONENT_TYPE || op == ClassifierOperationPartType.NEW_COMPONENT_IMPLEMENTATION)) {
				throw new RuntimeException(
						"Component category must not be null if new component type or component implementation is allowed.");
			}
		}

		public final Model model;
		public final EnumSet<ClassifierOperationPartType> allowedOperations;
		public final Object defaultPackage;
		public final Object defaultSelection;
		public final boolean showPrimaryPackageSelector;
		public final ComponentCategory componentCategory; // For create component operations
	}

	public static class ArgumentBuilder {
		private Model model;
		private EnumSet<ClassifierOperationPartType> allowedOperations;
		private Object defaultPackage;
		private Object defaultSelection;
		private boolean showPrimaryPackageSelector = true;
		private ComponentCategory componentCategory;

		public ArgumentBuilder(final Model model, final EnumSet<ClassifierOperationPartType> allowedOperations) {
			this.model = Objects.requireNonNull(model, "model must not be null");
			this.allowedOperations = Objects.requireNonNull(allowedOperations, "allowedOperations must not be null");
		}

		public ArgumentBuilder defaultPackage(final Object value) {
			this.defaultPackage = value;
			return this;
		}

		public ArgumentBuilder defaultSelection(final Object value) {
			this.defaultSelection = value;
			return this;
		}

		public ArgumentBuilder showPrimaryPackageSelector(final boolean value) {
			this.showPrimaryPackageSelector = value;
			return this;
		}

		public ArgumentBuilder componentCategory(final ComponentCategory value) {
			this.componentCategory = value;
			return this;
		}

		public Arguments create() {
			return new Arguments(model, allowedOperations, defaultPackage, defaultSelection,
					showPrimaryPackageSelector, componentCategory);
		}
	}

	private static class InnerDialog extends TitleAreaDialog {
		private final Arguments args;
		private ClassifierOperationPartEditor primaryPartEditor;
		private ClassifierOperationPartEditor baseValueWidget;
		private Group baseGroup;

		protected InnerDialog(final Shell parentShell, final Arguments args) {
			super(parentShell);
			this.args = Objects.requireNonNull(args, "args must not be null");
			setShellStyle(getShellStyle() | SWT.RESIZE);
			this.setHelpAvailable(false);
		}

		@Override
		protected void configureShell(final Shell newShell) {
			super.configureShell(newShell);
			newShell.setText(args.model.getTitle());
			newShell.setMinimumSize(250, 50);
		}

		@Override
		protected Point getInitialSize() {
			final Point initialSize = super.getInitialSize();
			return new Point(initialSize.x, Math.max(initialSize.y, 500));
		}

		@Override
		public void create() {
			super.create();
			setTitle(args.model.getTitle());
			updateMessage();
		}

		@Override
		protected Control createDialogArea(final Composite parent) {
			final Composite area = (Composite) super.createDialogArea(parent);

			// Scrollable
			final ScrolledComposite scrolled = new ScrolledComposite(area, SWT.H_SCROLL | SWT.V_SCROLL);
			scrolled.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
			scrolled.setExpandVertical(true);
			scrolled.setExpandHorizontal(true);

			final Composite container = new Composite(scrolled, SWT.NONE);
			container.setLayout(GridLayoutFactory.swtDefaults().numColumns(2).create());

			// Editor for the primary operation part
			primaryPartEditor = new ClassifierOperationPartEditor(container, args.allowedOperations,
					args.showPrimaryPackageSelector, args.componentCategory,
					new ClassifierOperationPartEditor.Model() {
				@Override
				public Collection<?> getPackageOptions() {
					return args.model.getPackageOptions();
				}

				@Override
				public String getSelectTitle() {
					return args.model.getPrimarySelectTitle();
				}

				@Override
				public String getSelectMessage() {
					return args.model.getPrimarySelectMessage();
				}

				@Override
				public Collection<?> getSelectOptions() {
					return args.model.getPrimarySelectOptions();
				}

				@Override
				public Collection<?> getUnfilteredSelectOptions() {
					return args.model.getUnfilteredPrimarySelectOptions();
				}
			});
			primaryPartEditor.setData(primaryPartIdentifier);
			primaryPartEditor.setSelectedElement(args.defaultSelection);
			primaryPartEditor.setSelectedPackage(args.defaultPackage);
			primaryPartEditor.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());

			baseGroup = new Group(container, SWT.NONE);
			baseGroup.setText("Base");
			baseGroup.setLayout(GridLayoutFactory.swtDefaults().create());
			baseGroup.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());

			baseValueWidget = new ClassifierOperationPartEditor(baseGroup,
					EnumSet.allOf(ClassifierOperationPartType.class), true, args.componentCategory,
					new ClassifierOperationPartEditor.Model() {
				@Override
				public Collection<?> getPackageOptions() {
					return args.model.getPackageOptions();
				}

				@Override
				public String getSelectTitle() {
					return "Select Base Classifier";
				}

				@Override
				public String getSelectMessage() {
					return "Select a base classifier.";
				}

				@Override
				public Collection<?> getSelectOptions() {
					return args.model
							.getBaseSelectOptions(primaryPartEditor.getConfiguredOperation().getType());
				}

				@Override
				public Collection<?> getUnfilteredSelectOptions() {
					return args.model.getUnfilteredBaseSelectOptions(
							primaryPartEditor.getConfiguredOperation().getType());
				}
			});
			baseValueWidget.setData(baseValueIdentifier);
			baseValueWidget.setSelectedElement(args.defaultSelection);
			baseValueWidget.setSelectedPackage(args.defaultPackage);
			baseValueWidget.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

			// Update the base whenever the primary widget is updated
			primaryPartEditor.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					updateBase();
					validate();
				}
			});

			baseValueWidget.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					validate();
				}
			});

			updateBase();

			// The set scrolled composite' content
			scrolled.setContent(container);
			scrolled.setMinSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));

			// Update the min size of the scrolled composite whenever the the size of the widgets change.
			final ControlListener resizeListener = new ControlAdapter() {
				@Override
				public void controlResized(ControlEvent e) {
					scrolled.setMinSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				}
			};

			primaryPartEditor.addControlListener(resizeListener);
			baseGroup.addControlListener(resizeListener);

			return area;
		}

		@Override
		protected void createButtonsForButtonBar(Composite parent) {
			super.createButtonsForButtonBar(parent);

			// Disable the OK button. Afterwards it will be updated based on validation results
			getButton(IDialogConstants.OK_ID).setEnabled(false);
		}

		private void updateBase() {
			final ClassifierOperationPartType primaryOp = primaryPartEditor.getConfiguredOperation().getType();
			baseGroup.setVisible(ClassifierOperationPartType.isCreate(primaryOp));

			if (baseGroup.getVisible()) {
				switch (primaryOp) {
				case NEW_COMPONENT_TYPE:
					baseValueWidget
					.setAllowedOperations(EnumSet.of(ClassifierOperationPartType.NONE, ClassifierOperationPartType.EXISTING));
					break;

				case NEW_COMPONENT_IMPLEMENTATION:
					baseValueWidget.setAllowedOperations(
							EnumSet.of(ClassifierOperationPartType.NEW_COMPONENT_TYPE, ClassifierOperationPartType.EXISTING));
					break;

				case NEW_FEATURE_GROUP_TYPE:
					baseValueWidget
					.setAllowedOperations(EnumSet.of(ClassifierOperationPartType.NONE, ClassifierOperationPartType.EXISTING));
					break;

				default:

				}
			}
		}

		private void validate() {
			final String errorMsg = args.model.validate(createResult());
			setErrorMessage(errorMsg);
			getButton(IDialogConstants.OK_ID).setEnabled(errorMsg == null);

			updateMessage();
		}

		private void updateMessage() {
			setMessage(args.model.getMessage(createResult()), IMessageProvider.INFORMATION);
		}

		private ClassifierOperation createResult() {
			return new ClassifierOperation(primaryPartEditor.getConfiguredOperation(),
					baseValueWidget.getConfiguredOperation());
		}

	}

	private final InnerDialog dlg;

	private ClassifierOperationDialog(final Shell parentShell, final Arguments args) {
		this.dlg = new InnerDialog(parentShell, args);
	}

	/**
	 * Returns if the user did not select OK.
	 * @return
	 */
	private ClassifierOperation open() {
		if (dlg.open() == Window.OK) {
			return dlg.createResult();
		} else {
			return null;
		}
	}

	public static ClassifierOperation show(final Shell parentShell, final Arguments args) {
		final ClassifierOperationDialog dlg = new ClassifierOperationDialog(parentShell, args);

		return dlg.open();
	}

	public static void main(final String[] args) {
		final Model testModel = new Model() {
			@Override
			public String getTitle() {
				return "Select Element";
			}

			@Override
			public String getMessage(final ClassifierOperation value) {
				return "Select an element.";
			}

			@Override
			public Collection<?> getPackageOptions() {
				final List<Object> result = new ArrayList<>();
				result.add("A");
				result.add("B");
				return result;
			}

			@Override
			public String getPrimarySelectTitle() {
				return "Select Element";
			}

			@Override
			public String getPrimarySelectMessage() {
				return "Select an element.";
			}

			@Override
			public List<Object> getPrimarySelectOptions() {
				final List<Object> result = new ArrayList<>();
				result.add("C");
				result.add("D");
				return result;
			}

			@Override
			public List<Object> getUnfilteredPrimarySelectOptions() {
				final List<Object> result = getPrimarySelectOptions();
				result.add("E");
				result.add("F");
				return result;
			}

			@Override
			public List<Object> getBaseSelectOptions(final ClassifierOperationPartType primaryOperation) {
				final List<Object> result = new ArrayList<>();
				result.add("G");
				result.add("H");
				return result;
			}

			@Override
			public Collection<?> getUnfilteredBaseSelectOptions(final ClassifierOperationPartType primaryOperation) {
				final List<Object> result = getBaseSelectOptions(primaryOperation);
				result.add("I");
				result.add("J");
				return result;
			}

			@Override
			public String validate(final ClassifierOperation value) {
				return (value.getPrimaryPart().getType() == ClassifierOperationPartType.NEW_COMPONENT_IMPLEMENTATION
						&& value.getPrimaryPart().getIdentifier().isEmpty())
						? "Primary identifier must not be empty."
								: null;
			}
		};

		Display.getDefault().syncExec(() -> {
			final ClassifierOperation result = show(new Shell(),
					new ArgumentBuilder(testModel,
							EnumSet.complementOf(EnumSet.of(ClassifierOperationPartType.NONE)))
					.componentCategory(ComponentCategory.ABSTRACT).create());
			System.out.println("Result: " + result);
		});

	}
}
