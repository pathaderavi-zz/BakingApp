package com.example.ravikiranpathade.bakingapp.singleList;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ravikiranpathade on 10/20/17.
 */

public class StepForRecipe implements Parcelable{
    public StepForRecipe(int id, String shortDesc, String desc, String videoUrl, String thumbnailUrl) {
        this.id = id;
        this.shortDesc = shortDesc;
        this.desc = desc;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    protected StepForRecipe(Parcel in) {
        id = in.readInt();
        shortDesc = in.readString();
        desc = in.readString();
        videoUrl = in.readString();
        thumbnailUrl = in.readString();
    }

    public static final Creator<StepForRecipe> CREATOR = new Creator<StepForRecipe>() {
        @Override
        public StepForRecipe createFromParcel(Parcel in) {
            return new StepForRecipe(in);
        }

        @Override
        public StepForRecipe[] newArray(int size) {
            return new StepForRecipe[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(shortDesc);
        dest.writeString(desc);
        dest.writeString(videoUrl);
        dest.writeString(thumbnailUrl);

    }
}
