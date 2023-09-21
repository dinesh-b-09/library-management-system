package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.dto.requestDTO.BookRequest;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.model.Book;

public class BookTransformer
{
    public static BookResponse BookToBookResponse(Book book)
    {
        return BookResponse.builder()
                .title(book.getTitle())
                .cost(book.getCost())
                .genre(book.getGenre())
                .noOfPages(book.getNoOfPages())
                .authorName(book.getAuthor().getName())
                .genre(book.getGenre())
                .build();
    }

//    public static Book BookRequestToBook(BookRequest bookRequest)
//    {
//        return Book.builder()
//                .title(bookRequest.getTitle())
//                .noOfPages(bookRequest.getNoOfPages())
//                .cost(bookRequest.getCost())
//                .noOfPages(bookRequest.getNoOfPages())
//                .author(bookRequest.getAuthorId())
//                .build();
//    }
}
