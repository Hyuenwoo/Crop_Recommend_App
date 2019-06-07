package com.example.hwhan.rrealfinal;

import android.widget.ImageView;

public class Recommend_crop_model {

    private String cropImage;
    private String cropTitle;
    private String cropContent;
    private String locate;

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public Recommend_crop_model(String cropImage, String cropTitle, String cropContent, String locate) {
        this.cropImage = cropImage;
        this.cropTitle = cropTitle;
        this.cropContent = cropContent;
        this.locate = locate;
    }

    public String getCropImage() {
        return cropImage;
    }

    public void setCropImage(String cropImage) {
        this.cropImage = cropImage;
    }

    public String getCropTitle() {
        return cropTitle;
    }

    public void setCropTitle(String cropTitle) {
        this.cropTitle = cropTitle;
    }

    public String getCropContent() {
        return cropContent;
    }

    public void setCropContent(String cropContent) {
        this.cropContent = cropContent;
    }
}
