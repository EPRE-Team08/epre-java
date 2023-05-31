package ch.hslu.refashioned.ui.info;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import ch.hslu.refashioned.databinding.FragmentInfoItemBinding;
import ch.hslu.refashioned.model.info.InfoItem;

import java.util.List;

public final class InfoItemRecyclerViewAdapter extends RecyclerView.Adapter<InfoItemRecyclerViewAdapter.ViewHolder> {
    private final List<InfoItem> mValues;

    public InfoItemRecyclerViewAdapter(List<InfoItem> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentInfoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mCategory.setText(holder.mItem.getCategory().getLabel());
        holder.mTitle.setText(holder.mItem.getTitle());
        holder.mBrief.setText(holder.mItem.getBrief());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public final static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mCategory;
        public final TextView mTitle;
        public final TextView mBrief;
        public InfoItem mItem;

        public ViewHolder(@NonNull FragmentInfoItemBinding binding) {
            super(binding.getRoot());
            mCategory = binding.category;
            mTitle = binding.title;
            mBrief = binding.brief;
        }

        @NonNull
        @Override
        public String toString() {
            return "ViewHolder{" +
                    "mCategory=" + mCategory.getText() +
                    ", mTitle=" + mTitle.getText() +
                    ", mBrief=" + mBrief.getText() +
                    '}';
        }
    }
}