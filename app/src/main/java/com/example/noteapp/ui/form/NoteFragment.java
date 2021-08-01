package com.example.noteapp.ui.form;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentNoteBinding;
import com.example.noteapp.model.TaskModel;
import com.example.noteapp.room.App;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class NoteFragment extends Fragment {
    SpeechRecognizer speechRecognizer;
    int count = 0;
    private FragmentNoteBinding binding;
    private TaskModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNoteBinding.inflate(inflater, container, false);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        editdata();
        onClick(navController);
        speechText();
        return binding.getRoot();
    }

    private void speechText() {

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
            ;
        {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext());
        final Intent speechRecord = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        binding.micro.setOnClickListener(v -> {
            if (count == 0) {
                binding.micro.setImageResource(R.drawable.ic_baseline_mic_24);
                speechRecognizer.startListening(speechRecord);
                count = 1;
            } else {
                binding.micro.setImageResource(R.drawable.ic_baseline_mic_off_24);

                speechRecognizer.stopListening();
                count = 0;
                Log.e("tage", "onChanged: ");
            }
        });
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> date = results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                binding.editXtx.setText(date.get(0));
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Permissions. Gratnted", Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(requireContext(), "Permissions. Gratnted", Toast.LENGTH_SHORT);
            }
        }
    }

    private void editdata() {
        if (getArguments() != null) {
            model = (TaskModel) getArguments().getSerializable("mod");
            if (model != null) {
                binding.editXtx.setText(model.getTxttitle());
            }

        }
    }

    private void onClick(NavController navController) {
        binding.doneNoteFragment.setOnClickListener(v -> {
            String title = binding.editXtx.getText().toString();
            if (binding.editXtx.getText().toString().trim().equals("")) {
                binding.editXtx.setError("Введите текст");
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .repeat(5)
                        .playOn(binding.doneNoteFragment);
            }
            if (model == null) {
                // отправка в HomeFragment
                model = new TaskModel(title);
                App.getInstance().noteDao().insertTask(model);

            } else {
                model.setTxttitle(title);
                App.getInstance().noteDao().update(model);
            }
            navController.navigateUp();
        });
        binding.backToNote.setOnClickListener(v -> {
            navController.navigateUp();
        });
    }
}