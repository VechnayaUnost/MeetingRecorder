package by.darya.zdzitavetskaya.meetingrecorder.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.darya.zdzitavetskaya.meetingrecorder.R;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.ViewHolder> {

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull final ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item_recycler, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
