package com.library.library1.controller;

import com.library.library1.entity.Book;
import com.library.library1.entity.BookAllotment;
import com.library.library1.serviece.BookAllotmentService;
import com.library.library1.serviece.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookAllotmentController {
    @Autowired
    private BookAllotmentService bookAllotmentService;
    @Autowired
    private BookService bookService;

    @GetMapping("/bookallotment/get/ba_id/{ba_id}")
    public Object getBookAllotment(@PathVariable Long ba_id) {
        Optional<BookAllotment> bookAllotment = null;
        try {
            bookAllotment = Optional.ofNullable(bookAllotmentService.getBookAllotmentByBa_id(ba_id));
            if (bookAllotment.isPresent()) {
                System.out.println(bookAllotment);
                return bookAllotment;
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("not found");
        }
        return "not found";
    }
    @GetMapping("/bookallotment/get/owner_id/{owner_id}")
    public Object getBookAllotmentsByOwnerId(@PathVariable Long owner_id) {
        List<BookAllotment> bookAllotments = null;
        try {
            bookAllotments = bookAllotmentService.getAllBookAllotmentByOwner_id(owner_id);
            if (bookAllotments != null) {
                System.out.println(bookAllotments);
                return bookAllotments;
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("not found");
        }
        return "no books taken to show";
    }
    @GetMapping("/bookallotment/get/all")
    public Object getAllBookAllotment() {
        List<BookAllotment> bookAllotments = null;
        try {
            bookAllotments = bookAllotmentService.getAllBookAllotments();
            if (bookAllotments != null) {
                System.out.println(bookAllotments);
                return bookAllotments;
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("no rows found");
        }
        return "no rows found";
    }
    @GetMapping("/bookallotment/get/b_id/{b_id}")
    public Object getBookAllotmentByB_id(@PathVariable Long b_id) {
        Optional<BookAllotment> bookAllotment = null;
        try {
            bookAllotment = Optional.ofNullable(bookAllotmentService.getAllBookAllotmentByB_id(b_id));
            if (bookAllotment.isPresent()) {
                System.out.println(bookAllotment);
                return bookAllotment;
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("not found");
        }
        return "not found";
    }
    @PostMapping("/bookallotment/save/all")
    public Object saveAllBookAllotment(@RequestBody List<BookAllotment> bookAllotments) {
        List<BookAllotment> savedBookAllotments = null;
        try {
            savedBookAllotments = bookAllotments;
            for (BookAllotment bookAllotment : bookAllotments) {
                Long owner_id = bookAllotment.getOwner_id();
                Long b_id = bookAllotment.getB_id();
                if (owner_id == null) {
                    return "owner_id should not be null";
                }
                if (b_id == null) {
                    return "b_id should not be null";
                }
                Book book = null;
                book = bookService.getBookById(b_id);
                if (book == null) {
                    return "book not available";
                }
                if (book.getOwner_id() != null) {
                    return "book already took by " + book.getOwner_id();
                }
            }
            if (bookAllotments != null) {
                bookAllotmentService.saveAllBookAllotments(savedBookAllotments);
                for (BookAllotment bookAllotment : savedBookAllotments){
                    Long b_id = bookAllotment.getB_id();
                    Book book = bookService.getBookById(b_id);
                    book.setOwner_id(bookAllotment.getOwner_id());
                    bookService.saveBook(book);
                }
                return bookAllotments;
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Book Allotments not saved");
        }
        return "Book Allotments not saved";
    }
    @DeleteMapping("/bookallotment/delete/ba_id/{ba_id}")
    public Object deleteBookAllotment(@PathVariable Long ba_id) {
        try {
            Book book = bookService.getBookById(bookAllotmentService.getBookAllotmentByBa_id(ba_id).getB_id());
            book.setOwner_id(null);
            bookService.saveBook(book);
            bookAllotmentService.deleteBookByB_Id(ba_id);
            System.out.println("deleted");
            return "deleted";
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("not found to delete");
        }
        return "not found to delete";
    }
    @DeleteMapping("/bookallotment/delete/b_id/{b_id}")
    public String deleteBookAllotmentByBId(@PathVariable Long b_id) {
        try {
            Book book = bookService.getBookById(b_id);
            book.setOwner_id(null);
            bookService.saveBook(book);
            bookAllotmentService.deleteAllBookAllotmentByB_id(b_id);
            System.out.println("deleted");
            return "deleted";
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("not found to delete");
        }
        return "not found to delete";
    }
}
