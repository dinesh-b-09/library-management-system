package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.controller.BookController;
import com.example.librarymanagementsystem.dto.requestDTO.BookRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;

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
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public BookResponse addBook(BookRequest bookRequest)
    {
        // find whether author already exists or not
    //    Book book = BookTransformer.BookRequestToBook(bookRequest);
        Optional<Author> optionalAuthor = authorRepository.findById(bookRequest.getAuthorId());
        if(optionalAuthor.isEmpty())
        {
            throw new AuthorNotFoundException("Invalid author id!!!");
        }

        Author author = optionalAuthor.get();
        Book book = Book.builder()
                .author(author)
                .title(bookRequest.getTitle())
                .noOfPages(bookRequest.getNoOfPages())
                .title(bookRequest.getTitle())
                .cost(bookRequest.getCost())
                .genre(bookRequest.getGenre())
                .issued(false)
                .build();

        author.getBooks().add(book);
        authorRepository.save(author);  // save both author and book
        return BookTransformer.BookToBookResponse(book);

    }


    public String deleteBook(int id)
    {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isEmpty())
        {
            throw new AuthorNotFoundException("Invalid author id!!!");

        }
        bookRepository.deleteById(id);
        return "book deleted";
    }

    public List<BookResponse> getBookOfGenre(Genre genre)
    {
        List<Book> booklist = bookRepository.findByGenre(genre);
        List<BookResponse> list = new ArrayList<>();

        for(Book book : booklist)
        {
//            if(book.getGenre() == genre)  -- did this using custom fn in book repo
//            {
//                list.add(book.getTitle());
//            }
            list.add(BookTransformer.BookToBookResponse(book));
        }
        return list;

    }

//    public List<String> getBookNamesOfGenreCost(Genre genre)
//    {
//        List<Book> booklist = bookRepository.findAll();
//        List<String> list = new ArrayList<>();
//
//        for(Book book : booklist)
//        {
//            if(book.getGenre() == genre && book.getCost() > 500)
//            {
//                list.add(book.getTitle());
//            }
//        }
//        return list;
//    }


    public List<BookResponse> getBooksByGenreAndCostGreaterThan(String genre, double cost) //SQL
    {

        List<Book> books = bookRepository.getBooksByGenreAndCostGreaterThan(genre,cost);

        // prepare the response. convert model to dto

        List<BookResponse> response = new ArrayList<>();
        for(Book book: books)
        {
            response.add(BookTransformer.BookToBookResponse(book));
        }

        return response;
    }


    public List<BookResponse> getBooksByGenreAndCostGreaterThanHQL(Genre genre, double cost) //HQL
    {

        List<Book> books = bookRepository.getBooksByGenreAndCostGreaterThanHQL(genre,cost);

        // prepare the response. convert model to dto
        List<BookResponse> response = new ArrayList<>();
        for(Book book: books)
        {
            response.add(BookTransformer.BookToBookResponse(book));
        }
        return response;
    }


    public List<BookResponse> getBookPagesXY(int x, int y)
    {
        List<Book> booklist = bookRepository.getBookPagesXY(x, y);
        List<BookResponse> list = new ArrayList<>();

        for(Book book : booklist)
        {
                list.add(BookTransformer.BookToBookResponse(book));
        }
        return list;
    }

    public List<AuthorResponse> getAuthorOfGenre(Genre genre)
    {
        List<AuthorResponse> list = new ArrayList<>();

        List<Book> booklist = bookRepository.findByGenre(genre); // custom fn in repo

        for(Book book : booklist)
        {
            if(!list.contains(AuthorTransformer.AuthorToAuthorResponse(book.getAuthor())))
            {

                list.add(AuthorTransformer.AuthorToAuthorResponse(book.getAuthor()));
            }

        }
        return list;
    }


}
