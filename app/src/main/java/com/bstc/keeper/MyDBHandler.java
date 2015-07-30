package com.bstc.keeper;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * Created by Brian on 7/23/2015.
 * does everything that has to do with databases
 * copy and paste this for all projects that deal with databases
 */
public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1; //whenever type of database changes
    private static final String DATABASE_NAME = "listOfAllThings.db";

    /* Inner class that defines the table contents
    * implementing basecolumns makes sure there's an _ID constant which is necessary to create table properly?*/
    public static abstract class TableEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_ICONRESOURCEID = "iconResourceId";
        public static final String COLUMN_TAGS = "tags";
    }


    //for housekeeping
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION); //context: background info, name: name of database, factory: background info, version: database version
    }

    //very first time this is run onCreate is called
    //create the table that data can be stored in
    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE IF EXISTS");
        String query = "CREATE TABLE " + TableEntry.TABLE_NAME + " (" +
                TableEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TableEntry.COLUMN_NAME + " TEXT, " +
                TableEntry.COLUMN_ICONRESOURCEID + " INTEGER, " +
                TableEntry.COLUMN_DESCRIPTION + " TEXT, " +
                TableEntry.COLUMN_TAGS + " TEXT " +
                ")";
        db.execSQL(query); //execute the sql query created, creates the table with that info
    }

    //called whenever database version is updated
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableEntry.TABLE_NAME); //deletes table
        onCreate(db); //oncreate with new db structure
    }

    //Add a new row to the database
    public void addThing(Thing thing) {
        //put data for the row into a ContentValues variable
        ContentValues values = new ContentValues(); //list of values
        values.put(TableEntry.COLUMN_NAME, thing.get_name()); //put proper data in column
        values.put(TableEntry.COLUMN_DESCRIPTION, thing.get_description());
        values.put(TableEntry.COLUMN_ICONRESOURCEID, thing.get_iconResourceId());
        values.put(TableEntry.COLUMN_TAGS, thing.get_tags()); //temporary while tags is still a whole string (put it into a table instead?)

        SQLiteDatabase db = getWritableDatabase(); //get the database we're going to write to
        db.insert(TableEntry.TABLE_NAME, null, values); //insert the list of values into the proper table
        db.close();
    }

    //Delete a row of the database
    public void deleteThing(String thingName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TableEntry.TABLE_NAME + " WHERE " + TableEntry.COLUMN_NAME + "=\"" + thingName + "\";"); //delete from proper table the row that has the thing's name by looking at the column of names
    }

    public Thing [] databaseToThingList(){
        Thing[] listOfThings = new Thing[databaseLength()];
        int counter = 0;
        SQLiteDatabase db = getWritableDatabase(); //get the database

        String query = "SELECT * FROM " + TableEntry.TABLE_NAME;

        //Cursor point to location in results of the query
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //while the cursor has more to move to
        do{
            if (c.getString(c.getColumnIndex(TableEntry.COLUMN_NAME)) != null){
                Thing item = new Thing(c.getString(c.getColumnIndex(TableEntry.COLUMN_NAME)),c.getInt(c.getColumnIndex(TableEntry.COLUMN_ICONRESOURCEID)),c.getString(c.getColumnIndex(TableEntry.COLUMN_DESCRIPTION)),c.getString(c.getColumnIndex(TableEntry.COLUMN_TAGS)));
                listOfThings[counter] = item;
                counter++;
            }
        }while(c.moveToNext());

        c.close();
        db.close();
        return listOfThings;
    }

    //gets the number of items in the database by checking for a name in a row
    public int databaseLength(){
        int length = 0;
        SQLiteDatabase db = getWritableDatabase(); //get the database

        String query = "SELECT * FROM " + TableEntry.TABLE_NAME; //select ALL from table of things where row

        //Cursor point to a location in your results (keeps track of each row)
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //while the cursor is not after the last row
        do{
            if (c.getString(c.getColumnIndex(TableEntry.COLUMN_NAME)) != null){
                length++;
            }
        }while(c.moveToNext());

        c.close();
        db.close();
        return length;
    }

    //print out database names as a string
    public String databaseToString(){
        String dbString = ""; //String that names are written to
        SQLiteDatabase db = getWritableDatabase(); //get the database

        String query = "SELECT * FROM " + TableEntry.TABLE_NAME; //select ALL from table of things where row

        //Cursor point to a location in your results (keeps track of each row)
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //while the cursor is not after the last row
        do{
            if (c.getString(c.getColumnIndex(TableEntry.COLUMN_NAME)) != null){
                dbString += c.getString(c.getColumnIndex(TableEntry.COLUMN_NAME));
                dbString += "\n";
            }
        }while(c.moveToNext());

        c.close();
        db.close();
        return dbString;
    }
}
