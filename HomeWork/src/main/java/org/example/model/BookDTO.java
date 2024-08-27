package org.example.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BookDTO {

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @NotBlank(message = "Author is mandatory")
    @Size(max = 100, message = "Author name must be less than 100 characters")
    private String author;

    @NotBlank(message = "ISBN is mandatory")
    @Size(max = 20, message = "ISBN must be less than 20 characters")
    private String isbn;

    public @NotBlank(message = "Title is mandatory") @Size(max = 100, message = "Title must be less than 100 characters") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is mandatory") @Size(max = 100, message = "Title must be less than 100 characters") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Author is mandatory") @Size(max = 100, message = "Author name must be less than 100 characters") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank(message = "Author is mandatory") @Size(max = 100, message = "Author name must be less than 100 characters") String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(@NotBlank(message = "ISBN is mandatory") @Size(max = 20, message = "ISBN must be less than 20 characters") String isbn) {
        this.isbn = isbn;
    }
}
