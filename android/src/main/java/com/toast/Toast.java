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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Toast extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";

    private static final String POSITION_TOP_KEY = "TOP";
    private static final String POSITION_CENTER_KEY = "CENTER";
    private static final String POSITION_BOTTOM_KEY = "BOTTOM";

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
    public void show(final String message, final int duration, final int position, ReadableMap styles) throws Exception {

        if (this.isPaused) {
            return;
        }
        final String backgroundColor = styles.hasKey("backgroundColor") ? styles.getString("backgroundColor") : "#000000";
        final String color = styles.hasKey("color") ? styles.getString("color") : "#ffffff";
        final int width = styles.hasKey("width") ? styles.getInt("width") : 100;
        final int height = styles.hasKey("height") ? styles.getInt("height") : 200;
        final int paddingLeft = styles.hasKey("paddingLeft") ? styles.getInt("paddingLeft") : 0;
        final int paddingRight = styles.hasKey("paddingRight") ? styles.getInt("paddingRight") : 0;
        final int paddingTop = styles.hasKey("paddingTop") ? styles.getInt("paddingTop") : 0;
        final int paddingBottom = styles.hasKey("paddingBottom") ? styles.getInt("paddingTop") : 0;
        final int fontSize = styles.hasKey("fontSize") ? styles.getInt("fontSize") : 12;
        final int lineHeight = styles.hasKey("lineHeight") ? styles.getInt("lineHeight") : 10;
        final int cornerRadius = styles.hasKey("borderRadius") ? styles.getInt("borderRadius") : 5;
        final int lines = styles.hasKey("lines") ? styles.getInt("lines") : 3;
        final int borderWidth = styles.hasKey("borderWidth") ? styles.getInt("borderWidth") : 2;
        final int xOffset = styles.hasKey("xOffset") ? styles.getInt("xOffset") : 0;
        final int yOffset = styles.hasKey("yOffset") ? styles.getInt("yOffset") : 0;
        final float letterSpacing = styles.hasKey("letterSpacing") ? (float) styles.getDouble("letterSpacing") : 0;
        final String fontWeight = styles.hasKey("fontWeight") ? styles.getString("fontWeight") : "default";
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                android.widget.Toast toast = android.widget.Toast.makeText(
                        getReactApplicationContext(),
                        message,
                        duration);
                View view = toast.getView();
                TextView text = (TextView) view.findViewById(android.R.id.message);
                GradientDrawable gd = new GradientDrawable();
                gd.setStroke(borderWidth, Color.parseColor(backgroundColor));
                gd.setColor(Color.parseColor(backgroundColor));
                gd.setCornerRadius(cornerRadius);
                gd.setSize(width, height);
                view.setBackground(gd);
                try{
                    text.setTextColor(Color.parseColor(color));
                    text.setTextSize(fontSize);
                    text.setLines(lines);
                    text.setMaxLines(lines);
                    text.setHeight(lineHeight);
                    text.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
                    text.setTypeface(Typeface.SANS_SERIF);
                    if(fontWeight.equals("bold")){
                        text.setTypeface(Typeface.DEFAULT_BOLD);
                    }else{
                        text.setTypeface(Typeface.DEFAULT);
                    }
                    text.setLetterSpacing(letterSpacing);
                } catch (NoSuchMethodError e) {
                     // ignore
                }
                toast.setView(view);
                toast.setGravity(position, xOffset, yOffset);
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
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, android.widget.Toast.LENGTH_SHORT);
        constants.put(DURATION_LONG_KEY, android.widget.Toast.LENGTH_LONG);
        constants.put(POSITION_TOP_KEY, Gravity.TOP);
        constants.put(POSITION_CENTER_KEY, Gravity.CENTER);
        constants.put(POSITION_BOTTOM_KEY, Gravity.BOTTOM);
        return constants;
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
