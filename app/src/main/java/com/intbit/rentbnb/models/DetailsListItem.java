package com.intbit.rentbnb.models;

import android.graphics.drawable.Drawable;

/**
 * Created by Adiba on 12/08/2016.
 */
public class DetailsListItem implements ListItem {
    private String title;
    private String value;
    private Drawable icon;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DetailsListItem(String title, String value, Drawable icon) {
        this.title = title;
        this.value = value;
        this.icon = icon;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
