package com.tni.nunthicha.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {
    private serverconnect connect = new serverconnect();
    private jsonClass jsondata = new jsonClass();

    private int id[] = new int[4];
    public static String EXTRA_MESSAGE_REPLY = "com.tni.nunthicha.project.extra.MESSAGE.REPLY";
    public String mOrderList[] = new String[4];
    public int count;
    int countAmount = 0;
    int countAmount1 = 0;
    int countAmount2 = 0;
    int countAmount3 = 0;

    private String tableNum = "not set";
    private String phoneNum = "not set";
    private String note = "not set";
    private String amount0 = "not set";
    private String amount1 = "not set";
    private String amount2 = "not set";
    private String amount3 = "not set";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();

        id[0] = R.id.order0;
        id[1] = R.id.order1;
        id[2] = R.id.order2;
        id[3] = R.id.order3;

        count = intent.getIntExtra(MainActivity.EXTRA_MESSAGE + "_count", 0);

        for(int i = 0; i < 4; i++){
            mOrderList[i] = intent.getStringExtra(MainActivity.EXTRA_MESSAGE+i);
            String message = mOrderList[i];
            TextView textView = findViewById(id[i]);
            textView.setText(message);
        }

    }

    public void countUp(View view) {
        ((TextView)findViewById(R.id.Amount)).setText(String.valueOf(++countAmount));
    }

    public void countDown(View view) {
        if(countAmount != 0)
            ((TextView)findViewById(R.id.Amount)).setText(String.valueOf(--countAmount));
        else
            countAmount = 0;
    }

    public void countUp1(View view) {
        ((TextView)findViewById(R.id.Amount1)).setText(String.valueOf(++countAmount1));
    }

    public void countDown1(View view) {
        if(countAmount1 != 0)
            ((TextView)findViewById(R.id.Amount1)).setText(String.valueOf(--countAmount1));
        else
            countAmount1 = 0;
    }

    public void countUp2(View view) {
        ((TextView)findViewById(R.id.Amount2)).setText(String.valueOf(++countAmount2));
    }

    public void countDown2(View view) {
        if(countAmount2 != 0)
            ((TextView)findViewById(R.id.Amount2)).setText(String.valueOf(--countAmount2));
        else
            countAmount2 = 0;
    }

    public void countUp3(View view) {
        ((TextView)findViewById(R.id.Amount3)).setText(String.valueOf(++countAmount3));
    }

    public void countDown3(View view) {
        if(countAmount3 != 0)
            ((TextView)findViewById(R.id.Amount3)).setText(String.valueOf(--countAmount3));
        else
            countAmount3 = 0;
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(),message,
                Toast.LENGTH_SHORT).show();
    }

    public void place_onclick(View view) {
        EditText tableTextInput = findViewById(R.id.tablenum);
        EditText phoneTextInput = findViewById(R.id.phonenum);
        EditText noteTextInput = findViewById(R.id.note);
        TextView amount_0 = findViewById(R.id.Amount);
        TextView amount_1 = findViewById(R.id.Amount1);
        TextView amount_2 = findViewById(R.id.Amount2);
        TextView amount_3 = findViewById(R.id.Amount3);

        if(!tableTextInput.getText().toString().toLowerCase().equals("")){
            tableNum = tableTextInput.getText().toString().toLowerCase();
        }
        if(!phoneTextInput.getText().toString().toLowerCase().equals("")){
            phoneNum = phoneTextInput.getText().toString().toLowerCase();
        }
        if(!noteTextInput.getText().toString().toLowerCase().equals("")){
            note = noteTextInput.getText().toString().toLowerCase();
        }
        if(!amount_0.getText().toString().toLowerCase().equals("")){
            amount0 = amount_0.getText().toString().toLowerCase();
        }
        if(!amount_1.getText().toString().toLowerCase().equals("")){
            amount1 = amount_1.getText().toString().toLowerCase();
        }
        if(!amount_2.getText().toString().toLowerCase().equals("")){
            amount2 = amount_2.getText().toString().toLowerCase();
        }
        if(!amount_3.getText().toString().toLowerCase().equals("")){
            amount3 = amount_3.getText().toString().toLowerCase();
        }

        jsondata.setRoot("Table : " + tableNum)
                .addString("First menu", amount0)
                .addString("Second menu", amount1)
                .addString("Third menu", amount2)
                .addString("Fourth menu", amount3)
                .addString("phone number", phoneNum)
                .addString("note", note);

        connect.addData("test", jsondata.get());
        displayToast("Place Ordered");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent Replyintent = new Intent();
        for (int i = 0; i < 4; i++) {
            Replyintent.putExtra(EXTRA_MESSAGE_REPLY + i, mOrderList[i]);
        }
        Replyintent.putExtra(EXTRA_MESSAGE_REPLY + "_count", count);
        setResult(RESULT_OK, Replyintent);
        finish();
        Log.d("test", "pause");
    }
}

