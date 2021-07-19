

package com.example.noteapp.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.example.noteapp.R;
import com.example.noteapp.adapter.TaskAdapter;
import com.example.noteapp.databinding.FragmentHomeBinding;
import com.example.noteapp.model.TaskModel;
import com.example.noteapp.onboard.OnBoardFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    ArrayList<TaskModel> list = new ArrayList<>();
    TaskAdapter adapter = new TaskAdapter();
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        //   NavController navController = Navigation.findNavController(requireActivity(), R.id)
        getData();
        search();
        SetUpRecycler();
        return binding.getRoot();
    }


    private void search() {
        binding.searchTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void filter(String text) {
        ArrayList<TaskModel> filteredList = new ArrayList<>();
        for (TaskModel item : list) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }


    private void getData() {
        getParentFragmentManager().setFragmentResultListener("ask", getViewLifecycleOwner(), (requestKey, result) -> {
            TaskModel model = (TaskModel) result.getSerializable("van");
            list.add(model);
            adapter.addText(model);
        });
    }

    private void SetUpRecycler() {
        binding.homeFrag.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TaskAdapter();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}