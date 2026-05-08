package lu.uni.mobilectf;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SubmitFlagActivity extends Activity {
    private ProgressBar progressBar;
    private TextView progressText;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = Ui.base(this, "Flag Submit", "Paste recovered flags in the format FLAG{...}.");
        LinearLayout card = Ui.card(this, root);

        progressText = Ui.text(this, card, "Progress loading...");
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setMax(FlagValidator.totalFlags());
        card.addView(progressBar, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                Ui.dp(this, 24)
        ));

        EditText input = new EditText(this);
        input.setHint("FLAG{example}");
        input.setSingleLine(true);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        card.addView(input);

        result = Ui.text(this, card, "No flag submitted yet.");

        Ui.button(this, card, "Check flag", v -> {
            String value = input.getText().toString();
            int submitResult = ProgressStore.submit(this, value);

            if (submitResult == ProgressStore.NEW) {
                result.setText("Correct flag. Progress saved.");
                input.setText("");
            } else if (submitResult == ProgressStore.DUPLICATE) {
                result.setText("Correct flag, but it was already submitted on this device.");
            } else {
                result.setText("Wrong flag. Keep looking.");
            }
            updateProgress();
        });

        Ui.secondaryButton(this, card, "Reset local progress", v -> {
            ProgressStore.reset(this);
            result.setText("Progress reset on this device.");
            updateProgress();
        });

        updateProgress();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateProgress();
    }

    private void updateProgress() {
        if (progressBar == null || progressText == null) return;
        int solved = ProgressStore.solvedCount(this);
        int total = FlagValidator.totalFlags();
        progressBar.setProgress(solved);
        progressText.setText("Solved: " + solved + " / " + total + " flags");
    }
}
