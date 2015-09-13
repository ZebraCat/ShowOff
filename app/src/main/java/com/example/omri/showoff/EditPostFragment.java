package com.example.omri.showoff;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Asaf on 28-Jul-15.
 */
public class EditPostFragment extends Fragment{

    private EditText editText;
    private ImageView postImage;
    private Button share;
    private Bitmap photo;

    public EditPostFragment(){}

    public void setPhoto(Bitmap photo){
        this.photo = photo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_post_fragment,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editText = (EditText)getActivity().findViewById(R.id.post_edit_text);
        postImage = (ImageView)getActivity().findViewById(R.id.discount_post_image);
        postImage.setImageBitmap(photo);
        share = (Button)getActivity().findViewById(R.id.share_post_button);
        // TO DO - use facebook api to share the post
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
