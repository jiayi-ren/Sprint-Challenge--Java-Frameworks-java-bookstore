package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.FoundationApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FoundationApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAllBooks() {
        assertEquals(5, bookService.findAllBooks().size());
    }

    @Test
    public void findBookById() {
        assertEquals("Flatterland", bookService.findBookById(76).getBooktitle());
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteWrote() {
    }

    @Test
    public void addWrote() {
    }
}