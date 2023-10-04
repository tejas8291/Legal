package com.example.legalify.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.legalify.Adapter.MessageRVAdapter;
import com.example.legalify.R;
import com.example.legalify.model.MessageModal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private EditText message_edt;
    private RecyclerView chatsRV;
    private ImageView send_iv;
    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";
    private RequestQueue mRequestQueue;
    private ArrayList < MessageModal > messageModalArrayList;
    private MessageRVAdapter messageRVAdapter;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_chat );
        chatsRV = findViewById(R.id.idRVChats);
        message_edt = findViewById(R.id.message_edt);
        send_iv = findViewById(R.id.send_iv);
        mRequestQueue = Volley.newRequestQueue(ChatActivity.this);
        mRequestQueue.getCache().clear();

        messageModalArrayList = new ArrayList<>();

        send_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v) {
                if (message_edt.getText().toString().isEmpty()) {
                    Toast.makeText(ChatActivity.this, "Please enter your message..", Toast.LENGTH_SHORT).show();
                    return;
                }
                send_iv(message_edt.getText().toString());
                message_edt.setText("");
            }
        });
        messageRVAdapter = new MessageRVAdapter(messageModalArrayList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatActivity.this, RecyclerView.VERTICAL, false);
        chatsRV.setLayoutManager(linearLayoutManager);
        chatsRV.setAdapter(messageRVAdapter);

    }
    private void send_iv(String userMsg) {
        messageModalArrayList.add(new MessageModal(userMsg, USER_KEY));
        messageRVAdapter.notifyDataSetChanged();
        String url = "http://api.brainshop.ai/get?bid=178059&key=odolfXbq2lJuKpmI&uid=[uid]&msg=Hello" + userMsg;
        RequestQueue queue = Volley.newRequestQueue(ChatActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( Request.Method.GET, url, null, new Response.Listener< JSONObject >() {
            @Override
            public void onResponse( JSONObject response) {
                try {
                    String botResponse = response.getString("cnt");
                    messageModalArrayList.add(new MessageModal(botResponse, BOT_KEY));
                    messageRVAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    messageModalArrayList.add(new MessageModal("No response", BOT_KEY));
                    messageRVAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse( VolleyError error) {
                // error handling.
                messageModalArrayList.add(new MessageModal("Sorry no response found", BOT_KEY));
                Toast.makeText(ChatActivity.this, "No response from the bot..", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonObjectRequest);
    }
}