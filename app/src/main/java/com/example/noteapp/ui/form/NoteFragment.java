package com.example.noteapp.ui.form;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentNoteBinding;
import com.example.noteapp.model.TaskModel;
import com.example.noteapp.room.App;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class NoteFragment extends Fragment {

    private FragmentNoteBinding binding;
    private TaskModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNoteBinding.inflate(inflater, container, false);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        onClick(navController);
        onClickBack(navController);
        return binding.getRoot();

    }


    private void onClickBack(NavController navController) {
        binding.backToNote.setOnClickListener(v -> {
            navController.navigate(R.id.action_noteFragment_to_nav_home);
        });
    }

    private void onClick(NavController navController) {


        binding.doneNoteFragment.setOnClickListener(v -> {
            String title = binding.editXtx.getText().toString();
            if (binding.editXtx.getText().toString().trim().equalsIgnoreCase("")) {
                binding.editXtx.setError("Введите текст");
            } if (model == null){
                // отправка в HomeFragment
                model = new TaskModel(title);
                App.getInstance().noteDao().insertTask(model);
                Log.e("TAG", "onClick: " + model.getTxttitle());
                navController.navigateUp();
            }
        });
    }
}