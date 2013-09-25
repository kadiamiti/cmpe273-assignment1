package edu.sjsu.cmpe.library.domain;

public class Book {
		private Long isbn;
	    private String title;
	    private String publication_date;
	    private String language;
	    private String num_pages;
	    private String status="available";
	    private long author_key;
	    private Author[] authors;
	 //   private Reviews[] reviews;
	    
	    private final Long generateAuthorKey() {
		
			return Long.valueOf(++author_key);
		}

	    public Author[] getAuthors() {
			return authors;
		}
	    
	    public void setAuthors(Author[] authors){
	    	for(Author author : authors){
	    		//Long authorkey = (long) (100*Math.random());
	    		//author.setAuthor_key(authorkey);
	    		long authorkey = generateAuthorKey();
	    		author.setAuthor_key(authorkey);
	    		
	    	}
	    	this.authors = authors;
	    }

		//public Reviews[] getReviews() {
			//return reviews;
		//}

		//public void setReviews(Reviews[] reviews) {
			//this.reviews = reviews;
	//	}

		public long getIsbn() {
		return isbn;
	    }

	       public void setIsbn(long isbn) {
		this.isbn = isbn;
	    }

	        public String getTitle() {
		return title;
	    }

	       public void setTitle(String title) {
		this.title = title;
	    }

		public String getPublication_date() {
			return publication_date;
		}

		public void setPublication_date(String publication_date) {
			this.publication_date = publication_date;
		}

		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		
		

		public String getNum_pages() {
			return num_pages;
		}

		public void setNum_pages(String num_pages) {
			this.num_pages = num_pages;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
			
}
