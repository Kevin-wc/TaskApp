package com.example.taskapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.taskapp.model.MyTaskContentProvider;
import com.example.taskapp.model.Task;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    LinkedList tasks;
    FragmentManager fg;
    TextViewModel textModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            textModel = new ViewModelProvider(this).get(TextViewModel.class);
            fg = getSupportFragmentManager();
            FragmentTransaction trans = fg.beginTransaction();
            TextFragment tf = new TextFragment();
            trans.add(R.id.textFragment,tf,"textFragment");
            ListFragment lf = new ListFragment();
            trans.add(R.id.ListFragment,lf,"ListFragment");

            trans.commit();
        }
    }


}