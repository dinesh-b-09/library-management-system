package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByGender(Gender gender); // to find if attribute is not primary-key
    // id is pk, so we can directly search for find() in service layer
    Student findByEmail(String email);

    Student findByGenderAndEmail(Gender gender, String email);
}
