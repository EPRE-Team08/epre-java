package ch.hslu.refashioned.ui.scanInfo;

import android.graphics.Color;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.Arrays;
import java.util.List;

import ch.hslu.refashioned.model.sustainability.Score;

public class SustainabilityBarChart {
    private static final String[] LABELS = new String[]{"Emissions", "Transparency", "Water & Chemicals", "Materials", "Workers' Rights", "Waste"};
    private final Score score;
    private final int textColor;
    private final BarChart chart;

    private final int[] colors;

    public SustainabilityBarChart(BarChart chart, Score score, int textColor, int[] colors) {
        this.score = score;
        this.textColor = textColor;
        this.chart = chart;
        this.colors = colors;
    }

    public void draw() {
        List<BarEntry> barEntries = getBarEntries();
        BarDataSet dataSet = new BarDataSet(barEntries, "Sustainability Score");
        configureDataSet(dataSet);
        BarData barData = new BarData(dataSet);
        XAxis xAxis = chart.getXAxis();
        configureXAxis(xAxis);
        YAxis yAxis = chart.getAxisLeft();
        configureYAxis(yAxis);
        chart.setData(barData);
        configureChart(chart);
        chart.invalidate();
    }

    private static void configureChart(BarChart chart) {
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        chart.setTouchEnabled(false); // disable touch gestures
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.setVisibleYRange(Score.MIN_SCORE, Score.MAX_SCORE, YAxis.AxisDependency.LEFT);
    }

    private void configureYAxis(YAxis yAxis) {
        yAxis.setTextColor(textColor);
    }

    private void configureXAxis(XAxis xAxis) {
        ValueFormatter valueFormatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return LABELS[(int) value];
            }
        };
        xAxis.setValueFormatter(valueFormatter);
        xAxis.setTextColor(textColor);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(-90);
    }

    private List<BarEntry> getBarEntries() {
        BarEntry emissionsEntry = new BarEntry(0, score.getEmissions());
        BarEntry transparencyEntry = new BarEntry(1, score.getTransparency());
        BarEntry waterAndChemicalsEntry = new BarEntry(2, score.getWaterAndChemicals());
        BarEntry materialsEntry = new BarEntry(3, score.getMaterials());
        BarEntry workersRightsEntry = new BarEntry(4, score.getWorkersRights());
        BarEntry wasteEntry = new BarEntry(5, score.getWaste());
        return Arrays.asList(emissionsEntry, transparencyEntry, waterAndChemicalsEntry, materialsEntry, workersRightsEntry, wasteEntry);
    }

    private void configureDataSet(DataSet<BarEntry> dataSet) {
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.WHITE);
    }
}
