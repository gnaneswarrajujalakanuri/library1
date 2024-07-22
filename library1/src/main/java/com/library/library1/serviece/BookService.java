package com.library.library1.serviece;

import com.library.library1.Repository.BookRepo;
import com.library.library1.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }
    public Book getBookById(Long id) {
        return bookRepo.findById(id).get();
    }
    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }
    public List<Book> saveBooks(List<Book> books) {
        return bookRepo.saveAll(books);
    }
    public Book updateBook(Book book) {
        return bookRepo.save(book);
    }
    public void deleteBookById(Long id) {
        bookRepo.deleteById(id);
    }
    public List<Book> getBooksByTitle(String title) {
        return bookRepo.findByTitle(title);
    }
}
