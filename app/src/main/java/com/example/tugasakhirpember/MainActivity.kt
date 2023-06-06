package com.example.tugasakhirpember

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.tugasakhirpember.fragment.PenyakitFragment
import com.example.tugasakhirpember.fragment.KonsultasiFragment
import com.example.tugasakhirpember.fragment.TentangFragment
import com.example.tugasakhirpember.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var fragmentPenyakit: PenyakitFragment
    private lateinit var fragmentKonsultasi: KonsultasiFragment
    private lateinit var fragmentTentang: TentangFragment

    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi fragment
        fragmentPenyakit = PenyakitFragment()
        fragmentKonsultasi = KonsultasiFragment()
        fragmentTentang = TentangFragment()

        // Set active fragment pertama kali
        activeFragment = fragmentPenyakit

        // Inisialisasi bottom navigation view
        bottomNavigationView = binding.navigation
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_todo -> {
                    switchFragment(fragmentPenyakit)
                    true
                }
                R.id.menu_inprogress -> {
                    switchFragment(fragmentKonsultasi)
                    true
                }
                R.id.menu_done -> {
                    switchFragment(fragmentTentang)
                    true
                }
                else -> false
            }
        }

        // Set default fragment saat pertama kali dibuka
        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainer.id, fragmentTentang)
            .hide(fragmentTentang)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainer.id, fragmentKonsultasi)
            .hide(fragmentKonsultasi)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainer.id, fragmentPenyakit)
            .commit()
    }

    private fun switchFragment(targetFragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .hide(activeFragment)
            .show(targetFragment)
            .commit()
        activeFragment = targetFragment
    }
}
