package com.github.MarioMedWilson.borrowing;

import com.github.MarioMedWilson.Request.BorrowRequest;
import com.github.MarioMedWilson.Response.ApiResponse;
import com.github.MarioMedWilson.book.Book;
import com.github.MarioMedWilson.book.BookRepository;
import com.github.MarioMedWilson.patron.Patron;
import com.github.MarioMedWilson.patron.PatronRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BorrowService {
    private final BorrowRepository borrowRepository;
    private final PatronRepository patronRepository;
    private final BookRepository bookRepository;

    public BorrowService(BorrowRepository borrowRepository, PatronRepository patronRepository, BookRepository bookRepository) {
        this.borrowRepository = borrowRepository;
        this.patronRepository = patronRepository;
        this.bookRepository = bookRepository;
    }

    public ResponseEntity borrowBook(Long patronId, Long bookId, BorrowRequest borrowRequest) {
        if (!patronExists(patronId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Patron with ID " + patronId + " not found"));
        }
        if (!bookExists(bookId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Book with ID " + bookId + " not found"));
        }
        BorrowingRecord fetchData = borrowRepository.findByBookIdAndPatronId(bookId, patronId);
        if (fetchData != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Book and patron already have a borrowing record"));
        }
        Timestamp borrowDate = borrowRequest.getBorrowDate();
        Timestamp returnDate = borrowRequest.getReturnDate();
        BorrowingRecord borrowingRecord = new BorrowingRecord(patronId, bookId, borrowDate, returnDate);
        return ResponseEntity.ok(borrowRepository.save(borrowingRecord));
    }

    public ResponseEntity returnBook(Long bookId, Long patronId, Timestamp returnDate) {
        BorrowingRecord borrowingRecord = borrowRepository.findByBookIdAndPatronId(bookId, patronId);
        if (borrowingRecord == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Borrowing record not found"));
        } else {
            borrowingRecord.setReturnDate(returnDate);
            borrowRepository.save(borrowingRecord);
            return ResponseEntity.ok(new ApiResponse("Book returned successfully"));
        }
    }

    private boolean patronExists(Long patronId) {
        Patron patron = patronRepository.findById(patronId).orElse(null);
        return patron != null;
    }

    private boolean bookExists(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        return book != null;
    }

    public void deleteBorrowingRecordByBookId(Long bookId) {
        List<BorrowingRecord> borrowingRecords = borrowRepository.findByBookId(bookId);
        for (BorrowingRecord borrowingRecord : borrowingRecords) {
            borrowRepository.delete(borrowingRecord);
        }
    }

    public void deleteBorrowingRecordByPatronId(Long patronId) {
        List<BorrowingRecord> borrowingRecords = borrowRepository.findByPatronId(patronId);
        for (BorrowingRecord borrowingRecord : borrowingRecords) {
            borrowRepository.delete(borrowingRecord);
        }
    }

}
