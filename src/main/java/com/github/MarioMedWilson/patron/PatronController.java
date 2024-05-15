package com.github.MarioMedWilson.patron;

import com.github.MarioMedWilson.Response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patron")
public class PatronController {

    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping(path = "{patronId}")
    public ResponseEntity getPatron(@PathVariable("patronId") Long id) {
        return patronService.getPatron(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addPatron(@RequestBody Patron patron) {
        return patronService.addPatron(patron);
    }

    @DeleteMapping(path = "{patronId}")
    public ResponseEntity<ApiResponse> deletePatron(@PathVariable("patronId") Long id) {
        return patronService.deletePatron(id);
    }

    @PutMapping(path = "{patronId}")
    public ResponseEntity<ApiResponse> updatePatron(@PathVariable("patronId") Long id, @RequestBody Patron patron) {
        return patronService.updatePatron(id, patron);
    }

}
