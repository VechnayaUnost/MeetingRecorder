package by.darya.zdzitavetskaya.meetingrecorder.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.darya.zdzitavetskaya.meetingrecorder.R;
import by.darya.zdzitavetskaya.meetingrecorder.room.model.Record;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.ViewHolder> {

    private List<Record> records;

    public List<Record> getRecords() {
        return records;
    }

    public RecordsAdapter(List<Record> records) {
        this.records = records;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull final ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item_recycler, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Record record = records.get(position);

        holder.tvTitle.setText(record.getTitle());
        holder.tvDate.setText(record.getDate());
    }

    @Override
    public int getItemCount() {
        if (records == null) {
            return 0;
        }
        return records.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_title)
        TextView tvTitle;

        @BindView(R.id.tv_item_date)
        TextView tvDate;

        ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}
