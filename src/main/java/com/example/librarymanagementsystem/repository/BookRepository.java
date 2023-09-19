package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>
{
    List<Book> findByAuthorId(int authorId);

    List<Book> findByGenre(Genre genre);

    @Query(value = "select * from book where genre = :genre and cost > :cost", nativeQuery = true) //custom query or sql query
    List<Book> getBooksByGenreAndCostGreaterThan(String genre, double cost); // Genre is enum DB cant understand, so change to string

    @Query(value = "select b from Book b where b.genre = :genre and b.cost > :cost")  //HQL
    List<Book> getBooksByGenreAndCostGreaterThanHQL(Genre genre, double cost); // here we give genre as enum only
}
