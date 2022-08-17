package com.example.kitaplar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kitaplar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: KitapAdapter
    private lateinit var kitapHelper: KitapHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        floatingButton()
        Refresh()

    }
    private fun floatingButton(){
        binding.floatingButton.setOnClickListener {
            val intent = Intent(this@MainActivity,AddKitapActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        Refresh()
    }

    fun Refresh(){
        kitapHelper = KitapHelper(this@MainActivity)
        val list = kitapHelper.readKitap()

        adapter = KitapAdapter(list,this@MainActivity)
        binding.listview.adapter = adapter
    }

}