package markowski.library.view;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import markowski.library.data.model.Book;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;

public class BookDetailsView extends ViewPart {

	private Table table;
	private ISelectionListener selectionListerner;
	private Text bookDetails;

	public BookDetailsView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		bookDetails = new Text(parent, SWT.BORDER);
		bookDetails.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		bookDetails.setEditable(false);
		
		TableViewer tableViewerLend = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewerLend.getTable();
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_table.widthHint = 350;
		table.setLayoutData(gd_table);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewerLend, SWT.NONE);
		TableColumn tableLend = tableViewerColumn.getColumn();
		tableLend.setWidth(350);
		tableLend.setText("Lend");
		
		selectionListerner = new ISelectionListener() {
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				if (!(selection instanceof IStructuredSelection)) {
					return;
				}
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				Object object = structuredSelection.getFirstElement();
				if (object instanceof Book) {
					Book book = (Book) object;
					String details = "\"" + book.getTitle() + "\", " + book.getAuthors();
					bookDetails.setText(details);
					tableLend.setData(book.getLendHistory());
					
					System.out.println(book.getLendHistory().size());
					System.out.println(book.getLendHistory().get(0));
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
