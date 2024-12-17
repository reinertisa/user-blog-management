package com.reinertisa.ubm.service;

import com.reinertisa.ubm.model.Address;
import com.reinertisa.ubm.model.Author;
import com.reinertisa.ubm.model.AuthorDto;
import com.reinertisa.ubm.model.AuthorRequest;
import com.reinertisa.ubm.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(author -> new AuthorDto(
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getEmail(),
                Period.between(author.getDob(), LocalDate.now()).getYears(),
                author.getGender(),
                author.getCreatedBy(),
                author.getUpdatedBy(),
                author.getAddress(),
                author.getBlogs()
        )).toList();
    }

    @Override @Transactional
    public AuthorDto createAuthor(@Valid AuthorRequest authorRequest) {
        Author author = new Author();
        author.setFirstName(authorRequest.getFirstName());
        author.setLastName(authorRequest.getLastName());
        author.setEmail(authorRequest.getEmail());
        author.setGender(authorRequest.getGender());
        author.setDob(authorRequest.getDob());
        author.setCreatedBy(LocalDate.now());

        Address address = new Address();
        address.setCity(authorRequest.getAddress().getCity());
        address.setState(authorRequest.getAddress().getState());
        address.setCountry(authorRequest.getAddress().getCountry());

        //bi-directional
        address.setAuthor(author);
        author.setAddress(address);

        authorRepository.save(author);

        return new AuthorDto(
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getEmail(),
                Period.between(author.getDob(), LocalDate.now()).getYears(),
                author.getGender(),
                author.getCreatedBy(),
                author.getUpdatedBy(),
                author.getAddress(),
                author.getBlogs()
        );
    }

    @Override @Transactional
    public void deleteAuthor(Long id) {
        authorRepository.findById(id).ifPresent(authorRepository::delete);
    }
}
