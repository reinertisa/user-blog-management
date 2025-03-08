package com.reinertisa.ubm.service.impl;

import com.reinertisa.ubm.dto.Author;
import com.reinertisa.ubm.dto.AuthorNameOptions;
import com.reinertisa.ubm.dtorequest.AuthorRequest;
import com.reinertisa.ubm.mapper.AuthorMapper;
import com.reinertisa.ubm.entity.*;
import com.reinertisa.ubm.repository.AuthorRepository;
import com.reinertisa.ubm.service.AuthorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackOn = Exception.class)
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<Author> getAllAuthors() {
        List<AuthorEntity> authorEntities = authorRepository.findAll();
        return authorMapper.toDtoListFromEntityList(authorEntities);
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

    @Override
    public Author createAuthor(@Valid AuthorRequest authorRequest) {
        AuthorEntity authorEntity = authorMapper.toEntityFromRequest(authorRequest);
        authorEntity.setAuthorId(UUID.randomUUID().toString());
        AddressEntity addressEntity = authorEntity.getAddress();
        addressEntity.setAuthor(authorEntity);

        authorRepository.save(authorEntity);
        return authorMapper.toDtoFromEntity(authorEntity);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.findById(id).ifPresent(authorRepository::delete);
    }
}
