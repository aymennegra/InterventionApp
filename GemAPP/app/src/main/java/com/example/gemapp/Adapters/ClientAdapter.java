package com.example.gemapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gemapp.R;
import com.example.gemapp.Retrofit.INodeJS;
import com.example.gemapp.models.Clients;
import com.example.gemapp.models.Taches;

import java.util.List;


public class ClientAdapter extends  RecyclerView.Adapter<com.example.gemapp.Adapters.ClientAdapter.myViewHolder> {

    Context mContext;

    private List<Clients> mData;
    TextView nom, tel, email, adresse;
    public static INodeJS iNodeJS;


    public ClientAdapter(Context mContext, List<Clients> mDataa) {
        this.mContext = mContext;
        this.mData = mDataa;
    }
    public void notifyChange(List<Clients> clients){
        this.mData = clients;
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.client_item, parent, false);
        myViewHolder vv = new myViewHolder(v);
        return vv;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        final Clients client = mData.get(position);

            nom.setText(client.getNom());
            tel.setText(client.getTel());
            adresse.setText(client.getAdresse());
            email.setText(client.getEmail());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            nom = itemView.findViewById(R.id.nom_client);
            tel = itemView.findViewById(R.id.tel_client);
            adresse = itemView.findViewById(R.id.adresse_client);
            email = itemView.findViewById(R.id.email_client);


        }
    }
}