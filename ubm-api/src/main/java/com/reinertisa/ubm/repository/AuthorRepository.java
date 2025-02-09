package com.reinertisa.ubm.repository;

import com.reinertisa.ubm.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a.id, a.firstName, a.lastName from Author a")
    List<Object[]> findAllAuthorNames();
}
