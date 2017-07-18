package com.toast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.*;

import java.util.Objects;

public class Toast extends ReactContextBaseJavaModule implements LifecycleEventListener {

    private android.widget.Toast mostRecentToast;

    // note that webView.isPaused() is not Xwalk compatible, so tracking it poor-man style
    private boolean isPaused;

    public Toast(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RCTToast";
    }

    @ReactMethod
    public void show(final String message, final int duration, final String position, ReadableMap styles) throws Exception {

        if (this.isPaused) {
            return;
        }
        final String backgroundColor = styles.hasKey("backgroundColor") ? styles.getString("backgroundColor") : "#000000";
        final String color = styles.hasKey("color") ? styles.getString("color") : "#ffffff";
        final int width = styles.hasKey("width") ? styles.getInt("width") : 50;
        final int height = styles.hasKey("height") ? styles.getInt("height") : 50;
        final int paddingLeft = styles.hasKey("paddingLeft") ? styles.getInt("paddingLeft") : 10;
        final int paddingRight = styles.hasKey("paddingRight") ? styles.getInt("paddingRight") : 10;
        final int paddingTop = styles.hasKey("paddingTop") ? styles.getInt("paddingTop") : 5;
        final int paddingBottom = styles.hasKey("paddingBottom") ? styles.getInt("paddingTop") : 5;
        final int fontSize = styles.hasKey("fontSize") ? styles.getInt("fontSize") : 12;
        final int lineHeight = styles.hasKey("lineHeight") ? styles.getInt("lineHeight") : 10;
        final int cornerRadius = styles.hasKey("cornerRadius") ? styles.getInt("cornerRadius") : 10;
        final int lines = styles.hasKey("lines") ? styles.getInt("lines") : 3;
        final float letterSpacing = styles.hasKey("letterSpacing") ? (float)styles.getDouble("letterSpacing") : 0;

        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                android.widget.Toast toast = android.widget.Toast.makeText(
                        getReactApplicationContext(),
                        message,
                        "short".equals(duration) ? android.widget.Toast.LENGTH_SHORT : android.widget.Toast.LENGTH_LONG);
                View view = toast.getView();
                TextView text = (TextView) view.findViewById(android.R.id.message);
                GradientDrawable gd = new GradientDrawable();
                view.setBackgroundColor(Color.parseColor(backgroundColor));
                view.setPadding(paddingLeft,paddingTop,paddingRight,paddingBottom);
                view.setMinimumHeight(height);
                view.setMinimumWidth(width);
                text.setTextSize(fontSize);
                text.setLines(lines);
                text.setMaxLines(lines);
                text.setHeight(lineHeight);
                text.setLetterSpacing(letterSpacing);
                text.setTypeface(Typeface.SANS_SERIF);
                gd.setStroke(2, Color.parseColor(backgroundColor));
                gd.setColor(Color.parseColor(backgroundColor));
                gd.setCornerRadius(cornerRadius);
                view.setBackground(gd);
                if ("top".equals(position)) {
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 20);
                } else if ("bottom".equals(position)) {
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 20);
                } else if ("center".equals(position)) {
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 20);
                } else {
                    FLog.e("RCTToast", "invalid position. valid options are 'top', 'center' and 'bottom'");
                    return;
                }
                toast.setView(view);
                toast.show();
                mostRecentToast = toast;
            }
        });
    }

    @ReactMethod
    public void hide() throws Exception {
        if (mostRecentToast != null) {
            mostRecentToast.cancel();
        }
    }

    @Override
    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
    }

    @Override
    public void onHostPause() {
        if (mostRecentToast != null) {
            mostRecentToast.cancel();
        }
        this.isPaused = true;
    }

    @Override
    public void onHostResume() {
        this.isPaused = false;
    }

    @Override
    public void onHostDestroy() {
        this.isPaused = true;
    }
}