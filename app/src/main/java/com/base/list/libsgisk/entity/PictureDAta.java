package com.base.list.libsgisk.entity;

import android.graphics.Bitmap;

/**
 * Created by Glanms on 16/4/21.
 * Anim Activity使用的图片model
 */
public class PictureData {
    public int resourceId;
    public String description;
    public Bitmap thumbnail;

    public PictureData(int resourceId, String description, Bitmap thumbnail) {
        this.resourceId = resourceId;
        this.description = description;
        this.thumbnail = thumbnail;
    }
}
