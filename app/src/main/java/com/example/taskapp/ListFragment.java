package com.example.taskapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
public class ListFragment extends Fragment {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> tasks;
    TextViewModel textModel;
    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = view.findViewById(R.id.elements);

        tasks = new ArrayList<>();
        adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, tasks);
        listView.setAdapter(adapter);

        textModel = new ViewModelProvider(requireActivity()).get(TextViewModel.class);

        // Observe the task list — this updates the UI automatically
        textModel.getTasks().observe(getViewLifecycleOwner(), updatedTasks -> {
            tasks.clear();
            tasks.addAll(updatedTasks);
            adapter.notifyDataSetChanged();
        });

        return view;
    }
}