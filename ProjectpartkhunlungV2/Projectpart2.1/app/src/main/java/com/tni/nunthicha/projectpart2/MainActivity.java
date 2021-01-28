package com.tni.nunthicha.projectpart2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private severconnect connect = new severconnect();
    private jsonClass jsondata = new jsonClass();

    private  final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new WordListAdapter(this, mWordList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button button = findViewById(R.id.rec);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = mWordList.size();
                mWordList.addLast("+ Order " + (wordListSize + 1));
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });
        for (int i = 0; i < 20; i++){
            mWordList.addLast("Order " + (i+1));
        }

        Button button1 = findViewById(R.id.clear_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWordList.clear();
                for (int i = 0; i < 20;i++){
                    mWordList.addLast("Order " + (i+1));
                }
                mAdapter.notifyDataSetChanged();

            }
        });

    }



    /*public void place_onclick(View view) {
        EditText tableTextInput = findViewById(R.id.tablenum);
        EditText phoneTextInput = findViewById(R.id.phonenum);
        EditText noteTextInput = findViewById(R.id.note);

        if(!tableTextInput.getText().toString().toLowerCase().equals("")){
            tableNum = tableTextInput.getText().toString().toLowerCase();
        }
        if(!phoneTextInput.getText().toString().toLowerCase().equals("")){
            phoneNum = phoneTextInput.getText().toString().toLowerCase();
        }
        if(!noteTextInput.getText().toString().toLowerCase().equals("")){
            note = noteTextInput.getText().toString().toLowerCase();
        }

        jsondata.setRoot("Table " + tableNum)
                .addString("phone number", phoneNum)
                .addString("note", note);

        connect.addData("test", jsondata.get());
        displayToast("Place Ordered");
    }*/
}