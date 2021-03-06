package com.example.sqlcoba1.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.sqlcoba1.dao.NoteDao;
import com.example.sqlcoba1.dao.UserDao;
import com.example.sqlcoba1.models.Note;
import com.example.sqlcoba1.models.User;

@Database(entities = {User.class, Note.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "notes";
    private static volatile  AppDatabase INSTANCE = null;

    public static AppDatabase getInstance(final Context context) {
        synchronized (AppDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room
                        .databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract UserDao userDao();

    public abstract NoteDao noteDao();
}
