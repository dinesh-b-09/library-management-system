package com.example.librarymanagementsystem.dto.requestDTO;

import com.example.librarymanagementsystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest
{
    String name;
    int age;
    String email;
    Gender gender;
}
