

package com.example.noteapp.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.noteapp.R;
import com.example.noteapp.adapter.TaskAdapter;
import com.example.noteapp.databinding.FragmentHomeBinding;
import com.example.noteapp.databinding.FragmentNoteBinding;
import com.example.noteapp.interfaces.ItemCLicklistener;
import com.example.noteapp.model.TaskModel;
import com.example.noteapp.onboard.OnBoardFragment;
import com.example.noteapp.room.App;

import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {


    List<TaskModel> list = new ArrayList<>();
    TaskAdapter adapter = new TaskAdapter();
    private FragmentHomeBinding binding;
    boolean isChange = true;
    


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        Pushdata();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecycler();
        search();
        getData();

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
            if (item.getTxttitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }


    private void getData() {
        getParentFragmentManager().setFragmentResultListener("ask", getViewLifecycleOwner(), (requestKey, result) -> {
            TaskModel model = (TaskModel) result.get("mod");
            if (model != null) {
                adapter.addText(model);
            }
        });
        App.getInstance().noteDao().getAll().observe(requireActivity(), new Observer<List<TaskModel>>() {
            @Override
            public void onChanged(List<TaskModel> models) {
                adapter.setetxt(models);
                list = models;
                Log.e("tag", "onChanged: ");
            }
        });
    }

    private void setUpRecycler() {
        if (!isChange) {
            binding.homeFrag.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        } else {
            binding.homeFrag.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        binding.homeFrag.setAdapter(adapter);
      new   ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
               App.getInstance().noteDao().delete(list.get(viewHolder.getAdapterPosition()));
                adapter.delete(viewHolder.getAdapterPosition());

            }
        }).attachToRecyclerView(binding.homeFrag);
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            if (isChange) {
                item.setIcon(R.drawable.ic_baseline_format_list_bulleted_24);
                binding.homeFrag.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                isChange = false;
            } else {
                item.setIcon(R.drawable.ic_baseline_dashboard_24);
                binding.homeFrag.setLayoutManager(new LinearLayoutManager(getContext()));
                isChange = true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void Pushdata(){
        adapter.setItemclicklistener(new ItemCLicklistener() {
            @Override
            public void clickitem(int posititon, TaskModel taskModel) {
                Bundle bundle = new Bundle();
                bundle.putInt("data",posititon);
                bundle.putSerializable("mod",taskModel);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.action_nav_home_to_noteFragment, bundle);
                Log.e("TAG", "clickitem: " + taskModel.getTxttitle() );
            }
        });
    }
}