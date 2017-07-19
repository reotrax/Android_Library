package android.library.com.android_library.android.library.com.android_library.fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.library.com.android_library.android.library.com.android_library.fragment.dummy.DummyContent;
import android.library.com.android_library.android.library.com.android_library.fragment.parts.DatabaseHelper;
import android.library.com.android_library.android.library.com.android_library.fragment.parts.MyDatabaseRecyclerViewAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.library.com.android_library.R;
import android.library.com.android_library.android.library.com.android_library.fragment.dummy.DummyContent.DummyItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class DatabaseFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    // TODO: Database
    private DatabaseHelper helper;
    private List<DummyItem> dummyItems;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DatabaseFragment() {
    }


    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DatabaseFragment newInstance(int columnCount) {
        DatabaseFragment fragment = new DatabaseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnListFragmentInteractionListener) context;
        } catch (ClassCastException e) {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        // SQLite
        helper = new DatabaseHelper(getActivity()); // ヘルパー準備
        SQLiteDatabase db = helper.getWritableDatabase(); // データベースを取得

        // PRIMARY KEYの最大値を取得
        Cursor c = db.query(
                "history",    	  	                             // FROM table
                new String[]{"max(history_id) as history_id"},   // SELECT columns
                null,                                            // WHERE
                null,                                            // WHERE args
                null,       	                                 // GROUP BY
                null,       		                             // HAVING
                null,           	                             // ORDER GY
                null         		                             // LIMIT
        );
        c.moveToFirst();
        int max_history_id = Integer.valueOf(c.getString(c.getColumnIndex("history_id")));

        // 全レコードを日付の降順に取得
        c = db.query(
                "history",    	  	                      // FROM table
                new String[]{"date", "genre", "money"},   // SELECT columns
                null, /*"date=?",*/                       // WHERE
                null, /*new String[]{"2017-07-01"},*/     // WHERE args
                null,       	                          // GROUP BY
                null,       		                      // HAVING
                "date DESC, genre DESC",     	          // ORDER BY
                null         		                      // LIMIT
        );
        c.moveToFirst();

        // Listに取得したレコードを登録
        Log.d("SELECT", "開始 / " + c.getCount());
        dummyItems = new ArrayList<>();
        for (int i=0; i<c.getCount(); i++) {
            String date = c.getString(c.getColumnIndex("date"));
            String genre = c.getString(c.getColumnIndex("genre"));
            String money = c.getString(c.getColumnIndex("money"));
            Log.d("SELECT " + i, date + " / " + genre + " / " + money);
            dummyItems.add(new DummyItem(Integer.toString(/*max_history_id +*/ i + 1), date, genre, money, "content", "details"));
            c.moveToNext();
        }

        db.close();
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_database_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            MyDatabaseRecyclerViewAdapter adapter = new MyDatabaseRecyclerViewAdapter(dummyItems, mListener) {
                @Override
                protected void onRawSelectListener(DummyItem item) {
                    super.onRawSelectListener(item);
                    Toast.makeText(getContext(), item.date, Toast.LENGTH_SHORT).show();
                }
            };
            recyclerView.setAdapter(adapter);
        }
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
