package com.zhou.gitproject.circleimageview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.zhou.gitproject.R;

/**
 * 自定义ImageView显示圆形、圆角头像
 * Created by zhou on 2015/08/04.
 */
public class CircleImageView extends ImageView {

    private int type;//图片类型
    private static final int TYPE_CIRCLE = 0;//圆形
    private static final int TYPE_ROUND = 1;//圆角

    private static final ScaleType SCALE_TYPE = ScaleType.CENTER_CROP;//只支持中心缩放

    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 1;

    private static final int DEFAULT_BORDER_WIDTH = 0;//默认无边框
    private static final int DEFAULT_BORDER_COLOR = android.graphics.Color.parseColor("#000000");//边框默认黑色
    private static final int DEFAULT_ROUND_RADIUS = 20;//圆角大小默认20

    private int mAddBorderRoundRadius;//加边框之后的圆角半径
    private RectF mAddBorderRect = new RectF();//加边框之后的范围

    private final RectF mDrawableRect = new RectF();
    private final RectF mBorderRect = new RectF();

    private final Matrix mShaderMatrix = new Matrix();
    private final Paint mBitmapPaint = new Paint();
    private final Paint mBorderPaint = new Paint();

    private int mBorderColor = DEFAULT_BORDER_COLOR;
    private int mBorderWidth = DEFAULT_BORDER_WIDTH;
    private int mRoundRadius = DEFAULT_ROUND_RADIUS;

    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private int mBitmapHeight;

    private float mDrawableRadius;
    private float mAddBorderRadius;

    private boolean mReady;
    private boolean mSetupPending;

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        super.setScaleType(SCALE_TYPE);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyle, 0);

        mBorderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_border_width, DEFAULT_BORDER_WIDTH);
        mBorderColor = a.getColor(R.styleable.CircleImageView_border_color, DEFAULT_BORDER_COLOR);
        mRoundRadius = a.getDimensionPixelSize(R.styleable.CircleImageView_round_radius, DEFAULT_ROUND_RADIUS);
        type = a.getInt(R.styleable.CircleImageView_img_type, TYPE_CIRCLE);// 默认为Circle
        a.recycle();

        mReady = true;

        if (mSetupPending) {
            setup();
            mSetupPending = false;
        }
    }

    @Override
    public ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if (scaleType != SCALE_TYPE) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType));
        }
    }

    private void setup() {
        if (!mReady) {
            mSetupPending = true;
            return;
        }
        if (mBitmap == null) {
            return;
        }
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setShader(mBitmapShader);

        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);

        mBitmapHeight = mBitmap.getHeight();
        mBitmapWidth = mBitmap.getWidth();

        //获取原始图片的范围
        mBorderRect.set(0, 0, getWidth(), getHeight());
        //主图片范围（原始图片-边框宽度）
        mDrawableRect.set(mBorderWidth, mBorderWidth, mBorderRect.width() - mBorderWidth,
                mBorderRect.height() - mBorderWidth);
        //边框宽度最好设成偶数
        if (type == TYPE_CIRCLE) {
            //圆形
            //边框半径
            mAddBorderRadius = Math.min(mBorderRect.height() / 2, mBorderRect.width() / 2);
            //主图片半径
            mDrawableRadius = Math.min(mDrawableRect.height() / 2, mDrawableRect.width() / 2);
        } else if (type == TYPE_ROUND) {
            //圆角
            //加边框范围：
            mAddBorderRect.set(mBorderWidth / 2, mBorderWidth / 2,
                    mBorderRect.width() - mBorderWidth / 2, mBorderRect.width() - mBorderWidth / 2);
            //加边框的圆角半径
            mAddBorderRoundRadius = mRoundRadius + mBorderWidth / 2;
        }

        updateShaderMatrix();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        if (type == TYPE_CIRCLE) {
            //画主图片
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mDrawableRadius, mBitmapPaint);
            if (mBorderWidth > 0) {
                //画边框
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, mAddBorderRadius - mBorderWidth / 2, mBorderPaint);
            }
        } else if (type == TYPE_ROUND) {
            //圆角
            canvas.drawRoundRect(mDrawableRect, mRoundRadius, mRoundRadius, mBitmapPaint);
            if (mBorderWidth > 0) {
                //画边框
                canvas.drawRoundRect(mAddBorderRect, mAddBorderRoundRadius, mAddBorderRoundRadius, mBorderPaint);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mBitmap = bm;
        setup();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap = getBitmapFromDrawable(drawable);
        setup();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        mBitmap = getBitmapFromUri(uri);
        setup();
    }

    private Bitmap getBitmapFromUri(Uri uri) {
        if (uri == null) {
            return null;
        }
        try {
            Bitmap bitmap;
            bitmap = BitmapFactory.decodeFile(uri.getPath());
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap bitmap;
            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }

    }

    private void updateShaderMatrix() {

        float scale;
        float dx = 0;
        float dy = 0;

        mShaderMatrix.set(null);

        if (mBitmapWidth * mDrawableRect.height() > mDrawableRect.width() * mBitmapHeight) {
            scale = mDrawableRect.height() / (float) mBitmapHeight;
            dx = (mDrawableRect.width() - mBitmapWidth * scale) * 0.5f;
        } else {
            scale = mDrawableRect.width() / (float) mBitmapWidth;
            dy = (mDrawableRect.height() - mBitmapHeight * scale) * 0.5f;
        }

        mShaderMatrix.setScale(scale, scale);
        mShaderMatrix.postTranslate((int) (dx + 0.5f) + mBorderWidth, (int) (dy + 0.5f) + mBorderWidth);
        mBitmapShader.setLocalMatrix(mShaderMatrix);
    }

    //- - - - - - - 对外公布的额外方法 - - - - - - -

    /**
     * 设置边框颜色
     *
     * @param borderColor
     */
    public void setBorderColor(int borderColor) {
        if (borderColor == mBorderColor) {
            return;
        }
        mBorderColor = borderColor;
        mBorderPaint.setColor(mBorderColor);
        invalidate();
    }

    /**
     * 获取边框颜色
     *
     * @return
     */
    public int getBorderColor() {
        return mBorderColor;
    }

    /**
     * 设置边框宽度
     *
     * @param borderWidth
     */
    public void setBorderWidth(int borderWidth) {
        if (borderWidth == mBorderWidth) {
            return;
        }
        mBorderWidth = borderWidth;
        setup();
    }

    /**
     * 获取边框宽度
     *
     * @return
     */
    public int getBorderWidth() {
        return mBorderWidth;
    }

    /**
     * 设置圆角角度
     *
     * @param radius
     */
    public void setRoundRadius(int radius) {
        if (radius == mRoundRadius) {
            return;
        }
        mRoundRadius = radius;
        setup();
    }

    /**
     * 获取圆角角度
     *
     * @return
     */
    public int getRoundRadius() {
        return mRoundRadius;
    }

}