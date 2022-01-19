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

public class InterventionsAdapter extends  RecyclerView.Adapter<com.example.gemapp.Adapters.InterventionsAdapter.myViewHolder> {

    Context mContext;
    CheckBox check;
    private List<Interventions> mData;
    TextView titre, comment, heuredebut, heurefin;

    public InterventionsAdapter(Context mContext, List<Interventions> mDataa) {
        this.mContext = mContext;
        this.mData = mDataa;
    }
    public void notifyChange(List<Interventions> interv){
        this.mData = interv;
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.card_item, parent, false);
        myViewHolder vv = new myViewHolder(v);
        return vv;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        final Interventions interventions = mData.get(position);
        titre.setText(interventions.getTitre());
        comment.setText(interventions.getCommentaires());
        heuredebut.setText(interventions.getHeuredebutplan());
        heurefin.setText(interventions.getHeurefinplan());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            titre = itemView.findViewById(R.id.titre);
            comment = itemView.findViewById(R.id.comment);
            heuredebut = itemView.findViewById(R.id.heuredebut);
            heurefin = itemView.findViewById(R.id.heurefin);
            check = itemView.findViewById(R.id.checkbox);
            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                    if (isChecked) {
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                        builder.setTitle("Décocher ?").setMessage("Etes-vous sùr de vouloir décocher cette intervention ?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int id){
                                        buttonView.setChecked(false);
                                    }
                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                buttonView.setChecked(true);
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }
                }
            });
           itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition(); // gets item position
                    if (position != RecyclerView.NO_POSITION) {
                        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("interventions", MODE_PRIVATE);
                        Interventions ce = mData.get(position);

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("titre", ce.getTitre());
                        editor.putInt("id_intervention", ce.getId());
                        editor.putString("commentaire", ce.getCommentaires());
                        editor.putString("heure_debut", ce.getHeuredebutplan());
                        editor.putString("heure_fin", ce.getHeurefinplan());


                        editor.apply();
                    }
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), Details.class));
                }
            });

        }
    }
}
