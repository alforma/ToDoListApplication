package com.example.mysaltytodolist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TheListOfThings extends AppCompatActivity {

    private TheListOfThingsAdapterViewModel theListOfThingsAdapterViewModel;


    public static final String EXTRA_ID =
            "com.example.mysaltytodoList.EXTRA_ID";





    Button addItemButton;



    public static final String EXTRA_REPLY = "com.example.mysaltytodolist.REPLY";

    private EditText mEditToDoName;

    private int position;
    ProjectDatabase db;
    int toDoItemId;
    public static int numToDoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_list_of_things);


        theListOfThingsAdapterViewModel = new ViewModelProvider(this).get(TheListOfThingsAdapterViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Add/Edit To Do Details");

        mEditToDoName = findViewById(R.id.editTextItemName);




        if (getIntent().getStringExtra("toDoItemName") != null) {
            mEditToDoName.setText(getIntent().getStringExtra("toDoItemName"));

        }






        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Intent replyIntent = new Intent();
        String toDoItemName = mEditToDoName.getText().toString();
       replyIntent.putExtra("toDoItemName", toDoItemName);


        if (getIntent().getStringExtra("toDoItemName") != null) {
            int toDoItemID = getIntent().getIntExtra("toDoItemID", 0);

            ToDoItem toDoItem= new ToDoItem(toDoItemID,  toDoItemName);
            theListOfThingsAdapterViewModel.insert(toDoItem);
        }
        setResult(RESULT_OK, replyIntent);
        finish();
    }

});



















    }




    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }












}

