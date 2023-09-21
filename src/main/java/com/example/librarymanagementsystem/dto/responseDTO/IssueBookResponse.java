package com.example.librarymanagementsystem.dto.responseDTO;

import com.example.librarymanagementsystem.Enum.TransactionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class IssueBookResponse
{
    String transactionNo;

    Date transactionTime;

    TransactionStatus transactionStatus;

    String authorName;

    String bookName;

    String libraryCardNumber;

    String studentName;


}
