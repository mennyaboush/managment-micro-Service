package management.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ManagementController {

	private ManagementService managementService;

	@Autowired	
	public ManagementController(ManagementService managementService) {
		super();
		this.managementService = managementService;
	}

	@RequestMapping(
			method=RequestMethod.GET,
			path="/catalog",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Book[] getAllBooks(
			@RequestParam(name="size", required=false, defaultValue="10") int size, 
			@RequestParam(name="page", required=false, defaultValue="0") int page){
		return this.managementService
			.getAllBooks(size, page)
			.toArray(new Book[0]);
	}

	@RequestMapping(
			method=RequestMethod.GET,
			path="/catalog/publish/{isbn}/{title}/{author1}/{publishedYear}/{rating}/{publisher}/{publisherAddress}/{publisherPhone}/{publisherURL}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Book publishBookToCatalog(
			@PathVariable("isbn") String isbn,
			@PathVariable("title") String title,
			@PathVariable("author1") String author1,
			@PathVariable("publishedYear") int publishedYear,
			@PathVariable("rating") int rating,
			@PathVariable("publisher") String publisher,
			@PathVariable("publisherAddress") String publisherAddress,
			@PathVariable("publisherPhone") String publisherPhone,
			@PathVariable("publisherURL") String publisherURL){
		return this.managementService
			.publishBook(isbn,
						title,
						author1,
						publishedYear,
						rating,
						publisher,
						publisherAddress,
						publisherPhone,
						publisherURL);
	}

	@RequestMapping(
			method=RequestMethod.GET,
			path="/readers",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Reader[] getAllReaders(
			@RequestParam(name="size", required=false, defaultValue="10") int size, 
			@RequestParam(name="page", required=false, defaultValue="0") int page){
		return this.managementService
			.getAllReaders(size, page)
			.toArray(new Reader[0]);
	}

	@RequestMapping(
			method=RequestMethod.GET,
			path="/readers/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Reader getSpecificReader(
			@PathVariable("id") String id) throws DataNotFoundException{
		return this.managementService
			.getSpecificReader(id);
	}

	@RequestMapping(
			method=RequestMethod.GET,
			path="/readers/{id}/{email}/{firstName}/{lastName}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Reader createReader(
			@PathVariable("id") String id,
			@PathVariable("email") String email,
			@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName){
		return this.managementService
			.createReader(id,email,firstName,lastName);
	}

	@RequestMapping(
			method=RequestMethod.GET,
			path="/loans/{readerId}/{isbn}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> loanBook(
			@PathVariable("readerId") String readerId,
			@PathVariable("isbn") String isbn){
		return this.managementService
			.loanBook(readerId, isbn);
	}

	@RequestMapping(
			method=RequestMethod.GET,
			path="/cleanup")
	public void clenup() {
		this.managementService
			.cleanup();
	}
	
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler
	public Map<String, Object> handleError (DataNotFoundException e){
		Map<String, Object> rv = new HashMap<>();
		rv.put("error", "Reader could not be found");
		return rv;
	}

}







