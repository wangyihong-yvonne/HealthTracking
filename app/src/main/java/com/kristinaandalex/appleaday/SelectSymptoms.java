package com.kristinaandalex.appleaday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import java.util.ArrayList;

public class SelectSymptoms extends AppCompatActivity {

    ListView listView;
    ListViewAdapter adapter;
    ArrayList<String> symptoms = new ArrayList<>();
    Button submit;
    String date;


    public static ArrayList<String> selectedSympts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_symptoms);

        getSymptoms();
        listView = findViewById(R.id.editSymptsList);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        adapter = new ListViewAdapter(symptoms, this);
        listView.setAdapter(adapter);
        try {
            date = getIntent().getExtras().getString("date");
        } catch (Exception e) {
            System.out.println("no date");
        }

        submit = findViewById(R.id.submitSympts);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSelectedSympts();
            }
        });


    }

    private void sendSelectedSympts(){
        Intent intent = new Intent(this, SymptomPage.class);
        intent.setFlags(0);
        intent.putStringArrayListExtra("symptoms", selectedSympts);
        intent.putExtra("changed", true);
        intent.putExtra("date", date);
        for (String x : selectedSympts) System.out.println(x);
        setResult(RESULT_OK, intent);
        finish();
        //startActivity(intent);
    }

    private void getSymptoms(){
        String[] items = getResources().getStringArray(R.array.symptomArray);
        for (String item : items){
            symptoms.add(item);
        }
    }

}