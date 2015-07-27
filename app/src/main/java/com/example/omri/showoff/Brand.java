package com.example.omri.showoff;

import android.net.Uri;

import com.example.omri.showoff.ShowoffItem;

/**
 * Created by Asaf on 26-Jul-15.
 */
public class Brand implements ShowoffItem{

    private String brandId;
    private String name;
    private Uri brandLogo;


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
}
