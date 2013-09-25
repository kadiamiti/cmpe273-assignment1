package edu.sjsu.cmpe.library.api.resources;


import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Reviews;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
	/** bookRepository instance */
	private final BookRepositoryInterface bookRepository;

	/**
	 * BookResource constructor
	 * 
	 * @param bookRepository
	 *            a BookRepository instance
	 */
	public BookResource(BookRepositoryInterface bookRepository) {
		this.bookRepository = bookRepository;
	}

	@GET
	@Path("/{isbn}")
	@Timed(name = "view-book")
	public Response getBookByIsbn(@PathParam("isbn") LongParam isbn) {
		Book book = new Book();
		try {
			book = bookRepository.getBookByISBN(isbn.get());
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		String location = "/books/" + book.getIsbn();
		BookDto bookResponse = new BookDto(book);
		if(book.getIsbn()==0)
		bookResponse.addLink(new LinkDto("view-book", "/books/"
				+ location, "GET"));
		bookResponse.addLink(new LinkDto("update-book", "/books/"
				+ location, "POST"));
		bookResponse.addLink(new LinkDto("delete-book", "/books/"
				+ location , "DELETE"));
		bookResponse.addLink(new LinkDto("create-reviews", "/books/"
				+ location + "reviews", "POST"));
		bookResponse.addLink(new LinkDto("view-all-reviews", "/books/"
				+ location + "reviews", "GET"));
		
			
		return Response.status(200).entity(bookResponse).build();
	}

	@POST
	@Timed(name = "create-book")
	public Response createBook(Book request) {
		// Store the new book in the BookRepository so that we can retrieve it.
		Book savedBook = bookRepository.saveBook(request);

		String location = "/books/" + savedBook.getIsbn();
		BookDto bookResponse = new BookDto(savedBook);

		bookResponse.addLink(new LinkDto("view-book", "/books/"
				+ location, "GET"));
		bookResponse.addLink(new LinkDto("update-book", "/books/"
				+ location, "POST"));
		bookResponse.addLink(new LinkDto("delete-book", "/books/"
				+ location , "DELETE"));
		bookResponse.addLink(new LinkDto("create-reviews", "/books/"
				+ location + "reviews", "POST"));

		return Response.status(201).entity(bookResponse).build();
	}

	@DELETE
	@Path("/{isbn}")
	@Timed(name = "delete-book")
	public Response deleteBookByIsbn(@PathParam("isbn") LongParam isbn) {
		System.out.println(isbn.get());
		Book book = new Book();
		try {
			book = bookRepository.deleteBookByISBN(isbn.get());
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		BookDto bookResponse = new BookDto(null);
		bookResponse.addLink(new LinkDto("create-book", "/books/", "POST"));

		//return bookResponse;
		return Response.status(200).entity(bookResponse).build();
	}
	
	@PUT
	@Path("/{isbn}")
	@Timed(name = "update-book")
	
		public Response updateBookByIsbn(@PathParam("isbn") LongParam isbn, @Context UriInfo uriInfo) {
		Book book = new Book();
		MultivaluedMap<String,String> paramMap = uriInfo.getQueryParameters();
		try {
			book = bookRepository.updateBookByISBN(isbn.get(), paramMap);
		} catch (Exception e) {
			// TODO Auto-gen-erated catch block
			e.printStackTrace();
		}
		
	
		String location = "/books/" + book.getIsbn();
		BookDto bookResponse = new BookDto(book);
		if(book.getIsbn()==0)
		bookResponse.addLink(new LinkDto("view-book", "/books/"
				+ location, "GET"));
		bookResponse.addLink(new LinkDto("update-book", "/books/"
				+ location, "POST"));
		bookResponse.addLink(new LinkDto("delete-book", "/books/"
				+ location , "DELETE"));
		bookResponse.addLink(new LinkDto("create-reviews", "/books/"
				+ location + "reviews", "POST"));
		bookResponse.addLink(new LinkDto("view-all-reviews", "/books/"
				+ location + "reviews", "GET"));
		
			
		return Response.status(200).entity(bookResponse).build();
	
	}
	
	
	
	@POST
	@Path("/{isbn}/reviews")
	@Timed(name = "create-review")
	
		
		public Response createReview(Reviews request, @PathParam("isbn") LongParam isbn) throws Exception {
			// Store the new book in the BookRepository so that we can retrieve it.
		
		
		ArrayList< Reviews> RequestList = new ArrayList<Reviews>();
		RequestList.add(0, request);
			Reviews review = bookRepository.saveReviews(RequestList, isbn.get());
			
			
			ReviewsDto bookResponse = new ReviewsDto(review);
			
			bookResponse.addLink(new LinkDto("view-book", "/books/reviews"
					+ isbn.get(), "GET"));
		
			return Response.status(201).entity(bookResponse).build();
			
		
		}
			
			
	@GET
	@Path("/{isbn}/reviews")
	@Timed(name = "view-all-reviews")
	public Response getReviewByISBN(@PathParam("isbn") LongParam isbn) throws Exception {
		

		
		ArrayList<Reviews> review = null;
		review = bookRepository.getreviewByISBN(isbn.get());
		
		ReviewsDto bookResponse = new ReviewsDto(review);

 	       bookResponse.addLink(null);
		
		
		return Response.status(200).entity(bookResponse).build();
	
	
		
	}	
		
	
	
	@GET
	@Path("/{isbn}/reviews/{id}")
	@Timed(name = "view-review-by-id")
	public Response getReviewByISBN(@PathParam("isbn") LongParam isbn, @PathParam("id") LongParam reviewkey) throws Exception {
		


		
		Reviews review = new Reviews();
		review = bookRepository.getreviewByID(isbn.get(),reviewkey.get());
		
		ReviewsDto bookResponse = new ReviewsDto(review);
	
		String location = "/books/" + isbn.get();
 	    
 	   bookResponse.addLink(new LinkDto("view-review", "/books/"
				+ location + "/reviews/" + reviewkey.get() , "GET"));
	
	
				return Response.status(200).entity(bookResponse).build();
	}	
		
	
	@GET
	@Path("/{isbn}/authors")
	@Timed(name = "view-all-authors")
	public Response getAuthorByISBN(@PathParam("isbn") LongParam isbn) throws Exception {
		

		Author[] author = bookRepository.getauthorByISBN(isbn.get());
		
		AuthorDto bookResponse = new AuthorDto(author);
		// if(review.getReview_key()==0)
 	    bookResponse.addLink(null);
	
	
				return Response.status(200).entity(bookResponse).build();
	}	
		
	
	@GET
	@Path("/{isbn}/authors/{id}")
	@Timed(name = "view-author")
	public Response getAuthorByID(@PathParam("isbn") LongParam isbn, @PathParam("id") LongParam id) throws Exception {
		

		Author author = bookRepository.getauthorByID(isbn.get(), id.get());
		
		AuthorDto bookResponse = new AuthorDto(author);
		// if(review.getReview_key()==0)
		String location = "/books/" + isbn.get();
		  bookResponse.addLink(new LinkDto("view-authors", "/books/"
					+ location + "/reviews/" + id.get() , "GET"));
		
		
					return Response.status(200).entity(bookResponse).build();
	
					}	
	
}
