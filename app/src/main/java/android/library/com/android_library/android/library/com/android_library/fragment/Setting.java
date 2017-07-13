package android.library.com.android_library.android.library.com.android_library.fragment;

import android.content.Context;
import android.library.com.android_library.R;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Setting.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Setting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Setting extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    // TODO:
    @BindView(R.id.radioGroup) RadioGroup radioGroup;
    @BindView(R.id.add_genre) EditText add_genre;
    @BindView(R.id.date) EditText date;
    @BindView(R.id.money) EditText money;
    @BindView(R.id.genre) Spinner spinner_genre;
    @BindView(R.id.registration) Button registration;
    private Unbinder unbinder;

    // スピナー：支出・収入のジャンル
    final String spending1 = "";
    final String income1   = "";
    String[] genreData = {""};
    String[] spending = {"カード引き落とし", "食費", "本", "雑費"}; // 支出
    String[] income   = {"給料", "おこづかい", "臨時収入"}; // 収入
    // スピナー：支出・収入のサブジャンル
    // スピナー用
    ArrayAdapter<String> adapter_genre, adapter_sub;

    // デバッグ用
    TextView debugtext;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Setting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Setting.
     */
    // TODO: Rename and change types and number of parameters
    public static Setting newInstance(String param1, String param2) {
        Setting fragment = new Setting();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
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
        View view = inflater.inflate(android.library.com.android_library.R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this, view);

//        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
//        date = (EditText) view.findViewById(R.id.date);
//        money = (EditText) view.findViewById(R.id.money);
//        spinner_genre    = (Spinner) view.findViewById(R.id.genre);
//        add_genre = (EditText) view.findViewById(R.id.add_genre);
//        registration = (Button) view.findViewById(R.id.registration);
//        debugtext = (TextView) view.findViewById(R.id.debugText);

        // スピナー設定
        genreData    = spending;
        adapter_genre = new ArrayAdapter<String>(getContext(), android.library.com.android_library.R.layout.support_simple_spinner_dropdown_item, genreData);
        spinner_genre.setAdapter(adapter_genre);

        // リスナー設定
        radioGroup.setOnCheckedChangeListener(this);
        registration.setOnClickListener(this);

        return view;
    }

    /**
     * リスナー：ボタン
     * @param view
     */
    @Override
    public void onClick(View view) {
        debugtext.setText(add_genre.getText());

        if (view == null) {
            // Nothing to do.
            debugtext.setText("view == null！");
        } else if (date.getText().toString().trim().equals("")) {
            // Nothing to do.
            debugtext.setText("日付が正しく入力されていません");
        } else if (money.getText().toString().trim().equals("")) {
            // Nothing to do.
            debugtext.setText("金額が正しく入力されていません");
        } else switch (view.getId()) {
                case android.library.com.android_library.R.id.registration:
                    debugtext.setText("登録！");
                    break;
            }
    }

    /**
     * リスナー設定：ラジオグループ
     * @param radioGroup
     * @param i
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case android.library.com.android_library.R.id.spending:
                // 支出：スピナーを変更
                genreData = spending;
                break;
            case android.library.com.android_library.R.id.income:
                // 収入：スピナーを変更
                genreData = income;
                break;
        }

        adapter_genre = new ArrayAdapter<String>(getContext(), android.library.com.android_library.R.layout.support_simple_spinner_dropdown_item, genreData);
        spinner_genre.setAdapter(adapter_genre);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
