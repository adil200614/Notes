package com.example.noteapp.ui.form;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentNoteBinding;
import com.example.noteapp.model.TaskModel;


public class NoteFragment extends Fragment {

    private FragmentNoteBinding binding;
    private TaskModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNoteBinding.inflate(inflater, container, false);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        OnClick(navController);
        return binding.getRoot();

    }

    private void OnClick(NavController navController) {
        binding.doneNoteFragment.setOnClickListener(v -> {
            String title = binding.editXtx.getText().toString();

            model = new TaskModel(title);
            Bundle bundle = new Bundle();
            bundle.putSerializable("van", model);
            getParentFragmentManager().setFragmentResult("ask", bundle);
            navController.navigateUp();
        });
    }
}