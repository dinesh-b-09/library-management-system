package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.dto.requestDTO.AuthorRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.transformer.AuthorTransformer;
import com.example.librarymanagementsystem.transformer.BookTransformer;
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


    public AuthorResponse addAuthor(AuthorRequest authorRequest)
    {
        Author author = AuthorTransformer.AuthorRequesttoAuthor(authorRequest);
        authorRepository.save(author);
        return AuthorTransformer.AuthorToAuthorResponse(author);

    }

    public AuthorResponse updateEmail(int id, String emailId)
    {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if(authorOptional.isEmpty())
        {
            throw new AuthorNotFoundException("Invalid author Id!!");
        }

        Author author = authorRepository.findById(id).get();
        author.setEmailId(emailId);
        authorRepository.save(author);
        return AuthorTransformer.AuthorToAuthorResponse(author);
    }

    public List<BookResponse> getAllBooksByAuthor(int authorId)
    {
        List<BookResponse> bookNames = new ArrayList<>();
        Optional<Author> authorOptional = authorRepository.findById(authorId);

        if(authorOptional.isEmpty())
        {
            throw new AuthorNotFoundException("Invalid author Id!!");
        }

        List<Book> books = bookRepository.findByAuthorId(authorId);
        for(Book book : books)
        {
            bookNames.add(BookTransformer.BookToBookResponse(book));
        }

        return bookNames;

    }


    public List<AuthorResponse> authorWrittenXBooks(int x)
    {
        List<Author> authorList = authorRepository.findAll();
        List<AuthorResponse> authorsMoreThanXBooks = new ArrayList<>();

        for(Author author : authorList)
        {
            if(author.getBooks().size() >= x)
            {
                authorsMoreThanXBooks.add(AuthorTransformer.AuthorToAuthorResponse(author));
            }
        }
        return authorsMoreThanXBooks;

    }
}
