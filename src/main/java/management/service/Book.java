package management.service;

import java.util.Arrays;

public class Book {
	private String isbn;

	private String title;

	private String[] authors;

	private Publisher publisher;

	private int publishedYear;

	private int rating;

	public Book() {
	}

	public Book(String isbn, String title, String[] authors, Publisher publisher,
			int publishedYear, int rating) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.authors = authors;
		this.publisher = publisher;
		this.publishedYear = publishedYear;
		this.rating = rating;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public int getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", authors=" + Arrays.toString(authors) + ", publisher="
				+ publisher + ", publishedYear=" + publishedYear + ", rating=" + rating + "]";
	}

}
