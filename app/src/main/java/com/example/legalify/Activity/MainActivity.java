package com.example.legalify.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.legalify.Adapter.MessageRVAdapter;
import com.example.legalify.Fragment.AddFragment;
import com.example.legalify.Fragment.ChatFragment;
import com.example.legalify.Fragment.HomeFragment;
import com.example.legalify.Fragment.ProfileFragment;
import com.example.legalify.R;
import com.example.legalify.model.MessageModal;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Fragment fragment = null;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        fragment=HomeFragment.newInstance( "", "" );
        getSupportFragmentManager().beginTransaction().replace( R.id.container, fragment, "TAG" ).commit();
        BottomNavigationView bottomNavigationView=(BottomNavigationView) findViewById( R.id.bottom_navigation );
        bottomNavigationView.setItemIconTintList( null );

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected ( @NonNull MenuItem item ) {
                        if (item.getItemId() == R.id.home) {
                            fragment=HomeFragment.newInstance( "", "" );
                            getSupportFragmentManager().beginTransaction().replace( R.id.container, fragment, "TAG" ).commit();
                        } else if (item.getItemId()== R.id.add) {
                            fragment=AddFragment.newInstance( "", "" );
                            getSupportFragmentManager().beginTransaction().replace( R.id.container, fragment, "TAG" ).commit();
                        } else if (item.getItemId()== R.id.plus) {
                            fragment=ChatFragment.newInstance( "", "" );
                            getSupportFragmentManager().beginTransaction().replace( R.id.container, fragment, "TAG" ).commit();
                        }else if (item.getItemId()== R.id.chat) {
                            fragment=ChatFragment.newInstance( "", "" );
                            getSupportFragmentManager().beginTransaction().replace( R.id.container, fragment, "TAG" ).commit();
                        }else if (item.getItemId()== R.id.profile) {
                            fragment=ProfileFragment.newInstance( "", "" );
                            getSupportFragmentManager().beginTransaction().replace( R.id.container, fragment, "TAG" ).commit();
                        }
                        return false;
                    }
                } );
    }

}

