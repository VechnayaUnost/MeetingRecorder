package by.darya.zdzitavetskaya.meetingrecorder.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import by.darya.zdzitavetskaya.meetingrecorder.room.model.Word;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insert(Word word);

    @Update
    Completable update(Word word);

    @Delete
    Completable delete(Word word);

    @Query("SELECT * FROM word WHERE recordId=:recordId")
    Single<List<Word>> findWordsForRecord(final Long recordId);
}
