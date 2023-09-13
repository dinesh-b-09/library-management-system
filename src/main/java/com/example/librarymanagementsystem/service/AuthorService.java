package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public String addAuthor(Author author)
    {
        authorRepository.save(author);
        return "Author saved Successfully";

    }

    public String updateEmail(int id, String emailId)
    {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if(authorOptional.isPresent())
        {
            Author author = authorRepository.findById(id).get();
            author.setEmailId(emailId);
            authorRepository.save(author);
            return "Email Updated Successfully";
        }
        return "Invalid Author Id !!";
    }
}
