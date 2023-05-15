package ch.hslu.refashioned.ui.info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ch.hslu.refashioned.repository.info.InfoItemRepo;
import ch.hslu.refashioned.service.info.InfoItemService;
import ch.hslu.refashioned.service.info.MockInfoItemService;

public final class InfoViewModel extends ViewModel {

    private final InfoItemService infoItemService = new InfoItemRepo(new MockInfoItemService());
    private final MutableLiveData<String> mText;

    public InfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}