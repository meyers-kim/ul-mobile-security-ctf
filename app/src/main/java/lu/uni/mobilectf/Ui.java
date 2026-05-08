package lu.uni.mobilectf;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

final class Ui {
    private static final int BLUE = Color.rgb(0, 47, 108);
    private static final int RED = Color.rgb(227, 6, 19);
    private static final int BACKGROUND = Color.rgb(244, 247, 251);
    private static final int CARD = Color.WHITE;
    private static final int TEXT = Color.rgb(21, 34, 56);
    private static final int MUTED = Color.rgb(91, 105, 125);

    private Ui() {}

    static LinearLayout base(Activity activity, String title, String subtitle) {
        ScrollView scroll = new ScrollView(activity);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(BACKGROUND);

        LinearLayout outer = new LinearLayout(activity);
        outer.setOrientation(LinearLayout.VERTICAL);
        int pad = dp(activity, 16);
        outer.setPadding(pad, pad, pad, pad);
        scroll.addView(outer, new ScrollView.LayoutParams(
                ScrollView.LayoutParams.MATCH_PARENT,
                ScrollView.LayoutParams.WRAP_CONTENT
        ));

        LinearLayout header = new LinearLayout(activity);
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setGravity(Gravity.CENTER_VERTICAL);
        header.setPadding(dp(activity, 16), dp(activity, 16), dp(activity, 16), dp(activity, 16));
        header.setBackground(rounded(CARD, dp(activity, 18), BLUE, 1));
        outer.addView(header, matchWrapMargins(activity, 0, 0, 0, 14));

        ImageView logo = new ImageView(activity);
        logo.setImageResource(R.drawable.ul_ctf_logo);
        header.addView(logo, new LinearLayout.LayoutParams(dp(activity, 72), dp(activity, 72)));

        LinearLayout headerText = new LinearLayout(activity);
        headerText.setOrientation(LinearLayout.VERTICAL);
        headerText.setPadding(dp(activity, 14), 0, 0, 0);
        header.addView(headerText, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        TextView course = new TextView(activity);
        course.setText("University of Luxembourg · Security of Mobiles");
        course.setTextColor(RED);
        course.setTextSize(12f);
        course.setTypeface(Typeface.DEFAULT_BOLD);
        headerText.addView(course);

        TextView titleView = new TextView(activity);
        titleView.setText(title);
        titleView.setTextColor(TEXT);
        titleView.setTextSize(24f);
        titleView.setTypeface(Typeface.DEFAULT_BOLD);
        titleView.setPadding(0, dp(activity, 4), 0, 0);
        headerText.addView(titleView);

        TextView subtitleView = new TextView(activity);
        subtitleView.setText(subtitle);
        subtitleView.setTextColor(MUTED);
        subtitleView.setTextSize(14f);
        subtitleView.setPadding(0, dp(activity, 6), 0, 0);
        headerText.addView(subtitleView);

        activity.setContentView(scroll);
        return outer;
    }

    static LinearLayout card(Activity activity, LinearLayout root) {
        LinearLayout card = new LinearLayout(activity);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(activity, 16), dp(activity, 14), dp(activity, 16), dp(activity, 14));
        card.setBackground(rounded(CARD, dp(activity, 16), Color.rgb(220, 228, 238), 1));
        root.addView(card, matchWrapMargins(activity, 0, 0, 0, 12));
        return card;
    }

    static TextView section(Activity activity, LinearLayout root, String value) {
        TextView view = new TextView(activity);
        view.setText(value);
        view.setTextColor(BLUE);
        view.setTextSize(18f);
        view.setTypeface(Typeface.DEFAULT_BOLD);
        view.setPadding(dp(activity, 2), dp(activity, 12), dp(activity, 2), dp(activity, 6));
        root.addView(view);
        return view;
    }

    static TextView text(Activity activity, LinearLayout root, String value) {
        TextView view = new TextView(activity);
        view.setText(value);
        view.setTextColor(TEXT);
        view.setTextSize(16f);
        view.setLineSpacing(2f, 1.05f);
        view.setPadding(dp(activity, 2), dp(activity, 6), dp(activity, 2), dp(activity, 6));
        root.addView(view);
        return view;
    }

    static TextView muted(Activity activity, LinearLayout root, String value) {
        TextView view = text(activity, root, value);
        view.setTextColor(MUTED);
        view.setTextSize(14f);
        return view;
    }

    static Button button(Activity activity, LinearLayout root, String label, View.OnClickListener listener) {
        Button button = new Button(activity);
        button.setText(label);
        button.setAllCaps(false);
        button.setTextColor(Color.WHITE);
        button.setTextSize(15f);
        button.setTypeface(Typeface.DEFAULT_BOLD);
        button.setPadding(dp(activity, 14), dp(activity, 10), dp(activity, 14), dp(activity, 10));
        button.setBackground(rounded(BLUE, dp(activity, 12), BLUE, 0));
        button.setOnClickListener(listener);
        root.addView(button, matchWrapMargins(activity, 0, 6, 0, 6));
        return button;
    }

    static Button secondaryButton(Activity activity, LinearLayout root, String label, View.OnClickListener listener) {
        Button button = button(activity, root, label, listener);
        button.setTextColor(BLUE);
        button.setBackground(rounded(Color.WHITE, dp(activity, 12), BLUE, 1));
        return button;
    }

    static void open(Activity activity, Class<?> target) {
        activity.startActivity(new Intent(activity, target));
    }

    static int dp(Activity activity, int value) {
        float density = activity.getResources().getDisplayMetrics().density;
        return Math.round(value * density);
    }

    private static GradientDrawable rounded(int color, int radius, int strokeColor, int strokeWidth) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radius);
        if (strokeWidth > 0) {
            drawable.setStroke(strokeWidth, strokeColor);
        }
        return drawable;
    }

    private static LinearLayout.LayoutParams matchWrapMargins(Activity activity, int l, int t, int r, int b) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(dp(activity, l), dp(activity, t), dp(activity, r), dp(activity, b));
        return params;
    }
}
