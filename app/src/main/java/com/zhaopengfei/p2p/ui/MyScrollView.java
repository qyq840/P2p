package com.zhaopengfei.p2p.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by admin on 2017/3/13.
 */

public class MyScrollView extends ScrollView {
    private View childView; //字试图
    private int lastY; //最后Y

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //矩形
    private Rect rect = new Rect();
    private boolean isAnimtionEnd = true;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (getChildCount() == 0 || !isAnimtionEnd) {
            return super.onTouchEvent(ev);
        }
         /*
        * getY(); 相对于父布局的Y值
        * getrawY(); 相对于屏幕的Y值
        * */
        int eventY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //保存第一次触摸到的点

                lastY = eventY;
                break;

            case MotionEvent.ACTION_MOVE:
                if (isNeedMove()) {
                    //移动的量
                    int dy = eventY - lastY;
                    //记录一下原来的位置
                    if (rect.isEmpty()) {
                        //当我们没有记录的时候需要记录原来的位置
                        rect.set(childView.getLeft(), childView.getTop(),
                                childView.getRight(), childView.getBottom());
                    }

                    childView.layout(childView.getLeft(), childView.getTop() + dy / 2,
                             childView.getRight(),childView.getBottom() + dy / 2);
                }
                lastY = eventY;
                break;
            case MotionEvent.ACTION_UP:

                if (!rect.isEmpty() && isAnimtionEnd) {
                    int translateY = childView.getBottom() - rect.bottom;
                    //平移动画所移动的距离
                    TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -translateY);
                    animation.setDuration(200);

                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            isAnimtionEnd = false;   //当动画开始执行的时候 需要设置成false
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                            //清除动画
                            childView.clearAnimation();
                            //回到原来的记录位置
                            childView.layout(rect.left, rect.top, rect.right,rect.bottom);
                            //吧原来的记录轻质清楚
                            rect.setEmpty();
                            isAnimtionEnd =true;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    childView.startAnimation(animation);
                }
                break;
        }
        return super.onTouchEvent(ev);

    }

    //事件拦截事件 true拦截，反之不拦截
    private  int downy,lastx,downx;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isOnIntercept =false;
        int eventx = (int) ev.getX();
        int eventy = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastY =downy =eventy;
                lastx =downx =eventx;
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                int absx =Math.abs(eventx -downx);
                int absy =Math.abs(eventy -downy);
                if(absy >absx && absy >=20) {
                    isOnIntercept =true;
                }
                lastx =eventx;
                lastY =eventy;
                break;
        }
        return isOnIntercept;

    }

    public boolean isNeedMove() {
        //scrillew的高度
        int scrollViewHeight = this.getMeasuredHeight();
        //字试图的高度
        int childHeight = childView.getMeasuredHeight();

        int dy = childHeight - scrollViewHeight;
        int scrollY = getScrollY();
        if (scrollY <= 0 || scrollY >= dy) {
            return true;
        }
        return false;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            childView = getChildAt(0);
        }
    }
}
