package markowski.library.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;

import markowski.library.data.model.Book;
import markowski.library.data.model.LibraryModel;
import markowski.library.dialog.AddNewBookDialog;

public class AddNewBookHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		AddNewBookDialog addBookDialog = new AddNewBookDialog(null);
	    
		if (addBookDialog.open() == Window.OK) {
			Book bookToAdd = new Book(null, addBookDialog.getBookTitle(), addBookDialog.getBookAuthors());
			LibraryModel.INSTANCE.addNewBook(bookToAdd);
		}
		return null;
	}
}