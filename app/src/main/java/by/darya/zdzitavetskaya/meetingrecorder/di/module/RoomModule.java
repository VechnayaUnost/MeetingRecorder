package by.darya.zdzitavetskaya.meetingrecorder.di.module;

import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import by.darya.zdzitavetskaya.meetingrecorder.room.AppDatabase;
import by.darya.zdzitavetskaya.meetingrecorder.room.dao.RecordDao;
import by.darya.zdzitavetskaya.meetingrecorder.room.dao.WordDao;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private final AppDatabase appDatabase;

    public RoomModule(final Application application) {
        appDatabase = Room
                .databaseBuilder(application, AppDatabase.class, "app_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    AppDatabase providesRoomDatabase() {
        return appDatabase;
    }

    @Provides
    @Singleton
    RecordDao providesRecordDao(final AppDatabase appDatabase) {
        return appDatabase.getRecordDao();
    }

    @Provides
    @Singleton
    WordDao providesWordDao(final AppDatabase appDatabase) {
        return appDatabase.getWordDao();
    }
}
