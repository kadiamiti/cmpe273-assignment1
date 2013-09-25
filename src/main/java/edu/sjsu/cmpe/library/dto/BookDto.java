package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Reviews;

@JsonPropertyOrder(alphabetic = true)
public class BookDto extends LinksDto {
    private Book book;
 //   private Reviews review;
  //  private Author author;
   // private ArrayList<Reviews> ReviewList;
    //private Author[] authorArray;
    /**
     * @param book
     */
   
    public BookDto(Book book) {
	super();
	this.book = book;
	//review.equals(null);
	
	
    }

    /**
     * @return the book
     */
    public Book getBook() {
	return book;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setBook(Book book) {
	this.book = book;
    }
    //
    
    /*
    public BookDto(Reviews review) {
    	super();
    	this.review = review;
    	//book.equals(null);
        }

        /**
         * @return the book
         */
      //  public Reviews getReview() {
    //	return review;
       // }

        /**
         * @param book
         *            the book to set
         */
//        public void setReview(Reviews review) {
    	//this.review = review;
       // }
        
        
        

        //public BookDto(ArrayList <Reviews> reviewlist) {
      //  	super();
    //    	this.ReviewList = reviewlist;
  //      	
//            }

		

		//public ArrayList<Reviews> getReviewList() {
	//		return ReviewList;
	//	}

//		public void setReviewList(ArrayList<Reviews> reviewList) {
			//ReviewList = reviewList;
		//}

		
		 //public BookDto(Author[] authors) {
	      //  	super();
	    //    	this.setAuthorArray(authors);
	        	
	  //          }

	//	public Author[] getAuthorArray() {
//			//return authorArray;
		//}

		//public void setAuthorArray(Author[] authorArray) {
			//this.authorArray = authorArray;
		//}
		
		
		
		  //public BookDto(Author author) {
				//super();
			//	this.author = author;
			
				
		//	    }

	//	public Author getAuthor() {
	//		return author;
	//	}

	//	public void setAuthor(Author author) {
	//		this.author = author;
	//	}

		
		

         
}
