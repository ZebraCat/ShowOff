package com.example.omri.showoff;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
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
    private OnCameraConfirmedListener listener;
    static final int REQUEST_IMAGE_CAPTURE = 1;


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
        showoffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
        discountText = (TextView)getActivity().findViewById(R.id.brand_discount_text);
        brandLogo = (ImageView)getActivity().findViewById(R.id.brand_discount_logo);
        Picasso.with(getActivity()).load(currentBrand.getBrandLogo()).into(brandLogo);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            try{
                listener = (OnCameraConfirmedListener)getActivity();
                listener.onCameraConfirm(imageBitmap);
            }
            catch(ClassCastException e){
                throw new ClassCastException(getActivity().toString() + "Must Implement OnCameraConfirmedListener");
            }
        }
    }

    public interface OnCameraConfirmedListener{
        void onCameraConfirm(Bitmap bitmap);
    }
}
