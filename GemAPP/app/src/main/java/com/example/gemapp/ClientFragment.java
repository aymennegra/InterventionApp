package com.example.gemapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gemapp.Adapters.ClientAdapter;
import com.example.gemapp.Retrofit.INodeJS;
import com.example.gemapp.Retrofit.RetrofitClient;
import com.example.gemapp.models.Clients;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ClientFragment extends Fragment {

    RecyclerView clientRecycler;
    List<Clients> ClientsList;
    Context mContext;
    public static INodeJS iNodeJS;
    Button back;

    public ClientFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_client, container, false);
        clientRecycler = view.findViewById(R.id.clientRecycler);
        iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);

        Call<List<Clients>> callclient = iNodeJS.getClient();
        callclient.enqueue(new Callback<List<Clients>>() {
            @Override
            public void onResponse(Call<List<Clients>> call, Response<List<Clients>> response) {
                ClientsList = response.body();
                ClientAdapter adapterclient = new ClientAdapter(mContext, ClientsList);
                clientRecycler.setAdapter(adapterclient);
                clientRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));

            }
            @Override
            public void onFailure(Call<List<Clients>> call, Throwable t) {

            }
        });



        return view;


    }
}