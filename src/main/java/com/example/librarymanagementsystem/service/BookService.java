package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;

import com.example.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    public String addBook(Book book)
    {
        // find whether author already exists or not
        Optional<Author> optionalAuthor = authorRepository.findById(book.getAuthor().getId());
        if(optionalAuthor.isEmpty())
        {
            throw new AuthorNotFoundException("Invalid author id!!!");
        }

        Author author = optionalAuthor.get();
        book.setAuthor(author);
        author.getBooks().add(book);

        authorRepository.save(author);  // save both author and book
        return "Book Added Successfully";


    }
}
