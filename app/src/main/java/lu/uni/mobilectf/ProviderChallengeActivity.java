package lu.uni.mobilectf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class ProviderChallengeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = Ui.base(this, "Content Provider Notes", "A provider exposes structured incident notes.");
        LinearLayout card = Ui.card(this, root);
        Ui.text(this, card, "Content providers are designed for structured data sharing. When exported without a permission, they can become a direct data leak.");
        Ui.muted(this, card, "Hint: query content://lu.uni.mobilectf.notes/evidence with adb.");
    }
}
