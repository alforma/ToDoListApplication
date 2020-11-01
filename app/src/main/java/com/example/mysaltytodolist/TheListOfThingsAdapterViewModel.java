package com.example.mysaltytodolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TheListOfThingsAdapterViewModel extends AndroidViewModel   {
        private ProjectRepository repository;
        private LiveData<List<ToDoItem>> allToDoItems;
        int toDoId;

    public TheListOfThingsAdapterViewModel(@NonNull Application application) {
        super(application);
        repository = new ProjectRepository(application);
        allToDoItems = repository.getAllToDoItems();
    }

        public LiveData<List<ToDoItem>> getAllToDoItems() {
        return allToDoItems;
    }

        public void insert(ToDoItem toDoItem) {
        repository.insert(toDoItem);
    }

        public void update(ToDoItem toDoItem) {
        repository.update(toDoItem);
    }

        public void delete(ToDoItem toDoItem) {
        repository.delete(toDoItem);
    }

        public void deleteAllToDoListItems() {
        repository.deleteAllToDoItems();
    }

        public int lastID(){
        return allToDoItems.getValue().size();
    }
}
