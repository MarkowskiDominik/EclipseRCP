package markowski.library.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import markowski.library.dialog.AddBookDialog;

public class AddNewBookHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		AddBookDialog addBookDialog = new AddBookDialog(null);
		addBookDialog.open();
		return null;
	}
}