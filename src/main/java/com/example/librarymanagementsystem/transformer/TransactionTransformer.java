package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.Enum.TransactionStatus;
import com.example.librarymanagementsystem.dto.responseDTO.IssueBookResponse;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.model.Transaction;

import java.util.UUID;

public class TransactionTransformer
{
    public static Transaction prepareTransaction(Student student, Book book)
    {
        return Transaction.builder()
                .transactionNo(String.valueOf(UUID.randomUUID()))
                .transactionStatus(TransactionStatus.SUCCESS)
                .book(book)
                .libraryCard(student.getLibraryCard())
                .build();
    }

    public static IssueBookResponse prepareResponse(Student student, Book book, Transaction transaction)
    {
        return IssueBookResponse.builder()
            .bookName(book.getTitle())
            .transactionStatus(transaction.getTransactionStatus())
            .transactionTime(transaction.getTransactionTime())
            .transactionNo(transaction.getTransactionNo())
            .studentName(student.getName())
            .libraryCardNumber(student.getLibraryCard().getCardNo())
            .authorName(book.getAuthor().getName())
            .build();
    }
}
