package team7.hack.com.hackjmiapplication;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

public class PieActivity extends AppCompatActivity {

    PieChart mPieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);

        mPieChart = findViewById(R.id.pieChart);
        mPieChart.getDescription().setEnabled(false);
        ArrayList<PieEntry> entries = new ArrayList<>();
        ArrayList<Float> category_percentage = new ArrayList<>();
        category_percentage.add(20.2f);
        category_percentage.add(15.1f);
        category_percentage.add(18.3f);
        category_percentage.add(10.2f);
        category_percentage.add(13.5f);
        category_percentage.add(14.7f);
        category_percentage.add(5.3f);
        category_percentage.add(2.7f);


        ArrayList<String> category_name = new ArrayList<>();
        category_name.add("A+");
        category_name.add("B+");
        category_name.add("O+");
        category_name.add("AB+");
        category_name.add("A-");
        category_name.add("B-");
        category_name.add("O-");
        category_name.add("AB-");



        for(int i = 0; i < category_percentage.size(); i++) {
            if (category_percentage.get(i) != 0) {
                entries.add(new PieEntry(category_percentage.get(i), category_name.get(i)));
            }
        }

        addDataSet(entries);
    }

    private void addDataSet(ArrayList<PieEntry> entries) {
        //create the dataset
        PieDataSet pieDataSet = new PieDataSet(entries, "");
        pieDataSet.setSliceSpace(1);
        pieDataSet.setValueTextSize(12);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueFormatter(new PercentFormatter());
        //TODO: all pie chart visual things
        mPieChart.setCenterText("B.G.");
        mPieChart.setHoleRadius(25);
        mPieChart.setTransparentCircleRadius(30);
        mPieChart.setUsePercentValues(true);
        mPieChart.setRotationEnabled(true);
        mPieChart.setCenterTextSize(20);
        mPieChart.setEntryLabelTextSize(0);
        mPieChart.setDragDecelerationFrictionCoef(0.85f);
        mPieChart.setDragDecelerationEnabled(true);
        //add colors to the dataset
        ArrayList<Integer> colorsid = new ArrayList<>();
        colorsid.add(R.color.color4);
        colorsid.add(R.color.color1);
        colorsid.add(R.color.color9);
        colorsid.add(R.color.color13);
        colorsid.add(R.color.color14);
        colorsid.add(R.color.color3);
        colorsid.add(R.color.color11);
        colorsid.add(R.color.colorPrimaryLight);

        ArrayList<Integer> colors = new ArrayList<>();
        for(int i=0; i<colorsid.size(); i++)
        {
            int id = colorsid.get(i);
            int color = ContextCompat.getColor(this ,id);
            colors.add(color);
        }

        pieDataSet.setColors(colors);


        //create piedata object
        PieData pieData= new PieData(pieDataSet);
        mPieChart.setData(pieData);
        mPieChart.invalidate();
        mPieChart.animateY(600, Easing.EasingOption.EaseInCubic);

    }



}
