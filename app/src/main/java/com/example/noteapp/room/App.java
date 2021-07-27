package com.example.noteapp.room;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.noteapp.shared.PreferncesHelper;

public class App extends Application {
    public static TaskDatabase instance = null;
    public static Context context;


    public static TaskDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(context
                    , TaskDatabase.class, "note.taskdatabase")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        init_shp();
        getInstance();
    }

    private void init_shp() {
        PreferncesHelper preferncesHelper = new PreferncesHelper();
        preferncesHelper.unit(this);
    }
}
