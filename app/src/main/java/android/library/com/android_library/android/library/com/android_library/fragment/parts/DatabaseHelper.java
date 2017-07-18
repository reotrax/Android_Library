package android.library.com.android_library.android.library.com.android_library.fragment.parts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
