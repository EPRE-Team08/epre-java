package ch.hslu.refashioned.ui.purchaseDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import ch.hslu.refashioned.databinding.ActivityPurchaseDetailBinding;
import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.ui.color.GradientProvider;
import ch.hslu.refashioned.ui.util.FileUtil;
import ch.hslu.refashioned.ui.util.ThemeUtil;

public class PurchaseDetail extends AppCompatActivity {
    public static final String PURCHASE_DATE = "PURCHASE_DATE";
    private PurchaseDetailViewModel viewModel;
    private ActivityPurchaseDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PurchaseDetailViewModel.class);
        binding = ActivityPurchaseDetailBinding.inflate(getLayoutInflater());
        loadExtras();
        initComponents();
        setContentView(binding.getRoot());
    }

    private void loadExtras() {
        LocalDateTime purchaseDate = LocalDateTime.parse(getIntent().getStringExtra(PURCHASE_DATE));
        viewModel.setPurchaseDate(purchaseDate);
    }

    private void initComponents() {
        Purchase purchase = viewModel.getPurchase();
        String imagePath = purchase.getImagePath();
        if (!imagePath.isEmpty()) {
            boolean doesImagePathExist = FileUtil.doesPathExist(imagePath, this);
            if (doesImagePathExist) {
                binding.imageView.setImageURI(Uri.parse(imagePath));
            }
        }
        Color scoreColor = GradientProvider.getScoreGradient().getColor(purchase.getScore());
        binding.lblScore.setText(String.valueOf(purchase.getScore()));
        binding.lblScore.setTextColor(scoreColor.toArgb());
        binding.icSustainability.setColorFilter(scoreColor.toArgb());
        binding.lblBrand.setText(purchase.getBrand().getLabel());
        binding.lblClothingType.setText(purchase.getClothingType().getLabel());
        binding.lblPurchaseDate.setText(purchase.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        binding.lblBrandScore.setText(String.valueOf(purchase.getSustainabilityScore()));
        Color brandColor = GradientProvider.getBrandGradient().getColor(purchase.getBrand().getScore().getOverall());
        binding.lblBrandScore.setTextColor(brandColor.toArgb());
        String sustainabilityFactor = DecimalFormat.getInstance(getResources().getConfiguration().getLocales().get(0)).format(purchase.getSustainabilityFactor());
        binding.lblFactor.setText(sustainabilityFactor);
        Color sustainabilityFactorColor = GradientProvider.getSustainabilityFactorGradient().getColor(Math.round(purchase.getSustainabilityFactor() * 10));
        binding.lblFactor.setTextColor(sustainabilityFactorColor.toArgb());
        binding.btnDelete.setOnClickListener(this::onDelete);
    }

    private void onDelete(View view) {
        viewModel.deletePurchase();
        super.onBackPressed();
    }
}