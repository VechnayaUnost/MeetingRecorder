package by.darya.zdzitavetskaya.meetingrecorder.presentation.meeting;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import by.darya.zdzitavetskaya.meetingrecorder.R;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.list.ListFragment;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class MeetingFragment extends MvpAppCompatFragment implements MeetingView{

    private Unbinder unbinder;
    private String text;
    private boolean isNewRecord = false;
    private Record record;

    @InjectPresenter
    MeetingPresenter meetingPresenter;

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.et_title)
    EditText etTitle;

    @BindView(R.id.et_text)
    EditText etText;


    public MeetingFragment() {
        // Required empty public constructor
    }

    public MeetingFragment(int id) {
        meetingPresenter.getRecord(id);
    }

    public MeetingFragment(String text) {
        this.text = text;
        isNewRecord = true;
    }


    @Override
    public View onCreateView(@NotNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_meeting, container, false);

        unbinder = ButterKnife.bind(this, view);
        if (isNewRecord)
            initFields();

        return view;
    }

    public void initFields() {
        etText.setText(text);
        tvDate.setText(new Date().toString());
    }

    @OnClick(R.id.floating_action_button)
    void onSaveBtnClick() {
        if (isNewRecord) {
            record = new Record();
            record.setTitle(etTitle.getText().toString());
            record.setText(etText.getText().toString());
            record.setDate(tvDate.getText().toString());

            meetingPresenter.insertRecord(record);
        } else {
            meetingPresenter.updateRecord(record);
        }
    }

    @Override
    public void onRecordSuccess(Record record) {
        this.record = record;
        tvDate.setText(record.getDate());
        etTitle.setText(record.getTitle());
        etText.setText(record.getText());
    }

    @Override
    public void updateList() {
        if (getFragmentManager() != null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_main_container, new ListFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
