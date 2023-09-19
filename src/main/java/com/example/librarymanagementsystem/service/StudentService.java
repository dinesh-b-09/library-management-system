package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.LibraryCardResponse;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.repository.StudentRepository;
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

        //create object using builder
        Student student = Student.builder()
                .name(studentRequest.getName())
                .age(studentRequest.getAge())
                .email(studentRequest.getEmail())
                .gender(studentRequest.getGender())
                .build();


        // give a library card
//        LibraryCard libraryCard = new LibraryCard();
//        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
//        libraryCard.setCardStatus(CardStatus.ACTIVE);
//        libraryCard.setStudent(student);

        //create object using builder
        LibraryCard libraryCard = LibraryCard.builder()
                .cardNo(String.valueOf(UUID.randomUUID()))
                .cardStatus(CardStatus.ACTIVE)
                .student(student)
                .build();



        student.setLibraryCard(libraryCard);  // set card library for student
        Student savedStudent = studentRepository.save(student); // save both student and library card as we used cascadeType.ALl

        // saved model to response DTO  and
//        StudentResponse studentResponse = new StudentResponse();
//        studentResponse.setName(savedStudent.getName());
//        studentResponse.setEmail(savedStudent.getEmail());
//        studentResponse.setMessage("You have been Registered");
   //     studentResponse.setCardIssuedNo(savedStudent.getLibraryCard().getCardNo());

        // create object using builder
        StudentResponse studentResponse = StudentResponse.builder()
                .name(savedStudent.getName())
                .email(savedStudent.getEmail())
                .message("You have been registered")
                .build();

        // create object using builder
        LibraryCardResponse cardResponse = LibraryCardResponse.builder()
                        .cardNo(savedStudent.getLibraryCard().getCardNo())
                        .cardStatus((savedStudent.getLibraryCard().getCardStatus()))
                        .issueDate(savedStudent.getLibraryCard().getIssueDate())
                        .build();


        studentResponse.setLibraryCardResponse(cardResponse);  // save LibraryCardResponse in StudentResponse

        return studentResponse;
    }

    public Student getStudent(int regNo)
    {
        Optional<Student> optionalStudent = studentRepository.findById(regNo);
        if(optionalStudent.isPresent())
        {
            return  optionalStudent.get();
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

    public String updateAge(int regNo, int age)
    {
        Optional<Student> optionalStudent = studentRepository.findById(regNo);
        if(optionalStudent.isPresent())
        {
            Student student = studentRepository.findById(regNo).get();
            student.setAge(age);
            studentRepository.save(student);
            return "Age Updated Successfully";
        }
        return "Invalid RegNo given";
    }

    public List<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }


    public List<String> getAllMaleStudents()
    {
        List<String> maleStudentNames = new ArrayList<>();
        List<Student> students = studentRepository.findByGender(Gender.MALE);
        // manually define own findBy in repo
        for(Student s: students)
        {
            maleStudentNames.add(s.getName());
        }
        return maleStudentNames;
    }
}
