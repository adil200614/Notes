package com.example.noteapp.onboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.shared.PreferncesHelper;
import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        setClick();
        return binding.getRoot();
    }

    private void setClick() {
        binding.propusk.setOnClickListener(v -> {
            PreferncesHelper helper = new PreferncesHelper();
            helper.unit(requireContext());
            helper.onSaveOnBoardState();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigateUp();
        });

    }
}