package by.darya.zdzitavetskaya.meetingrecorder.presentation.record;

import moxy.MvpView;

public interface RecordView extends MvpView {

    void onRecordSaved(Long id);
    void buttonEnabled(Boolean enabled);
}
