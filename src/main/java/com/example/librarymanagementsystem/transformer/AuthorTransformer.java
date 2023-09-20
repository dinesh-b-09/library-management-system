package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.dto.requestDTO.AuthorRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;
import com.example.librarymanagementsystem.model.Author;

public class AuthorTransformer
{
    public static Author AuthorRequesttoAuthor(AuthorRequest authorRequest)
    {
        return Author.builder()
                .name(authorRequest.getName())
                .emailId(authorRequest.getEmailId())
                .age(authorRequest.getAge())
                .build();
    }

    public static AuthorResponse AuthorToAuthorResponse(Author author)
    {
        return AuthorResponse.builder()
                .name(author.getName())
                .age(author.getAge())
                .emailId(author.getEmailId())
                .lastActivity(author.getLastActivity())
                .build();
    }
}
