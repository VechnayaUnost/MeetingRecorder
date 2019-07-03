package by.darya.zdzitavetskaya.meetingrecorder.presentation.record;

import javax.inject.Inject;

import by.darya.zdzitavetskaya.meetingrecorder.App;
import by.darya.zdzitavetskaya.meetingrecorder.room.dao.RecordDao;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import io.reactivex.Single;

public final class RecordInteractor {

    @Inject
    RecordDao recordDao;

    public RecordInteractor() {
        App.getAppComponent().inject(this);
    }

    public Single<Long> insertRecord(final Record record) {
        return recordDao.insert(record);
    }
}
