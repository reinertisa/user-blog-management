package com.reinertisa.ubm.service;

import com.reinertisa.ubm.model.AuthorDto;
import com.reinertisa.ubm.model.AuthorNameOptions;
import com.reinertisa.ubm.model.AuthorRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAllAuthors();

    List<AuthorNameOptions> getAllAuthorNames();

    AuthorDto createAuthor(@Valid AuthorRequest authorRequest);

    void deleteAuthor(Long id);

}
