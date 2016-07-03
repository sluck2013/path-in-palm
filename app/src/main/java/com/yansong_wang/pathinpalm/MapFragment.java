package com.yansong_wang.pathinpalm;

import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MapFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CoordinatorLayout layout = (CoordinatorLayout) inflater.inflate(R.layout.map_wrapper_view, container, false);
        loadMap(layout);
        return layout;
    }

    private void loadMap(final View view) {
        ImageView mapImageView = (ImageView) view.findViewById(R.id.img_view_map);
        Drawable[] mapLayers = loadMapLayers();
        LayerDrawable layerDrawable = new LayerDrawable(mapLayers);
        mapImageView.setImageDrawable(layerDrawable);
    }

    private Drawable[] loadMapLayers() {
        Resources resources = getResources();
        Drawable[] layers = new Drawable[2];
        layers[0] = resources.getDrawable(R.drawable.pathmap_weekend);
        layers[1] = resources.getDrawable(R.drawable.pathmap_weekday);
        return layers;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.options_menu_item_map_weekday) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
