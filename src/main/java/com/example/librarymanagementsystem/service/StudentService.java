package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.Enum.Gender;
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

    public String addStudent(Student student)
    {
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard);  // set card library for student
        Student savedStudent = studentRepository.save(student); // save both student and library card as we used cascadeType.ALl
        return "Student Saved Successfully";
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
