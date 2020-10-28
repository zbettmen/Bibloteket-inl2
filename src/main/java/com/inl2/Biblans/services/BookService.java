package com.inl2.Biblans.services;


import com.inl2.Biblans.entities.Book;
import com.inl2.Biblans.reposotories.BookRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepository;


    @GetMapping
    public List<Book> findAll(String title){
        var books = bookRepository.findAll();

        if(title!=null){
            books = books.stream().filter(b -> b.getNames().toLowerCase()
                            .contains(title.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return books;
    }

    @PutMapping
    public Book save(Book book){
        return bookRepository.save(book);
    }


    public void update(String id, Book book){
        if(!bookRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        book.setId(id);
        bookRepository.save(book);
    }

    public Optional<Book> findById(String id){
        return bookRepository.findById(id);

    }




}
