package com.session4.borrow.listItems;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.session4.borrow.db.AppDatabase;
import com.session4.borrow.db.BorrowModel;

import java.util.List;


public class BorrowedListViewModel extends AndroidViewModel {

    private final LiveData<List<BorrowModel>> itemAndPersonList;

    private AppDatabase appDatabase;

    public BorrowedListViewModel(Application application) {
        super(application);
      //  TODO: get an instance of our database using
        appDatabase = AppDatabase.getDatabase(this.getApplication());
      // TODO:  load the list of borrowed items from the database Hint : Use should use the query we defined in the DAO class via getAllBorrowedItems().
        itemAndPersonList = appDatabase.itemAndPersonModel().getAllBorrowedItems();
    }


    public LiveData<List<BorrowModel>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public void deleteItem(BorrowModel borrowModel) {
        new deleteAsyncTask(appDatabase).execute(borrowModel);
    }

    private static class deleteAsyncTask extends AsyncTask<BorrowModel, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final BorrowModel... params) {
            db.itemAndPersonModel().deleteBorrow(params[0]);
            return null;
        }

    }

}
