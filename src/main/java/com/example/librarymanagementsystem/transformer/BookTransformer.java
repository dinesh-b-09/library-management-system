package com.example.librarymanagementsystem.transformer;

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
                .build();
    }
}
