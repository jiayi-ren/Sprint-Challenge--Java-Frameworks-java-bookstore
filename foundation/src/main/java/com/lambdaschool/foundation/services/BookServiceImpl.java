package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.Author;
import com.lambdaschool.foundation.models.Book;
import com.lambdaschool.foundation.models.Wrote;
import com.lambdaschool.foundation.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private UserAuditing userAuditing;

    @Override
    public List<Book> findAllBooks() {
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(bookList::add);
        return bookList;
    }

    @Override
    public Book findBookById(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book id " + id + " Not Found"));
    }

    @Transactional
    @Override
    public Book save(Book book) {
        Book newBook = new Book();

        newBook.setBooktitle(book.getBooktitle());
        newBook.setISBN(book.getISBN());
        newBook.setCopy(book.getCopy());
        newBook.setSection(book.getSection());

        newBook.getWrotes().clear();
        if (book.getBookid() == 0) {
            for (Wrote wrote : book.getWrotes()) {
                Author author = authorService.findAuthorById(wrote.getAuthor().getAuthorid());
                newBook.addAuthor(author);
            }
        }

        return bookRepository.save(newBook);
    }

    @Transactional
    @Override
    public void delete(long id) {
        bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book id " + id + " Not Found"));
        bookRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteWrote(long bookid, long authorid) {
        bookRepository.findById(bookid)
                .orElseThrow(() -> new ResourceNotFoundException("Book id " + bookid + " Not Found"));
        authorService.findAuthorById(authorid);

        if (bookRepository.checkWroteCombo(bookid,
                authorid)
                .getCount() > 0) {
            bookRepository.deleteWrote(bookid,
                    authorid);
        } else {
            throw new ResourceNotFoundException("Book and Author Combination Does Not Exists");
        }
    }

    @Override
    public void addWrote(long bookid, long authorid) {
        bookRepository.findById(bookid)
                .orElseThrow(() -> new ResourceNotFoundException("Book id " + bookid + " Not Found"));
        authorService.findAuthorById(authorid);

        if (bookRepository.checkWroteCombo(bookid,
                authorid)
                .getCount() > 0) {
            bookRepository.insertWrote(userAuditing.getCurrentAuditor()
                            .get(),
                    bookid,
                    authorid);
        } else {
            throw new ResourceNotFoundException("Book and Author Combination Does Not Exists");
        }
    }
}
