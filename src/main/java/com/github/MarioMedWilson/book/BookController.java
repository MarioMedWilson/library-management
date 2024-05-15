package com.github.MarioMedWilson.book;

import com.github.MarioMedWilson.Response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "{bookId}")
    public ResponseEntity getBook(@PathVariable("bookId") Long id) {
        return bookService.getBook(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return ResponseEntity.ok(new ApiResponse("Book has been successfully added."));
    }

    //make it delete with json repsonse
    @DeleteMapping(path = "{bookId}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable("bookId") Long id) {
        return bookService.deleteBook(id);
    }


    @PutMapping(path = "{bookId}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable("bookId") Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }
}
