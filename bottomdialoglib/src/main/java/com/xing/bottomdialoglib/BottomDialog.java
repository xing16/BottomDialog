package com.xing.bottomdialoglib;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by star.tao on 2017/4/15.
 */

public class BottomDialog extends Dialog {

    public BottomDialog(Context context) {
        this(context, R.style.BottomDialogStyle);
    }

    public BottomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = getScreenWidth();
        params.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(params);


    }

    private int getScreenWidth() {
        return getContext().getResources().getDisplayMetrics().widthPixels;
    }


    public static class Builder {

        private Context mContext;

        private BottomDialog dialog;

        private LinearLayout dialogLayout;

        private LinearLayout.LayoutParams layoutParams;

        public Builder(Context context) {
            mContext = context;
            dialog = new BottomDialog(context);
            dialogLayout = new LinearLayout(context);
            dialogLayout.setOrientation(LinearLayout.VERTICAL);


        }

        public Builder addButton(String text, BackgroundType type, final OnDialogClickListener listener) {
            Button button = new Button(mContext);
            button.setTextColor(Color.parseColor("#FF0BC6FF"));
            button.setText(text);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = dp2Px(10);
            layoutParams.rightMargin = dp2Px(10);
            button.setPadding(0, dp2Px(10), 0, dp2Px(10));
            if (type == BackgroundType.TOP) {
                button.setBackgroundResource(R.drawable.dialog_btn_top);
                View view = new View(mContext);
            } else if (type == BackgroundType.MIDDLE) {
                button.setBackgroundResource(R.drawable.dialog_btn_middle);
            } else if (type == BackgroundType.BOTTOM) {
                button.setBackgroundResource(R.drawable.dialog_btn_bottom);
            } else if (type == BackgroundType.SINGLE) {
                button.setBackgroundResource(R.drawable.dialog_btn_single);
                layoutParams.topMargin = dp2Px(10);
                layoutParams.bottomMargin = dp2Px(10);
            }

            button.setLayoutParams(layoutParams);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (listener != null) {
                        listener.onDialogItemClick();
                    }
                }
            });
            dialogLayout.addView(button);
            return this;
        }

        public Builder addButton(int resId, BackgroundType type, OnDialogClickListener listener) {
            addButton(mContext.getResources().getString(resId), type, listener);
            return this;
        }

        public BottomDialog build() {
            dialog.setContentView(dialogLayout);
            return dialog;
        }

        public void dismiss() {
            dialog.dismiss();
        }


        private int dp2Px(int dpValue) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue,
                    mContext.getResources().getDisplayMetrics());
        }

    }


    public enum BackgroundType {
        TOP,  // 第一个button
        MIDDLE,  // 中间button
        BOTTOM,  // 底部button
        SINGLE   // 单独button
    }


    public interface OnDialogClickListener {
        void onDialogItemClick();
    }


}
