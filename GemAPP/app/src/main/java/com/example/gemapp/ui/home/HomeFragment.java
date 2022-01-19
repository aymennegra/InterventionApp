package com.example.gemapp.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gemapp.Adapters.InterventionsAdapter;
import com.example.gemapp.R;
import com.example.gemapp.Retrofit.INodeJS;
import com.example.gemapp.Retrofit.RetrofitClient;
import com.example.gemapp.models.Interventions;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    List<Interventions> InterventionList;
    RecyclerView recyclerView;
    TextView currentdate;
    public static INodeJS iNodeJS;
    Context mContext;
    Button left,right;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        currentdate = view.findViewById(R.id.currentdate);
        left = view.findViewById(R.id.leftarrow);
        right = view.findViewById(R.id.rightarrow);
        //Consuming Api with retrofit
        iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);

        //get date
        Calendar c = Calendar.getInstance();
        Date today = c.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(today);
        System.out.println("today date => " + formattedDate);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.add(Calendar.DATE,1);
                Date future = c.getTime();
                String tomorrow = df.format(future);
                currentdate.setText(tomorrow);
                getInterventionData ();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.add(Calendar.DATE,-1);
                Date past = c.getTime();
                String yesterday = df.format(past);
                currentdate.setText(yesterday);
                getInterventionData ();
            }
        });
        currentdate.setText(formattedDate);
        getInterventionData ();
        return view;
    }
    public void getInterventionData (){
        SharedPreferences sh = getContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        int idU = sh.getInt("idUser", 0);
        Call<List<Interventions>> call = iNodeJS.getintervList(idU,currentdate.getText().toString());
        call.enqueue(new Callback<List<Interventions>>() {
            @Override
            public void onResponse(Call<List<Interventions>> call, Response<List<Interventions>> response) {
                InterventionList = response.body();
                InterventionsAdapter adapter = new InterventionsAdapter(mContext, InterventionList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

            }
            @Override
            public void onFailure(Call<List<Interventions>> call, Throwable t) {
                InterventionList =Collections.emptyList();
                InterventionsAdapter adapter = new InterventionsAdapter(mContext, InterventionList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            }
        });
    }
    }
