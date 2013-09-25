package edu.sjsu.cmpe.library.domain;

public class Reviews{
	
	    private long review_key;
	    private String rating;
	    private String comment;
	    
	
	public long getReview_key() {
		return review_key;		
	}
	public void setReview_key(long review_key) {
		this.review_key = review_key;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
		
}