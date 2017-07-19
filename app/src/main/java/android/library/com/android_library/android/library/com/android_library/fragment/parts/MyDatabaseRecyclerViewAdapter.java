package android.library.com.android_library.android.library.com.android_library.fragment.parts;

import android.library.com.android_library.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.library.com.android_library.android.library.com.android_library.fragment.DatabaseFragment.OnListFragmentInteractionListener;
import android.library.com.android_library.android.library.com.android_library.fragment.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyDatabaseRecyclerViewAdapter extends RecyclerView.Adapter<MyDatabaseRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyDatabaseRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_database, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mDateView.setText(mValues.get(position).date);
        holder.mGenreView.setText(mValues.get(position).genre);
        holder.mMoneyView.setText(mValues.get(position).money);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRawSelectListener(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * ViewHolderに設定したClickListenerから呼ばれる。<br>
     * 呼び出し元のフラグメントなどでOverrideしてClickListenerの処理内容を記述できる。
     * @param item
     */
    protected void onRawSelectListener(DummyItem item) {
    }

    /**
     * RecyclerViewのアイテムデータを保持するホルダー。
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mDateView;
        public final TextView mGenreView;
        public final TextView mMoneyView;
        public final TextView mContentView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView      = (TextView) view.findViewById(R.id.id);
            mDateView    = (TextView) view.findViewById(R.id.list_date);
            mGenreView   = (TextView) view.findViewById(R.id.list_genre);
            mMoneyView   = (TextView) view.findViewById(R.id.list_money);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
