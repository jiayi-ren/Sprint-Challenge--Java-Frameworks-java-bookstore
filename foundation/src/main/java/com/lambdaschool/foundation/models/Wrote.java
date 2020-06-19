package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "wrotes", uniqueConstraints = {@UniqueConstraint(columnNames = {"bookid", "authorid"})})
public class Wrote extends Auditable implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "authorid")
    @JsonIgnoreProperties(value = "books", allowSetters = true)
    private Author author;

    @Id
    @ManyToOne
    @JoinColumn(name = "bookid")
    @JsonIgnoreProperties(value = "authors", allowSetters = true)
    private Book book;

    public Wrote() {
    }

    public Wrote(Author author, Book book) {
        this.author = author;
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wrote wrote = (Wrote) o;
        return author.equals(wrote.author) &&
                book.equals(wrote.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, book);
    }
}
