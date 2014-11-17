package info.androidhive.slidingmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	static final String KEY_ROWID = "_id";
	static final String KEY_NAME = "name";
	static final String KEY_EMAIL = "email";
	static final String KEY_PSD = "password";
	static final String TAG = "DBAdapter";
	static final String DATABASE_NAME = "MyDB";
	static final String DATABASE_TABLE = "contact";
	static final int DATABASE_VERSION = 5;
	static final String DATABASE_CREATE = "create table contact (_id integer primary key autoincrement, "
			+ "name text not null);";
	final Context context;
	DatabaseHelper DBHelper;
	SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			try {
				//db.execSQL("DROP TABLE IF EXISTS contact");
				db.execSQL(DATABASE_CREATE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + "to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS contact");
			onCreate(db);
		}
	}

	// ---opens the database---
	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// ---closes the database---
	public void close() {
		DBHelper.close();
	}

	// ---insert a contact into the database---
	public long insertContact(String name) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		
		Log.e("insert","lllll");
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	// ---deletes a particular contact---
	public boolean deleteContact(long rowId) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}
	public boolean deleteContactByname(String name){
		return db.delete(DATABASE_TABLE,KEY_NAME + "="+"'"+ name+"'",null)>0;
	}
	// ---retrieves all the contacts---
	public Cursor getAllContacts() {
		return db.query(DATABASE_TABLE, new String[] 
				{ KEY_ROWID, KEY_NAME }, null, null, null, null, null);
	}

	// ---retrieves a particular contact---
	public Cursor getContact(long rowId) throws SQLException {

		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_NAME, KEY_EMAIL }, KEY_ROWID + "=" + rowId,
				null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	public Cursor getContactbyname(String name,String psd) throws SQLException {
		Cursor cursor = db.rawQuery(
				"SELECT name,email FROM contact WHERE name ="+"'"+name+"'"+"and password="+"'"+psd+"'", null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		return cursor;
	}

	// ---updates a contact---
	public boolean updateContact(long rowId, String name, String email) {
		ContentValues args = new ContentValues();
		args.put(KEY_NAME, name);
		args.put(KEY_EMAIL, email);
		return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}
}
