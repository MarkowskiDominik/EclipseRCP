package markowski.library.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import markowski.library.data.model.Book;
import markowski.library.data.model.LibraryModel;

public class BookAuthorFilter extends ViewerFilter {

	private LibraryModel model = LibraryModel.INSTANCE;
	
    public boolean select(Viewer viewer, Object parentElement, Object element) {
    	String filterAuthorsText = model.getFilterAuthorsText();
        Book book = (Book) element;

        if (filterAuthorsText == null || filterAuthorsText.isEmpty()) {
			return true;
		}
        
    	if(book.getAuthors().toLowerCase().matches(".*" + filterAuthorsText.toLowerCase() + ".*")) {
            return true;
        }
    	
        return false;
    }
}

