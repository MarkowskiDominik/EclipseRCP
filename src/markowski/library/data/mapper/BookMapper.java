package markowski.library.data.mapper;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import markowski.library.data.model.Book;

public class BookMapper {
	private static ObjectMapper mapper = new ObjectMapper();

	public static List<Book> map2List(String json) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, Book.class));
	}
	
	public static String map2String(List<Book> books) throws JsonProcessingException{
		return mapper.writeValueAsString(books);
	}
	
	public static Book map(String json) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, Book.class);
	}
		
	public static String map(Book book) throws JsonProcessingException{
		return mapper.writeValueAsString(book);
	}
	
}