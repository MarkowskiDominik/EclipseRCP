package markowski.library.data.provider;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import markowski.library.data.Book;
import markowski.library.data.mapper.BookMapper;

public class DataProvider {

	private final String FIND_BOOKS_URL = "http://localhost:9721/workshop/all_books"; //get
	private final String ADD_BOOK_URL = "http://localhost:9721/workshop/book"; //post JSON: { "title": "", "authors": "" }
	private static final Logger LOG = Logger.getLogger(DataProvider.class.getName());
	
	public Book addNewBook(Book book) {
		try {
			return BookMapper.map(restAddNewBook(BookMapper.map(book)));
		} catch (Exception e) {
			LOG.warning(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	private String restAddNewBook(String json) {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPost request = new HttpPost(ADD_BOOK_URL);
			request.addHeader("content-type", "application/json");
			request.setEntity(new StringEntity(json));
			
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				@Override
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};
			
			return httpClient.execute(request, responseHandler);
		} catch (Exception e) {
			LOG.warning(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Book> getBooks() {
		try {
			return BookMapper.map2List(restFindAllBook());
		} catch (Exception e) {
			LOG.warning(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	private String restFindAllBook() {
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpGet request = new HttpGet(FIND_BOOKS_URL);

			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				@Override
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};
			
			return httpClient.execute(request, responseHandler);
		} catch (Exception e) {
			LOG.warning(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}