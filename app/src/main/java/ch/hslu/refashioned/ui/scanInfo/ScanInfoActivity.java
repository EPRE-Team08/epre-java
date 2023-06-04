package ch.hslu.refashioned.ui.scanInfo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import java.util.stream.IntStream;

import ch.hslu.refashioned.R;
import ch.hslu.refashioned.database.converter.BrandConverter;
import ch.hslu.refashioned.databinding.ActivityScanInfoBinding;
import ch.hslu.refashioned.ui.util.FileUtil;

public class ScanInfoActivity extends AppCompatActivity {
    public static final String EXTRA_BRAND_VALUE = "EXTRA_BRAND_VALUE";
    public static final String EXTRA_IMAGE_URI = "EXTRA_IMAGE_URI";

    private ScanInfoViewModel viewModel;
    private ActivityScanInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = new ViewModelProvider(this).get(ScanInfoViewModel.class);
        this.binding = ActivityScanInfoBinding.inflate(getLayoutInflater());
        loadExtras();
        initViewComponents();
        drawChart();
        setContentView(binding.getRoot());
    }

    private void initViewComponents() {
        this.binding.btnCancel.setOnClickListener(this::onCancel);
        this.binding.btnAdd.setOnClickListener(this::onAdd);
        this.binding.imageView.setImageURI(this.viewModel.getImageUri());
        this.binding.textView.setText(this.viewModel.getBrand().getLabel());
    }

    private void loadExtras() {
        var brandValue = getIntent().getIntExtra(EXTRA_BRAND_VALUE, 0);
        this.viewModel.setBrand(BrandConverter.from(brandValue));
        this.viewModel.setImageUri(getIntent().getParcelableExtra(EXTRA_IMAGE_URI));
    }

    private void drawChart() {
        int textColor = isDarkTheme() ? Color.WHITE : Color.BLACK;
        int[] barColors = IntStream.of(R.color.red, R.color.orange, R.color.light_green, R.color.light_blue, R.color.purple, R.color.pink).map(color -> ContextCompat.getColor(this, color)).toArray();
        SustainabilityBarChart chart = new SustainabilityBarChart(this.binding.chart, this.viewModel.getBrand().getScore(), textColor, barColors);
        chart.draw();
    }

    private boolean isDarkTheme() {
        return (getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK) == android.content.res.Configuration.UI_MODE_NIGHT_YES;
    }

    private void onAdd(View view) {
    }

    private void onCancel(View view) {
        this.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        FileUtil.deleteImage(this, this.viewModel.getImageUri());
        super.onBackPressed();
    }
}