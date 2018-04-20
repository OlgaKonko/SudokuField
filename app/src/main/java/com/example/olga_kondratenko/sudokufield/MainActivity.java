package com.example.olga_kondratenko.sudokufield;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends Activity {
    public ImageView[][] buttons;
    private TableLayout tableLayout;
    private int FIELD_SIZE = 9;
    private int LINES_SIZE = 4;
    private int LINE_SIZE = 150;
    private int lineWidth;
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = findViewById(R.id.sudokuField);
        buttons = new ImageView[FIELD_SIZE][FIELD_SIZE];
        drawField();
    }

    private void drawField() {
        int tableWidth = getTableSize() -(tableLayout.getPaddingLeft()+tableLayout.getPaddingRight());
        lineWidth = tableWidth/LINE_SIZE;
        width = ((tableWidth -(lineWidth*LINES_SIZE))/(FIELD_SIZE));
        TableRow row;
        for (int x = 0; x < FIELD_SIZE; x++) {
            if (x%3 == 0){
                createHorizontalLine();
            }
            row = new TableRow(this);
            for (int y = 0; y < FIELD_SIZE; y++) {
                createButton(x, y, row);
            }
            createLine(row, lineWidth, width);
            tableLayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
        createHorizontalLine();
    }

    private void createButton(int x, int y, TableRow row) {
        if (y%3 == 0){
           createLine(row, lineWidth, width);
        }
        ImageView button = new ImageView(this);
        buttons[x][y] = button;
        button.setImageResource(R.drawable.shape);
        button.setOnClickListener(new MoveListener(x, y));
        row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        button.getLayoutParams().width = width;
        button.getLayoutParams().height = width;

        button.setScaleType(ImageView.ScaleType.FIT_XY);
    }
    private void createLine(TableRow row, int width, int height){
       ImageView button = new ImageView(this);
        button.setImageResource(R.drawable.fill);
        row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        button.getLayoutParams().width = width;
        button.getLayoutParams().height = height;

        button.setScaleType(ImageView.ScaleType.FIT_XY);
    }
    private void createHorizontalLine(){
        TableRow row = new TableRow(this);
        for (int y = 0; y < FIELD_SIZE; y++) {
            if (y%3 == 0){
                createLine(row, lineWidth, lineWidth);
            }
            createLine(row, width, lineWidth);
        }
        createLine(row, lineWidth, lineWidth);
        tableLayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
    }

  /*  private void setVerticalLine(int width, TableRow row) {
        ImageView button = new ImageView(this);
        button.setImageResource(R.drawable.color_shape);
        row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));
        button.getLayoutParams().width = getTableSize();
        button.getLayoutParams().height = LINE_SIZE;
        //button.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    private void setHorizontalLine(TableRow row) {
        ImageView button = new ImageView(this);
        button.setImageResource(R.drawable.color_shape);
        row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        button.getLayoutParams().height = LINE_SIZE;
        button.setScaleType(ImageView.ScaleType.FIT_XY);
    }*/

    private int getTableSize() {
        Point size = new Point();
        WindowManager w = getWindowManager();
        w.getDefaultDisplay().getSize(size);
       return size.x ;
    }
}
