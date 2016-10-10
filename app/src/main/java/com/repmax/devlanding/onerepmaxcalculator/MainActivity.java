package com.repmax.devlanding.onerepmaxcalculator;

import android.content.ReceiverCallNotAllowedException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.repmax.devlanding.onerepmaxcalculator.calculator.OneRepMaxTemplate;
import com.repmax.devlanding.onerepmaxcalculator.calculator.PercentRepMax;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText weightEditText;
    private EditText repsEditText;

    private int weight;
    private int reps;
    private int[] data;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        repsEditText = (EditText) findViewById(R.id.repsEditText);

        weight = getIntFromEditText(weightEditText.getText().toString());
        reps = getIntFromEditText(repsEditText.getText().toString());

        data = get10RepMaxs(200, 5);

        adapter = new RecyclerViewAdapter(getApplicationContext(), data);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        weightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.equals("") || charSequence == null || charSequence.length() == 0) {
                    weight = 0;
                } else {
                    weight = Integer.parseInt(charSequence.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                reps = Integer.parseInt(repsEditText.getText().toString());
                data = get10RepMaxs(weight, reps);
                adapter = new RecyclerViewAdapter(getApplicationContext(), data);
                recyclerView.setAdapter(adapter);
            }
        });
        repsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.equals("") || charSequence == null || charSequence.length() == 0) {
                    reps = 1;
                } else {
                    reps = Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                weight = Integer.parseInt(weightEditText.getText().toString());
                data = get10RepMaxs(weight, reps);
                adapter = new RecyclerViewAdapter(getApplicationContext(), data);
                recyclerView.setAdapter(adapter);

            }
        });
    }

    private int getIntFromEditText(String editTextString) {
        int editTextInt = 0;
        if (!editTextString.isEmpty()) {
            editTextInt = Integer.parseInt(editTextString);
        }
        return editTextInt;
    }

    private int[] get10RepMaxs(int weight, int reps) {
        OneRepMaxTemplate oneRepMaxTemplate = new PercentRepMax();
        int[] data = oneRepMaxTemplate.determineRepMaxes(weight, reps);

        return data;
    }
}
