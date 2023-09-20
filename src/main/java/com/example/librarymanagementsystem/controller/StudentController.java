package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController
{
    @Autowired
    StudentService studentService;

//    @PostMapping("/add")
//    public ResponseEntity addStudent(@RequestBody Student student)
//    {
//        String response = studentService.addStudent(student);
//        return new ResponseEntity(response, HttpStatus.CREATED);
//    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody StudentRequest studentRequest)
    {
        StudentResponse response = studentService.addStudent(studentRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo)
    {
        StudentResponse studentResponse = studentService.getStudent(regNo);

        if(studentResponse != null)
        {
            return new ResponseEntity(studentResponse, HttpStatus.FOUND);
        }

        return  new ResponseEntity("Invalid Id !!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@RequestParam("id") int regNo)
    {
        String response = studentService.deleteStudent(regNo);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/update-age")
    public ResponseEntity updateAge(@RequestParam("id") int regNo,@RequestParam("age") int age)
    {
        StudentResponse studentResponse = studentService.updateAge(regNo, age);
        return new ResponseEntity(studentResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-all-students")
    public ResponseEntity getAllStudents()
    {
       List<StudentResponse> studentList = studentService.getAllStudents();
       return new ResponseEntity(studentList, HttpStatus.FOUND);
    }

    @GetMapping("all-male-students")
    public ResponseEntity getAllMaleStudents()
    {
        List<StudentResponse> maleslist = studentService.getAllMaleStudents();
        return new ResponseEntity(maleslist,HttpStatus.OK);
    }



}
