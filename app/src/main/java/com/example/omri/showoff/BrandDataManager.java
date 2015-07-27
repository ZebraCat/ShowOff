package com.example.omri.showoff;

import com.example.omri.showoff.Helpers.QueryParams;

import java.util.List;

/**
 * Created by Asaf on 26-Jul-15.
 */
public class BrandDataManager implements DataManager {

    MyAdapter<Brand> adapter;
    NetworkHelper brandNetworkHelper;

    public BrandDataManager(MyAdapter<Brand> adapter){
        this.adapter = adapter;
        this.brandNetworkHelper = new ParseBrandNetworkHelper(this);
    }

    public void getNextBrands(){
        QueryParams params = new QueryParams();
        params.setQueryNum(0);
        brandNetworkHelper.fetch(params);
    }

    @Override
    public void getResults(List<? extends ShowoffItem> results) {
        adapter.getResults((List<Brand>)results);
    }
}
