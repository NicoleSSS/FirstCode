package com.example.first.ui;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @ClassName: Fruit
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/4/3 13:30
 * @Version: 1.0
 */
public class Fruit implements Parcelable {

    private String name;
    private int imageId;

    public Fruit(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(imageId);
    }

    public static final Parcelable.Creator<Fruit> CREATOR = new Parcelable.Creator<Fruit>(){
        @Override
        public Fruit createFromParcel(Parcel source) {
            Fruit fruit = new Fruit(source.readString(), source.readInt());
            return fruit;
        }

        @Override
        public Fruit[] newArray(int size) {
            return new Fruit[size];
        }
    };
}
