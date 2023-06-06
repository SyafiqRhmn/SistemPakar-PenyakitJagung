package com.example.tugasakhirpember.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasakhirpember.R;
import com.example.tugasakhirpember.activities.HasilDiagnosaActivity;
import com.example.tugasakhirpember.adapter.KonsultasiAdapter;
import com.example.tugasakhirpember.database.DatabaseHelper;
import com.example.tugasakhirpember.model.ModelKonsultasi;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class KonsultasiFragment extends Fragment {

    KonsultasiAdapter konsultasiAdapter;
    ArrayList<ModelKonsultasi> modelKonsultasiArrayList = new ArrayList<>();
    DatabaseHelper databaseHelper;
    RecyclerView rvGejalaPenyakit;
    MaterialButton btnHasilDiagnosa;

    public KonsultasiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_konsultasi, container, false);
        rvGejalaPenyakit = view.findViewById(R.id.rvGejalaPenyakit);
        btnHasilDiagnosa = view.findViewById(R.id.btnHasilDiagnosa);

        rvGejalaPenyakit.setLayoutManager(new LinearLayoutManager(requireContext()));
        konsultasiAdapter = new KonsultasiAdapter(requireContext(), modelKonsultasiArrayList);
        rvGejalaPenyakit.setAdapter(konsultasiAdapter);
        rvGejalaPenyakit.setHasFixedSize(true);

        btnHasilDiagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer gejalaTerpilih = new StringBuffer();

                ArrayList<ModelKonsultasi> gejalaList = modelKonsultasiArrayList;
                for (int i = 0; i < gejalaList.size(); i++) {
                    ModelKonsultasi gejala = gejalaList.get(i);
                    if (gejala.isSelected()) {
                        gejalaTerpilih.append(gejala.getStrGejala()).append("#");
                    }
                }

                if (gejalaTerpilih.toString().equals("")) {
                    Toast.makeText(requireContext(), "Silakan pilih gejala dahulu!", Toast.LENGTH_SHORT).show();
                } else {
                    // Tampilkan activity hasil diagnosa
                    Intent intent = new Intent(requireContext(), HasilDiagnosaActivity.class);
                    intent.putExtra("HASIL", gejalaTerpilih.toString());
                    startActivity(intent);
                }
            }
        });

        getListData();

        return view;
    }

    public void getListData() {
        modelKonsultasiArrayList = databaseHelper.getDaftarGejala();
        if (modelKonsultasiArrayList.size() == 0) {
            rvGejalaPenyakit.setVisibility(View.GONE);
        } else {
            rvGejalaPenyakit.setVisibility(View.VISIBLE);
            konsultasiAdapter.setData(modelKonsultasiArrayList);
        }
    }

}
