package com.example.noteapp.onboard;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.R;
import com.example.noteapp.adapter.OnBoardAdapter;
import com.example.noteapp.databinding.FragmentNoteBinding;
import com.example.noteapp.databinding.FragmentOnBoardBinding;
import com.google.android.material.tabs.TabLayout;

public class OnBoardFragment extends Fragment {

    private FragmentOnBoardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false);
        OnBoardAdapter onBoardAdapter = new OnBoardAdapter(getActivity().getSupportFragmentManager());
        binding.viewPager.setAdapter(onBoardAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager, true);
        return binding.getRoot();
    }
}
