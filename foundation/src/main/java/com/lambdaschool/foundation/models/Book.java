package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    @NotNull
    @Column(nullable = false)
    private String booktitle;

    @NotNull
    @Column(nullable = false, unique = true)
    private String ISBN;

    private int copy;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "book", allowSetters = true)
    private List<Wrote> wrotes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "sectionid",
            nullable = false)
    @JsonIgnoreProperties(value = "books",
            allowSetters = true)
    private Section section;

    public Book() {
    }

    public Book(@NotNull String booktitle, @NotNull String ISBN, int copy, Section section) {
        this.booktitle = booktitle;
        this.ISBN = ISBN;
        this.copy = copy;
        this.section = section;
    }

    public long getBookid() {
        return bookid;
    }

    public void setBookid(long bookid) {
        this.bookid = bookid;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }

    public List<Wrote> getWrotes() {
        return wrotes;
    }

    public void setWrotes(List<Wrote> wrotes) {
        this.wrotes = wrotes;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void addAuthor(Author author) {
        wrotes.add(new Wrote(author, this));
    }
}
