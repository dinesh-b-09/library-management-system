package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;

import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
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


    public String deleteBook(int id)
    {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isPresent())
        {
  //          Book book = bookRepository.findById(id).get();
//            Author author = book.getAuthor();
            bookRepository.deleteById(id);
   //         bookRepository.save(book);
            return "book deleted";
        }
        return "invalid id";
    }

    public List<String> getBookNamesOfGenre(Genre genre)
    {
        List<Book> booklist = bookRepository.findAll();
        List<String> list = new ArrayList<>();

        for(Book book : booklist)
        {
            if(book.getGenre() == genre)
            {
                list.add(book.getTitle());
            }
        }
        return list;

    }

    public List<String> getBookNamesOfGenreCost(Genre genre)
    {
        List<Book> booklist = bookRepository.findAll();
        List<String> list = new ArrayList<>();

        for(Book book : booklist)
        {
            if(book.getGenre() == genre && book.getCost() > 500)
            {
                list.add(book.getTitle());
            }
        }
        return list;
    }

    public List<String> getBookNamesPagesXY(int x, int y)
    {
        List<Book> booklist = bookRepository.findAll();
        List<String> list = new ArrayList<>();

        for(Book book : booklist)
        {
            if(book.getNoOfPages() >= x && book.getNoOfPages() <= y)
            {
                list.add(book.getTitle());
            }
        }
        return list;
    }

    public List<String> getAuthorNamesOfGenre(Genre genre)
    {
        List<String> list = new ArrayList<>();

        List<Book> booklist = bookRepository.findByGenre(genre); // custom fn in repo

        for(Book book : booklist)
        {
            if(!list.contains(book.getAuthor().getName()))
            {
                list.add(book.getAuthor().getName());
            }

        }
        return list;
    }
}
