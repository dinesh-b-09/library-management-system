package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;


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

    public List<String> getAllBooksByAuthor(int authorId)
    {
        List<String> bookNames = new ArrayList<>();
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if(authorOptional.isPresent())
        {

            List<Book> books = bookRepository.findByAuthorId(authorId);

            for(Book book : books)
            {
                bookNames.add(book.getTitle());
            }

        }
        return bookNames;

    }


    public List<String> authorWrittenXBooks(int x)
    {
        List<Author> authorList = authorRepository.findAll();
        List<String> authorsMoreThanXBooks = new ArrayList<>();

        for(Author author : authorList)
        {
            if(author.getBooks().size() > x)
            {
                authorsMoreThanXBooks.add(author.getName());
            }
        }
        return authorsMoreThanXBooks;

    }
}
