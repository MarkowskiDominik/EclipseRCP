package markowski.library.view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;

public class BookDetailsView extends ViewPart {
	private Table table;

	public BookDetailsView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(3, false));
		
		Label idLabel = new Label(parent, SWT.NONE);
		GridData gd_idLabel = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_idLabel.widthHint = 100;
		idLabel.setLayoutData(gd_idLabel);
		idLabel.setText("bind id");
		
		Label titleLabel = new Label(parent, SWT.NONE);
		GridData gd_titleLabel = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
		gd_titleLabel.widthHint = 100;
		titleLabel.setLayoutData(gd_titleLabel);
		titleLabel.setText("bind title");
		
		Label authorLabel = new Label(parent, SWT.NONE);
		authorLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		authorLabel.setText("bind author");
		
		TableViewer tableViewerLend = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewerLend.getTable();
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, false, true, 3, 1);
		gd_table.widthHint = 300;
		table.setLayoutData(gd_table);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewerLend, SWT.NONE);
		TableColumn tableLend = tableViewerColumn.getColumn();
		tableLend.setWidth(100);
		tableLend.setText("Lend");
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
