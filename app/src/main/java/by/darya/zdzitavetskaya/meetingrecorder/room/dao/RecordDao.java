package by.darya.zdzitavetskaya.meetingrecorder.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insert(Record record);

    @Update
    Completable update(Record record);

    @Delete
    Completable delete(Record record);

    @Query("SELECT * FROM record")
    Single<List<Record>> getAllRecords();

    @Query("SELECT * FROM record WHERE id=:id")
    Single<Record> getRecordById(final Long id);
}
