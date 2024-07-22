package com.library.library1.serviece;

import com.library.library1.Repository.BookAllotmentRepo;
import com.library.library1.entity.BookAllotment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookAllotmentService {
    @Autowired
    private BookAllotmentRepo bookAllotmentRepo;

    public BookAllotment getBookAllotmentByBa_id(Long ba_id) {
        return bookAllotmentRepo.findById(ba_id).get();
    }
    public List<BookAllotment> getAllBookAllotments() {
        return bookAllotmentRepo.findAll();
    }
    public List<BookAllotment> getAllBookAllotmentByAllot_date(Date allot_date) {
        return bookAllotmentRepo.findByAllot_date(allot_date);
    }
    public List<BookAllotment> getAllBookAllotmentByReturn_date(Date return_date) {
        return bookAllotmentRepo.findByReturn_date(return_date);
    }
    public BookAllotment getAllBookAllotmentByB_id(Long b_id){
        return bookAllotmentRepo.findByB_id(b_id);
    }
    public List<BookAllotment> getAllBookAllotmentByOwner_id(Long owner_id){
        return bookAllotmentRepo.findByOwner_id(owner_id);
    }
    public List<BookAllotment> saveAllBookAllotments(List<BookAllotment> bookAllotments) {
        return bookAllotmentRepo.saveAll(bookAllotments);
    }
    public void deleteBookByB_Id(Long ba_id) {
        bookAllotmentRepo.deleteById(ba_id);
    }
    public void deleteAllBookAllotmentByB_id(Long b_id) {
        bookAllotmentRepo.deleteBookAllotmentByB_id(b_id);
    }
}
