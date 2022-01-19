package com.example.gemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gemapp.Retrofit.INodeJS;
import com.example.gemapp.Retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Editintervention extends AppCompatActivity {
    Button btnedit;
    public static INodeJS iNodeJS;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    EditText titre_edit, comment_edit, date_debut_edit, date_fin_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_intervention);
        Bundle extras = getIntent().getExtras();

            String titre = extras.getString("titre_edit");
            String comment = extras.getString("commentaire_edit");
            String datedebut = extras.getString("heure_debut_edit");
            String datefin = extras.getString("heure_fin_edit");

            //The key argument here must match that used in the other activity

        iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);
        titre_edit = findViewById(R.id.titre_edit);
        btnedit = findViewById(R.id.btn_edit);
        comment_edit = findViewById(R.id.comment_edit);
        date_debut_edit = findViewById(R.id.date_debut);
        date_fin_edit = findViewById(R.id.date_fin);

        titre_edit.setText(titre);
        comment_edit.setText(comment);
        date_debut_edit.setText(datedebut);
        date_fin_edit.setText(datefin);



        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(titre_edit.getText().toString().equals("")){
                    titre_edit.setError("Required email");
                }else if(comment_edit.getText().toString().equals("")){
                    comment_edit.setError("Required nom");
                }else if(date_debut_edit.getText().toString().equals("")){
                    date_debut_edit.setError("Required prenom");
                }else if(date_fin_edit.getText().toString().equals("")){
                    date_fin_edit.setError("Required login");
                }else {
                    EditIntervention(titre_edit.getText().toString(), comment_edit.getText().toString(), date_debut_edit.getText().toString(), date_fin_edit.getText().toString());
                    gotoMainActivity();
                }
            }
        });
    }
    private void gotoMainActivity(){
        Intent intent=new Intent(this,Details.class);
        startActivity(intent);
    }
    private void EditIntervention(final String titre, final String comment, final String datedebut, final String datefun) {
        SharedPreferences sharedPreferencesH = getSharedPreferences("interventions", Context.MODE_PRIVATE);
        int id_intervention_pref = sharedPreferencesH.getInt("id_intervention",0);

        compositeDisposable.add(iNodeJS.updateinterv(id_intervention_pref,titre, comment, datedebut, datefun)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(Editintervention.this, "" + s, Toast.LENGTH_SHORT).show();
                    }
                })
        );

    }

}