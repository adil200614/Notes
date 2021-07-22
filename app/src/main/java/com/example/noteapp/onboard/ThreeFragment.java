package com.example.noteapp.onboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.PreferncesHelper;
import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentThreeBinding;


public class ThreeFragment extends Fragment {

    private FragmentThreeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentThreeBinding.inflate(inflater, container, false);
        GoToWork();
        return binding.getRoot();
    }

    private void GoToWork() {
        binding.textwork.setOnClickListener(v -> {
            PreferncesHelper helper = new PreferncesHelper();
            helper.unit(requireActivity());
            helper.onSaveOnBoardState();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_home);
        });

    }
}