package by.darya.zdzitavetskaya.meetingrecorder.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import by.darya.zdzitavetskaya.meetingrecorder.room.model.Word;

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Update
    void update(Word... words);

    @Delete
    void delete(Word... words);

    @Query("SELECT * FROM word WHERE recordId=:recordId")
    List<Word> findWordsForRecord(final int recordId);
}
