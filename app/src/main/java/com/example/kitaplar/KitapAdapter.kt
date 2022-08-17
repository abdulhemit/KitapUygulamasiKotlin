package com.example.kitaplar

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AlertDialog
import com.example.kitaplar.databinding.ListItemBinding

class KitapAdapter (val kitapList: ArrayList<Kitap>,val context: Context): BaseAdapter() {
    val kitapHelper = KitapHelper(context)
    override fun getCount(): Int {
        return kitapList.size
    }

    override fun getItem(p0: Int): Any {
        val kitap = kitapList.get(p0)
        return kitap
    }

    override fun getItemId(p0: Int): Long {
        val kitap = kitapList.get(p0)
        return kitap.id?.toLong() ?: 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = ListItemBinding.inflate(LayoutInflater.from(context))
        val kitap = getItem(p0) as Kitap
        binding.NameText.setText(kitap.name)
        binding.AuthorText.setText(kitap.author)
        binding.ButtonDelete.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Yes",object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        kitapHelper.deletekitap(kitap)
                    }

                })
                .setNegativeButton("NO",null)
                .show()
        }
        binding.ButtonEdit.setOnClickListener {
            val intent = Intent(context,AddKitapActivity::class.java)
            intent.putExtra("id",kitap.id)
            intent.putExtra("name",kitap.name)
            intent.putExtra("author",kitap.author)
            context.startActivity(intent)
        }
        return binding.root
    }
}