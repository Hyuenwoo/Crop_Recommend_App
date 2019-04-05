package com.example.hwhan.rrealfinal;

public class Recommend_crop_model {

    private int cropImage;
    private String cropTitle;
    private String cropContent;

    public Recommend_crop_model(int cropImage, String cropTitle, String cropContent) {
        this.cropImage = cropImage;
        this.cropTitle = cropTitle;
        this.cropContent = cropContent;
    }

    public int getCropImage() {
        return cropImage;
    }

    public void setCropImage(int cropImage) {
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
