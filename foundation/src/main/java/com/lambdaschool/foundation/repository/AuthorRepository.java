package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.models.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
