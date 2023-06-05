package ch.hslu.refashioned.ui.purchaseAddDialog;

import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import ch.hslu.refashioned.R;
import ch.hslu.refashioned.databinding.FragmentPurchaseAddDialogBinding;
import ch.hslu.refashioned.model.history.ClothingType;
import ch.hslu.refashioned.model.history.PurchaseType;

public class PurchaseAddDialog extends DialogFragment implements AdapterView.OnItemSelectedListener {

    public PurchaseAddDialog() {
        this(ClothingType.SHIRT, PurchaseType.FIRST_HAND);
    }

    public PurchaseAddDialog(ClothingType clothingType, PurchaseType purchaseType) {
        initialClothingType = clothingType;
        initialPurchaseType = purchaseType;
    }

    private final PurchaseType initialPurchaseType;
    private final ClothingType initialClothingType;

    private PurchaseAddDialogViewModel viewModel;
    private FragmentPurchaseAddDialogBinding binding;

    private PurchaseAddDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        binding = FragmentPurchaseAddDialogBinding.bind(requireActivity().getLayoutInflater().inflate(R.layout.fragment_purchase_add_dialog, null));
        binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> onPurchaseTypeChecked(checkedId));
        binding.spinner.setOnItemSelectedListener(this);
        builder.setView(binding.getRoot()).setPositiveButton(R.string.add_to_history, (dialog, id) -> onPositiveButtonClick()).setNegativeButton(R.string.cancel, (dialog, id) -> onNegativeButtonClick());
        initViewModel();
        initView();
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (PurchaseAddDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement PurchaseAddDialogListener");
        }
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(PurchaseAddDialogViewModel.class);
        viewModel.setPurchaseType(initialPurchaseType);
        viewModel.setClothingType(initialClothingType);
    }

    private void initView() {
        ArrayAdapter<ClothingType> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, ClothingType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
        int position = adapter.getPosition(viewModel.getClothingType());
        binding.spinner.setSelection(position);
        binding.radioButtonFirstHand.setChecked(viewModel.getPurchaseType().getValue() == PurchaseType.FIRST_HAND.getValue());
    }

    private void onPositiveButtonClick() {
        listener.onDialogPositiveClick(viewModel.getPurchaseType(), viewModel.getClothingType());
    }

    private void onNegativeButtonClick() {
        listener.onDialogNegativeClick();
        requireDialog().cancel();
    }

    private void onPurchaseTypeChecked(int checkedId) {
        switch (checkedId) {
            case R.id.radioButtonFirstHand:
                viewModel.setPurchaseType(PurchaseType.FIRST_HAND);
                break;
            case R.id.radioButtonSecondHand:
                viewModel.setPurchaseType(PurchaseType.SECOND_HAND);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ClothingType type = (ClothingType) parent.getItemAtPosition(position);
        viewModel.setClothingType(type);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing
    }
}