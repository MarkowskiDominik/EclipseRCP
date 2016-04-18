package markowski.library.data.model;

import java.util.ArrayList;

import org.eclipse.core.databinding.observable.list.WritableList;

import markowski.library.data.provider.DataProvider;

public class LibraryModel {

	public static LibraryModel INSTANCE = getInstance();

	private static LibraryModel singleton;
	private final WritableList books;
	private final DataProvider dataProvider;

	private LibraryModel() {
		books = new WritableList(new ArrayList<>(), Book.class);
		dataProvider = new DataProvider();
		update();
	}

	private static LibraryModel getInstance() {
		if (singleton == null) {
			singleton = new LibraryModel();
		}
		return singleton;
	}

	public void update() {
		books.clear();
		books.addAll(dataProvider.getBooks());
	}

	public WritableList getBooks() {
		return books;
	}

	public void addNewBook(Book book) {
		dataProvider.addNewBook(book);
		update();
	}

}