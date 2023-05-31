package ch.hslu.refashioned.ui.userScore;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ch.hslu.refashioned.databinding.FragmentUserScoreBinding;

public final class UserScoreFragment extends Fragment {
    private UserScoreViewModel viewModel;
    private FragmentUserScoreBinding binding;

    public UserScoreFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = new ViewModelProvider(this).get(UserScoreViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentUserScoreBinding.inflate(inflater, container, false);

        this.viewModel.getScore().observe(getViewLifecycleOwner(), this::updateScore);
        this.viewModel.getScoreColor().observe(getViewLifecycleOwner(), this::updateColor);

        return this.binding.getRoot();
    }

    private void updateScore(final Integer score) {
        this.binding.score.setText(String.valueOf(score));
    }

    private void updateColor(final Color color) {
        this.binding.score.setTextColor(color.toArgb());
    }

}