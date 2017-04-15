package com.xing.bottomdialogsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.xing.bottomdialoglib.BottomDialog;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        final BottomDialog.Builder builder = new BottomDialog.Builder(this);
        builder.addButton("拍照", BottomDialog.BackgroundType.TOP, new BottomDialog.OnDialogClickListener() {
            @Override
            public void onDialogItemClick() {

            }
        });
        builder.addButton("从相册中选择", BottomDialog.BackgroundType.MIDDLE, new BottomDialog.OnDialogClickListener() {
            @Override
            public void onDialogItemClick() {
            }
        });
        builder.addButton("查看大图", BottomDialog.BackgroundType.BOTTOM, new BottomDialog.OnDialogClickListener() {
            @Override
            public void onDialogItemClick() {
                Toast.makeText(MainActivity.this, "查看大图", Toast.LENGTH_LONG).show();
            }
        });
        builder.addButton("取消", BottomDialog.BackgroundType.SINGLE, new BottomDialog.OnDialogClickListener() {
            @Override
            public void onDialogItemClick() {
            }
        });
        BottomDialog dialog = builder.build();
        dialog.show();
    }
}
