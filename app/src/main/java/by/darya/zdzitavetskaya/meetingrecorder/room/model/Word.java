package by.darya.zdzitavetskaya.meetingrecorder.room.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Record.class,
        parentColumns = "id",
        childColumns = "recordId",
        onDelete = CASCADE,
        onUpdate = CASCADE))
public class Word {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private int position;
    private String word;
    private String color;
    private Long recordId;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(final int position) {
        this.position = position;
    }

    public String getWord() {
        return word;
    }

    public void setWord(final String word) {
        this.word = word;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(final Long recordId) {
        this.recordId = recordId;
    }
}
