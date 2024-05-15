package com.github.MarioMedWilson.borrowing;


import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patronId;
    private Long bookId;
    private Timestamp borrowDate;
    private Timestamp returnDate;

    public BorrowingRecord(Long patronId, Long bookId, Timestamp borrowDate, Timestamp returnDate) {
        this.patronId = patronId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public BorrowingRecord() {

    }

    public Long getId() {
        return id;
    }

    public Long getPatronId() {
        return patronId;
    }

    public Long getBookId() {
        return bookId;
    }

    public Timestamp getBorrowDate() {
        return borrowDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setPatronId(Long patronId) {
        this.patronId = patronId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setBorrowDate(Timestamp borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }
}
