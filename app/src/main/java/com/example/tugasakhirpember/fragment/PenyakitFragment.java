package com.example.tugasakhirpember.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasakhirpember.R;
import com.example.tugasakhirpember.adapter.DaftarPenyakitAdapter;
import com.example.tugasakhirpember.database.DatabaseHelper;
import com.example.tugasakhirpember.model.ModelDaftarPenyakit;

import java.util.ArrayList;

public class PenyakitFragment extends Fragment {

    private RecyclerView recyclerView;
    private DaftarPenyakitAdapter daftarPenyakitAdapter;
    private ArrayList<ModelDaftarPenyakit> daftarPenyakitList;
    private DatabaseHelper databaseHelper;

    public PenyakitFragment() {
        // Constructor kosong diperlukan untuk Fragment.
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_penyakit, container, false);
        recyclerView = view.findViewById(R.id.rvDaftarPenyakit);

        // Inisialisasi database dan daftar penyakit
        databaseHelper = new DatabaseHelper(requireContext());
        daftarPenyakitList = databaseHelper.getDaftarPenyakit();

        // Setup RecyclerView dan Adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        daftarPenyakitAdapter = new DaftarPenyakitAdapter(requireContext(), daftarPenyakitList);
        recyclerView.setAdapter(daftarPenyakitAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Mengatur tampilan dan logika lainnya setelah tampilan dibuat...
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Tutup koneksi database saat Fragment dihancurkan
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}
