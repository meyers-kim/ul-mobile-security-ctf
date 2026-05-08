package lu.uni.mobilectf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class InstrumentationLabActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = Ui.base(this, "Instrumentation Lab", "Static instrumentation can change program behavior in a controlled lab.");
        LinearLayout card = Ui.card(this, root);
        Ui.text(this, card, "This screen has a locked branch. Decode the APK with apktool, patch the branch in Smali, rebuild, sign, and reinstall.");
        if (gateOpen()) {
            Ui.text(this, card, unlockFlag());
        } else {
            Ui.text(this, card, "Gate closed. The branch currently returns false.");
        }
    }

    private boolean gateOpen() {
        return false;
    }

    private String unlockFlag() {
        return decode(new int[]{
                65, 75, 70, 64, 124, 116, 106, 102, 107, 110, 88, 96, 102, 115, 98, 88, 97, 107, 110, 119, 119, 98, 99, 122
        });
    }

    private static String decode(int[] encoded) {
        StringBuilder out = new StringBuilder();
        for (int value : encoded) {
            out.append((char) (value ^ 7));
        }
        return out.toString();
    }
}
