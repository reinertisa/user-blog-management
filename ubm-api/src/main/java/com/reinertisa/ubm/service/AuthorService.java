package com.reinertisa.ubm.service;

import com.reinertisa.ubm.model.Author;
import com.reinertisa.ubm.model.AuthorDto;
import com.reinertisa.ubm.model.AuthorRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAllAuthors();

    AuthorDto createAuthor(@Valid AuthorRequest authorRequest);

    void deleteAuthor(Long id);

}
