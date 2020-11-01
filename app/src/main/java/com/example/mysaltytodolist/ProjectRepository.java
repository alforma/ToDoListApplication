package com.example.mysaltytodolist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProjectRepository {

    private TheListOfThingsDAO theListOfThingsDAO;
    private LiveData<List<ToDoItem>> allToDoItems;
    public ProjectRepository(Application application) {
        ProjectDatabase database= ProjectDatabase.getDatabase(application);
        theListOfThingsDAO = database.theListOfThingsDAO();
        allToDoItems =theListOfThingsDAO.getAllToDoItems();
    }
    public void insert(ToDoItem toDoItem) {
        new InsertToDoItemsAsyncTask(theListOfThingsDAO ).execute(toDoItem);
    }
    public void update(ToDoItem toDoItem) {
        new UpdateToDoItemsAsyncTask(theListOfThingsDAO ).execute(toDoItem);
    }
    public void delete(ToDoItem toDoItem) {
        new DeleteToDoItemsAsyncTask(theListOfThingsDAO ).execute(toDoItem);
    }
    public void deleteAllToDoItems() {
        new DeleteAllToDoItemsAsyncTask(theListOfThingsDAO ).execute();
    }
    public LiveData<List<ToDoItem>> getAllToDoItems() {
        return allToDoItems;
    }



    private static class InsertToDoItemsAsyncTask extends AsyncTask<ToDoItem, Void, Void> {
        private TheListOfThingsDAO theListOfThingsDAO;
        private InsertToDoItemsAsyncTask(TheListOfThingsDAO theListOfThingsDAO) {
            this.theListOfThingsDAO = theListOfThingsDAO;
        }
        @Override
        protected Void doInBackground(ToDoItem... toDoItems) {
            theListOfThingsDAO.insert(toDoItems[0]);
            return null;
        }
    }



    private static class UpdateToDoItemsAsyncTask extends AsyncTask<ToDoItem, Void, Void> {
        private TheListOfThingsDAO theListOfThingsDAO;
        private UpdateToDoItemsAsyncTask(TheListOfThingsDAO theListOfThingsDAO) {
            this.theListOfThingsDAO = theListOfThingsDAO;
        }
        @Override
        protected Void doInBackground(ToDoItem... toDoItems) {
            theListOfThingsDAO.update(toDoItems[0]);
            return null;
        }
    }
    private static class DeleteToDoItemsAsyncTask extends AsyncTask<ToDoItem, Void, Void> {
        private TheListOfThingsDAO theListOfThingsDAO;
        private DeleteToDoItemsAsyncTask(TheListOfThingsDAO theListOfThingsDAO) {
            this.theListOfThingsDAO = theListOfThingsDAO;
        }
        @Override
        protected Void doInBackground(ToDoItem... toDoItems) {
            theListOfThingsDAO.delete(toDoItems[0]);
            return null;
        }
    }
    private static class DeleteAllToDoItemsAsyncTask extends AsyncTask<Void, Void, Void> {
        private TheListOfThingsDAO theListOfThingsDAO;
        private DeleteAllToDoItemsAsyncTask(TheListOfThingsDAO theListOfThingsDAO) {
            this.theListOfThingsDAO = theListOfThingsDAO;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            theListOfThingsDAO.deleteAllToDoItems();
            return null;
        }
    }
}