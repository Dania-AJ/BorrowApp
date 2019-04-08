package com.session4.borrow.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
// TODO :  Use @Dao to tell Room that this is a DAO class.
@Dao
@TypeConverters(DateConverter.class)
public interface BorrowModelDao {
    // TODO : Write SQL query to all find a Borrowed

    @Query("select * from BorrowModel")
    LiveData<List<BorrowModel>> getAllBorrowedItems();

    // TODO : Write add borrow Function

    @Insert(onConflict = REPLACE)
    void addBorrow(BorrowModel borrowModel);

    @Delete
    void deleteBorrow(BorrowModel borrowModel);

}
