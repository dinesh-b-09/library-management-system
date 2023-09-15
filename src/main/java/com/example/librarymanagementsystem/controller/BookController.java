package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/delete-book")
    public ResponseEntity deleteBook(@RequestParam("id") int id)
    {
        String response = bookService.deleteBook(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //give names of all books of a particular genre
    @GetMapping("/get-books-genre")
    public ResponseEntity getBookNamesOfGenre(@RequestParam("genre") Genre genre)
    {
        List<String> list = bookService.getBookNamesOfGenre(genre);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //give names of all books particular genre and cost greater than 500
    @GetMapping("/get-books-genre-cost")
    public ResponseEntity getBookNamesOfGenreCost(@RequestParam("genre") Genre genre)
    {
        List<String> list = bookService.getBookNamesOfGenreCost(genre);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //give all books having no. of pages between 'x' and 'y'
    @GetMapping("/get-books-pages-btwn-XY")
    public ResponseEntity getBookNamesPagesXY(@RequestParam("x") int x, @RequestParam("y") int y)
    {
        List<String> list = bookService.getBookNamesPagesXY(x,y);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //give names of all authors who write particular genre
    @GetMapping("/get-author-name-genre")
    public ResponseEntity getAuthorNamesOfGenre(@RequestParam("genre") Genre genre)
    {
        List<String> list = bookService.getAuthorNamesOfGenre(genre);
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
