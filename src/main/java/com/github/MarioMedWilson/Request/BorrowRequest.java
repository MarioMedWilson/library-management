package com.github.MarioMedWilson.Request;

import java.sql.Timestamp;

public class BorrowRequest {
    private Timestamp borrowDate;
    private Timestamp returnDate;


    public Timestamp getBorrowDate() {
        return borrowDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

}