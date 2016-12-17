package com.example.exceloid_android.quickhrmobileapp;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Exceloid-Android on 07-12-2016.
 */
public class CustomText extends TextView {
    /*public CustomText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
           // this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Italic.ttf"));
    }*/

   /* public CustomText(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Italic.ttf"));
    }*/

    public CustomText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public CustomText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public CustomText(Context context) {
        super(context);
    }
    public void setTypeface(Typeface tf, int style) {

        if (style == Typeface.BOLD) {
            super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/MaterialIcons-Regular.ttf"));
        } else {
            super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/MaterialIcons-Regular.ttf"));
        }
    }
}
