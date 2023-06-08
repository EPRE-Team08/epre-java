package ch.hslu.refashioned.ui.purchases;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ch.hslu.refashioned.R;

public final class PurchasesFragment extends Fragment {

    private PurchasesViewModel viewModel;
    private PurchasesRecyclerViewAdapter adapter;

    public PurchasesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = new ViewModelProvider(this).get(PurchasesViewModel.class);
        this.adapter = new PurchasesRecyclerViewAdapter(requireContext(), this.viewModel.get());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_purchases, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.refreshPurchases();
    }

    private void refreshPurchases() {
        adapter.setPurchases(this.viewModel.get());
    }
}