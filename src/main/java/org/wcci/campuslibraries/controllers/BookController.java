package org.wcci.campuslibraries.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wcci.campuslibraries.resources.Author;
import org.wcci.campuslibraries.resources.Book;
import org.wcci.campuslibraries.resources.Campus;
import org.wcci.campuslibraries.storage.BookRepository;
import org.wcci.campuslibraries.storage.CampusStorage;

@RestController
public class BookController {
    private BookRepository bookRepo;
    private CampusStorage campusStorage;

    public BookController(BookRepository bookRepo, CampusStorage campusStorage) {
        this.bookRepo = bookRepo;
        this.campusStorage = campusStorage;
    }

    @PostMapping("/api/books")
    public Campus addBook(@RequestBody Book bookToAdd){
        System.out.println(bookToAdd.getCampus());
        campusStorage.saveCampus(bookToAdd.getCampus());
        Book book = new Book(bookToAdd.getCampus(), bookToAdd.getTitle(), bookToAdd.getSummary(), true, bookToAdd.getAuthors().toArray(new Author[0]));
        bookRepo.save(book);
        return bookToAdd.getCampus();
    }
}
