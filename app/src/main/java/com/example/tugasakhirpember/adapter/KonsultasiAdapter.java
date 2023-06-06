package com.example.tugasakhirpember.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasakhirpember.R;
import com.example.tugasakhirpember.model.ModelKonsultasi;

import java.util.ArrayList;

public class KonsultasiAdapter extends RecyclerView.Adapter<KonsultasiAdapter.KonsultasiHolder> {

    private Context ctx;
    private ArrayList<ModelKonsultasi> modelKonsultasiArrayList;

    public KonsultasiAdapter(Context context, ArrayList<ModelKonsultasi> items) {
        this.ctx = context;
        this.modelKonsultasiArrayList = items;
    }

    @Override
    public KonsultasiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_gejala, parent, false);
        return new KonsultasiHolder(view);
    }

    @Override
    public void onBindViewHolder(KonsultasiHolder holder, final int position) {
        final ModelKonsultasi data = modelKonsultasiArrayList.get(position);

        holder.cbGejala.setOnCheckedChangeListener(null);
        holder.cbGejala.setChecked(data.isSelected());
        holder.cbGejala.setText(data.getStrGejala());
        holder.cbGejala.setOnCheckedChangeListener((checkboxView, isChecked) -> {
            data.setSelected(isChecked);

            int varGlobal = 0;
            for (ModelKonsultasi konsultasi : modelKonsultasiArrayList) {
                if (konsultasi.isSelected()) {
                    varGlobal++;
                }
            }

            if (varGlobal > 3) {
                checkboxView.setChecked(false);
                data.setSelected(false);
                varGlobal--;
                Toast.makeText(ctx, "Maaf, maksimal 3 pilihan saja", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void setData(ArrayList<ModelKonsultasi> items) {
        modelKonsultasiArrayList.clear();
        modelKonsultasiArrayList.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return modelKonsultasiArrayList.size();
    }

    static class KonsultasiHolder extends RecyclerView.ViewHolder {
        CheckBox cbGejala;

        public KonsultasiHolder(View itemView) {
            super(itemView);
            cbGejala = itemView.findViewById(R.id.cbGejala);
        }
    }
}
