package com.example.omri.showoff.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.omri.showoff.Brand;
import com.example.omri.showoff.BrandDataManager;
import com.example.omri.showoff.BrandDiscountActivity;
import com.example.omri.showoff.MyAdapter;
import com.example.omri.showoff.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asaf on 26-Jul-15.
 */
public class BrandAdapter extends ArrayAdapter<Brand> implements MyAdapter{

    private List<Brand> brands;
    private List<Brand> orig;
    private Context context;
    private BrandDataManager dataManager;


    public BrandAdapter(Context context,List<Brand> brands){
        super(context,R.layout.brand_item,brands);
        this.brands = brands;
        this.context = context;
        this.dataManager = new BrandDataManager(this);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.brand_item,parent,false);
            imageView = (ImageView)convertView.findViewById(R.id.brand_logo);
            Log.d("BrandAdapter","In If - convert view == null");
        }
        else{
            Log.d("BrandAdapter","In Else - convert view != null");
            imageView = (ImageView) convertView;
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TO DO - change on click listener to open next fragment
                Brand current = brands.get(position);
                Intent intent = new Intent(context,BrandDiscountActivity.class);
                intent.putExtra("Brand",current);
                context.startActivity(intent);
            }
        });
        Picasso.with(context).load(brands.get(position).getBrandLogo()).into(imageView);
        return imageView;
    }

    @Override
    public void getResults(List list) {
        brands.addAll(list);
        notifyDataSetChanged();
    }

    public void getNextBrands(){
        dataManager.getNextBrands();
    }

    public int getCount(){
        return brands.size();
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Brand> results = new ArrayList<Brand>();
                if (orig == null)
                    orig = brands;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final Brand brand : orig) {
                            if (brand.getName().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(brand);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                brands = (ArrayList<Brand>)results.values;
                notifyDataSetChanged();
            }
        };
    }
}
