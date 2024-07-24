package com.library.library1.controller;

import com.library.library1.entity.Book;
import com.library.library1.serviece.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/book/get/b_id/{b_id}")
    public Book getBook(@PathVariable Long b_id) {
        try {
            Book book = bookService.getBookById(b_id);
            if (book != null) {
                System.out.println(book);
                return book;
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("book not found");
        }
        return null;
    }
    @GetMapping("/book/get/all")
    public List<Book> getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            if (books != null) {
                System.out.println(books);
                return books;
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("books not found");
        }
        return null;
    }
    @GetMapping("/book/get/title")
    public Map getBookByTitle(@RequestParam String title) {
        try {
            List<Book> books = bookService.getAllBooks();
            Map<Long,String> bookTitles = new HashMap<Long,String>();
            Map<Long,String> booksResult = new HashMap<Long,String>();
            for (Book book : books) {
                bookTitles.put(book.getB_id(), book.getTitle());
            }
            for (Map.Entry<Long, String> entry : bookTitles.entrySet()) {
                if (entry.getValue().contains(title.toLowerCase()) || entry.getValue().contains(title.toUpperCase())) {
                    booksResult.put(entry.getKey(), entry.getValue());
                }
            }
            if (booksResult != null){
                System.out.println(booksResult);
                return booksResult;
            }

        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("books not found");
        }
        return null;
    }
    @PostMapping("/book/save")
    public String addBook(@RequestBody Book book) {
        if (book.getTitle() == null){
            System.out.println("title is mandatory");
            return "book not saved";
        }
        Book book1 = bookService.saveBook(book);
        System.out.println(book1);
        return "book saved" + book1.toString();
    }
    @PostMapping("/book/save/all")
    public String addBookAll(@RequestBody List<Book> books) {
        if (books != null) {
            List<Book> books1 = bookService.saveBooks(books);
            System.out.println(books1);
            return "books saved";
        }
        return "books not saved";
    }
    @DeleteMapping("/book/delete/{b_id}")
    public String deleteBook(@PathVariable Long b_id) {
        Optional<Book> book = null;
        try {
            book = Optional.ofNullable(bookService.getBookById(b_id));
            if (book.isPresent()) {
                bookService.deleteBookById(b_id);
                System.out.println(book + "deleted successfully");
                return "book deleted sucessfully";
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("book not found to delete");
        }
        return "book not found to delete";
    }

}
