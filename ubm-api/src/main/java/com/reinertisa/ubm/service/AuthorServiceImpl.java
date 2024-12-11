package com.reinertisa.ubm.service;

import com.reinertisa.ubm.model.Author;
import com.reinertisa.ubm.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override @Transactional
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override @Transactional
    public void deleteAuthor(Long id) {
        authorRepository.findById(id).ifPresent(authorRepository::delete);
    }
}
