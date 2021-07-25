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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class NoteFragment extends Fragment {

    private FragmentNoteBinding binding;
    private TaskModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNoteBinding.inflate(inflater, container, false);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        OnClick(navController);
//        getItemTime();
        OnClickBack(navController);
        return binding.getRoot();

    }

//    private void getItemTime() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("K:mm a");
//        Date currentTime = Calendar.getInstance().getTime();
//        binding.date.setText(currentTime);
//    }


    private void OnClickBack(NavController navController) {
        binding.backToNote.setOnClickListener(v -> {
            navController.navigate(R.id.action_noteFragment_to_nav_home);
        });
    }

    private void OnClick(NavController navController) {

        binding.doneNoteFragment.setOnClickListener(v -> {
            if (binding.editXtx.getText().toString().trim().equalsIgnoreCase("")) {
                binding.editXtx.setError("Введите текст");
            } else {
                // отправка в HomeFragment
                String title = binding.editXtx.getText().toString();
                model = new TaskModel(title);
                Bundle bundle = new Bundle();
                bundle.putSerializable("van", model);
                getParentFragmentManager().setFragmentResult("ask", bundle);
                navController.navigateUp();
            }
        });
    }
}