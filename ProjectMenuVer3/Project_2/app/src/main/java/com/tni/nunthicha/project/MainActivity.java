package com.tni.nunthicha.project;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.tni.nunthicha.project.extra.MESSAGE";


    public String mOrderList[] = new String[4];
    private int count = 0;
    private int price = 0;

    CheckBox kapao,rice,egg,padthai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        kapao = (CheckBox) findViewById(R.id.krapao_box);
        egg = (CheckBox) findViewById(R.id.egg_box);
        rice = (CheckBox) findViewById(R.id.rice_box);
        padthai = (CheckBox) findViewById(R.id.padtai_box);

        for(int i = 0; i < 4; i++){
            mOrderList[i] = "";
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, OrderActivity.class);

                if (kapao.isChecked()){
                    mOrderList[count] = "Krapao  " + getString(R.string.kapao_price);
                    count++;
                }
                if (egg.isChecked()){
                    mOrderList[count] = "Omelet  " + getString(R.string.egg_price);
                    count++;
                }
                if (rice.isChecked()){
                    mOrderList[count] = "Fried rice  " + getString(R.string.rice_price);
                    count++;
                }
                if (padthai.isChecked()){
                    mOrderList[count] = "Padtai  " + getString(R.string.padthai_price);
                    count++;
                }

                for(int i = 0; i < 4; i++){
                    intent.putExtra(EXTRA_MESSAGE + i, mOrderList[i]);
                }
                intent.putExtra(EXTRA_MESSAGE + "_count", count);
                startActivityForResult(intent, 1);

            }
        });
    }

    public void addListenerOnButtonClick(View view) {
        CheckBox checkbox = findViewById(view.getId());

        if(checkbox.isChecked()){
            String Article = " a ";
            String Name = checkbox.getText().toString().toLowerCase();
            switch (Name.charAt(0)){
                case 'a' : case 'e' : case 'i' : case 'o' : case 'u' : Article = " an " ;
                    break;
            }
            displayToast("You ordered" + Article + Name.toLowerCase());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("test", "here");
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                for(int i = 0; i < 4; i++){
                    mOrderList[i] = data.getStringExtra(OrderActivity.EXTRA_MESSAGE_REPLY+i);
                }
                count = data.getIntExtra(OrderActivity.EXTRA_MESSAGE_REPLY+"_count", 0);
            }
        }
    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

}