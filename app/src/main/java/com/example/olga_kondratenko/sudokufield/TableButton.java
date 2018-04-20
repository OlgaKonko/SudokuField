package com.example.olga_kondratenko.sudokufield;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;


@SuppressLint("AppCompatCustomView")
public class TableButton extends TableLayout {
    public ImageView[][] buttons;
    private static int SELLS_SIZE = 3;
    private int sellSize;

    public TableButton(Context context) {
        super(context);
    }
    public void fillSell(int size){
        this.getLayoutParams().width = size;
        this.getLayoutParams().height = size;
        sellSize = size/SELLS_SIZE;
        buttons = new ImageView[SELLS_SIZE][SELLS_SIZE];
        TableRow row;
        for (int x = 0; x < SELLS_SIZE; x++) {

            row = new TableRow(this.getContext());
            for (int y = 0; y < SELLS_SIZE; y++) {
                createButton(x, y, row);
            }

            this.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    private void createButton(int x, int y, TableRow row) {

        ImageView button = new ImageView(this.getContext());
        buttons[x][y] = button;
        button.setImageResource(R.drawable.light_fill);

        row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        button.getLayoutParams().width = sellSize;
        button.getLayoutParams().height = sellSize;

        button.setScaleType(ImageView.ScaleType.FIT_XY);

    }

    public void setImageResource(int resId) {
        Drawable drawable = ContextCompat.getDrawable(this.getContext(),resId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.setForeground(drawable);
        }
    }


}
