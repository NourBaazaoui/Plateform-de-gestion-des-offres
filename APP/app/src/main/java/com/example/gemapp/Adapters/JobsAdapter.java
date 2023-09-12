package com.example.gemapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gemapp.R;
import com.example.gemapp.models.Jobs;

import java.util.List;

public class JobsAdapter extends  RecyclerView.Adapter<JobsAdapter.myViewHolder> {

    Context mContext;
    private List<Jobs> mData;
    TextView nom_entreprise, poste, exigences, contact_recruteur;

    public JobsAdapter(Context mContext, List<Jobs> mDataa) {
        this.mContext = mContext;
        this.mData = mDataa;
    }
    public void notifyChange(List<Jobs> interv){
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
        final Jobs Jobs = mData.get(position);
        nom_entreprise.setText(Jobs.getNom_entreprise());
        poste.setText(Jobs.getPoste());
        exigences.setText(Jobs.getExigences());
        contact_recruteur.setText(Jobs.getContact_recruteur());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            nom_entreprise = itemView.findViewById(R.id.nom_entreprise);
            poste = itemView.findViewById(R.id.poste);
            exigences = itemView.findViewById(R.id.exigences);
            contact_recruteur = itemView.findViewById(R.id.contact);


        }
    }
}
