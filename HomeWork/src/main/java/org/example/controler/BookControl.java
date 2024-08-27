package org.example.controler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.model.Book;
import org.example.model.BookDTO;
import org.example.model.BookStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.UUID;

@Tag(name = "Book Controller", description = "Управление книгами")
@RestController
public class BookControl {

    private static final Logger logger = LoggerFactory.getLogger(BookControl.class);

    @Operation(summary = "Получить список книг", description = "Возвращает список всех доступных книг")
    @GetMapping("/")
    public String booksList(Model model) {
        model.addAttribute("books", BookStorage.getBooks());
        return "books-list";
    }

    @Operation(summary = "Получить форму для создания книги", description = "Возвращает форму для создания новой книги")
    @GetMapping("/create-form")
    public String createForm() {
        return "create-form";
    }

    @Operation(summary = "Создать книгу", description = "Создает новую книгу на основе переданных данных")
    @PostMapping("/create")
    public String create(@Valid BookDTO bookDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input");
        }
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setName(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPages(bookDTO.getIsbn());

        BookStorage.getBooks().add(book);
        logger.info("Created book with ID: {}", book.getId());
        return "redirect:/";
    }

    @Operation(summary = "Удалить книгу", description = "Удаляет книгу по её ID")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        Book bookToDelete = BookStorage.getBooks()
                .stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Книга не найдена"));
        BookStorage.getBooks().remove(bookToDelete);
        logger.info("Deleted book with ID: {}", id);
        return "redirect:/";
    }

    @Operation(summary = "Получить форму для редактирования книги", description = "Возвращает форму для редактирования книги по её ID")
    @GetMapping("/edit-form/{id}")
    public String updateBooks(@PathVariable("id") String id, Model model) {
        Book bookToEdit = BookStorage.getBooks()
                .stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Книга не найдена"));
        model.addAttribute("book", bookToEdit);
        return "update-form";
    }

    @Operation(summary = "Обновить книгу", description = "Обновляет информацию о книге на основе переданных данных")
    @PostMapping("/update")
    public String update(@Valid BookDTO bookDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input");
        }

        Book bookToUpdate = BookStorage.getBooks()
                .stream()
                .filter(b -> b.getId().equals(bookDTO.getIsbn()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Книга не найдена для обновления"));

        BookStorage.getBooks().remove(bookToUpdate);

        Book updatedBook = new Book();
        updatedBook.setId(bookToUpdate.getId());
        updatedBook.setName(bookDTO.getTitle());
        updatedBook.setAuthor(bookDTO.getAuthor());
        updatedBook.setPages(bookDTO.getIsbn());

        BookStorage.getBooks().add(updatedBook);
        logger.info("Updated book with ID: {}", bookToUpdate.getId());
        return "redirect:/";
    }
}
