package com.example.kitaplar

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class KitapHelper(val context:Context) : SQLiteOpenHelper(context,"db.kitapler",null,1) {

    val TABLE_NAME = "kitap"
    val FIELD_ID = "id"
    val FIELD_NAME = "kitap_ismi"
    val FIELD_AUTHOR = "author"
    override fun onCreate(db: SQLiteDatabase?) {
        val sql_str =
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME ($FIELD_ID INTEGER PRIMARY KEY AUTOINCREMENT,$FIELD_NAME TEXT ,$FIELD_AUTHOR TEXT)"
        db?.execSQL(sql_str)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun saveDataToSQLite(kitap: Kitap) {
        //val sql_str = "INSERT INTO $TABLE_NAME ($FIELD_NAME,$FIELD_AUTHOR) VALUES ('${kitap.name}','${kitap.author}')"
        val db = this.readableDatabase
        //db.execSQL(sql_str)
        val contentValue = ContentValues()
        contentValue.put(FIELD_NAME, kitap.name)
        contentValue.put(FIELD_AUTHOR, kitap.author)
        db.insert(TABLE_NAME, null, contentValue)
        db.close()
    }

    fun readKitap(): ArrayList<Kitap> {
        val list = ArrayList<Kitap>()
        val sql_str = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(sql_str, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val author = cursor.getString(2)
            val kitap = Kitap(id,name,author)
            list.add(kitap)
        }
        cursor.close()
        db.close()
        return list
    }

    fun deletekitap(kitap: Kitap) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME,"id = ?", arrayOf(kitap.id.toString()))
        (context as MainActivity).Refresh()
        db.close()
    }
    fun updatekitap ( kitap: Kitap){
        val db = this.writableDatabase
        var contentValue = ContentValues()
        contentValue.put(FIELD_NAME, kitap.name)
        contentValue.put(FIELD_AUTHOR, kitap.author)
        db.update(TABLE_NAME,contentValue,"id = ?", arrayOf(kitap.id.toString()))
        db.close()
    }

}