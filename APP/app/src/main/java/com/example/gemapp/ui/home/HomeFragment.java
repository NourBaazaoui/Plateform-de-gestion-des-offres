package com.example.gemapp.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gemapp.Adapters.JobsAdapter;
import com.example.gemapp.R;
import com.example.gemapp.Retrofit.INodeJS;
import com.example.gemapp.Retrofit.RetrofitClient;
import com.example.gemapp.models.Jobs;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    List<Jobs> JobsList;
    RecyclerView recyclerView;
    public static INodeJS iNodeJS;
    Context mContext;

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
        //Consuming Api with retrofit
        iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);
        getJobs();

        return view;
    }
    public void getJobs(){
        SharedPreferences sh = getContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        int idU = sh.getInt("idUser", 0);
        Call<List<Jobs>> call = iNodeJS.getJobs();
        call.enqueue(new Callback<List<Jobs>>() {
            @Override
            public void onResponse(Call<List<Jobs>> call, Response<List<Jobs>> response) {
                JobsList = response.body();
                JobsAdapter adapter = new JobsAdapter(mContext, JobsList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

            }
            @Override
            public void onFailure(Call<List<Jobs>> call, Throwable t) {
                JobsList =Collections.emptyList();
                JobsAdapter adapter = new JobsAdapter(mContext, JobsList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            }
        });
    }
    }
