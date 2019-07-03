package by.darya.zdzitavetskaya.meetingrecorder.presentation.meeting;

import javax.inject.Inject;

import by.darya.zdzitavetskaya.meetingrecorder.App;
import by.darya.zdzitavetskaya.meetingrecorder.room.dao.RecordDao;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import io.reactivex.Completable;
import io.reactivex.Single;

public final class MeetingInteractor {

    @Inject
    RecordDao recordDao;

    public MeetingInteractor() {
        App.getAppComponent().inject(this);
    }

    public Single<Record> getRecordById(final Long id) {
        return recordDao.getRecordById(id);
    }

    public Completable updateRecord(final Record record) {
        return recordDao.update(record);
    }
}
