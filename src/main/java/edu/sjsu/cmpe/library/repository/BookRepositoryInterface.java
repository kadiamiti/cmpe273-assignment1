package edu.sjsu.cmpe.library.repository;

import java.util.ArrayList;

import javax.ws.rs.core.MultivaluedMap;



//import edu.sjsu.cmpe.library.api.resources.Authors;
import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Reviews;

/**
 * Book repository interface.
 * 
 * What is repository pattern?
 * 
 * @see http://martinfowler.com/eaaCatalog/repository.html
 */
public interface BookRepositoryInterface {
    /**
     * Save a new book in the repository
     * 
     * @param newBook
     *            a book instance to be create in the repository
     * @return a newly created book instance with auto-generated ISBN
     */
    Book saveBook(Book newBook);

    /**
     * Retrieve an existing book by ISBN
     * 
     * @param isbn
     *            a valid ISBN
     * @return a book instance
     * @throws Exception 
     */
    Book getBookByISBN(Long isbn) throws Exception;

	Book deleteBookByISBN(Long isbn) throws Exception;

	Book updateBookByISBN(Long isbn, MultivaluedMap<String, String> paramMap) throws Exception;

	Reviews saveReviews(ArrayList <Reviews> request, Long isbn) throws Exception;

	ArrayList<Reviews> getreviewByISBN(Long isbn) throws Exception;



	Reviews getreviewByID(Long isbn, Long review_key) throws Exception;

	Author[] getauthorByISBN(Long isbn) throws Exception;

	Author getauthorByID(Long isbn, Long id) throws Exception;

	

    // TODO: add other operations here!
}
