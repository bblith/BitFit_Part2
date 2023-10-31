package com.example.bitfit

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = AppDatabase.getInstance(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, EntriesFragment())
                .commit()
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

            when (menuItem.itemId) {
                R.id.nav_create_entry -> {
                    if (currentFragment !is CreateEntryFragment) {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, CreateEntryFragment())
                            .addToBackStack(null)
                            .commit()
                    }
                    true
                }

                R.id.nav_entries -> {
                    if (currentFragment !is EntriesFragment) {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, EntriesFragment())
                            .addToBackStack(null)
                            .commit()
                    }
                    true
                }

                else -> false
            }
        }
    }
}