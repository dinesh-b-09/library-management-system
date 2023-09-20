package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.LibraryCardResponse;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.model.Student;

import java.util.UUID;

public class StudentTransformer
{
    public static Student StudentRequestToStudent(StudentRequest studentRequest)
    {
        return Student.builder()
                .name(studentRequest.getName())
                .age(studentRequest.getAge())
                .email(studentRequest.getEmail())
                .gender(studentRequest.getGender())
                .build();
    }
    public static StudentResponse StudentToStudentResponse(Student student)
    {
        LibraryCardResponse cardResponse = LibraryCardTransformer.LibrarytoLibraryCardResponse(student.getLibraryCard());

        return StudentResponse.builder()
                .name(student.getName())
                .email(student.getEmail())
                .age(student.getAge())
                .gender(student.getGender())
                .libraryCardResponse(cardResponse)
                .build();
    }
}
