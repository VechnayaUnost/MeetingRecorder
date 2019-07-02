package by.darya.zdzitavetskaya.meetingrecorder.presentation.list;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import by.darya.zdzitavetskaya.meetingrecorder.R;
import by.darya.zdzitavetskaya.meetingrecorder.adapters.RecordsAdapter;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.BaseFragment;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.dialog.NewRecordDialogFragment;
import by.darya.zdzitavetskaya.meetingrecorder.presentation.meeting.MeetingFragment;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import moxy.presenter.InjectPresenter;

public class ListFragment extends BaseFragment implements ListView, RecordsAdapter.Listener {

    @InjectPresenter
    ListPresenter listPresenter;

    @BindView(R.id.rv_records)
    RecyclerView recyclerView;

    @Override
    public int getLayoutFragment() {
        return R.layout.fragment_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecycler();
        setupAdapter();

        listPresenter.getAllRecords();
    }

    private void setupRecycler() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setupAdapter() {
        final RecordsAdapter adapter = new RecordsAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.floating_action_button)
    void fabClick() {
        NewRecordDialogFragment newRecordDialogFragment = new NewRecordDialogFragment();
        newRecordDialogFragment.setCancelable(false);
        newRecordDialogFragment.show(getFragmentManager(), getString(R.string.create_new_record_dialog_fragment));
    }

    @Override
    public void showList(List<Record> records) {
        ((RecordsAdapter) recyclerView.getAdapter()).setItems(records);
    }

    @Override
    public void onItemClick(Long id) {
        if (getFragmentManager() != null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_main_container, MeetingFragment.newInstance(id))
                    .addToBackStack(null)
                    .commit();
        }
    }
}
