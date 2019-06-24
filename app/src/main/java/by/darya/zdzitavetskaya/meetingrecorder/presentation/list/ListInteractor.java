package by.darya.zdzitavetskaya.meetingrecorder.presentation.list;

import java.util.List;

import by.darya.zdzitavetskaya.meetingrecorder.App;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import io.reactivex.Single;

public final class ListInteractor{

    public Single<List<Record>> getAllRecordsFromDatabase() {
        return App.getAppDatabase().getRecordDao().getAllRecords();
    }

//    public Observable<Long> insertRecord(final Record record) {
//        return Observable.fromCallable(() -> App.getAppDatabase().getRecordDao().insert(record));
//    }
}
