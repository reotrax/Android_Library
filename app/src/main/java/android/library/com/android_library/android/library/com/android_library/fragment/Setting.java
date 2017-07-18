package android.library.com.android_library.android.library.com.android_library.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.library.com.android_library.R;
import android.library.com.android_library.android.library.com.android_library.fragment.parts.DatabaseHelper;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Setting.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Setting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Setting extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    // TODO:
    //    @BindView(R.id.radioGroup)
    private RadioGroup radioGroup;
    private EditText dateEditText;
    private EditText money;
    private Spinner spinner_genre;
    private CheckBox checkBox;
    private EditText add_genre;
    private Button yearBtn, registration;
    //    private Unbinder unbinder;

    // スピナー：支出・収入のジャンル
    String[] genreData = {""};
    String[] spending = {"カード引き落とし", "食費", "本", "雑費"}; // 支出
    String[] income   = {"給料", "おこづかい", "臨時収入"}; // 収入
    // スピナー：支出・収入のサブジャンル
    // スピナー用
    ArrayAdapter<String> adapter_genre;

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

        // packageのトップ？をandroidにしてしまったため、エラーが発生するので使えない。package名変更を試したが失敗...
        // unbinder = ButterKnife.bind(this, view);

        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        dateEditText = (EditText) view.findViewById(R.id.year);
        yearBtn = (Button) view.findViewById(R.id.yearBtn);
        money = (EditText) view.findViewById(R.id.money);
        spinner_genre    = (Spinner) view.findViewById(R.id.genre);
        add_genre = (EditText) view.findViewById(R.id.add_genre);
        checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        registration = (Button) view.findViewById(R.id.registration);
        debugtext = (TextView) view.findViewById(R.id.debugText);

        // 日付設定
        String[] ymd = todayOfString();
        dateEditText.setText(ymd[0] + "-" + ymd[1] + "-" + ymd[2]);

        // スピナー設定
        genreData = spending;
        adapter_genre = new ArrayAdapter<>(getContext(), android.library.com.android_library.R.layout.support_simple_spinner_dropdown_item, genreData);
        spinner_genre.setAdapter(adapter_genre);

        // チェックボックス関係
        add_genre.setTextColor(Color.LTGRAY);
        checkBox.setChecked(false);

        // リスナー設定
        radioGroup.setOnCheckedChangeListener(this);
        yearBtn.setOnClickListener(this);
        registration.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(this);

        return view;
    }

    /**
     * 今日の日付を文字列で作成。/n
     * Android OSのバージョンによって内部処理が異なる。
     * @return 要素[0]:String year, [1]:String month, [2]:String day
     */
    private String[] todayOfString() {
        // Android Versionを取得
        int versionCode = Build.VERSION.SDK_INT;
        int versionCodeLine = Build.VERSION_CODES.N;
        debugtext.setText(Integer.toString(versionCode) + " / " + versionCodeLine);

        // Android OSのバージョンで処理が異なる。
        String year = "";
        String month = "";
        String day = "";
        if (versionCode >= versionCodeLine) {
            Calendar cal = Calendar.getInstance();
            year  = Integer.toString(cal.get(Calendar.YEAR));
            month = String.format("%02d", cal.get(Calendar.MONTH) + 1);
            day   = String.format("%02d", cal.get(Calendar.DATE));
        } else if (versionCode < versionCodeLine) {
            Date d = new Date();
            year  = Integer.toString(1900 + d.getYear());
            month = String.format("%02d", d.getMonth() + 1);
            day   = String.format("%02d", d.getDate() + 1);
        }

        return new String[]{year, month, day};
    }

    /**
     * リスナー：ボタン
     * @param view
     */
    @Override
    public void onClick(View view) {
        debugtext.setText(add_genre.getText());

        switch (view.getId()) {
            case R.id.yearBtn:
                final String[] ymd;
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        // イベントリスナー登録
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                setYearMonthDay(year, month, day);
                            }
                        }, 2017, 7, 14);
                datePickerDialog.setTitle("ぴっかー");
                datePickerDialog.show();
                break;
            case R.id.registration:
                if (view == null) {
                    // Nothing to do.
                    debugtext.setText("view == null！");
                } else if (dateEditText.getText().toString().trim().equals("")) {
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

                // TODO: テスト登録
                DatabaseHelper helper = new DatabaseHelper(getActivity()); // ヘルパー準備
                SQLiteDatabase db = helper.getWritableDatabase(); // データベースを取得
                db.execSQL("INSERT INTO history VALUES " +
                        "(5, '1', '2017-07-26', '1', 1000, 'content05')"
                );
                break;
            default:
                break;
        }
    }

    /**
     * 日付を設定する。
     * @param year
     * @param month
     * @param day
     */
    public void setYearMonthDay(int year, int month, int day) {
        dateEditText.setText(
                year + "-" +
                String.format("%02d", month) + "-" +
                String.format("%02d", day)
        );
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

        adapter_genre = new ArrayAdapter<>(getContext(), android.library.com.android_library.R.layout.support_simple_spinner_dropdown_item, genreData);
        spinner_genre.setAdapter(adapter_genre);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * リスナー：チェックボックス
     * @param compoundButton
     * @param b
     */
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b == true) {
            spinner_genre.setEnabled(false);
            add_genre.setTextColor(Color.BLACK);
        } else if (b == false) {
            spinner_genre.setEnabled(true);
            add_genre.setTextColor(Color.LTGRAY);
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
//        unbinder.unbind();
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
