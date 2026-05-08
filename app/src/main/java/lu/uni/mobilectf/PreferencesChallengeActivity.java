package lu.uni.mobilectf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class PreferencesChallengeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = Ui.base(this, "Local Preferences", "The app saves a small profile marker on launch.");
        LinearLayout card = Ui.card(this, root);
        Ui.text(this, card, "SharedPreferences are useful for simple key/value data. They are also easy to inspect in a debug build.");
        Ui.muted(this, card, "Hint: try adb run-as and look for shared_prefs/campus_profile.xml.");
    }
}
