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

    private final List<Record> records;
    private final Listener listener;

    public List<Record> getRecords() {
        return records;
    }

    public RecordsAdapter(final List<Record> records, final Listener listener) {
        this.listener = listener;
        this.records = records;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item_recycler, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Record record = records.get(position);

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

    private void addItems(final List<Record> newItems) {
        records.addAll(newItems);

        notifyDataSetChanged();
    }

    public void setItems(final List<Record> items) {
        clearList();
        addItems(items);
    }

    private void clearList() {
        records.clear();
    }

    public interface Listener {
        void onItemClick(Long id);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_title)
        TextView tvTitle;

        @BindView(R.id.tv_item_date)
        TextView tvDate;

        ViewHolder(final View view) {
            super(view);

            ButterKnife.bind(this, view);

            view.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(records.get(getAdapterPosition()).getId());
                }
            });
        }
    }
}
