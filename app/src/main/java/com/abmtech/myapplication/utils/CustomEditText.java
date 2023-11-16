package com.abmtech.myapplication.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;

public class CustomEditText extends androidx.appcompat.widget.AppCompatEditText {


    public void setHandleDismissingKeyboard(
            handleDismissingKeyboard handleDismissingKeyboard) {
        this.handleDismissingKeyboard = handleDismissingKeyboard;
    }

    private handleDismissingKeyboard handleDismissingKeyboard;

    public interface handleDismissingKeyboard {
        public void dismissKeyboard();
    }

    @SuppressLint("NewApi")
    public CustomEditText(Context context, AttributeSet attrs,
                          int defStyleAttr, int defStyleRes) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public CustomEditText(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
    }


    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_UP) {
            handleDismissingKeyboard.dismissKeyboard();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}