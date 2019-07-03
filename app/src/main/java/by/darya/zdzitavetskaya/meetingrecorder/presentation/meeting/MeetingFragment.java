package by.darya.zdzitavetskaya.meetingrecorder.presentation.meeting;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import by.darya.zdzitavetskaya.meetingrecorder.R;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.BaseFragment;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.list.ListFragment;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import by.darya.zdzitavetskaya.meetingrecorder.utils.AppConstants;
import moxy.presenter.InjectPresenter;

public class MeetingFragment extends BaseFragment implements MeetingView{

    private Record record;
    private Long id;

    @InjectPresenter
    MeetingPresenter meetingPresenter;

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.et_title)
    EditText etTitle;

    @BindView(R.id.et_text)
    EditText etText;

    public static MeetingFragment newInstance(final Long id) {
        final MeetingFragment meetingFragment = new MeetingFragment();

        final Bundle args = new Bundle();
        args.putLong(AppConstants.ARG_ID, id);
        meetingFragment.setArguments(args);

        return meetingFragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getLong(AppConstants.ARG_ID);
        }

        meetingPresenter.getRecord(id);
    }

    @Override
    public int getLayoutFragment() {
        return R.layout.fragment_meeting;
    }

    @OnClick(R.id.floating_action_button)
    void onSaveBtnClick() {
        record.setTitle(etTitle.getText().toString());
        record.setText(etText.getText().toString());

        meetingPresenter.updateRecord(record);
    }

    @Override
    public void onRecordSuccess(final Record record) {
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
                    .commit();
        }
    }
}
