package com.dailymood.tracker.daily.diary.database.async;

import android.os.AsyncTask;
import android.util.Log;

import com.dailymood.tracker.daily.diary.MyApplication;
import com.dailymood.tracker.daily.diary.database.crud.NoteDao;
import com.dailymood.tracker.daily.diary.database.table.NoteTable;
import com.dailymood.tracker.daily.diary.util.OperationType;

public class NoteAsync extends AsyncTask<Void, Void, Void> {

    NoteDao noteDao;
    NoteTable noteTable;
    String operation;

    public NoteAsync(NoteDao noteDao, NoteTable noteTable, String operation) {
        this.noteDao = noteDao;
        this.noteTable = noteTable;
        this.operation = operation;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (operation.equals(OperationType.Insert)) {
            MyApplication.noteId = noteDao.InsertNote(noteTable);
            } else if (operation.equals(OperationType.Delete)) {
            noteDao.DeleteNote(noteTable.getNote_id());
        }
        return null;
    }

}
