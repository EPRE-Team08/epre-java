package ch.hslu.refashioned.ui.scanInfo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import ch.hslu.refashioned.R;
import ch.hslu.refashioned.database.converter.BrandConverter;
import ch.hslu.refashioned.databinding.ActivityScanInfoBinding;
import ch.hslu.refashioned.model.history.ClothingType;
import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.model.history.PurchaseType;
import ch.hslu.refashioned.ui.purchaseAddDialog.PurchaseAddDialog;
import ch.hslu.refashioned.ui.purchaseAddDialog.PurchaseAddDialogListener;
import ch.hslu.refashioned.ui.util.FileUtil;
import ch.hslu.refashioned.ui.util.ThemeUtil;

public class ScanInfoActivity extends AppCompatActivity implements PurchaseAddDialogListener {
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
        int textColor = ThemeUtil.isDarkTheme(getResources()) ? Color.WHITE : Color.BLACK;
        int[] barColors = IntStream.of(R.color.red, R.color.orange, R.color.light_green, R.color.light_blue, R.color.purple, R.color.pink).map(color -> ContextCompat.getColor(this, color)).toArray();
        SustainabilityBarChart chart = new SustainabilityBarChart(this.binding.chart, this.viewModel.getBrand().getScore(), textColor, barColors);
        chart.draw();
    }

    private void onAdd(View view) {
        var dialog = new PurchaseAddDialog();
        dialog.onAttach((Context) this);
        dialog.show(getSupportFragmentManager(), "PurchaseAddDialog");
    }

    private void onCancel(View view) {
        this.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        FileUtil.deleteImage(this, this.viewModel.getImageUri());
        super.onBackPressed();
    }

    @Override
    public void onDialogPositiveClick(PurchaseType purchaseType, ClothingType clothingType) {
        int cntPurchasesLastMonth = viewModel.getCountPurchaseByLastMonth(clothingType);
        int score = Purchase.calculateScore(purchaseType, viewModel.getBrand(), cntPurchasesLastMonth);
        Purchase purchase = new Purchase(LocalDateTime.now(), viewModel.getImageUri().toString(), purchaseType, clothingType, viewModel.getBrand(), score);
        viewModel.insertPurchase(purchase);
        Log.d("ScanInfoActivity", "onDialogPositiveClick: " + clothingType + " " + clothingType);
        super.onBackPressed();
    }

    @Override
    public void onDialogNegativeClick() {
        Log.d("ScanInfoActivity", "onDialogNegativeClick");
    }
}