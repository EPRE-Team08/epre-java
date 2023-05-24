package ch.hslu.refashioned.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;
import java.util.Optional;

import ch.hslu.refashioned.R;
import ch.hslu.refashioned.model.map.Shop;
import ch.hslu.refashioned.ui.speech.Speaker;

public final class MapFragment extends Fragment {
    private final MapViewModel viewModel;
    private Speaker speaker;

    public MapFragment() {
        this.viewModel = new MapViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null)
            mapFragment.getMapAsync(this::populateMap);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getActivity() != null)
            this.speaker = new Speaker(getActivity().getApplicationContext(), Locale.UK);
    }

    @Override
    public void onPause() {
        super.onPause();

        this.speaker.dispose();
    }

    private void populateMap(final GoogleMap map) {
        for (Shop shop : this.viewModel.get()) {
            MarkerOptions options = new MarkerOptions();
            options.position(shop.getLocation());
            options.title(shop.getName());

            Marker marker = map.addMarker(options);
            this.viewModel.put(marker, shop);
        }

        map.setOnMarkerClickListener(this::readShop);

        Optional<Shop> shop = this.viewModel.get().stream().findFirst();
        shop.ifPresent(s ->
                map.moveCamera(CameraUpdateFactory.newCameraPosition(
                        CameraPosition.builder()
                                .target(s.getLocation())
                                .zoom(12f)
                                .build())));
    }

    private boolean readShop(final Marker marker) {
        Shop shop = this.viewModel.get(marker);
        this.speaker.speak(shop);

        // The return value specifies whether the event was handled and
        // thus the default behaviour should be skipped
        return false;
    }
}