package com.zhaopengfei.p2p.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.zhaopengfei.p2p.R;

/**
 * Created by admin on 2017/3/13.
 */

/*
* 自定义控件
* 第一步 分析 继承 view / viewgroup / 其它控件
*       一般来说一个功能的实现(一个控件)就继承view
*       一般来说二个控件组合以上继承viewGroup
*       对某些控件进行扩展的时候就继承该控件
* */
public class MyProgress extends View {

    private int sweepArc = 60;
    private int sweepColor = Color.RED;//进度条的颜色
    private int roundColor;//圆环的颜色
    private Paint paint;
    private int measureWidth;
    private int measureHeight;
    private int roundWidt = 10; //圆环的宽度

    public MyProgress(Context context) {
        super(context);
        init();
    }

    public MyProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        //自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.progress);

        roundColor = typedArray.getColor(R.styleable.progress_roundColor, Color.GRAY);

        sweepColor = typedArray.getColor(R.styleable.progress_sweepColor, Color.RED);

        sweepArc = typedArray.getColor(R.styleable.progress_sweepArc, 0);

        typedArray.recycle();
    }


    private void init() {

        //画笔
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);//抗尺
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cx = measureWidth / 2;
        int cy = measureHeight / 2;
        int radius = cx - roundWidt / 2;

        paint.setColor(roundColor);
        //设置圆环的宽度
        paint.setStrokeWidth(roundWidt);
        canvas.drawCircle(cx, cy, radius, paint);

        RectF rectf = new RectF(roundWidt / 2, roundWidt / 2, measureWidth - roundWidt / 2, measureHeight - roundWidt / 2);

        paint.setColor(sweepColor);

        canvas.drawArc(rectf, 0, sweepArc * 360 / 100, false, paint);

        //画文字
        String text = sweepArc + "%";
        Rect rect = new Rect();

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(0);
        paint.setTextSize(30);
        paint.getTextBounds(text, 0, text.length(), rect);
        float tx = measureWidth / 2 - rect.width() / 2;
        float ty = measureHeight / 2 + rect.height() / 2;
        canvas.drawText(text, tx, ty, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureHeight = getMeasuredHeight(); //控件的高
        measureWidth = getMeasuredWidth();// 控件的宽
    }

    //设置进度条
    public void setProgress(int progress) {
        sweepArc = progress;
         /*
        * invalidate 是在主线程强制刷新
        * postinvalidate 是在分线程强制刷新
        * */
        postInvalidate();

    }
}
