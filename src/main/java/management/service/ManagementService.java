package management.service;

import java.util.List;
import java.util.Map;

public interface ManagementService {

	public List<Reader> getAllReaders(int size, int page);

	public Reader createReader(String id, String email, String firstName, String lastName);

	public Reader getSpecificReader(String id) throws DataNotFoundException;

	public void cleanup();

	public List<Book> getAllBooks(int size, int page);

	public Book publishBook(String isbn, String title, String author1, int publishedYear, int rating, String publisher,
			String publisherAddress, String publisherPhone, String publisherURL);

	public Map<String, Object> loanBook(String readerId, String isbn);

}
