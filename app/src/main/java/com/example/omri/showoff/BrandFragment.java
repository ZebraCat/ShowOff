package com.example.omri.showoff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SearchView;

import com.example.omri.showoff.Helpers.BrandAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asaf on 26-Jul-15.
 */
public class BrandFragment extends Fragment implements SearchView.OnQueryTextListener{

    private static final String ARG_SECTION_NUMBER = "section_number";
    private GridView gridView;
    private List<Brand> brands;
    private BrandAdapter adapter;
    private SearchView searchView;

    public static BrandFragment newInstance(int sectionNumber) {
        BrandFragment fragment = new BrandFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public BrandFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.brand_fragment,container,false);
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        gridView = (GridView)getActivity().findViewById(R.id.brand_grid_view);
        searchView = (SearchView)getActivity().findViewById(R.id.filter_brands);
        searchView.setQueryHint("Search Brands");
        searchView.setOnQueryTextListener(this);
        brands = new ArrayList<>();
        adapter = new BrandAdapter(getActivity(),brands);
        gridView.setAdapter(adapter);
        gridView.setTextFilterEnabled(true);
        adapter.getNextBrands();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            gridView.clearTextFilter();
        } else {
            gridView.setFilterText(newText);
        }
        return false;
    }
}
