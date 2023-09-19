package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class LibraryCard
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String cardNo;

    @Enumerated(EnumType.STRING)
    CardStatus cardStatus;

    @CreationTimestamp
    Date issueDate;

  //  @JsonIgnore -not a good practise to use it becos at the end it still returns model, so go for dto
    @OneToOne
    @JoinColumn
    Student student;

    @OneToMany(mappedBy = "libraryCard", cascade = CascadeType.ALL)
    List<Transaction> transactions = new ArrayList<>();

}
