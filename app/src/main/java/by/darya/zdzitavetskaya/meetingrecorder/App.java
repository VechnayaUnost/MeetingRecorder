package by.darya.zdzitavetskaya.meetingrecorder;

import android.app.Application;

import by.darya.zdzitavetskaya.meetingrecorder.room.AppDatabase;

public class App extends Application {

    private static AppDatabase appDatabase;

    @Override
    public void onCreate(){
        super.onCreate();

        appDatabase = AppDatabase.getDatabase(getApplicationContext());
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
