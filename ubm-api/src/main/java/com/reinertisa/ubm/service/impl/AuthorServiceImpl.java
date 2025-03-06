package com.reinertisa.ubm.service.impl;

import com.reinertisa.ubm.dtorequest.AuthorNameOptions;
import com.reinertisa.ubm.dtorequest.dto.AuthorDto;
import com.reinertisa.ubm.dtorequest.request.AuthorRequest;
import com.reinertisa.ubm.mapper.AuthorMapper;
import com.reinertisa.ubm.entity.*;
import com.reinertisa.ubm.repository.AuthorRepository;
import com.reinertisa.ubm.service.AuthorService;
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

    @Override @Transactional
    public AuthorDto createAuthor(@Valid AuthorRequest authorRequest) {
        AuthorEntity authorEntity = authorMapper.toEntityFromRequest(authorRequest);
        AddressEntity addressEntity = authorEntity.getAddress();
        addressEntity.setAuthor(authorEntity);

        authorRepository.save(authorEntity);
        return authorMapper.toDtoFromEntity(authorEntity);
    }

    @Override @Transactional
    public void deleteAuthor(Long id) {
        authorRepository.findById(id).ifPresent(authorRepository::delete);
    }
}
