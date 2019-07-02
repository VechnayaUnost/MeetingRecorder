package by.darya.zdzitavetskaya.meetingrecorder.presentation.record;

import by.darya.zdzitavetskaya.meetingrecorder.App;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import io.reactivex.Single;

public final class RecordInteractor {

    public Single<Long> insertRecord(final Record record) {
        return App.getAppDatabase().getRecordDao().insert(record);
    }
}
