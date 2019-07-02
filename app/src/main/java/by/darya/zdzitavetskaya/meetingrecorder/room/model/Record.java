package by.darya.zdzitavetskaya.meetingrecorder.room.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Record {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String title;
    private String date;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
