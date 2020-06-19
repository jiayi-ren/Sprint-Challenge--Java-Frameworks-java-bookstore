package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.models.Book;
import com.lambdaschool.foundation.views.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Query(value = "SELECT COUNT(*) as count FROM wrotes WHERE bookid = :bookid AND authorid = :authorid",
            nativeQuery = true)
    JustTheCount checkWroteCombo(
            long bookid,
            long authorid);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM wrotes WHERE bookid = :bookid AND authorid = :authorid", nativeQuery = true)
    void deleteWrote(
            long bookid,
            long authorid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO wrotes(bookid, authorid, created_by, created_date, last_modified_by, last_modified_date) VALUES (:bookid, :authorid, :uname, CURRENT_TIMESTAMP, :uname, CURRENT_TIMESTAMP)",
            nativeQuery = true)
    void insertWrote(
            String uname,
            long bookid,
            long authorid);
}
