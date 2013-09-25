package edu.sjsu.cmpe.library.domain;

public class Author {
	
	private long author_key;
	private String name;
	
	public long getAuthor_key() {
		//author_key=(long) (Math.random());
		return author_key;
	}
	
	
	public void setAuthor_key(long author_key) {
		
		this.author_key = author_key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	  
	  
	  
	
	  
	  
	  
}