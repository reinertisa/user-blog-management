package com.reinertisa.ubm.service;

import com.reinertisa.ubm.dto.Author;
import com.reinertisa.ubm.dto.AuthorNameOptions;
import com.reinertisa.ubm.dtorequest.AuthorRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface AuthorService {

    List<Author> getAllAuthors();

    List<AuthorNameOptions> getAllAuthorNames();

    Author createAuthor(@Valid AuthorRequest authorRequest);

    void deleteAuthor(Long id);

}
