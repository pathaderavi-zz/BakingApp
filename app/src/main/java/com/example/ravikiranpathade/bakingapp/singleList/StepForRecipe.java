package com.example.ravikiranpathade.bakingapp.singleList;

/**
 * Created by ravikiranpathade on 10/20/17.
 */

public class StepForRecipe {
    public StepForRecipe(int id, String shortDesc, String desc, String videoUrl, String thumbnailUrl) {
        this.id = id;
        this.shortDesc = shortDesc;
        this.desc = desc;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getId() {
        return id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getDesc() {
        return desc;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    int id;
    String shortDesc;
    String desc;
    String videoUrl;
    String thumbnailUrl;

}
