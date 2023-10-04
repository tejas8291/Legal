package com.example.legalify.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.legalify.R;


public class DiscoverActivity extends AppCompatActivity {
    private AppCompatButton login_btn;

    private TextView register_tv;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_discover );

        login_btn = (AppCompatButton) findViewById( R.id.login_btn );
        register_tv = (TextView) findViewById( R.id.register_tv );

        login_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(DiscoverActivity.this, LoginActivity.class);
                startActivity( intent );
            }
        } );

        register_tv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(DiscoverActivity.this, RegisterActivity.class);
                startActivity( intent );
            }
        } );
    }
}