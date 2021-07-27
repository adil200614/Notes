package com.example.noteapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.noteapp.model.TaskModel;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM taskmodel")
    LiveData<List<TaskModel>> getAll();

    @Insert
    void insertTask(TaskModel taskModel);
     @Delete
    void delete(TaskModel taskModel);

     @Update
    void update(TaskModel taskModel);
}
