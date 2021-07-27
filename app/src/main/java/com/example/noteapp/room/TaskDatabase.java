package com.example.noteapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.noteapp.model.TaskModel;

@Database(entities = TaskModel.class, version = 1, exportSchema = false)

public abstract class TaskDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();







}
