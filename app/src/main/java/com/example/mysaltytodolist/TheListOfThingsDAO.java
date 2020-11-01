package com.example.mysaltytodolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface TheListOfThingsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ToDoItem toDoItem);

    @Update
    void update(ToDoItem toDoItem);

    @Delete
    void delete(ToDoItem toDoItem);

    @Query("DELETE FROM toDo_table")
    void deleteAllToDoItems();

    @Query ("SELECT * FROM toDo_table")
    LiveData<List<ToDoItem>> getAllToDoItems();

}

