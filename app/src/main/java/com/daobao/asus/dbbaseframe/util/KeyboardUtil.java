package com.daobao.asus.dbbaseframe.util;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


/**
 * 键盘工具类
 */
public class KeyboardUtil {

    /**
     * 隐藏键盘
     */
    public static void hideSoftInput(Activity acitivity) {
        InputMethodManager imm = (InputMethodManager) acitivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(acitivity.getWindow().getDecorView().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示键盘
     */
    public static void showSoftInput(EditText et) {
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) et.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    /**
     * 展示键盘并选中最后一个
     */
    public static void showSoftInputSelect(EditText et) {
        showSoftInputSelect(et, 300);
    }

    /**
     * 展示键盘并选中最后一个
     */
    public static void showSoftInputSelect(final EditText et, long delayMillis) {
        et.postDelayed(new Runnable() {

            @Override
            public void run() {
                showSoftInput(et);
                et.setSelection(et.getText().length());
            }
        }, delayMillis);
    }

}
