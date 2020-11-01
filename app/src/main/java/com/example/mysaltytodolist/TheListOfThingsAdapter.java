package com.example.mysaltytodolist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



    public class TheListOfThingsAdapter extends RecyclerView.Adapter<TheListOfThingsAdapter.TheListOfThingsViewHolder> {


        class TheListOfThingsViewHolder extends RecyclerView.ViewHolder {

            private final TextView toDoListItemName;


            private TheListOfThingsViewHolder(View itemView) {
                super(itemView);


                toDoListItemName = itemView.findViewById(R.id.toDoNameTextView);


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();

                        final ToDoItem current =  toDoItems.get(position);
                        Intent intent = new Intent(context, TheListOfThings.class);
                        intent.putExtra("toDoItemName", current.getToDoItemName());
                        intent.putExtra("toDoItemID", current.getToDoItemID());
                        intent.putExtra("position", position);

                        context.startActivity(intent);
                    }
                });
            }
        }

        private final LayoutInflater mInflater;
        private final Context context;
        private List<ToDoItem> toDoItems;

        public TheListOfThingsAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
        }

        @Override
        public TheListOfThingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.the_list_of_things_item_list, parent, false);
            return new TheListOfThingsViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(TheListOfThingsViewHolder holder, int position) {

            if (toDoItems != null) {
               ToDoItem currentItem =  toDoItems.get(position);
                holder.toDoListItemName.setText(currentItem.getToDoItemName());



            }
        }


        public void setToDoItems(List<ToDoItem> toDoItems) {
            this.toDoItems = toDoItems;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if ( toDoItems != null)
                return  toDoItems.size();
            else return 0;
        }


        public ToDoItem getToDoItem(int position) {
            return toDoItems.get(position);
        }






    }


