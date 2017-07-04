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

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GraphPieFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GraphPieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GraphPieFragment extends Fragment {

	//
	PieChart pieChart;
	View view;

	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	public GraphPieFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment GraphFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static GraphPieFragment newInstance(String param1, String param2) {
		GraphPieFragment fragment = new GraphPieFragment();
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		view = inflater.inflate(R.layout.fragment_graph_pie, container, false);
		return view;
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

	private void createPieChart() {
		PieChart pieChart = (PieChart) view.findViewById(R.id.pie_chart);

		pieChart.setDrawHoleEnabled(true); // 真ん中に穴を空けるかどうか
		pieChart.setHoleRadius(50f);       // 真ん中の穴の大きさ(%指定)
		pieChart.setDrawHoleEnabled(true);//.setHoleColorTransparent(true);
		pieChart.setTransparentCircleRadius(55f);
		pieChart.setRotationAngle(270);          // 開始位置の調整
		pieChart.setRotationEnabled(true);       // 回転可能かどうか
		pieChart.getLegend().setEnabled(true);   //
		pieChart.getDescription().setText("PieChart 説明");
		pieChart.setData(createPieChartData());

		// 更新
		pieChart.invalidate();
		// アニメーション
		pieChart.animateXY(2000, 2000); // 表示アニメーション
	}

	// pieChartのデータ設定
	private PieData createPieChartData() {

		List<String> xVals = new ArrayList<>();
		xVals.add("A");
		xVals.add("B");
		xVals.add("C");

		List<PieEntry> yVals = new ArrayList<>();
		yVals.add(new PieEntry(20, 0));
		yVals.add(new PieEntry(30, 1));
		yVals.add(new PieEntry(50, 2));

		PieDataSet dataSet = new PieDataSet(yVals, "Data");
		dataSet.setSliceSpace(5f);
		dataSet.setSelectionShift(1f);

		// 色の設定
		List<Integer> colors = new ArrayList<>();
		colors.add(ColorTemplate.COLORFUL_COLORS[0]);
		colors.add(ColorTemplate.COLORFUL_COLORS[1]);
		colors.add(ColorTemplate.COLORFUL_COLORS[2]);
		dataSet.setColors(colors);
		dataSet.setDrawValues(true);

		PieData data = new PieData(dataSet);//xVals, dataSet);
		data.setValueFormatter(new PercentFormatter());

		// テキストの設定
		data.setValueTextSize(12f);
		data.setValueTextColor(Color.WHITE);
		return data;
	}

}

