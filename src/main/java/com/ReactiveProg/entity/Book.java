package com.ReactiveProg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("book_details")
@Data
@NoArgsConstructor
public class Book {

    @Id
    @Column("book_id")
    private  int bookId;

    private  String name;

    @Column("book_desc")
    private  String description;

    private  String publisher;

    private  String author;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
