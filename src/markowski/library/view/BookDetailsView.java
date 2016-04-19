package markowski.library.view;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import markowski.library.data.model.Book;

public class BookDetailsView extends ViewPart {

	private Text bookDetails;
	private ISelectionListener selectionListerner;
	private TableViewer lendTableViewer;

	public BookDetailsView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		bookDetails = new Text(parent, SWT.BORDER);
		bookDetails.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		bookDetails.setEditable(false);
		
		lendTableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		lendTableViewer.setContentProvider(ArrayContentProvider.getInstance());
		Table lendTable = lendTableViewer.getTable();
		GridData gd_lendTable = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_lendTable.widthHint = 350;
		lendTable.setLayoutData(gd_lendTable);
		
		selectionListerner = new ISelectionListener() {
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				if (!(selection instanceof IStructuredSelection)) {
					return;
				}
				Object object = ((IStructuredSelection) selection).getFirstElement();
				if (object instanceof Book) {
					Book book = (Book) object;
					String details = "\"" + book.getTitle() + "\", " + book.getAuthors();
					bookDetails.setText(details);
					lendTableViewer.setInput(book.getLendHistory());
				}
			}

		};
		getSite().getPage().addSelectionListener(selectionListerner);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}