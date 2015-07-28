package com.example.omri.showoff;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A placeholder fragment containing a simple view.
 */
public class BrandDiscountActivityFragment extends Fragment {

    private Button showoffButton;
    private ImageView brandLogo;
    private TextView discountText;

    public BrandDiscountActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_brand_discount, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Brand currentBrand = getActivity().getIntent().getParcelableExtra("Brand");
        showoffButton = (Button)getActivity().findViewById(R.id.show_off_button);
        discountText = (TextView)getActivity().findViewById(R.id.brand_discount_text);
        brandLogo = (ImageView)getActivity().findViewById(R.id.brand_discount_logo);
        Picasso.with(getActivity()).load(currentBrand.getBrandLogo()).into(brandLogo);
    }
}
