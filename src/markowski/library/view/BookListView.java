package markowski.library.view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import markowski.library.data.model.Book;
import markowski.library.data.model.LibraryModel;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

import java.util.logging.Logger;

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;

public class BookListView extends ViewPart {

	private Logger LOG = Logger.getLogger(getClass().getName());
	private LibraryModel model = LibraryModel.INSTANCE;

	private Text filterTitleText;
	private Text filterAuthorText;
	private Table table;

	public BookListView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2, false));

		createTitleLabel(parent);

		createFilterComposites(parent);

		TableViewer tableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		createTableViewerColumns(tableViewer);
		tableViewer.setContentProvider(new ArrayContentProvider());
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		ViewerSupport.bind(tableViewer, model.getBooks(), BeanProperties.values(Book.class, new String[]{"id","title", "authors"}));
		getSite().setSelectionProvider(tableViewer);
	}

	private void createTitleLabel(Composite parent) {
		Label bookListLabel = new Label(parent, SWT.NONE);
		bookListLabel.setText("Books list");
		bookListLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		bookListLabel.setAlignment(SWT.CENTER);
	}

	private void createFilterComposites(Composite parent) {
		Label filterTitleLabel = new Label(parent, SWT.NONE);
		filterTitleLabel.setText("Title");
		filterTitleLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		filterTitleText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		filterTitleText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label filterAuthorLabel = new Label(parent, SWT.NONE);
		filterAuthorLabel.setText("Author");
		filterAuthorLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		filterAuthorText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		filterAuthorText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}

	private void createTableViewerColumns(TableViewer tableViewer) {
		TableViewerColumn tableViewerColumnId = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn columnId = tableViewerColumnId.getColumn();
		columnId.setText("ID");
		columnId.setWidth(50);
		columnId.setResizable(true);
		tableViewerColumnId.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return Long.toString(((Book) element).getId());
			}
		});

		TableViewerColumn tableViewerColumnTitle = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn columnTitle = tableViewerColumnTitle.getColumn();
		columnTitle.setText("Title");
		columnTitle.setWidth(150);
		columnTitle.setResizable(true);
		tableViewerColumnTitle.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Book) element).getTitle();
			}
		});

		TableViewerColumn tableViewerColumnAuthor = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn columnAuthor = tableViewerColumnAuthor.getColumn();
		columnAuthor.setText("Author");
		columnAuthor.setWidth(150);
		columnAuthor.setResizable(true);
		tableViewerColumnAuthor.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Book) element).getAuthors();
			}
		});
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
