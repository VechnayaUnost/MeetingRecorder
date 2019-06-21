package by.darya.zdzitavetskaya.meetingrecorder.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import by.darya.zdzitavetskaya.meetingrecorder.room.dao.RecordDao;
import by.darya.zdzitavetskaya.meetingrecorder.room.dao.WordDao;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Word;

@Database(entities = {Record.class, Word.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RecordDao getRecordDao();
    public abstract WordDao getWordDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
