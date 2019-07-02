package by.darya.zdzitavetskaya.meetingrecorder.presentation.meeting;

import by.darya.zdzitavetskaya.meetingrecorder.App;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import io.reactivex.Completable;
import io.reactivex.Single;

public final class MeetingInteractor {

    public Single<Record> getRecordById(final Long id) {
        return App.getAppDatabase().getRecordDao().getRecordById(id);
    }

    public Completable updateRecord(final Record record) {
        return App.getAppDatabase().getRecordDao().update(record);
    }
}
