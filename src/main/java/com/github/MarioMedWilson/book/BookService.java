package com.github.MarioMedWilson.book;

import com.github.MarioMedWilson.Response.ApiResponse;
import com.github.MarioMedWilson.borrowing.BorrowRepository;
import com.github.MarioMedWilson.borrowing.BorrowService;
import com.github.MarioMedWilson.patron.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class BookService {
    @Autowired
    BookRepository repo;

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Autowired
    private BookRepository bookRepository;

    private final static Logger logger = Logger.getLogger(BookService.class.getName());

    public ResponseEntity<?> getBook(Long id) {
        Book book = repo.findById(id).orElse(null);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Book with ID " + id + " not found."));
        }
    }

    public ResponseEntity<ApiResponse> addBook(Book book) {
        repo.save(book);
        return ResponseEntity.ok(new ApiResponse("Book has been successfully added."));
    }

    public ResponseEntity<ApiResponse> deleteBook(Long id) {
        try {
            Optional<Book> optionalBook = repo.findById(id);
            if (optionalBook.isPresent()) {
                BorrowService borrowService = new BorrowService(borrowRepository, patronRepository, bookRepository);
                borrowService.deleteBorrowingRecordByBookId(id);
                repo.deleteById(id);
                logger.info("Book with ID " + id + " has been successfully deleted.");
                return ResponseEntity.ok(new ApiResponse("Book with ID " + id + " has been successfully deleted."));
            } else {
                logger.info("Book with ID " + id + " not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Book with ID " + id + " not found."));
            }
        } catch (Exception e) {
            logger.warning("Error deleting book with ID " + id + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("An error occurred while deleting the book."));
        }
    }


    public ResponseEntity<ApiResponse> updateBook(Long id, Book book) {
        Book existingBook = repo.findById(id).orElse(null);
        if (existingBook != null) {
            if (book.getTitle() != null) {
                existingBook.setTitle(book.getTitle());
            }
            if (book.getAuthor() != null) {
                existingBook.setAuthor(book.getAuthor());
            }
            if (book.getDescription() != null) {
                existingBook.setDescription(book.getDescription());
            }
            if (book.getIsbn() != null) {
                existingBook.setIsbn(book.getIsbn());
            }
            if (book.getYear() != 0) {
                existingBook.setYear(book.getYear());
            }
            repo.save(existingBook);
            return ResponseEntity.ok(new ApiResponse("Book with ID " + id + " has been successfully updated."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Book with ID " + id + " not found."));
        }
    }


}
