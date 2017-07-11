package android.library.com.android_library;

import android.graphics.Point;
import android.library.com.android_library.android.library.com.android_library.fragment.GraphBarFragment;
import android.library.com.android_library.android.library.com.android_library.fragment.GraphPieFragment;
import android.library.com.android_library.android.library.com.android_library.fragment.Setting;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
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

		mTextMessage = (TextView) findViewById(R.id.message);
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
		Point point = getDisplaySize(mTextMessage);
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
