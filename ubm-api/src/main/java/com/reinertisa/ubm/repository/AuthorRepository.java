package com.reinertisa.ubm.repository;

import com.reinertisa.ubm.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    @Query("select a.id, a.firstName, a.lastName from AuthorEntity a")
    List<Object[]> findAllAuthorNames();
}
