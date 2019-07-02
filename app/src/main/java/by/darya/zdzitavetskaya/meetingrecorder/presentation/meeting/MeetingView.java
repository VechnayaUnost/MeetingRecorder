package by.darya.zdzitavetskaya.meetingrecorder.presentation.meeting;

import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import moxy.MvpView;

public interface MeetingView extends MvpView {

    void onRecordSuccess(Record record);
    void updateList();
}
