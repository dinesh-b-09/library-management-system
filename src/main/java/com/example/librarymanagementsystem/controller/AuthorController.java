package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.requestDTO.AuthorRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController
{
    @Autowired
    AuthorService authorService;

    @PostMapping("/add-author")
    public ResponseEntity addAuthor(@RequestBody AuthorRequest authorRequest)
    {
        AuthorResponse authorResponse = authorService.addAuthor(authorRequest);
        return new ResponseEntity(authorResponse, HttpStatus.CREATED);
    }

    @PutMapping("update-author-emailId")
    public ResponseEntity updateEmail(@RequestParam("id") int id, @RequestParam("emailId") String emailId)
    {
        try
        {
            AuthorResponse response = authorService.updateEmail(id, emailId);
            return new ResponseEntity(response, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    // give the names of all books written by a particular author
    @GetMapping("/get-all-books-by-author/{id}")
    public ResponseEntity getAllBooksByAuthor(@PathVariable("id") int authorId)
    {
        try
        {
            List<BookResponse> list = authorService.getAllBooksByAuthor(authorId);
            return new ResponseEntity(list, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    //give the names of authors who have written more than 'x' no. of books
    @GetMapping("author-names-wrote-more-than-x-books/{x}")
    public ResponseEntity authorWrittenXBooks(@PathVariable("x") int x)
    {
        List<AuthorResponse> list = authorService.authorWrittenXBooks(x);

        if(list.isEmpty())
        {
            return new ResponseEntity("No Author has more than "+x+" books.", HttpStatus.OK);
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }

}
