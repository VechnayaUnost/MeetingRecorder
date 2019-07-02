package by.darya.zdzitavetskaya.meetingrecorder.presentation.record;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import by.darya.zdzitavetskaya.meetingrecorder.R;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.BaseFragment;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.list.ListFragment;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.meeting.MeetingFragment;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import moxy.presenter.InjectPresenter;

public class RecordFragment extends BaseFragment implements RecordView{

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean permissionToRecordAccepted = false;
    private final String [] permissions = {Manifest.permission.RECORD_AUDIO};

    @InjectPresenter
    RecordPresenter recordPresenter;

    @BindView(R.id.et_meeting_speech)
    EditText etMeetingSpeech;

    @BindView(R.id.btn_start)
    ImageButton btnStart;

    @BindView(R.id.btn_pause)
    ImageButton btnPause;

    @BindView(R.id.btn_save)
    Button btnSave;

    @BindView(R.id.et_meeting_title)
    EditText etMeetingTitle;

    private SpeechRecognizer speech;
    private Intent recognizerIntent;
    private boolean isListening = false;

    private AudioManager audioManager;
    private int streamVolume = 0;

    private final StringBuilder str = new StringBuilder();
    private Record record;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCompat.requestPermissions(getActivity(), permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        setupRecognitionListener();
        createIntent();

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public int getLayoutFragment() {
        return R.layout.fragment_record;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnPause.setEnabled(false);
        recordPresenter.setButtonEnabled(etMeetingTitle, etMeetingSpeech);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
        if (!permissionToRecordAccepted ) {
            getFragmentManager().beginTransaction().replace(R.id.fl_main_container, new ListFragment()).commit();
        }
    }

    private void setupRecognitionListener() {
        speech = SpeechRecognizer.createSpeechRecognizer(getContext());
        speech.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, streamVolume, 0);
            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {
                if (isListening)
                    recordStart();
            }

            @Override
            public void onError(int error) {
                String message;

                switch (error) {
                    case SpeechRecognizer.ERROR_AUDIO:
                        message = "audio";
                        break;
                    case SpeechRecognizer.ERROR_CLIENT:
                        message = "client";
                        break;
                    case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                        message = "insufficient permissions";
                        break;
                    case SpeechRecognizer.ERROR_NETWORK:
                        message = "network";
                        break;
                    case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                        message = "network timeout";
                        break;
                    case SpeechRecognizer.ERROR_NO_MATCH:
                        message = "no match found";
                        break;
                    case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                        message = "recognizer busy";
                        break;
                    case SpeechRecognizer.ERROR_SERVER:
                        message = "server";
                        break;
                    case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                        message = "speech timeout";
                        break;
                    default:
                        message = "error understand";
                        break;
                }
                Log.d("error ", message);
                if (isListening)
                    recordStart();
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                if (matches != null) {
                    str.append(matches.get(0)).append(" ");
                    etMeetingSpeech.setText(str);
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
    }

    private void createIntent() {
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 1000000000);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 1000000000);
    }

    @OnClick(R.id.btn_start)
    void recordStart() {
        isListening = true;
        speech.startListening(recognizerIntent);
        streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
        btnStart.setEnabled(false);
        btnPause.setEnabled(true);
    }

    @OnClick(R.id.btn_pause)
    void recordStop() {
        isListening = false;
        speech.stopListening();
        btnStart.setEnabled(true);
        btnPause.setEnabled(false);
    }

    @OnClick(R.id.btn_save)
    void saveRecord() {
        isListening = false;
        speech.stopListening();
        btnStart.setEnabled(true);
        btnPause.setEnabled(false);

        record = new Record();
        record.setDate(new Date().toString());
        record.setText(etMeetingSpeech.getText().toString());
        record.setTitle(etMeetingTitle.getText().toString());

        recordPresenter.insertRecord(record);
    }

    @Override
    public void onRecordSaved(Long id) {
        if (getFragmentManager() != null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_main_container, MeetingFragment.newInstance(id))
                    .commit();
        }
    }

    @Override
    public void buttonEnabled(Boolean enabled) {
        btnSave.setEnabled(enabled);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        speech.destroy();
    }
}
