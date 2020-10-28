package com.inl2.Biblans.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;


@Data
public class Book implements Serializable {
    private static final long serialVersionUID = 913715112220L;
    @Id
    private String id;

    private String author;
    private String isbn;
    private String names;
    private String genre;
    private boolean isAvailable;

    public Book() {
    }


}
