package edu.sjsu.cmpe.library.dto;



import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;


@JsonPropertyOrder(alphabetic = true)
public class AuthorDto extends LinksDto {
   
    private Author author;
    
    private Author[] authorArray;
    /**
     * @param book
     */
   
    

		
		 public AuthorDto(Author[] authors) {
	        	super();
	        	this.setAuthorArray(authors);
	        	
	            }

		public Author[] getAuthorArray() {
			return authorArray;
		}

		public void setAuthorArray(Author[] authorArray) {
			this.authorArray = authorArray;
		}
		
		
		
		  public AuthorDto(Author author) {
				super();
				this.author = author;
			
				
			    }

		public Author getAuthor() {
			return author;
		}

		public void setAuthor(Author author) {
			this.author = author;
		}

		
		

         
}
