package by.darya.zdzitavetskaya.meetingrecorder.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import moxy.MvpAppCompatFragment;

public abstract class BaseFragment extends MvpAppCompatFragment {

    private Unbinder unbinder;

    protected abstract int getLayoutFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayoutFragment(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }
}
