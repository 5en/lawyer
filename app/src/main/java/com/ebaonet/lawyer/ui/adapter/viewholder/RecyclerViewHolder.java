package com.ebaonet.lawyer.ui.adapter.viewholder;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 适用于RecyclerView的ViewHolder
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private ViewHolderImpl mHolderImpl;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mHolderImpl = new ViewHolderImpl(itemView);
    }

    public Context getContext() {
        return mHolderImpl.mItemView.getContext();
    }

    public <T extends View> T findViewById(int viewId) {
        return mHolderImpl.findViewById(viewId);
    }

    public View getItemView() {
        return mHolderImpl.getItemView();
    }

    public RecyclerViewHolder setText(int viewId, int stringId) {
        mHolderImpl.setText(viewId, stringId);
        return this;
    }

    public RecyclerViewHolder setText(int viewId, String text) {
        mHolderImpl.setText(viewId, text);
        return this;
    }

    public RecyclerViewHolder setTextColor(int viewId, int color) {
        mHolderImpl.setTextColor(viewId, color);
        return this;
    }

    public RecyclerViewHolder setBackgroundColor(int viewId, int color) {
        mHolderImpl.setBackgroundColor(viewId, color);
        return this;
    }

    public RecyclerViewHolder setBackgroundResource(int viewId, int resId) {
        mHolderImpl.setBackgroundResource(viewId, resId);
        return this;
    }

    public RecyclerViewHolder setBackgroundDrawable(int viewId, Drawable drawable) {
        mHolderImpl.setBackgroundDrawable(viewId, drawable);
        return this;
    }

    public RecyclerViewHolder setFresco(int viewId, String imgID) {
        SimpleDraweeView image=findViewById(viewId);
        image.setImageURI(Uri.parse(imgID));
        return this;
    }
    @TargetApi(16)
    public RecyclerViewHolder setBackground(int viewId, Drawable drawable) {
        mHolderImpl.setBackground(viewId, drawable);
        return this;
    }

    public RecyclerViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        mHolderImpl.setImageBitmap(viewId, bitmap);
        return this;
    }

    public RecyclerViewHolder setImageResource(int viewId, int resId) {
        mHolderImpl.setImageResource(viewId, resId);
        return this;
    }

    public RecyclerViewHolder setImageDrawable(int viewId, Drawable drawable) {
        mHolderImpl.setImageDrawable(viewId, drawable);
        return this;
    }

    public RecyclerViewHolder setImageDisplay(int viewId, String url) {
        mHolderImpl.setImageDisplay(viewId, url);
        return this;
    }

    public RecyclerViewHolder setImageDrawable(int viewId, Uri uri) {
        mHolderImpl.setImageDrawable(viewId, uri);
        return this;
    }

    public void setVisibility(int viewId, int visible) {
        View target = findViewById(viewId);
        target.setVisibility(visible);
    }

    @TargetApi(16)
    public RecyclerViewHolder setImageAlpha(int viewId, int alpha) {
        mHolderImpl.setImageAlpha(viewId, alpha);
        return this;
    }

    public RecyclerViewHolder setChecked(int viewId, boolean checked) {
        mHolderImpl.setChecked(viewId, checked);
        return this;
    }

    public RecyclerViewHolder setProgress(int viewId, int progress) {
        mHolderImpl.setProgress(viewId, progress);
        return this;
    }

    public RecyclerViewHolder setProgress(int viewId, int progress, int max) {
        mHolderImpl.setProgress(viewId, progress, max);
        return this;
    }

    public RecyclerViewHolder setMax(int viewId, int max) {
        mHolderImpl.setMax(viewId, max);
        return this;
    }

    public RecyclerViewHolder setRating(int viewId, float rating) {
        mHolderImpl.setRating(viewId, rating);
        return this;
    }

    public RecyclerViewHolder setRating(int viewId, float rating, int max) {
        mHolderImpl.setRating(viewId, rating, max);
        return this;
    }

    public RecyclerViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        mHolderImpl.setOnClickListener(viewId, listener);
        return this;
    }

    public RecyclerViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        mHolderImpl.setOnTouchListener(viewId, listener);
        return this;
    }

    public RecyclerViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        mHolderImpl.setOnLongClickListener(viewId, listener);
        return this;
    }

    public RecyclerViewHolder setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
        mHolderImpl.setOnItemClickListener(viewId, listener);
        return this;
    }

    public RecyclerViewHolder setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
        mHolderImpl.setOnItemLongClickListener(viewId, listener);
        return this;
    }

    public RecyclerViewHolder setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener) {
        mHolderImpl.setOnItemSelectedClickListener(viewId, listener);
        return this;
    }

    public RecyclerViewHolder setAnimation(int viewId, Animation animation) {
        mHolderImpl.setAnimation(viewId, animation);
        return this;
    }

    public RecyclerViewHolder setTag(int viewId, Object data) {
        mHolderImpl.setTag(viewId, data);
        return this;
    }

    public RecyclerViewHolder setClickable(int viewId, boolean clickable) {
        mHolderImpl.setClickable(viewId, clickable);
        return this;
    }


}
