package com.example.legalify.Fragment;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import com.example.legalify.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    private RelativeLayout edit_profile_rl;
    private CircleImageView profile_civ;

    private static final String ARG_PARAM1="param1";
    private static final String ARG_PARAM2="param2";

    private static final int CAMERA_REQUEST = 100;
    private static final int SELECT_PICTURE = 200;

    private String mParam1;
    private String mParam2;

    public ProfileFragment () {

    }

    public static ProfileFragment newInstance ( String param1, String param2 ) {
        ProfileFragment fragment=new ProfileFragment();
        Bundle args=new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1=getArguments().getString( ARG_PARAM1 );
            mParam2=getArguments().getString( ARG_PARAM2 );
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        View view =  inflater.inflate( R.layout.fragment_profile, container, false );
        edit_profile_rl =(RelativeLayout) view.findViewById( R.id.edit_profile_rl );
        profile_civ =(CircleImageView) view.findViewById( R.id.profile_civ );

        edit_profile_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature( Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.alert_dailog);
                dialog.findViewById( R.id.camera_rl );
                dialog.findViewById( R.id.gallery_rl );
                final RelativeLayout cameraImageView = (RelativeLayout) dialog.findViewById(R.id.camera_rl);
                final RelativeLayout galleryImageView = (RelativeLayout) dialog.findViewById(R.id.gallery_rl);

                cameraImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent( android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
                        startActivityForResult( Intent.createChooser( intent, "Select Picture" ), CAMERA_REQUEST);
                        dialog.dismiss();

                    }
                });

                galleryImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        return view;
    }
    public void onActivityResult ( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo=(Bitmap) data.getExtras().get( "data" );
            profile_civ.setImageBitmap( photo );
            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            // Uri tempUri=getImageUri( getApplicationContext(), photo );
            //  image = getRealPathFromURI( tempUri );

        }

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri=data.getData();
                if (null != selectedImageUri) {
                    profile_civ.setImageURI( selectedImageUri );
                    //image = getPathOfSavedImage(uriToBitmap(selectedImageUri));

                   }
            }
        }
    }
}