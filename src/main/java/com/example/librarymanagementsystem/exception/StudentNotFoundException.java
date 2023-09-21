package com.example.librarymanagementsystem.exception;

import com.example.librarymanagementsystem.model.Student;

public class StudentNotFoundException extends RuntimeException
{
        public StudentNotFoundException(String message)
        {
            super(message);
        }

}
