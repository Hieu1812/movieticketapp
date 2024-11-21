package com.example.duanvexemphim.models;

import android.net.Uri;

public class Photo {
    private String resourceId;

    public Photo(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
