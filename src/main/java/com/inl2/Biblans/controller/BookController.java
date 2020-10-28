package com.inl2.Biblans.controller;


import com.inl2.Biblans.entities.Book;
import com.inl2.Biblans.reposotories.BookRepo;
import com.inl2.Biblans.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepo bookRepo;

    @GetMapping("/findall")
    public ResponseEntity<List<Book>> findAllBooks(@RequestParam(required = false) String title){
        return ResponseEntity.ok(bookService.findAll(title));
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<Book> save(@RequestBody Book book){
        return ResponseEntity.ok(bookService.save(book));
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable String id, @RequestBody   Book book){
        bookService.update(id, book);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteBook(@PathVariable @RequestBody String id) {
        try{
            bookRepo.deleteById(id);
        } catch(NumberFormatException ex){

        }
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping("/borrow/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Optional<Book> borrow(@PathVariable String id){
        return bookRepo.findById(id).map(book -> {
            book.setAvailable(false);
            return bookRepo.save(book);
        });



    }
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping("/return/{id}")
    public Optional<Book> returnBook(@PathVariable String id){
        return bookRepo.findById(id).map(book -> {
            book.setAvailable(true);
            return bookRepo.save(book);
        });
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{id}")
   public Optional<Book> findBookById(@PathVariable String id){

        return bookService.findById(id);
   }

}
