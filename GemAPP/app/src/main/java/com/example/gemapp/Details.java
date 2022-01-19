package com.example.gemapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gemapp.Adapters.ClientAdapter;
import com.example.gemapp.Adapters.InterventionsAdapter;
import com.example.gemapp.Adapters.TachesAdapter;
import com.example.gemapp.Adapters.TachesEffectueAdapter;
import com.example.gemapp.Retrofit.INodeJS;
import com.example.gemapp.Retrofit.RetrofitClient;
import com.example.gemapp.models.Clients;
import com.example.gemapp.models.Interventions;
import com.example.gemapp.models.Taches;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Details extends AppCompatActivity {

    Button back,delete,edit,sync,carte;
    TextView titre , heuredebut,heurefin;
    public static INodeJS iNodeJS;
    Context mContext;
    List<Taches> TachesList;
    List<Clients> ClientsList;
    RecyclerView taskrec,taskeffect,clientrecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        back = findViewById(R.id.btn_back);
        heuredebut = findViewById(R.id.heuredebut);
        heurefin = findViewById(R.id.heurefin);
        titre = findViewById(R.id.titre);
        delete=findViewById(R.id.delete);
        edit=findViewById(R.id.edit);
        taskrec=findViewById(R.id.taskrecycler);
        taskeffect=findViewById(R.id.taskeffectrecycler);
        clientrecycler=findViewById(R.id.clientrecycler);
        carte=findViewById(R.id.carte);

        SharedPreferences sharedPreferencesH = getSharedPreferences("interventions", Context.MODE_PRIVATE);
        String titre_pref = sharedPreferencesH.getString("titre","");
        String commentaire_pref = sharedPreferencesH.getString("commentaire","");
        String heuredebut_pref = sharedPreferencesH.getString("heure_debut","");
        String heurefin_pref = sharedPreferencesH.getString("heure_fin","");
        int id_intervention_pref = sharedPreferencesH.getInt("id_intervention",0);

        carte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Intent i = new Intent(Details.this,MapActivity.class);
                    startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("http://192.168.75.73:3000/")
                        .addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit = builder.build();
                iNodeJS = retrofit.create(INodeJS.class);
                Call<Interventions>call=iNodeJS.deleteintervention(id_intervention_pref);
                call.enqueue(new Callback<Interventions>() {
                    @Override
                    public void onResponse(Call<Interventions> call, Response<Interventions> response) {
                        Toast.makeText(getApplicationContext(), "intervention supprimé", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Details.this,MainActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<Interventions> call, Throwable t) {

                    }
                });

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Details.this,Editintervention.class);
                i.putExtra("titre_edit",titre_pref);
                i.putExtra("commentaire_edit",commentaire_pref);
                i.putExtra("heure_debut_edit",heuredebut_pref);
                i.putExtra("heure_fin_edit",heurefin_pref);
                i.putExtra("id_interv",id_intervention_pref);
                startActivity(i);
            }
        });

        titre.setText(titre_pref);
        heuredebut.setText(heuredebut_pref);
        heurefin.setText(heurefin_pref);
        iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);
        Call<List<Taches>> call = iNodeJS.gettaches(id_intervention_pref);

        call.enqueue(new Callback<List<Taches>>() {
            @Override
            public void onResponse(Call<List<Taches>> call, Response<List<Taches>> response) {
                TachesList = response.body();
                TachesAdapter adapter = new TachesAdapter(mContext, TachesList);
                taskrec.setAdapter(adapter);
                taskrec.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
                //taches effectué
                TachesEffectueAdapter adaptereffect = new TachesEffectueAdapter(mContext, TachesList);
                taskeffect.setAdapter(adaptereffect);
                taskeffect.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));

            }
            @Override
            public void onFailure(Call<List<Taches>> call, Throwable t) {

            }
        });

        Call<List<Clients>> callclient = iNodeJS.getClient();
        callclient.enqueue(new Callback<List<Clients>>() {
            @Override
            public void onResponse(Call<List<Clients>> call, Response<List<Clients>> response) {
                ClientsList = response.body();
                ClientAdapter adapterclient = new ClientAdapter(mContext, ClientsList);
                clientrecycler.setAdapter(adapterclient);
                clientrecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));

            }
            @Override
            public void onFailure(Call<List<Clients>> call, Throwable t) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Details.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}