package com.example.gemapp.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gemapp.Details;
import com.example.gemapp.R;
import com.example.gemapp.Retrofit.INodeJS;
import com.example.gemapp.Retrofit.RetrofitClient;
import com.example.gemapp.models.Interventions;
import com.example.gemapp.models.Taches;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class TachesAdapter extends  RecyclerView.Adapter<com.example.gemapp.Adapters.TachesAdapter.myViewHolder> {

    Context mContext;

    private List<Taches> mData;
    TextView nom, duree, prixheure, dateaction;
    public static INodeJS iNodeJS;


    public TachesAdapter(Context mContext, List<Taches> mDataa) {
        this.mContext = mContext;
        this.mData = mDataa;
    }
    public void notifyChange(List<Taches> taches){
        this.mData = taches;
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.task_item, parent, false);
        myViewHolder vv = new myViewHolder(v);
        return vv;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        final Taches taches = mData.get(position);
        if (taches.getRefernce().equals("false")){
            nom.setText(taches.getNom());
            duree.setText(taches.getDuree());
            prixheure.setText(taches.getPrixheure());
            dateaction.setText(taches.getDateaction());
        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            nom = itemView.findViewById(R.id.nomtache);
            duree = itemView.findViewById(R.id.dureetache);
            prixheure = itemView.findViewById(R.id.prixheure);
            dateaction = itemView.findViewById(R.id.datetache);


        }
    }
}
