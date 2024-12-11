package com.reinertisa.ubm.service;

import com.reinertisa.ubm.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAllAuthors();

    Author createAuthor(Author author);

    void deleteAuthor(Long id);

}
