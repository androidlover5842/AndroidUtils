package com.androidlover5842.androidUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class ProgressButton extends AppCompatButton {

    private Context context;
    private AttributeSet attrs;
    private RotateAnimation progressRotateAnim;
    private String text;
    private Drawable background;
    private int defaultLoadingColor=Color.BLACK;
    private Paint progressPaint=new Paint();
    private RectF rect= new RectF();

    private boolean loading;
    private int loadingColor;

    public ProgressButton(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public ProgressButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        this.attrs=attrs;
        init();
    }

    public ProgressButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        this.attrs=attrs;
        init();
    }

    private void init(){
        if (attrs!=null){
            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.ProgressButton, 0, 0);
            progressRotateAnim = new RotateAnimation(0f,
                    360f, Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF,
                    0.5f);
            text=getText().toString();
            background=getBackground();
            if (background instanceof ColorDrawable)
                defaultLoadingColor=((ColorDrawable) background).getColor();
            loadingColor=a.getColor(R.styleable.ProgressButton_loadingColor,defaultLoadingColor);
            loading=a.getBoolean(R.styleable.ProgressButton_loading,false);
            setupProgress();

            progressRotateAnim.setAnimationListener(new AnimationStat());
            progressRotateAnim.cancel();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (loading){
            float mRadius = (float) (this.getHeight() - 2 * dip2px(2)) / 2;
            rect.set(this.getWidth()/2- mRadius,
                    (this.getHeight()/2) - mRadius,
                    this.getWidth()/2 + mRadius,
                    this.getHeight()/2 + mRadius);
            canvas.drawArc(rect, 90, 90, false, progressPaint);
        }
    }

    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void loading(boolean loading) {
        this.loading = loading;
        startProgress(loading,false);
    }

    private void startProgress(boolean loading,boolean fromAnimation) {
        if (loading) {
            setClickable(false);
            progressPaint.setAlpha(255);
            setText("");
            setBackground(null);
            if (!fromAnimation) {
                this.startAnimation(progressRotateAnim);
            }

        }
        else {
            if (progressRotateAnim!=null) {
                clearAnimation();
            }
            setBackground(background);
            setText(text);
            progressPaint.setAlpha(0);
            setClickable(true);
            invalidate();
        }
    }

    @SuppressLint("ResourceType")
    public void loadingColor(@ColorInt int colorID) {
        this.loadingColor = getContext().getResources().getColor(colorID);
    }

    private void setupProgress(){
        progressRotateAnim.setRepeatCount(Animation.INFINITE);
        progressRotateAnim.setInterpolator(new LinearInterpolator());
        progressRotateAnim.setFillAfter(true);
        progressRotateAnim.setDuration(400);
        progressPaint.setColor(loadingColor);
        progressPaint.setStrokeWidth(dip2px(5));
        progressPaint.setAntiAlias(true);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStyle(Paint.Style.STROKE);
    }

    private class AnimationStat implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {
            startProgress(true,true);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            startProgress(false,true);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
