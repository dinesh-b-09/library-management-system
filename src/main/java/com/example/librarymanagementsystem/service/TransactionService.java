package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.responseDTO.IssueBookResponse;
import com.example.librarymanagementsystem.exception.BookNotAvailableException;
import com.example.librarymanagementsystem.exception.StudentNotFoundException;
import com.example.librarymanagementsystem.exception.TransactionNotFoundException;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.model.Transaction;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.repository.TransactionRepository;
import com.example.librarymanagementsystem.transformer.TransactionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService
{
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public IssueBookResponse issueBook(int bookId, int studentId)
    {

        //check if student is present or not
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isEmpty())
        {
            throw new StudentNotFoundException("Invalid student id!!");
        }

        // check if book is available or not
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty())
        {
            throw new BookNotAvailableException("Invalid book id");
        }

        Book book = optionalBook.get();
        if(book.isIssued()){
            throw new BookNotAvailableException("Book already issued");
        }

        // if student is present and book also available then issue the book
        Student student = studentOptional.get();

        // create transaction
        Transaction transaction = TransactionTransformer.prepareTransaction(student, book);

        //save transaction
        Transaction savedTransaction = transactionRepository.save(transaction);

        // update book
        book.setIssued(true);
        book.getTransactions().add(savedTransaction);

        // card changes
        student.getLibraryCard().getTransactions().add(savedTransaction);

        Book savedBook = bookRepository.save(book);  // book and transaction
        Student savedStudent = studentRepository.save(student);  // student and transaction


        // prepare response
        return TransactionTransformer.prepareResponse(savedStudent, savedBook, savedTransaction);

    }

    public String returnBook(int id)
    {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if(optionalTransaction.isEmpty())
        {
            throw new TransactionNotFoundException("Invalid Transaction Id !!");
        }

        // get transaction
        Transaction transaction = optionalTransaction.get();

        // get book and library card
        Book book = transaction.getBook();
        LibraryCard libraryCard = transaction.getLibraryCard();

        //set book false and remove transaction from parent tables i.e- book and library card
        book.setIssued(false);
        book.getTransactions().remove(transaction);
        libraryCard.getTransactions().remove(transaction);

        //remove transaction from transaction repo
        transactionRepository.delete(transaction);

        // save book and library card and library card parent is student
        bookRepository.save(book);
        studentRepository.save(libraryCard.getStudent()); // saves both student and libraryCard

        return "Book returned Successfully";
    }

}
