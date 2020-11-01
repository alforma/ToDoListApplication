package com.example.mysaltytodolist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "toDo_table")


public class ToDoItem {


    @PrimaryKey
    private int toDoItemID;

    private String toDoItemName;


    public int getToDoItemID() {
        return toDoItemID;
    }

    public void setToDoItemID(int toDoItemID) {
        this.toDoItemID = toDoItemID;
    }

    public String getToDoItemName() {
        return toDoItemName;
    }

    public void setToDoItemName(String toDoItemName) {
        this.toDoItemName = toDoItemName;
    }

    public ToDoItem(int toDoItemID, String toDoItemName) {
        this.toDoItemID = toDoItemID;
        this.toDoItemName = toDoItemName;
    }
}
