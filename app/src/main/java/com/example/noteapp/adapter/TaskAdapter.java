package com.example.noteapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.R;
import com.example.noteapp.model.TaskModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

   public List<TaskModel> list = new ArrayList<>();

    public void  addText (TaskModel title) {
        list.add(title);
        notifyDataSetChanged();
    }
    public void  setetxt (List<TaskModel> models) {
        list.clear();
        this.list.addAll(models);
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.text_view.setText(list.get(position).getTxttitle());
    }

    @Override
    public int getItemCount(){
        if (list != null){
        return list.size();}
        else {
            return 0;
        }
    }


    public void filterList(ArrayList<TaskModel> filterList){
        list = filterList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_view;

        public ViewHolder(View itemView) {
            super(itemView);
            text_view = itemView.findViewById(R.id.item_title);
        }
    }
}
