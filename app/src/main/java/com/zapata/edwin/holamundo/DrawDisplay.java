package com.zapata.edwin.holamundo;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;



public class DrawDisplay extends View {

    private static final int SIZE = 60;
    private SparseArray<PointF> mActivePointers;
    private Paint mPaint;
    private int[] colors = {Color.BLUE, Color.GREEN, Color.MAGENTA, Color.BLACK, Color.CYAN, Color.GRAY, Color.LTGRAY, Color.YELLOW};
    public DataBaseE MBD;
    private Paint textPaint;

    public DrawDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        this.MBD = new DataBaseE(context);
    }

    private void initView() {
        mActivePointers = new SparseArray<PointF>();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // set painter color to a color you like mPaint.setColor(Color.BLUE); mPaint.setStyle(Paint.Style.FILL_AND_STROKE); textPaint = new
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(20);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int pointerIndex = event.getActionIndex();

        int pointerId = event.getPointerId(pointerIndex);

        int maskedAction = event.getActionMasked();

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                // We have a new pointer. Lets add it to the list of pointers
                PointF f = new PointF();
                f.x = event.getX(pointerIndex);
                f.y = event.getY(pointerIndex);
                mActivePointers.put(pointerId, f);
                break;
            }
            case MotionEvent.ACTION_MOVE: { // a pointer fue movido
                for (int size = event.getPointerCount(), i = 0; i < size; i++) {

                    PointF point = mActivePointers.get(event.getPointerId(i));

                    if (point != null) {
                        point.x = event.getX(i);
                        point.y = event.getY(i);
                    }
                }
                break;
            }

            case MotionEvent.ACTION_UP:{

            }
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL: {
                mActivePointers.remove(pointerId);
                break;
            }
        }

        invalidate();
        return true;
    }

    @Override

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        String texto = "";
        int indiceDB = MBD.recuperarDATOS().size();

        // draw all pointers
        for (int size = mActivePointers.size(), i = 0;  i < size; i++) {
            PointF point = mActivePointers.valueAt(i);
            if (point != null) {
                mPaint.setColor(colors[0 % 9]);
                canvas.drawCircle(point.x, point.y, SIZE, mPaint);
                Double PosX = Double.valueOf(point.x);
                Double PosY = Double.valueOf(point.y);
                texto = "Total dedos: " + mActivePointers.size() + " ";
                //canvas.drawText(texto, 5, 30, textPaint);
                //canvas.drawText(" Dedo: " + i + " (" + PosX + " , " + PosY + ")", 5, 60 + 30 * i, textPaint);
                //canvas.drawText(MBD.recuperarDato(8).getDedo() + "tamaño db: " + Integer.toString(indiceDB+1), 300, 300, textPaint);
                MBD.insertarDato(indiceDB+1, Integer.toString(i), PosX, PosY);
                indiceDB++;
            }
        }

    }

}
