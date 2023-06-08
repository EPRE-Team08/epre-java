package ch.hslu.refashioned.ui.purchases;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import ch.hslu.refashioned.databinding.ActivityPurchaseDetailBinding;
import ch.hslu.refashioned.databinding.FragmentPurchaseBinding;
import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.ui.color.ColorFactory;
import ch.hslu.refashioned.ui.color.GradientProvider;
import ch.hslu.refashioned.ui.purchaseDetail.PurchaseDetail;
import ch.hslu.refashioned.ui.util.DateFormats;
import ch.hslu.refashioned.ui.util.FileUtil;

public final class PurchasesRecyclerViewAdapter extends RecyclerView.Adapter<PurchasesRecyclerViewAdapter.ViewHolder> {

    private final ColorFactory colorFactory = GradientProvider.getScoreGradient();
    private final List<Purchase> mValues;
    private final Context context;

    public PurchasesRecyclerViewAdapter(Context context, final List<Purchase> items) {
        mValues = items;
        this.context = context;
    }

    public void setPurchases(final List<Purchase> purchases) {
        this.mValues.clear();
        this.mValues.addAll(purchases);
        synchronized (this) {
            this.notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentPurchaseBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), context);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        if (!holder.mItem.getImagePath().isEmpty()) {
            boolean doesPathExist = FileUtil.doesPathExist(holder.mItem.getImagePath(), context);
            if (doesPathExist) {
                holder.mImage.setImageURI(Uri.parse(holder.mItem.getImagePath()));
            }
        }
        holder.mCategory.setText(holder.mItem.getClothingType().getLabel());
        holder.mBrand.setText(holder.mItem.getBrand().getLabel());
        holder.mDate.setText(holder.mItem.getDateTime().format(DateFormats.getDefault()));
        holder.mScore.setText(String.valueOf(holder.mItem.getScore()));
        holder.mScore.setTextColor(colorFactory.getColor(holder.mItem.getScore()).toArgb());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public final static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mImage;
        public final TextView mCategory;
        public final TextView mBrand;
        public final TextView mDate;
        public final TextView mScore;
        private final Context context;
        public Purchase mItem;

        public ViewHolder(FragmentPurchaseBinding binding, Context context) {
            super(binding.getRoot());
            this.context = context;
            binding.getRoot().setOnClickListener(this::onClick);
            mImage = binding.scannedImage;
            mCategory = binding.category;
            mBrand = binding.brand;
            mDate = binding.date;
            mScore = binding.score;
        }

        @NonNull
        @Override
        public String toString() {
            return "ViewHolder{" + "mImage=" + mImage.toString() + ", mCategory=" + mCategory.getText() + ", mBrand=" + mBrand.getText() + ", mDate=" + mDate.getText() + ", mScore=" + mScore.getText() + '}';
        }

        private void onClick(View view) {
            if (this.mItem != null) {
                Intent intent = new Intent(context, PurchaseDetail.class)
                        .putExtra(PurchaseDetail.PURCHASE_DATE, mItem.getDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                context.startActivity(intent);
            }
        }
    }
}