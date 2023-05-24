package ch.hslu.refashioned.ui.speech;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public final class Speaker {
    private final static String UTTERANCE_ID = "utterance_id";

    private final Locale locale;
    private final TextToSpeech speaker;
    private boolean ready;

    public Speaker(final Context context, final Locale locale) {
        this.locale = locale;
        this.speaker = new TextToSpeech(context, this::onInit);
    }

    public void speak(final Speakable speakable) {
        if (!isReady())
            return;

        speaker.speak(speakable.getSpeakableText(), TextToSpeech.QUEUE_FLUSH, null, UTTERANCE_ID);
    }

    public void dispose() {
        if (!isReady())
            return;

        speaker.stop();
        speaker.shutdown();
    }

    private boolean isReady() {
        return ready;
    }

    private void onInit(int status) {
        ready = (status != TextToSpeech.ERROR);
        if (isReady())
            speaker.setLanguage(locale);
    }
}