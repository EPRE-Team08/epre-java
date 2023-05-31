package ch.hslu.refashioned.ui.purchases;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ch.hslu.refashioned.databinding.FragmentPurchaseBinding;
import ch.hslu.refashioned.model.history.Purchase;
import ch.hslu.refashioned.ui.color.GradientColor;
import ch.hslu.refashioned.ui.color.GradientProvider;
import ch.hslu.refashioned.ui.util.DateFormats;

public final class PurchasesRecyclerViewAdapter extends RecyclerView.Adapter<PurchasesRecyclerViewAdapter.ViewHolder> {

    private final GradientColor gradient = GradientProvider.getScoreGradient();
    private final List<Purchase> mValues;

    public PurchasesRecyclerViewAdapter(final List<Purchase> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentPurchaseBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        if (!holder.mItem.getImagePath().isEmpty())
            holder.mImage.setImageURI(Uri.parse(holder.mItem.getImagePath()));
        holder.mCategory.setText(holder.mItem.getClothingType().getLabel());
        holder.mBrand.setText(holder.mItem.getBrand().getLabel());
        holder.mDate.setText(holder.mItem.getDateTime().format(DateFormats.getDefault()));
        holder.mScore.setText(String.valueOf(holder.mItem.getScore()));
        holder.mScore.setTextColor(gradient.getColor(holder.mItem.getScore()).toArgb());
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
        public Purchase mItem;

        public ViewHolder(FragmentPurchaseBinding binding) {
            super(binding.getRoot());
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
    }
}