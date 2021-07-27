package com.example.noteapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class TaskModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public TaskModel(String txttitle) {
        this.txttitle = txttitle;
    }

    public String getTxttitle() {
        return txttitle;
    }

    public void setTxttitle(String txttitle) {
        this.txttitle = txttitle;
    }

    private String txttitle;

}
