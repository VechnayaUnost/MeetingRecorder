package by.darya.zdzitavetskaya.meetingrecorder.presentation.list;

import java.util.List;

import javax.inject.Inject;

import by.darya.zdzitavetskaya.meetingrecorder.App;
import by.darya.zdzitavetskaya.meetingrecorder.room.dao.RecordDao;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import io.reactivex.Single;

public final class ListInteractor{

    @Inject
    RecordDao recordDao;

    public ListInteractor() {
        App.getAppComponent().inject(this);
    }

    public Single<List<Record>> getAllRecordsFromDatabase() {
        return recordDao.getAllRecords();
    }
}
