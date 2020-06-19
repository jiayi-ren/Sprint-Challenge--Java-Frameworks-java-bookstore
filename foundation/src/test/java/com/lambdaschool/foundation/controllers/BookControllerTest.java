package com.lambdaschool.foundation.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.foundation.models.Author;
import com.lambdaschool.foundation.models.Book;
import com.lambdaschool.foundation.models.Section;
import com.lambdaschool.foundation.models.Wrote;
import com.lambdaschool.foundation.services.BookService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
public class BookControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private List<Book> bookList;

    @Before
    public void setUp() throws Exception {

        bookList = new ArrayList<>();

        Author a1 = new Author("John", "Mitchell");
        a1.setAuthorid(1);
        Author a2 = new Author("Dan", "Brown");
        a2.setAuthorid(2);
        Author a3 = new Author("Jerry", "Poe");
        a3.setAuthorid(3);
        Author a4 = new Author("Wells", "Teague");
        a4.setAuthorid(4);
        Author a5 = new Author("George", "Gallinger");
        a5.setAuthorid(5);
        Author a6 = new Author("Ian", "Stewart");
        a6.setAuthorid(6);

        Section s1 = new Section("Fiction");
        s1.setSectionid(1);
        Section s2 = new Section("Technology");
        s2.setSectionid(2);
        Section s3 = new Section("Travel");
        s3.setSectionid(3);
        Section s4 = new Section("Business");
        s4.setSectionid(4);
        Section s5 = new Section("Religion");
        s5.setSectionid(5);

        List<Wrote> wrote = new ArrayList<>();
        wrote.add(new Wrote(a6, new Book()));
        Book b1 = new Book("Flatterland", "9780738206752", 2001, s1);
        b1.setWrotes(wrote);
        b1.setBookid(1);
        bookList.add(b1);

        wrote = new ArrayList<>();
        wrote.add(new Wrote(a2, new Book()));
        Book b2 = new Book("Digital Fortess", "9788489367012", 2007, s1);
        b2.setWrotes(wrote);
        b2.setBookid(2);
        bookList.add(b2);

        wrote = new ArrayList<>();
        wrote.add(new Wrote(a2, new Book()));
        Book b3 = new Book("The Da Vinci Code", "9780307474278", 2009, s1);
        b3.setWrotes(wrote);
        b3.setBookid(3);
        bookList.add(b3);

        wrote = new ArrayList<>();
        wrote.add(new Wrote(a5, new Book()));
        wrote.add(new Wrote(a3, new Book()));
        Book b4 = new Book("Essentials of Finance", "1314241651234", 0, s4);
        b4.setWrotes(wrote);
        b4.setBookid(4);
        bookList.add(b4);

        wrote = new ArrayList<>();
        wrote.add(new Wrote(a4, new Book()));
        Book b5 = new Book("Calling Texas Home", "1885171382134", 2000, s3);
        b5.setWrotes(wrote);
        b5.setBookid(5);
        bookList.add(b5);

        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAllBooks() throws Exception {

        String apiUrl = "/books/books";

        Mockito.when(bookService.findAllBooks())
                .thenReturn(bookList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl)
                .accept(MediaType.APPLICATION_JSON);

        // the following actually performs a real controller call
        MvcResult r = mockMvc.perform(rb)
                .andReturn(); // this could throw an exception
        String tr = r.getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(bookList);

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void findBookById() throws Exception {

        String apiUrl = "/books/book/1";

        Mockito.when(bookService.findBookById(1))
                .thenReturn(bookList.get(1));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb)
                .andReturn(); // this could throw an exception
        String tr = r.getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(bookList.get(1));

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals("Rest API Returns List", er, tr);
    }
}