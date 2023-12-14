package com.own_world.androidknowledgesqlitelearning

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import androidx.core.content.contentValuesOf

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION) {

    companion object {
        private const val VERSION = 1
        private const val DB_NAME = "knowledge.db"
        private const val TABLE_NAME = "knowledge"
        private const val Culum_ID = "ID"
        private const val Culum_Name = "Name"
        private const val Culum_Description = "Description"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLE_NAME ($Culum_ID INTEGER PRIMARY KEY AUTOINCREMENT, $Culum_Name TEXT, $Culum_Description TEXT)"
        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTable)
        onCreate(db)
    }

    fun insertNote(note: DataClass) {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(Culum_Name, note.title)
            put(Culum_Description, note.content)
        }
        db.insert(TABLE_NAME, null, contentValues)
        db.close()

    }


    fun getallNotes(): List<DataClass> {
        val list = mutableListOf<DataClass>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(Culum_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(Culum_Name))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(Culum_Description))

            val note = DataClass(id, title, content)
            list.add(note)
        }
        cursor.close()
        db.close()
        return list


    }
}