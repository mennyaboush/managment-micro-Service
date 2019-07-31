package management.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ManagementServiceImpl implements ManagementService {
	private String readersServiceUrl;
	private String catalogServiceUrl;
	private String loansBaseUrl;

	private RestTemplate rest;
	private WebClient webClient;

	@Value("${library.loans.url:invalid}")
	public void setLoansBaseUrl(String loansBaseUrl) {
		this.loansBaseUrl = loansBaseUrl;
	}
	
	@Value("${library.readers.url:invalid}")
	public void setReadersServiceUrl(String readersServiceUrl) {
		this.readersServiceUrl = readersServiceUrl;
	}

	@Value("${library.catalog.url:invalid}")
	public void setCatalogServiceUrl(String catalogServiceUrl) {
		this.catalogServiceUrl = catalogServiceUrl;
	}

	@PostConstruct
	public void init() {
		this.rest = new RestTemplate();
		this.webClient = WebClient.create(this.loansBaseUrl);
	}

	@Override
	public List<Reader> getAllReaders(int size, int page) {
		return Arrays.asList(this.rest.getForObject(this.readersServiceUrl + "?size={size}&page={page}", Reader[].class,
				size, page));
	}

	@Override
	public Reader createReader(String id, String email, String firstName, String lastName) {
		return this.rest.postForObject(this.readersServiceUrl, new Reader(id, email, firstName, lastName), Reader.class,
				id);
	}

	@Override
	public Reader getSpecificReader(String id) throws DataNotFoundException {
		System.err.println(this.readersServiceUrl);
		try {
			ResponseEntity<Reader> response = this.rest.getForEntity(this.readersServiceUrl + "/{id}", Reader.class,
					id);

			if (response.getStatusCode().is2xxSuccessful()) {
				return response.getBody();
			} else {
				throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().compareTo(HttpStatus.NOT_FOUND) == 0) {
				throw new DataNotFoundException("reader not found");
			}
			throw new RuntimeException("geberal error");
		}
	}

	@Override
	public List<Book> getAllBooks(int size, int page) {
		return Arrays.asList(this.rest.getForObject(
				this.catalogServiceUrl + "/all?content=detailed&size={size}&page={page}", Book[].class, size, page));
	}

	@Override
	public Book publishBook(
			String isbn, 
			String title, 
			String author1, 
			int publishedYear, 
			int rating, 
			String publisher,
			String publisherAddress, 
			String publisherPhone, 
			String publisherURL) {
		
		Book newBook = new Book(
				isbn, 
				title, 
				new String[] {author1}, 
				new Publisher(
						publisher, 
						publisherAddress, 
						publisherPhone, 
						publisherURL), 
				publishedYear, rating);
		return this.rest
				.postForObject(
						this.catalogServiceUrl + "/echo", 
						newBook, 
						Book.class);
	}

	@Override
	public Map<String, Object> loanBook(
			String readerId, 
			String isbn) {
		Reader reader = this.rest
			.getForObject(
					this.readersServiceUrl + "/{id}", 
					Reader.class, 
					readerId);
		
		this.rest
			.getForObject(
					this.catalogServiceUrl + "/byIsbn/{isbn}", 
					String.class, 
					isbn);
		return 
		this.webClient
			.post()
			.uri("/{isbn}", isbn)
			.contentType(MediaType.APPLICATION_JSON)
			.syncBody(reader)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(Map.class)
			.block();
	}
	
	@Override
	public void cleanup() {
		// TODO implement this by deleting data using other services

	}

}
