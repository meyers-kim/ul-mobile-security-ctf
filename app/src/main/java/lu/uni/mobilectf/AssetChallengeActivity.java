package lu.uni.mobilectf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class AssetChallengeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = Ui.base(this, "Assets and Archives", "Evidence is bundled inside the APK package.");
        LinearLayout card = Ui.card(this, root);
        Ui.text(this, card, "One clue is only encoded, not encrypted.");
        Ui.text(this, card, "Another clue has the wrong extension after being exported from the incident cache.");
        Ui.muted(this, card, "Hint: APK files are ZIP files. Inspect assets/.");
    }
}
