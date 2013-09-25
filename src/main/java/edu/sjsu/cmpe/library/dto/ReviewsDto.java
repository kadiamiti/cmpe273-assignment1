package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import edu.sjsu.cmpe.library.domain.Reviews;

@JsonPropertyOrder(alphabetic = true)
public class ReviewsDto extends LinksDto {
   
    private Reviews review;
    
    private ArrayList<Reviews> ReviewList;
   
   
  
    
    
    public ReviewsDto(Reviews review) {
    	super();
    	this.review = review;
    	//book.equals(null);
        }

        /**
         * @return the book
         */
        public Reviews getReview() {
    	return review;
        }

        /**
         * @param book
         *            the book to set
         */
        public void setReview(Reviews review) {
    	this.review = review;
        }
        
        
        

        public ReviewsDto(ArrayList <Reviews> reviewlist) {
        	super();
        	this.ReviewList = reviewlist;
        	
            }

		

		public ArrayList<Reviews> getReviewList() {
			return ReviewList;
		}

		public void setReviewList(ArrayList<Reviews> reviewList) {
			ReviewList = reviewList;
		}

		
	
}
