package edu.sjsu.cmpe.library.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.core.MultivaluedMap;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Reviews;

public class BookRepository implements BookRepositoryInterface {
	/** In-memory map to store books. (Key, Value) -> (ISBN, Book) */
	private final ConcurrentHashMap<Long, Book> bookInMemoryMap;
	private final ConcurrentHashMap<Long, ArrayList<Reviews>> reviewsInMemoyMap;
	
	
	/** Never access this key directly; instead use generateISBNKey() */
	private long isbnKey;
	private long reviewKey;



	public BookRepository(ConcurrentHashMap<Long, Book> bookMap,
			ConcurrentHashMap<Long, ArrayList <Reviews>> reviewsMap) {
		// TODO Auto-generated constructor stub
		checkNotNull(bookMap, "bookMap must not be null for BookRepository");
		bookInMemoryMap = bookMap;
		isbnKey = 0;
				
		checkNotNull(reviewsMap, "bookMap must not be null for BookRepository");
	reviewsInMemoyMap = reviewsMap;
	isbnKey = 0;
	}

	/**
	 * This should be called if and only if you are adding new books to the
	 * repository.
	 * 
	 * @return a new incremental ISBN number
	 */
	private final Long generateISBNKey() {
		// increment existing isbnKey and return the new value
		return Long.valueOf(++isbnKey);
	}

	/**
	 * This will auto-generate unique ISBN for new books.
	 */
	@Override
	public Book saveBook(Book newBook) {
		checkNotNull(newBook, "newBook instance must not be null");
		// Generate new ISBN
		Long isbn = generateISBNKey();
		newBook.setIsbn(isbn);

		// TODO: create and associate other fields such as author

		// Finally, save the new book into the map
		bookInMemoryMap.putIfAbsent(isbn, newBook);

		return newBook;
	}

	/**
	 * @see edu.sjsu.cmpe.library.repository.BookRepositoryInterface#getBookByISBN(java.lang.Long)
	 */
	
	
	
	
	
	
	@Override
	public Book getBookByISBN(Long isbn) throws Exception {
		Book book = new Book();
		checkArgument(isbn > 0,
				"ISBN was %s but expected greater than zero value", isbn);

		book = bookInMemoryMap.get(isbn);
		if(book.getTitle() == null){
			throw new Exception("No book created for the isbn in question");
		}

		return book;
	}

	@Override
	public ArrayList<Reviews> getreviewByISBN(Long isbn) throws Exception{
		
		ArrayList< Reviews> review;
		
		checkArgument(isbn > 0,
				"ISBN was %s but expected greater than zero value", isbn);
		
		review = reviewsInMemoyMap.get(isbn);
		
		return review;
				
	}
	@Override
	public Reviews getreviewByID(Long isbn, Long reviewkey) {
		
		Reviews review = new Reviews();
		ArrayList <Reviews> ReviewList = new ArrayList<Reviews>();
		Reviews selectedReview = new Reviews();
		
		ReviewList = reviewsInMemoyMap.get(isbn);
	
		
		for(int i = ReviewList.size() -1 ;i >= 0 ; i-- )
		{
			review = ReviewList.get(i);
			if(review.getReview_key() == reviewkey)
			{
				selectedReview = review;
				break;
			}
			
			
		}
		
				
		return selectedReview;
		
		
	}
	
	
	
	
	@Override
	public Book deleteBookByISBN(Long isbn) throws Exception {
		Book book = new Book();
		// TODO Auto-generated method stub
		if (bookInMemoryMap.containsKey(isbn)) {
			book = bookInMemoryMap.get(isbn);
			bookInMemoryMap.remove(isbn);
		} else {
			throw new Exception("This book does not exist in Memory");
		}
		return book;
	}

	@Override
	public Book updateBookByISBN(Long isbn,
			MultivaluedMap<String, String> paramMap) throws Exception{
		
		Book book = new Book();
		// TODO Auto-generated method stub
		if (bookInMemoryMap.containsKey(isbn)) {
			book = bookInMemoryMap.get(isbn);
			if(paramMap.containsKey("title")){
				List<String> bookTitle = paramMap.get("title");
				book.setTitle(bookTitle.get(0));
				bookInMemoryMap.put(isbn, book);
			}
			
			
			else if(paramMap.containsKey("publication_date")){
				List<String> bookTitle = paramMap.get("publication_date");
				book.setPublication_date(bookTitle.get(0));
				bookInMemoryMap.put(isbn, book);
			}
			
			else if(paramMap.containsKey("num_pages")){
				List<String> bookTitle = paramMap.get("num_pages");
				book.setNum_pages(bookTitle.get(0));
				bookInMemoryMap.put(isbn, book);
			}
			
			else if(paramMap.containsKey("language")){
				List<String> bookTitle = paramMap.get("language");
				book.setLanguage(bookTitle.get(0));
				bookInMemoryMap.put(isbn, book);
			}
			else if(paramMap.containsKey("status")){
				List<String> bookTitle = paramMap.get("status");
				book.setStatus(bookTitle.get(0));
				bookInMemoryMap.put(isbn, book);
			}
		} else {
			throw new Exception("This book does not exist in Memory");
		}
		return book;
		
	}
	
	
@Override
	



	
public Reviews saveReviews (ArrayList <Reviews> request, Long isbn) throws Exception {
		
		
		Long review_key = generateReviewKey();
		request.get(0).setReview_key(review_key);
		
		Reviews review = request.get(0);
			
		if (reviewsInMemoyMap.get(isbn) == null){
			
			reviewsInMemoyMap.put(isbn, request);
			
		}
		
		else
		{
			
			reviewsInMemoyMap.get(isbn).add(request.get(0));
			
		}
		
		
		
		return review;
			

				
			}
		
	


@Override
public Author[] getauthorByISBN(Long isbn) throws Exception
	{
		
				
		Book book = new Book();
		
		
		book = bookInMemoryMap.get(isbn); 
		Author[] arryAuthor = book.getAuthors();
		
		
		return arryAuthor;
		
		
	}


@Override
public Author getauthorByID(Long isbn, Long id) throws Exception {
	
	Book book = new Book();
	
	
	book = bookInMemoryMap.get(isbn); 
	Author[] arryAuthor = book.getAuthors();
	Author selectedAuthor = new Author();
	Author author = new Author();
	
	
	for(int i = arryAuthor.length -1 ;i >= 0 ; i-- )
	{
		author = arryAuthor[i];
		if(author.getAuthor_key() == id)
		{
			selectedAuthor = author;
			break;
		}
		
		
	}
	
	
	
	return selectedAuthor;
	
	
}
			
			
	private final Long generateReviewKey() {
		// TODO Auto-generated method stub
		return Long.valueOf(++reviewKey);
	}
	}


