package by.darya.zdzitavetskaya.meetingrecorder.presentation.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import by.darya.zdzitavetskaya.meetingrecorder.R;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.record.RecordFragment;
import moxy.MvpAppCompatDialogFragment;

public class NewRecordDialogFragment extends MvpAppCompatDialogFragment {

    private Unbinder unbinder;

    public NewRecordDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_new_record_dialog, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btn_OK)
    public void showNewRecordFragment(){
        unbinder.unbind();
        dismiss();
        getFragmentManager().beginTransaction().replace(R.id.fl_main_container, new RecordFragment()).commit();
    }

    @OnClick(R.id.btn_cancel)
    public void cancelDialog() {
        unbinder.unbind();
        dismiss();
    }
}