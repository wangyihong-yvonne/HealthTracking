package com.kristinaandalex.appleaday;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<String> {

    List<String> symptoms;
    Context context;

    public ListViewAdapter(List<String> symptoms, Context context){
        super(context, R.layout.item_layout,symptoms);
        this.context = context;
        this.symptoms = symptoms;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){

        LayoutInflater inf = ((Activity)context).getLayoutInflater();
        View row = inf.inflate(R.layout.item_layout, parent, false);
        final TextView symptType = row.findViewById(R.id.symptomText);
        symptType.setText(symptoms.get(pos));

        CheckBox checkBox = row.findViewById(R.id.checkBox);
        checkBox.setTag(pos);
        if (SelectSymptoms.selectedSympts.contains(symptoms.get(pos))){
            checkBox.setChecked(true);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int pos = (int) buttonView.getTag();
                if (!SelectSymptoms.selectedSympts.contains(symptoms.get(pos))){
                    SelectSymptoms.selectedSympts.add(symptoms.get(pos));
                    System.out.println("adding: " + symptoms.get(pos));
                } else {
                    SelectSymptoms.selectedSympts.remove(symptoms.get(pos));
                    System.out.println("removing: " + symptoms.get(pos));
                    for (String x : SelectSymptoms.selectedSympts){
                        System.out.println(x);
                    }
                }
            }
        });

        return row;
    }

}