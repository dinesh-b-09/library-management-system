package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController
{
    @Autowired
    AuthorService authorService;

    @PostMapping("/add-author")
    public ResponseEntity addAuthor(@RequestBody Author author)
    {
        String response = authorService.addAuthor(author);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping("update-author-emailId")
    public ResponseEntity updateEmail(@RequestParam("id") int id, @RequestParam("emailId") String emailId)
    {
        String response = authorService.updateEmail(id, emailId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
