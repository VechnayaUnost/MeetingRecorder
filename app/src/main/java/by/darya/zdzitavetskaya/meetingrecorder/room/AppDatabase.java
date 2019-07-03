package by.darya.zdzitavetskaya.meetingrecorder.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import by.darya.zdzitavetskaya.meetingrecorder.room.dao.RecordDao;
import by.darya.zdzitavetskaya.meetingrecorder.room.dao.WordDao;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Word;

@Database(entities = {Record.class, Word.class}, version = AppDatabase.VERSION)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RecordDao getRecordDao();
    public abstract WordDao getWordDao();

    static final int VERSION = 3;
}
