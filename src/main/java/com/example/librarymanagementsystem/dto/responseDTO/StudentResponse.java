package com.example.librarymanagementsystem.dto.responseDTO;

import com.example.librarymanagementsystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class StudentResponse
{
    String name;
    String email;
    int age;

    Gender gender;

   // String message;

  //  String cardIssuedNo; - we are giving library card response dto attributes
    LibraryCardResponse libraryCardResponse;   // nested dto

}
