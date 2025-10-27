package com.example.taskapp;

import androidx.lifecycle.ViewModelProvider;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskapp.model.MyTaskContentProvider;
import com.example.taskapp.model.Task;

import java.util.LinkedList;

public class TextFragment extends Fragment {

    private EditText editTextTask, editTextWho;
    private Button buttonAdd;
    private TextViewModel mViewModel;
    LinkedList<Task> tasks;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);

        editTextTask = view.findViewById(R.id.editTextTask);
        editTextWho = view.findViewById(R.id.editTextWho);
        buttonAdd = view.findViewById(R.id.buttonAdd);
        tasks = new LinkedList<>();

        // Get shared ViewModel (scoped to Activity)
        mViewModel = new ViewModelProvider(requireActivity()).get(TextViewModel.class);

        buttonAdd.setOnClickListener(v -> {
            String task = editTextTask.getText().toString().trim();
            String owner = editTextWho.getText().toString().trim();

            ContentValues mNewValues = new ContentValues();
            mNewValues.put(MyTaskContentProvider.COLUMN_TASK, task);
            mNewValues.put(MyTaskContentProvider.COLUMN_OWNER, owner);

            getActivity().getContentResolver().insert(MyTaskContentProvider.CONTENT_URI,mNewValues);
            Cursor mCursor = getActivity().getContentResolver().query(
                    MyTaskContentProvider.CONTENT_URI, null, null, null, null);
            tasks = new LinkedList<>();
            if (mCursor != null){
                Log.i("COUNT", mCursor.getCount() + " entries");
                mCursor.moveToFirst();
                if (mCursor.getCount() > 0){
                    while(mCursor.isAfterLast() == false){
                        String d = mCursor.getString(1);
                        String o = mCursor.getString(2);
                        Task tt = new Task(d, o);
                        Log.d("DB", tt.toString());
                        mCursor.moveToNext();
                    }
                }
            }

            if (!task.isEmpty() && !owner.isEmpty()) {
                mViewModel.addTask(task + " by " + owner);
                editTextTask.setText("");
                editTextWho.setText("");
            }
        });

        return view;
    }

//    public void addToList(Task t){
//        if (tasks == null){
//            tasks = new LinkedList<>();
//        }
//        tasks.add(t);
//        ContentValues mNewValues = new ContentValues();
//
//        mNewValues.put(MyTaskContentProvider.COLUMN_TASK, t.getTask());
//        mNewValues.put(MyTaskContentProvider.COLUMN_OWNER, t.getOwner());
//
//        getContentResolver().insert(MyTaskContentProvider.CONTENT_URI,mNewValues);
//
//        Cursor mCursor = getContentResolver().query(
//                MyTaskContentProvider.CONTENT_URI, null, null, null, null);
//        tasks = new LinkedList<>();
//        if (mCursor != null){
//            Log.i("COUNT", mCursor.getCount() + " entries");
//            mCursor.moveToFirst();
//            if (mCursor.getCount() > 0){
//                while(mCursor.isAfterLast() == false){
//                    String d = mCursor.getString(1);
//                    String o = mCursor.getString(2);
//                    Task tt = new Task(d, o);
//                    Log.d("DB", tt.toString());
//                    mCursor.moveToNext();
//                }
//            }
//        }
//    }
}