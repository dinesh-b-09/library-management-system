package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.Enum.Genre;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    int noOfPages;

    double cost;

    @Enumerated(EnumType.STRING)
    Genre genre;

    @ManyToOne
    @JoinColumn
    Author author;
}
