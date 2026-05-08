package lu.uni.mobilectf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class AboutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = Ui.base(this, "APK Entry and Notes", "Start where Android starts: the manifest and launcher Activity.");
        LinearLayout card = Ui.card(this, root);
        Ui.text(this, card, "The incident app has a launcher activity, several exported components, local storage, resources, and bundled assets.");
        Ui.text(this, card, "Hint: static analysis tools can recover values that are assembled at runtime or hidden behind unused branches.");
        String notDisplayed = RotatedStrings.rebuild();
        if (notDisplayed.length() == 0) {
            Ui.text(this, card, "No notes available.");
        }
    }
}
