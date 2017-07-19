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
import android.library.com.android_library.android.library.com.android_library.fragment.parts.Money;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
		checkTables();

		// BottomNavigationView
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
	 *
	 */
	public void checkTables() {
		DatabaseHelper helper = new DatabaseHelper(this); // ヘルパー準備

		helper.getGenreTable();
		List<Money> moneys = helper.getMoneyTable("money",new String[]{"money_id","title","money","genre_id","subgenre_id","date"},null,null,null,null,null,null);
		moneys.get(0);
		helper.getHistoryTable();
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
