package com.example.omri.showoff;

import android.net.Uri;
import android.util.Log;

import com.example.omri.showoff.Helpers.QueryParams;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asaf on 26-Jul-15.
 */
public class ParseBrandNetworkHelper implements NetworkHelper {

    private DataManager brandManager;
    private final int GET_ALL_BRANDS = 0;

    public ParseBrandNetworkHelper(DataManager brandManager){
        this.brandManager = brandManager;
    }

    @Override
    public void fetch(QueryParams params) {
        switch(params.getQueryNum()){
            case GET_ALL_BRANDS:
                fetchAllBrands();
                break;
        }
    }

    @Override
    public void update(QueryParams params) {

    }

    public void fetchAllBrands(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Brand");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e == null) {
                    if(list.size() == 0){
                        Log.d("ParseBrandNetworkHelper","List size is 0");
                        return;
                    }
                    List<Brand> brands = ParseToBrandTransformer.transformBrands(list);
                    brandManager.getResults(brands);
                }else
                    Log.d("ParseBrandNetworkHelper",e.toString());
            }
        });
    }

    private static class ParseToBrandTransformer{

        public static List<Brand> transformBrands(List<ParseObject> list){

            List<Brand> brands = new ArrayList<>();
            for(ParseObject object: list){
                Brand brand = new Brand();
                brand.setBrandId(object.getObjectId());
                brand.setBrandLogo(Uri.parse(object.getParseFile("logo").getUrl()));
                brand.setName(object.getString("brandName"));
                brands.add(brand);
            }
            return brands;
        }
    }
}
