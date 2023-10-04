package com.example.legalify.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.legalify.Activity.ChatActivity;
import com.example.legalify.Adapter.MessageRVAdapter;
import com.example.legalify.R;
import com.example.legalify.model.MessageModal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private EditText message_edt;
    private RecyclerView chatsRV;
    private ImageView send_iv;
    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";
    private RequestQueue mRequestQueue;
    private ArrayList < MessageModal > messageModalArrayList;
    private MessageRVAdapter messageRVAdapter;


    private static final String ARG_PARAM1="param1";
    private static final String ARG_PARAM2="param2";

    private String mParam1;
    private String mParam2;

    public ChatFragment () {
    }

    public static ChatFragment newInstance ( String param1, String param2 ) {
        ChatFragment fragment=new ChatFragment();
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

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate( R.layout.fragment_chat, container, false );

        chatsRV = view.findViewById(R.id.idRVChats);
        message_edt = view.findViewById(R.id.message_edt);
        send_iv = view.findViewById(R.id.send_iv);
        mRequestQueue = Volley.newRequestQueue( getActivity());
        mRequestQueue.getCache().clear();
        messageModalArrayList = new ArrayList<>();
        send_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v) {
                if (message_edt.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter your message..", Toast.LENGTH_SHORT).show();
                    return;
                }
                send_iv(message_edt.getText().toString());
                message_edt.setText("");
            }
        });
        messageRVAdapter = new MessageRVAdapter(messageModalArrayList, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        chatsRV.setLayoutManager(linearLayoutManager);
        chatsRV.setAdapter(messageRVAdapter);

        return view;
    }

    private void send_iv(String userMsg) {
        messageModalArrayList.add(new MessageModal(userMsg, USER_KEY));
        messageRVAdapter.notifyDataSetChanged();
        String url = "http://api.brainshop.ai/get?bid=178059&key=odolfXbq2lJuKpmI&uid=[uid]&msg=Hello" + userMsg;
        RequestQueue queue = Volley.newRequestQueue(getActivity());
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
                Toast.makeText(getActivity(), "No response from the bot..", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonObjectRequest);
    }
}