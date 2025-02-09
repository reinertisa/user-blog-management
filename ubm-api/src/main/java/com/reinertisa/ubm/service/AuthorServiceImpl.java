package com.reinertisa.ubm.service;

import com.reinertisa.ubm.model.*;
import com.reinertisa.ubm.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authorMapper.toDtoListFromEntityList(authors);
    }

    @Override
    public List<AuthorNameOptions> getAllAuthorNames() {
        List<Object[]> authorNames = authorRepository.findAllAuthorNames();
        List<AuthorNameOptions> authorNameOptions = new ArrayList<>();

        for (Object[] objects : authorNames) {
            Long id = Long.parseLong(objects[0].toString());
            String firstName = objects[1].toString();
            String lastName = objects[2].toString();
            AuthorNameOptions options = new AuthorNameOptions(id, firstName + " " + lastName);
            authorNameOptions.add(options);
        }
        return authorNameOptions;
    }

    @Override @Transactional
    public AuthorDto createAuthor(@Valid AuthorRequest authorRequest) {
        Author author = authorMapper.toEntityFromRequest(authorRequest);
        Address address = author.getAddress();
        address.setAuthor(author);

        authorRepository.save(author);
        return authorMapper.toDtoFromEntity(author);
    }

    @Override @Transactional
    public void deleteAuthor(Long id) {
        authorRepository.findById(id).ifPresent(authorRepository::delete);
    }
}
