package com.yansong_wang.pathinpalm;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MapFragment extends Fragment {

    private MapLoader mapLoader;
    private RelativeLayout fragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        fragmentView = (RelativeLayout) inflater.inflate(R.layout.map_content_view, container, false);
        ImageView mapImageView = (ImageView) fragmentView.findViewById(R.id.img_view_map);
        mapLoader = new MapLoader(mapImageView, getResources());
        loadMap(R.drawable.pathmap_weekend, R.drawable.pathmap_weekday);
        return fragmentView;
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

    private void loadMap(@DrawableRes final int... resourceId) {
        try {
            mapLoader.loadMap(resourceId);
        } catch (NullPointerException e) {
            Log.e(getResources().getString(R.string.app_name), "loadMap: mapResourceId is null");
        }
    }
}
