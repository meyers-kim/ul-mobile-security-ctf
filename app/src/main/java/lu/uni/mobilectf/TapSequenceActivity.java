package lu.uni.mobilectf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TapSequenceActivity extends Activity {
    private final List<String> sequence = new ArrayList<>();
    private final List<String> expected = Arrays.asList("N", "E", "N", "W", "S", "E");
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = Ui.base(this, "Tap Sequence", "A sensor keypad was replaced with a direction pad.");
        LinearLayout card = Ui.card(this, root);
        Ui.text(this, card, "Hint: the correct route is hidden in the app logic and can also be found by observing the state machine.");
        status = Ui.text(this, card, "Current sequence: empty");

        Ui.button(this, card, "North", v -> press("N"));
        Ui.button(this, card, "East", v -> press("E"));
        Ui.button(this, card, "South", v -> press("S"));
        Ui.button(this, card, "West", v -> press("W"));
        Ui.secondaryButton(this, card, "Reset", v -> {
            sequence.clear();
            status.setText("Current sequence: empty");
        });
    }

    private void press(String value) {
        sequence.add(value);
        if (sequence.size() > expected.size()) {
            sequence.remove(0);
        }
        if (sequence.equals(expected)) {
            status.setText("Unlocked: FLAG{tap_sequence_replayed}");
        } else {
            status.setText("Current sequence: " + sequence);
        }
    }
}
