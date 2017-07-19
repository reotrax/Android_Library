package android.library.com.android_library;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.library.com.android_library.android.library.com.android_library.fragment.DatabaseFragment;
import android.library.com.android_library.android.library.com.android_library.fragment.GraphBarFragment;
import android.library.com.android_library.android.library.com.android_library.fragment.GraphPieFragment;
import android.library.com.android_library.android.library.com.android_library.fragment.Setting;
import android.library.com.android_library.android.library.com.android_library.fragment.dummy.DummyContent;
import android.library.com.android_library.android.library.com.android_library.fragment.parts.DatabaseHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DatabaseFragment.OnListFragmentInteractionListener {

	public Setting settingFragment;

//	@BindView(R.id.navigation) public BottomNavigationView navigation;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener() {

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			switch (item.getItemId()) {
				case R.id.navigation_home:
					getSupportFragmentManager().beginTransaction()
							.replace(R.id.content, settingFragment)
							.commit();
					return true;
				case R.id.navigation_database:
					DatabaseFragment databaseFragment = new DatabaseFragment();
					getSupportFragmentManager().beginTransaction()
							.replace(R.id.content, databaseFragment)
							.commit();
					return true;
				case R.id.navigation_dashboard:
					GraphPieFragment graphPieFragment = new GraphPieFragment();
					getSupportFragmentManager().beginTransaction()
							.replace(R.id.content, graphPieFragment)
							.commit();
					return true;
				case R.id.navigation_notifications:
					GraphBarFragment graphBarFragment = new GraphBarFragment();
					getSupportFragmentManager().beginTransaction()
							.replace(R.id.content, graphBarFragment)
							.commit();
					return true;
			}
			return false;
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		// SQLite
		DatabaseHelper helper = new DatabaseHelper(this); // ヘルパー準備
		SQLiteDatabase db = helper.getWritableDatabase(); // データベースを取得
		Cursor c = db.query(
				"genre",    	  					// FROM table
				new String[]{"genre","subgenre"},   // SELECT columns
				"genre_id=?",       				// WHERE
				new String[]{"1"}, 					// WHERE args
				null,       	    				// GROUP BY
				null,       						// HAVING
				"genre_id",     					// ORDER BY
				null         						// LIMIT
		);
		c.moveToFirst();
		Log.d("SELECT", "開始 / " + c.getCount());
		for (int i=0; i<c.getCount(); i++) {
			String genre = c.getString(c.getColumnIndex("genre"));
			String subgenre = c.getString(c.getColumnIndex("subgenre"));
			Log.d("SELECT " + i, genre + " / " + subgenre);
			c.moveToNext();
		}
		db.close();

		//
		BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

		// フラグメント
		settingFragment  = new Setting();
		getSupportFragmentManager().beginTransaction()
				.add(R.id.content, settingFragment)
				.addToBackStack(null)
				.commit();

		// 画面サイズ取得＞ビューのサイズ設定
//		Point point = getDisplaySize(mTextMessage);
	}


	/**
	 * Viewのサイズを取得。
	 * @param view
	 * @return
	 */
	public Point getDisplaySize(View view) {
		Point point = new Point(0, 0);
		point.set(view.getWidth(), view.getHeight());
		return point;
	}

	@Override
	public void onListFragmentInteraction(DummyContent.DummyItem item) {
		Log.d("onListFragmentInteracti", item.genre);
	}
}
