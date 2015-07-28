package com.example.omri.showoff;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.omri.showoff.ShowoffItem;

import java.io.Serializable;

/**
 * Created by Asaf on 26-Jul-15.
 */
public class Brand implements ShowoffItem,Parcelable{

    private String brandId;
    private String name;
    private Uri brandLogo;

    public Brand(){}

    public Uri getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(Uri brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brandId);
        dest.writeString(name);
        dest.writeString(brandLogo.toString());
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Brand> CREATOR = new Parcelable.Creator<Brand>() {
        public Brand createFromParcel(Parcel in) {
            return new Brand(in);
        }

        public Brand[] newArray(int size) {
            return new Brand[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Brand(Parcel in) {
        brandId = in.readString();
        name = in.readString();
        brandLogo = Uri.parse(in.readString());
    }
}
