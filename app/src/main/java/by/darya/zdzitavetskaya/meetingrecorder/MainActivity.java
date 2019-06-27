package by.darya.zdzitavetskaya.meetingrecorder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import by.darya.zdzitavetskaya.meetingrecorder.presentation.list.ListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fl_main_container, new ListFragment()).commit();
        }
    }
}
