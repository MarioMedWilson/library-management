package com.github.MarioMedWilson.patron;

import com.github.MarioMedWilson.Response.ApiResponse;
import com.github.MarioMedWilson.book.BookRepository;
import com.github.MarioMedWilson.borrowing.BorrowRepository;
import com.github.MarioMedWilson.borrowing.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {
    @Autowired
    PatronRepository repo;
    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Patron> getAllPatrons() {
        return repo.findAll();
    }

    public ResponseEntity<?> getPatron(Long id) {
        Optional<Patron> patron = repo.findById(id);
        if (patron.isPresent()) {
            return ResponseEntity.ok(patron.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Patron with ID " + id + " not found."));
        }
    }

    public ResponseEntity<ApiResponse> addPatron(Patron patron) {
        repo.save(patron);
        return ResponseEntity.ok(new ApiResponse("Patron has been successfully added."));
    }


    public ResponseEntity<ApiResponse> deletePatron(Long id) {
        Optional<Patron> patron = repo.findById(id);
        if (patron.isPresent()) {
            BorrowService borrowService = new BorrowService(borrowRepository, patronRepository, bookRepository);
            borrowService.deleteBorrowingRecordByPatronId(id);
            repo.deleteById(id);
            return ResponseEntity.ok(new ApiResponse("Patron with ID " + id + " has been successfully deleted."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Patron with ID " + id + " not found."));
        }
    }


    public ResponseEntity<ApiResponse> updatePatron(Long id, Patron patron) {
        Patron existingPatron = repo.findById(id).orElse(null);
        if (existingPatron != null) {
            if (patron.getName() != null) {
                existingPatron.setName(patron.getName());
            }
            if (patron.getEmail() != null) {
                existingPatron.setEmail(patron.getEmail());
            }
            if (patron.getPhone() != null) {
                existingPatron.setPhone(patron.getPhone());
            }
            if (patron.getAddress() != null) {
                existingPatron.setAddress(patron.getAddress());
            }
            repo.save(existingPatron);
            return ResponseEntity.ok(new ApiResponse("Patron with ID " + id + " has been successfully updated."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Patron with ID " + id + " not found."));
        }
    }
}
