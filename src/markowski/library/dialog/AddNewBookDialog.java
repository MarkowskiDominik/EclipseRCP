package markowski.library.dialog;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class AddNewBookDialog extends Dialog {
	private Text bookTitleText;
	private Text bookAuthorsText;
	
	private String bookTitle;
	private String bookAuthors;
	
	private Button buttonOk;

	public AddNewBookDialog(Shell parentShell) {
		super(parentShell);
	}
	
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("New book addition");
	};

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout layout = new GridLayout(2, false);
		layout.marginRight = 5;
		layout.marginLeft = 10;
		container.setLayout(layout);
		
//		IValidator validator = new IValidator() {
//			@Override
//			public IStatus validate(Object value) {
//				if (value != null && !value.toString().isEmpty()) {
//					return ValidationStatus.ok();
//				}
//				return ValidationStatus.error("Field is empty");
//			}
//		};

		Label bookTitleLabel = new Label(container, SWT.NONE);
		bookTitleLabel.setText("Title:");

		bookTitleText = new Text(container, SWT.BORDER);
		bookTitleText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label bookAuthorsLabel = new Label(container, SWT.NONE);
		bookAuthorsLabel.setText("Authors:");

		bookAuthorsText = new Text(container, SWT.BORDER);
		bookAuthorsText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		return container;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		buttonOk = createButton(parent, IDialogConstants.OK_ID, "Add book", true);
//		buttonOk.setEnabled(false);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(450, 175);
	}

	@Override
	protected void okPressed() {
		bookTitle = bookTitleText.getText();
		bookAuthors = bookAuthorsText.getText();
		super.okPressed();
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getBookAuthors() {
		return bookAuthors;
	}

}