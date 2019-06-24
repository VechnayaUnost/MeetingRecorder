package by.darya.zdzitavetskaya.meetingrecorder.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import io.reactivex.Single;

@Dao
public interface RecordDao {

    @Insert
    void insert(Record record);

    @Update
    void update(Record record);

    @Delete
    void delete(Record record);

    @Query("SELECT * FROM record")
    Single<List<Record>> getAllRecords();
}
