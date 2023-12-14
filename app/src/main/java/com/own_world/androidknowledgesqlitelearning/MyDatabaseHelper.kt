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


/* onCreate(): This method is called when the database is first created.
 It creates the database table.
* onCreate(): The onCreate() method is called when the database is first created.
 It creates the database table.
 The table name is "knowledge" and it has three columns: "ID", "Name", and "Description".
  The "ID" column is the primary key and is auto-incremented. The "Name" column is a text column and stores the title of the note.
  The "Description" column is also a text column and stores the content of the note.
* */
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLE_NAME ($Culum_ID INTEGER PRIMARY KEY AUTOINCREMENT, $Culum_Name TEXT, $Culum_Description TEXT)"
        db?.execSQL(createTable)

    }


    /*onUpgrade(): This method is called when the database is upgraded to a new version.
    It drops the old table and creates a new one.
    onUpgrade(): The onUpgrade() method is called when the database is upgraded to a new version.
    It drops the old table and creates a new one.
     This is necessary because the schema of the database may have changed since the last time it was upgraded.
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTable)
        onCreate(db)
    }
/*insertNote(): This method inserts a new note into the database.
* insertNote(): The insertNote() method inserts a new note into the database.
* The method takes a DataClass object as its parameter.
*  The DataClass object contains the title and content of the note.
* The method inserts the data from the DataClass object into the database table.*/
    fun insertNote(note: DataClass) {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(Culum_Name, note.title)
            put(Culum_Description, note.content)
        }
        db.insert(TABLE_NAME, null, contentValues)
        db.close()

    }

/*getallNotes(): This method returns a list of all the notes in the database.
* getallNotes(): The getallNotes() method returns a list of all the notes in the database.
* The method returns a list of DataClass objects.
* Each DataClass object contains the title and content of a note.*/
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