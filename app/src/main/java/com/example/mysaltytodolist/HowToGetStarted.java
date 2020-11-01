package com.example.mysaltytodolist;

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

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class HowToGetStarted extends AppCompatActivity {
Button enterButton2;
TheListOfThingsAdapterViewModel theListOfThingsAdapterViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_get_started);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Today's Stuff I need to do");



        RecyclerView recyclerView = findViewById(R.id.addRecyclerView);
        final TheListOfThingsAdapter adapter = new TheListOfThingsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        theListOfThingsAdapterViewModel = new ViewModelProvider(this).get(TheListOfThingsAdapterViewModel.class);

        theListOfThingsAdapterViewModel.getAllToDoItems().observe(this, new Observer<List<ToDoItem>>() {
            @Override
            public void onChanged(@Nullable final List<ToDoItem> toDoItems) {

                adapter.setToDoItems(toDoItems);
            }
        });



        FloatingActionButton fab = findViewById(R.id.floatingActionButtonADD);
        theListOfThingsAdapterViewModel = new ViewModelProvider(this).get(TheListOfThingsAdapterViewModel.class);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HowToGetStarted.this, TheListOfThings.class);
                intent.putExtra("toDoItemID", theListOfThingsAdapterViewModel.lastID()+1);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
               theListOfThingsAdapterViewModel.delete(adapter.getToDoItem(viewHolder.getAdapterPosition()));
                Toast.makeText(HowToGetStarted.this, "BAM! It's done!", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);






    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            ToDoItem toDoItem = new ToDoItem(theListOfThingsAdapterViewModel.lastID() + 1, data.getStringExtra("toDoItemName"));

            theListOfThingsAdapterViewModel.insert(toDoItem);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    }
