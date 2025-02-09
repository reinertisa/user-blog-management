package com.reinertisa.ubm.controller;


import com.reinertisa.ubm.model.AuthorDto;
import com.reinertisa.ubm.model.AuthorRequest;
import com.reinertisa.ubm.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@CrossOrigin(origins = "*")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authorService.getAllAuthors());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @PostMapping("")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid AuthorRequest authorRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(authorRequest));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
