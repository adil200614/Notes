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
import com.example.noteapp.databinding.FragmentSecondBinding;


public class SecondFragment extends Fragment {
    private FragmentSecondBinding binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        setClick2();
        return binding.getRoot();

    }

    private void setClick2() {
        binding.propusk2.setOnClickListener(v -> {
            PreferncesHelper helper = new PreferncesHelper();
            helper.unit(requireContext());
            helper.onSaveOnBoardState();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigateUp();
        });

    }
}