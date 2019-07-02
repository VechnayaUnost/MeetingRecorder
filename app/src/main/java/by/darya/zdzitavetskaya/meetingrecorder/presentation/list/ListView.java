package by.darya.zdzitavetskaya.meetingrecorder.presentation.list;

import java.util.List;

import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import moxy.MvpView;

interface ListView extends MvpView {

    void showList(List<Record> records);
}
