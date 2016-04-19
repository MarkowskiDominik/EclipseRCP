package markowski.library.handler;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import markowski.library.data.model.Book;
import markowski.library.data.model.LibraryModel;
import markowski.library.dialog.AddNewBookDialog;
import markowski.library.dialog.ConfirmBookDeleteDialog;

public class DeleteSelectedBookHandler  extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow activeWorkbenchWindow = HandlerUtil.getActiveWorkbenchWindow(event);
		ISelection selection = activeWorkbenchWindow.getActivePage().getSelection();
		if (selection != null & selection instanceof IStructuredSelection) {
			List<Book> booksToBeDeleted = ((IStructuredSelection) selection).toList();

			Shell shell = activeWorkbenchWindow.getShell();
			ConfirmBookDeleteDialog deleteDialog = new ConfirmBookDeleteDialog(shell, booksToBeDeleted);

			if (deleteDialog.open() == Window.OK) {
				
			}
		}
		return null;
	}
}