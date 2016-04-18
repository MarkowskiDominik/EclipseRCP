package markowski.library.data.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Book extends ModelObject {

	private Long id;
	private String title;
	private String authors;
	private List<String> lendHistory = new ArrayList<>();

	@JsonCreator
	public Book(@JsonProperty(defaultValue = "null", value = "id") Long id, @JsonProperty("title") String title,
			@JsonProperty("authors") String authors) {
		this.id = id;
		this.title = title;
		this.authors = authors;
		lendHistory.add("empty history");
		lendHistory.add("long time ago in a galaxy far far away");
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthors() {
		return authors;
	}

	public List<String> getLendHistory() {
		return lendHistory;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", authors=" + authors + "]";
	}

}