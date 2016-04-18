package markowski.library.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import markowski.library.data.model.Book;
import markowski.library.data.model.LibraryModel;

public class BookTitleFilter extends ViewerFilter {

	LibraryModel model = LibraryModel.INSTANCE;
	
    public boolean select(Viewer viewer, Object parentElement, Object element) {
    	String filterTitleText = model.getFilterTitleText();
        Book book = (Book) element;
        
        if (filterTitleText == null || filterTitleText.isEmpty()) {
			return true;
		}
        
    	if(book.getTitle().toLowerCase().matches(".*" + filterTitleText.toLowerCase() + ".*")) {
            return true;
        }
    	
        return false;
    }
}

