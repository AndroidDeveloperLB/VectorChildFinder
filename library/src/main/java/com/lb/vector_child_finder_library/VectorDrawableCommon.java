package com.lb.vector_child_finder_library;

/**
 * Created by ${Deven} on 1/31/18.
 */

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.graphics.drawable.TintAwareDrawable;

/**
 * Internal common delegation shared by VectorDrawableCompat and AnimatedVectorDrawableCompat
 */
@SuppressLint("RestrictedApi")
abstract class VectorDrawableCommon extends Drawable implements TintAwareDrawable {
    /**
     * Obtains styled attributes from the theme, if available, or unstyled
     * resources if the theme is null.
     *
     * @hide
     */
    protected static TypedArray obtainAttributes(
            @NonNull final Resources res, @Nullable final Resources.Theme theme, final AttributeSet set, @NonNull final int[] attrs) {
        if (theme == null) return res.obtainAttributes(set, attrs);
        return theme.obtainStyledAttributes(set, attrs, 0, 0);
    }

    // Drawable delegation for Lollipop and above.
    Drawable mDelegateDrawable;

    @Override
    public void setColorFilter(final int color, @NonNull final PorterDuff.Mode mode) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setColorFilter(color, mode);
            return;
        }
        super.setColorFilter(color, mode);
    }

    @Override
    public ColorFilter getColorFilter() {
        if (this.mDelegateDrawable != null)
            return DrawableCompat.getColorFilter(this.mDelegateDrawable);
        return null;
    }

    @Override
    protected boolean onLevelChange(final int level) {
        if (this.mDelegateDrawable != null) return this.mDelegateDrawable.setLevel(level);
        return super.onLevelChange(level);
    }

    @Override
    protected void onBoundsChange(@NonNull final Rect bounds) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setBounds(bounds);
            return;
        }
        super.onBoundsChange(bounds);
    }

    @Override
    public void setHotspot(final float x, final float y) {
        // API >= 21 only.
        if (this.mDelegateDrawable != null) DrawableCompat.setHotspot(this.mDelegateDrawable, x, y);
    }

    @Override
    public void setHotspotBounds(final int left, final int top, final int right, final int bottom) {
        if (this.mDelegateDrawable != null)
            DrawableCompat.setHotspotBounds(this.mDelegateDrawable, left, top, right, bottom);
    }

    @Override
    public void setFilterBitmap(final boolean filter) {
        if (this.mDelegateDrawable != null) this.mDelegateDrawable.setFilterBitmap(filter);
    }

    @Override
    public void jumpToCurrentState() {
        if (this.mDelegateDrawable != null) this.mDelegateDrawable.jumpToCurrentState();
    }

    @Override
    public void applyTheme(@NonNull final Resources.Theme t) {
        // API >= 21 only.
        if (this.mDelegateDrawable != null) DrawableCompat.applyTheme(this.mDelegateDrawable, t);
    }

    @Override
    public void clearColorFilter() {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.clearColorFilter();
            return;
        }
        super.clearColorFilter();
    }

    @NonNull
    @Override
    public Drawable getCurrent() {
        if (this.mDelegateDrawable != null) return this.mDelegateDrawable.getCurrent();
        return super.getCurrent();
    }

    @Override
    public int getMinimumWidth() {
        if (this.mDelegateDrawable != null) return this.mDelegateDrawable.getMinimumWidth();
        return super.getMinimumWidth();
    }

    @Override
    public int getMinimumHeight() {
        if (this.mDelegateDrawable != null) return this.mDelegateDrawable.getMinimumHeight();
        return super.getMinimumHeight();
    }

    @Override
    public boolean getPadding(@NonNull final Rect padding) {
        if (this.mDelegateDrawable != null) return this.mDelegateDrawable.getPadding(padding);
        return super.getPadding(padding);
    }

    @NonNull
    @Override
    public int[] getState() {
        if (this.mDelegateDrawable != null) return this.mDelegateDrawable.getState();
        return super.getState();
    }


    @Override
    public Region getTransparentRegion() {
        if (this.mDelegateDrawable != null) return this.mDelegateDrawable.getTransparentRegion();
        return super.getTransparentRegion();
    }

    @Override
    public void setChangingConfigurations(final int configs) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setChangingConfigurations(configs);
            return;
        }
        super.setChangingConfigurations(configs);
    }

    @Override
    public boolean setState(@NonNull final int[] stateSet) {
        if (this.mDelegateDrawable != null) return this.mDelegateDrawable.setState(stateSet);
        return super.setState(stateSet);
    }
}
