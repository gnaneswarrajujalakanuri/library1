package com.library.library1.Repository;

import com.library.library1.entity.BookAllotment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface BookAllotmentRepo extends JpaRepository<BookAllotment, Long> {

    @Query("select ba.ba_id,ba.b_id,ba.owner_id,ba.allot_date,ba.return_date from BookAllotment ba where ba.allot_date= :allot_date")
    List<BookAllotment> findByAllot_date(Date allot_date);

    @Query("select ba.ba_id,ba.b_id,ba.owner_id,ba.allot_date,ba.return_date from BookAllotment ba where ba.return_date= :return_date")
    List<BookAllotment> findByReturn_date(Date return_date);

    @Query(value = "select ba.ba_id,ba.b_id,ba.owner_id,ba.allot_date,ba.return_date from BookAllotment ba where ba.b_id= :b_id",nativeQuery = true)
    BookAllotment findByB_id(long b_id);

    @Query(value = "select ba.ba_id,ba.b_id,ba.owner_id,ba.allot_date,ba.return_date from BookAllotment ba where ba.owner_id= :owner_id",nativeQuery = true)
    List<BookAllotment> findByOwner_id(long owner_id);

    @Transactional
    @Modifying
    @Query("DELETE FROM BookAllotment ba WHERE ba.b_id = :b_id")
    void deleteBookAllotmentByB_id(long b_id);
}
