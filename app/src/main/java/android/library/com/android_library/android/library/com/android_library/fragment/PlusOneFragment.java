package android.library.com.android_library.android.library.com.android_library.fragment;

import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.library.com.android_library.R;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.plus.PlusOneButton;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link PlusOneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlusOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlusOneFragment extends Fragment implements View.OnClickListener {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	// The request code must be 0 or greater.
	private static final int PLUS_ONE_REQUEST_CODE = 0;
	// The URL to +1.  Must be a valid URL.
	private final String PLUS_ONE_URL = "http://developer.android.com";
	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	private PlusOneButton mPlusOneButton;
	private EditText display;
	private Button button0,button00,button1,button2,button3,button4,button5,button6,button7,button8,button9;

	private OnFragmentInteractionListener mListener;

	public PlusOneFragment() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment PlusOneFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static PlusOneFragment newInstance(String param1, String param2) {
		PlusOneFragment fragment = new PlusOneFragment();
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
		View view = inflater.inflate(R.layout.fragment_plus_one, container, false);

		//Find the +1 button
		mPlusOneButton = (PlusOneButton) view.findViewById(R.id.plus_one_button);
		display = (EditText) view.findViewById(R.id.display);
		button1 = (Button) view.findViewById(R.id.button1);
		button2 = (Button) view.findViewById(R.id.button2);
		button3 = (Button) view.findViewById(R.id.button3);
		button7 = (Button) view.findViewById(R.id.button7);
		button8 = (Button) view.findViewById(R.id.button8);
		button9 = (Button) view.findViewById(R.id.button9);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button7.setOnClickListener(this);
		button8.setOnClickListener(this);
		button9.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button1:
				display.setText("1");
				//Activit経由で

				break;
			case R.id.button2:
				display.setText("2");
				break;
			case R.id.button3:
				display.setText("3");
				break;
			case R.id.button7:
				display.setText("7");
				break;
			case R.id.button8:
				display.setText("8");
				break;
			case R.id.button9:
				display.setText("9");
				break;
			default:
				break;
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		// Refresh the state of the +1 button each time the activity receives focus.
		mPlusOneButton.initialize(PLUS_ONE_URL, PLUS_ONE_REQUEST_CODE);
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
