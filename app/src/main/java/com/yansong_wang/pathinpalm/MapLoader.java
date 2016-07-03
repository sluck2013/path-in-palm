package com.yansong_wang.pathinpalm;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;

import com.google.common.base.Preconditions;

/**
 * Created by ywang on 7/3/2016.
 */
public class MapLoader {
    private final ImageView mapImageView;
    private final Resources resources;

    public MapLoader(ImageView mapImageView, Resources resources) {
        this.mapImageView = mapImageView;
        this.resources = resources;
    }

    public void loadMap(final @DrawableRes int... mapImageResourceIds) {
        Drawable[] mapLayers = loadMapLayers(mapImageResourceIds);
        LayerDrawable layerDrawable = new LayerDrawable(mapLayers);
        mapImageView.setImageDrawable(layerDrawable);
    }

    private Drawable[] loadMapLayers(final @DrawableRes int... mapImageResourceIds) {
        Drawable[] layers = new Drawable[mapImageResourceIds.length];
        int index = 0;
        for (final int resourceId : mapImageResourceIds) {
            layers[index++] = resources.getDrawable(resourceId);
        }
        return layers;
    }
}
