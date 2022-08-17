package com.example.kitaplar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kitaplar.databinding.ActivityAddKitapBinding

class AddKitapActivity : AppCompatActivity() {
    var id = 0
    private lateinit var kitapHelper: KitapHelper
    private lateinit var binding: ActivityAddKitapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddKitapBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        id = intent.getIntExtra("id",0)
        saveDataToSQlite()
        getintent()

        kitapHelper = KitapHelper(this)


        /*
        val name = intent.getStringExtra("name")
        val author = intent.getStringExtra("author")
        binding.kitapText.setText(name)
        binding.aptorText.setText(author)





        binding.button.setOnClickListener {
            val name = binding.kitapText.text.toString()
            val author = binding.aptorText.text.toString()
            if (!name.isNullOrEmpty() && !author.isNullOrEmpty()){

                if (id == 0){
                    val kitap = Kitap(null,name,author)
                    kitapHelper.saveDataToSQLite(kitap)
                }else{
                    val kitap = Kitap(id,name,author)
                    kitapHelper.updatekitap(kitap)
                }
            }
            finish()


        }

         */


    }

    private fun saveDataToSQlite(){

        binding.button.setOnClickListener {
            val name = binding.kitapText.text.toString()
            val author = binding.aptorText.text.toString()
            if (!name.isNullOrEmpty() && !author.isNullOrEmpty()){

                Log.i("Veri",name)
                if (id == 0){
                    Log.i("Veri",id.toString())
                    val kitap = Kitap(null,name,author)
                    kitapHelper.saveDataToSQLite(kitap)
                }else{
                    val kitap = Kitap(id,name,author)
                    kitapHelper.updatekitap(kitap)
                }
            }
            finish()
        }
    }

    fun getintent(){
         id = intent.getIntExtra("id",0)
        val name = intent.getStringExtra("name")
        val author = intent.getStringExtra("author")

        binding.kitapText.setText(name)
        binding.aptorText.setText(author)

    }


}