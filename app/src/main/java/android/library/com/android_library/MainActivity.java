package android.library.com.android_library;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.library.com.android_library.android.library.com.android_library.fragment.GraphBarFragment;
import android.library.com.android_library.android.library.com.android_library.fragment.GraphPieFragment;
import android.library.com.android_library.android.library.com.android_library.fragment.Setting;
import android.library.com.android_library.android.library.com.android_library.fragment.parts.DatabaseHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	public Setting settingFragment;
	public GraphPieFragment graphPieFragment;
	public GraphBarFragment graphBarFragment;
	public TextView mTextMessage;

//	@BindView(R.id.content) FrameLayout frameLayout;
//	@BindView(R.id.message) public TextView mTextMessage;
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
				case R.id.navigation_dashboard:
					getSupportFragmentManager().beginTransaction()
							.replace(R.id.content, graphPieFragment)
							.commit();
					return true;
				case R.id.navigation_notifications:
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
				"genre",    	  	// table
				new String[]{"genre","subgenre"},   // column
				"genre_id=?",       // where
				new String[]{"1"},  // where args
				null,       	    // group by
				null,       		// having
				"genre_id",     	// order by
				null         		// limit
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
		settingFragment = new Setting();
		graphPieFragment = new GraphPieFragment();
		graphBarFragment = new GraphBarFragment();
		getSupportFragmentManager().beginTransaction()
				.add(R.id.content, settingFragment)
				.addToBackStack(null)
				.commit();

		// 画面サイズ取得＞ビューのサイズ設定
//		Point point = getDisplaySize(mTextMessage);
	}


	/**
	 * Viesのサイズを取得。
	 * @param view
	 * @return
	 */
	public Point getDisplaySize(View view) {
		Point point = new Point(0, 0);
		point.set(view.getWidth(), view.getHeight());
		return point;
	}

}
