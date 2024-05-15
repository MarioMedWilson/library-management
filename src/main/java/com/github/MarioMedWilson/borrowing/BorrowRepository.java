package com.github.MarioMedWilson.borrowing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowingRecord,Long> {
    BorrowingRecord findByBookIdAndPatronId(Long bookId, Long patronId);
    List<BorrowingRecord> findByBookId(Long bookId);
    List<BorrowingRecord> findByPatronId(Long patronId);
}
