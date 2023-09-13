package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController
{

    @Autowired
    BookService bookService;

    @PostMapping("/add-book")
    public ResponseEntity addBook(@RequestBody Book book)
    {
        try
        {
            String response = bookService.addBook(book);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            String response = e.getMessage();
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

}
