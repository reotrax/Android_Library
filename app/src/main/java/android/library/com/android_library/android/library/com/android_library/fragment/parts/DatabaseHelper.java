package android.library.com.android_library.android.library.com.android_library.fragment.parts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by reo on 2017/07/12.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // TODO: データベース.スキーマ（？）
    static final private String DBNAME = "sample.sqlite";

    // TODO: 変更するとテーブルが再作成される作りにしているので安易に変更しない。
    static final private int VERSION = 5;

    /**
     * コンストラクター
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    /**
     * コンストラクター
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    /**
     * データベース作成時にテーブルとテストデータを作成
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 基本テーブル
        db.execSQL("CREATE TABLE money (money_id INTEGER, title TEXT, money INTEGER, genre_id INTEGER, subgenre_id INTEGER, date DATE, PRIMARY KEY(money_id))");
        db.execSQL("CREATE TABLE genre (genre_id INTEGER, genre TEXT, subgenre_id INTEGER, subgenre TEXT)");
        db.execSQL("INSERT INTO genre VALUES " +
                "(1, '支出', 1, 'カード引き落とし')," +
                "(1, '支出', 2, '食費')," +
                "(1, '支出', 3, '学習')," +
                "(1, '支出', 4, '趣味')," +
                "(1, '支出', 5, '雑費')," +
                "(2, '収入', 1, '給料')," +
                "(2, '収入', 2, 'おこづかい')," +
                "(2, '収入', 1, '臨時収入')"
        );

        // 履歴テーブル
        db.execSQL("CREATE TABLE history (history_id INTEGER, id TEXT, date TEXT, title TEXT, genre TEXT, money INTEGER, content TEXT)");
        db.execSQL("INSERT INTO history VALUES " +
                "(1, '1', '2017-07-01', '1', '食費',   1000, 'content01')," +
                "(2, '1', '2017-07-11', '1', '趣味',   5000, 'content02')," +
                "(3, '1', '2017-07-21', '2', 'おこづかい', 100000, 'content03')"
        );
    }

    /**
     * データベースのVERSIONがアップした時に実行される。
     * @param db
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS money");
        db.execSQL("DROP TABLE IF EXISTS genre");
        db.execSQL("DROP TABLE IF EXISTS history");
        onCreate(db);
    }


    public void getGenreTable() {
        SQLiteDatabase db = getWritableDatabase(); // データベースを取得
        Cursor c = db.query(
                "genre",    	  					// FROM table
                new String[]{"genre_id","genre","subgenre_id","subgenre"},   // SELECT columns
                "genre_id=?",       				// WHERE
                new String[]{"1"}, 					// WHERE args
                null,       	    				// GROUP BY
                null,       						// HAVING
                "genre_id",     					// ORDER BY
                null         						// LIMIT
        );
        c.moveToFirst();
        Log.d("SELECT", "開始 | genre / " + c.getCount());
        Log.d("SELECT", "genre_id | genre | subgenre_id | subgenre");
        for (int i=0; i<c.getCount(); i++) {
            int genre_id = c.getInt(c.getColumnIndex("genre_id"));
            String genre = c.getString(c.getColumnIndex("genre"));
            int subgenre_id = c.getInt(c.getColumnIndex("subgenre_id"));
            String subgenre = c.getString(c.getColumnIndex("subgenre"));
            Log.d("SELECT " + i, genre_id + " | " + genre + " | " + subgenre_id + " | " + subgenre);
            c.moveToNext();
        }
        // クローズ処理
        c.close();
    }

    /**
     * テーブルmoneyのレコードを取得する。<br>
     * @param table String
     * @param columns String[]
     * @param where String
     * @param whereArgs String[]
     * @param groupBy String
     * @param having String
     * @param orderBy String
     * @param limit String
     * @return List
     */
    public List<Money> getMoneyTable(String table, String[] columns, String where, String[] whereArgs, String groupBy, String having, String orderBy, String limit) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(table, columns, where, whereArgs, groupBy, having, orderBy, limit);
        c.moveToFirst();
        Log.d("SELECT", "開始 | genre / " + c.getCount());
        Log.d("SELECT", "money_id" + " | " + "title" + " | " + "money" + " | " + "genre_id" + " | " + "subgenre_id" + " | " + "date");

        List<Money> moneys = new ArrayList<>();
        for (int i=0; i<c.getCount(); i++) {
            int money_id = c.getInt(c.getColumnIndex("money_id"));
            String title = c.getString(c.getColumnIndex("title"));
            int money = c.getInt(c.getColumnIndex("money"));
            int genre_id = c.getInt(c.getColumnIndex("genre_id"));
            int subgenre_id = c.getInt(c.getColumnIndex("subgenre_id"));
            String date = c.getString(c.getColumnIndex("date"));
            Log.d("SELECT " + i, money_id + " | " + title + " | " + money + " | " + genre_id  + " | " + subgenre_id + " | " + date);
            moneys.add(new Money(money_id, title, money, genre_id, subgenre_id, date));
            c.moveToNext();
        }
        c.close();

        return moneys;
    }


    public void getHistoryTable() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(
                "history",
                new String[]{"history_id","id","date",/*"title",*/"genre","money","content"},
                null,	// WHERE
                null,	// WHERE args
                null,	// GROUP BY
                null,	// HAVING
                null,	// ORDER BY
                null	// LIMIT
        );
        c.moveToFirst();
        Log.d("SELECT", "開始 | genre / " + c.getCount());
        Log.d("SELECT", "history_id" + " | " + "id" + " | " + "date" + " | " + "title" + " | " + "genre" + " | " + "money" + " | " + "content");
        for (int i=0; i<c.getCount(); i++) {
            int history_id = c.getInt(c.getColumnIndex("history_id"));
            int id = c.getInt(c.getColumnIndex("id"));
            String date = c.getString(c.getColumnIndex("date"));
//			String title = c.getString(c.getColumnIndex("title"));
            String genre = c.getString(c.getColumnIndex("genre"));
            int money = c.getInt(c.getColumnIndex("money"));
            int content = c.getInt(c.getColumnIndex("content"));
            Log.d("SELECT " + i, history_id + " | " + id + " | " + date + " | " + /*title + " | " + */genre + " | " + money  + " | " + content);
            c.moveToNext();
        }
        db.close();
    }
}
