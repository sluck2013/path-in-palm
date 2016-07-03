package com.yansong_wang.pathinpalm;

import android.content.res.Resources;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MapLoaderTest {

    @DrawableRes
    private static final int MAP_LAYER_WEEKEND_RESOURCE_ID = 4545;

    @DrawableRes
    private static final int MAP_LAYER_WEEKDAY_RESOURCE_ID = 3298;

    @Mock
    private Resources resources;

    @Mock
    private ImageView mapView;

    private MapLoader mapLoader;

    @Before
    public void setUp() {
        mapLoader = new MapLoader(mapView, resources);
    }

    @Test
    public void shouldLoadMap() {
        givenMapLayerResourcesExist();
        whenLoadMap();
        thenMapViewIsSet();
    }

    private void givenMapLayerResourcesExist() {
        when(resources.getDrawable(anyInt())).thenReturn(new DrawableContainer());
    }

    private void whenLoadMap() {
        mapLoader.loadMap(MAP_LAYER_WEEKEND_RESOURCE_ID, MAP_LAYER_WEEKDAY_RESOURCE_ID);
    }

    private void thenMapViewIsSet() {
        verify(mapView).setImageDrawable(Matchers.any(LayerDrawable.class));
    }

    @Test
    public void showThrowIllegalArgumentException_NullArgument() {
        try {
            whenLoadMap_ResourceIdIsNull();
        } catch (Exception e) {
            thenNullPointerExceptionIsThrown(e);
        }
    }

    private void whenLoadMap_ResourceIdIsNull() {
        mapLoader.loadMap(null);
    }

    private void thenNullPointerExceptionIsThrown(final Exception e) {
        assertThat(e, instanceOf(NullPointerException.class));
    }
}