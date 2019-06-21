package by.darya.zdzitavetskaya.meetingrecorder.presentation.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import by.darya.zdzitavetskaya.meetingrecorder.R;
import by.darya.zdzitavetskaya.meetingrecorder.adapters.RecordsAdapter;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class ListFragment extends MvpAppCompatFragment implements ListView {

    @InjectPresenter
    ListPresenter listPresenter;

    Unbinder unbinder;

    @BindView(R.id.rv_records)
    RecyclerView recyclerView;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
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
        final RecordsAdapter adapter = new RecordsAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.floating_action_button)
    public void fabClick() {
        //call alertdialog
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showList(List<Record> records) {
        ((RecordsAdapter) recyclerView.getAdapter()).getRecords().addAll(records);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
