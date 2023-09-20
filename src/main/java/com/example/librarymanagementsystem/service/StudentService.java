package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.LibraryCardResponse;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.transformer.LibraryCardTransformer;
import com.example.librarymanagementsystem.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService
{

    @Autowired
    StudentRepository studentRepository;

    public StudentResponse addStudent(StudentRequest studentRequest)
    {

        // convert request dto to model
//        Student student = new Student();
//        student.setName(studentRequest.getName());
//        student.setAge(studentRequest.getAge());
//        student.setGender(studentRequest.getGender());
//        student.setEmail(studentRequest.getEmail());

        // convert request dto to model and create object using builder
        Student student = StudentTransformer.StudentRequestToStudent(studentRequest);

        //  give a library card and create object using builder
        LibraryCard libraryCard = LibraryCardTransformer.prepareLibraryCard();

        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard);  // set card library for student
        Student savedStudent = studentRepository.save(student); // save both student and library card as we used cascadeType.ALl


        return StudentTransformer.StudentToStudentResponse(savedStudent);
    }

    public StudentResponse getStudent(int regNo)
    {
        Optional<Student> optionalStudent = studentRepository.findById(regNo);
        if(optionalStudent.isPresent())
        {
            Student student = optionalStudent.get();
            return  StudentTransformer.StudentToStudentResponse(student);
        }
        return null;
    }

    public String deleteStudent(int regNo)
    {
        Optional<Student> optionalStudent = studentRepository.findById(regNo);
        if(optionalStudent.isPresent())
        {
            studentRepository.deleteById(regNo);
            return "Student Deleted Successfully";
        }
        return "Invalid RegNo";

    }

    public StudentResponse updateAge(int regNo, int age)
    {
        Optional<Student> optionalStudent = studentRepository.findById(regNo);
        if(optionalStudent.isPresent())
        {
            Student student = studentRepository.findById(regNo).get();
            student.setAge(age);
            studentRepository.save(student);
            return StudentTransformer.StudentToStudentResponse(student);
        }
        return null;
    }

    public List<StudentResponse> getAllStudents()
    {
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> studentResponses = new ArrayList<>();

        for(Student student : students)
        {
            studentResponses.add(StudentTransformer.StudentToStudentResponse(student));
        }
        return studentResponses;
    }


    public List<StudentResponse> getAllMaleStudents()
    {
        List<StudentResponse> maleStudentList = new ArrayList<>();
        List<Student> students = studentRepository.findByGender(Gender.MALE);
        // manually define own findBy in repo
        for(Student s: students)
        {
            maleStudentList.add(StudentTransformer.StudentToStudentResponse(s));
        }
        return maleStudentList;
    }
}
