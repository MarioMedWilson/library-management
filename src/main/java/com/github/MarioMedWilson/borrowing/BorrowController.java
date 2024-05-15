package com.github.MarioMedWilson.borrowing;


import com.github.MarioMedWilson.Request.BorrowRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/v1")
public class BorrowController {
    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping(path = "/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity borrowBook(@PathVariable Long bookId, @PathVariable Long patronId, @RequestBody BorrowRequest borrowRequest) {
        return borrowService.borrowBook(patronId, bookId, borrowRequest);
    }

    @PutMapping(path = "/return/{bookId}/patron/{patronId}")
    public ResponseEntity returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        Timestamp returnDate = new Timestamp(System.currentTimeMillis());
        return borrowService.returnBook(bookId,patronId,returnDate);
    }
}
