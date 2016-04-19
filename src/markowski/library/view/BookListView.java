package markowski.library.view;

import java.util.logging.Logger;

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import markowski.library.data.model.Book;
import markowski.library.data.model.LibraryModel;
import markowski.library.filter.BookAuthorFilter;
import markowski.library.filter.BookTitleFilter;

import org.eclipse.core.databinding.DataBindingContext;

public class BookListView extends ViewPart {

	private Logger LOG = Logger.getLogger(getClass().getName());
	private static LibraryModel model = LibraryModel.INSTANCE;
	private static final String[] PROPERTY_NAMES = new String[] { "id", "title", "authors" };
	private DataBindingContext dataBindingContext = new DataBindingContext();

	private Text filterTitleText;
	private Text filterAuthorText;
	private TableViewer tableViewer;

	public BookListView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2, false));

		createTitleLabel(parent);

		createFilterComposites(parent);

		createTableViewer(parent);
	}

	private void createTitleLabel(Composite parent) {
		LOG.info("createTitleLabel");

		Label bookListLabel = new Label(parent, SWT.NONE);
		bookListLabel.setText("Books list");
		bookListLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		bookListLabel.setAlignment(SWT.CENTER);
	}

	private void createFilterComposites(Composite parent) {
		LOG.info("createFilterComposites");

		Label filterTitleLabel = new Label(parent, SWT.NONE);
		filterTitleLabel.setText("Title");
		filterTitleLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		filterTitleText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		filterTitleText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		IObservableValue widgetValue = WidgetProperties.text(SWT.Modify).observe(filterTitleText);
		IObservableValue modelValue = BeanProperties.value(LibraryModel.class, "filterTitleText").observe(model);
		dataBindingContext.bindValue(widgetValue, modelValue);
		
		filterTitleText.addModifyListener(new ModifyListener() {
		    @Override
		    public void modifyText(ModifyEvent event) {
		    	tableViewer.refresh();
		    }
		});

		Label filterAuthorLabel = new Label(parent, SWT.NONE);
		filterAuthorLabel.setText("Author");
		filterAuthorLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		filterAuthorText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		filterAuthorText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		widgetValue = WidgetProperties.text(SWT.Modify).observe(filterAuthorText);
		modelValue = BeanProperties.value("filterAuthorsText", LibraryModel.class).observe(model);
		dataBindingContext.bindValue(widgetValue, modelValue);
		
		filterAuthorText.addModifyListener(new ModifyListener() {
		    @Override
		    public void modifyText(ModifyEvent event) {
		    	tableViewer.refresh();
		    }
		});
	}

	private void createTableViewer(Composite parent) {
		LOG.info("createTableViewer");

		tableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tableViewer.setContentProvider(new ArrayContentProvider());
		createTableViewerColumns(tableViewer);

		ViewerSupport.bind(tableViewer, model.getBooks(),
				BeanProperties.values(Book.class, PROPERTY_NAMES));

		Table table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		tableViewer.addFilter(new BookTitleFilter());
		tableViewer.addFilter(new BookAuthorFilter());

		MenuManager menuManager = new MenuManager();
		table.setMenu(menuManager.createContextMenu(table));

		getSite().setSelectionProvider(tableViewer);
		getSite().registerContextMenu(menuManager, tableViewer);

	}

	private void createTableViewerColumns(TableViewer tableViewer) {
		LOG.info("createTableViewerColumns");

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
