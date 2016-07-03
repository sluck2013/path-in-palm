package com.yansong_wang.pathinpalm;

import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MapFragment extends Fragment {

    private CoordinatorLayout fragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        fragmentView = (CoordinatorLayout) inflater.inflate(R.layout.map_wrapper_view, container, false);
        loadMap(R.drawable.pathmap_weekend, R.drawable.pathmap_weekday);
        return fragmentView;
    }

    private void loadMap(final @DrawableRes int... mapImageResourceIds) {
        ImageView mapImageView = (ImageView) fragmentView.findViewById(R.id.img_view_map);
        Drawable[] mapLayers = loadMapLayers(mapImageResourceIds);
        LayerDrawable layerDrawable = new LayerDrawable(mapLayers);
        mapImageView.setImageDrawable(layerDrawable);
    }

    private Drawable[] loadMapLayers(final @DrawableRes int... mapImageResourceIds) {
        Resources resources = getResources();
        Drawable[] layers = new Drawable[mapImageResourceIds.length];
        int index = 0;
        for (final int resourceId : mapImageResourceIds) {
            layers[index++] = resources.getDrawable(resourceId);
        }
        return layers;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.map_options, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.options_menu_item_map_weekday:
                loadMap(R.drawable.pathmap_weekday);
                break;
            case R.id.options_menu_item_map_weekend:
                loadMap(R.drawable.pathmap_weekend);
                break;
            case R.id.options_menu_item_map_show_all:
                loadMap(R.drawable.pathmap_weekend, R.drawable.pathmap_weekday);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

}
