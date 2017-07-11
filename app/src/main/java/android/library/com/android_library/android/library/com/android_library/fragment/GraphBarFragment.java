package android.library.com.android_library.android.library.com.android_library.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.library.com.android_library.R;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GraphBarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GraphBarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GraphBarFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	public GraphBarFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment GraphBarFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static GraphBarFragment newInstance(String param1, String param2) {
		GraphBarFragment fragment = new GraphBarFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}


	BarChart barChart;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_graph_bar, container, false);

		barChart = (BarChart) view.findViewById(R.id.bar);
		barChart.getAxisLeft().setAxisMaximum(10000f);
		barChart.getAxisLeft().setAxisMinimum(0f);
//		barChart.getAxisLeft().setStartAtZero(false);
		barChart.getDescription().setText("Bar 説明");
		barChartSetting();

		return view;
	}

	/**
	 * v3以降の書き方
	 */
	public void barChartSetting() {
		//
		List<BarEntry> entries = new ArrayList<>();
		entries.add(new BarEntry(0f, 3000f));
		entries.add(new BarEntry(1f, 8000f));
		entries.add(new BarEntry(2f, 6000f));
		entries.add(new BarEntry(3f, 5000f));
		// gap of 2f
		entries.add(new BarEntry(5f, 7000f));
		entries.add(new BarEntry(6f, 6000f));

		BarDataSet set = new BarDataSet(entries, "Barデータセット");

		//
		BarData data = new BarData(set);
		data.setBarWidth(0.9f); // set custom bar width
		barChart.setData(data);
		barChart.setClickable(false); //
		barChart.setDrawValueAboveBar(false); // 値の表示位置。false:バーの内側、trur：バーの外側
		barChart.setEnabled(false); //
		barChart.setFitBars(false); // make the x-axis fit exactly all bars
		barChart.setFocusable(false); //
		barChart.setFocusableInTouchMode(false); //
		barChart.setHighlightFullBarEnabled(false); //
		barChart.setTouchEnabled(false); // タッチ関係の反応の許可
		barChart.setDrawBorders(false);
		barChart.invalidate(); // refresh


		// #Grouped BarChart
//		int[] group1 = {500,	3500,	5500,	7500,	8500,	9500};
//		int[] group2 = {2000,	4000,	6000,	8000,	9000,	10000};
//
//		List<BarEntry> entriesGroup1 = new ArrayList<>();
//		List<BarEntry> entriesGroup2 = new ArrayList<>();
//
//		// fill the lists
//		for(int i = 0; i < group1.length; i++) {
//			entriesGroup1.add(new BarEntry(i, group1[i]);
//			entriesGroup2.add(new BarEntry(i, group2[i]);
//		}
//
//		BarDataSet set1 = new BarDataSet(entriesGroup1, "Group 1");
//		BarDataSet set2 = new BarDataSet(entriesGroup2, "Group 2");
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
//			throw new RuntimeException(context.toString()
//					+ " must implement OnFragmentInteractionListener");
		}
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
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}
}
