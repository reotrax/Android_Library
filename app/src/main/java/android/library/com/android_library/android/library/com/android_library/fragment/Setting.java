package android.library.com.android_library.android.library.com.android_library.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.library.com.android_library.R;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Setting.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Setting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Setting extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO:
    private EditText text_subgenre;
    private Spinner spinner_genre, spinner_subgenre;
    // スピナー項目
    String[] area      = {"北海道", "東京", "大阪", "福岡", "沖縄"};
    String[] genre     = {"選択してください", "音楽","美術","建築","スポーツ","TV/ラジオ",};
    String[] sub_music = {"クラブ","ロック","ジャズ"};
    String[] sub_art   = {"ロマン派","抽象派","印象派"};
    String[] sub_architect = {"近代建築","西洋建築","メタボリズム"};
    String[] sub_sport = {"野球","サッカー","オリンピック"};
    String[] sub_tv = {"日テレ","テレ朝","ＮＨＫ"};


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
        View view = inflater.inflate(R.layout.fragment_setting, container, false);


        // スピナー
        spinner_genre    = (Spinner) view.findViewById(R.id.spinner_genre);
        spinner_subgenre = (Spinner) view.findViewById(R.id.spinner_subgenre);
        // スピナーアダプター
        ArrayAdapter<String> adapter_genre    = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, genre);
        ArrayAdapter<String> adapter_subgenre = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, sub_music);
        spinner_genre.setAdapter(adapter_genre);
        spinner_subgenre.setAdapter(adapter_subgenre);
        spinner_genre.setOnItemSelectedListener(this);

        // EditText
        text_subgenre = (EditText) view.findViewById(R.id.editText_subgenre);


        return view;
    }

    /**
     * スピナーの項目変更時の対応
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        ArrayAdapter<String> adapter_subgenre = null;

        switch (adapterView.getSelectedItem().toString()) {
            case "選択してください":
                break;
            case "音楽":
                adapter_subgenre = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, sub_music);
                break;
            case "美術":
                adapter_subgenre = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, sub_art);
                break;
            case "建築":
                adapter_subgenre = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, sub_architect);
                break;
            case "スポーツ":
                adapter_subgenre = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, sub_sport);
                break;
            case "TV/ラジオ":
                adapter_subgenre = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, sub_tv);
                break;
        }

        if (adapter_subgenre != null)
            spinner_subgenre.setAdapter(adapter_subgenre);

    }

    /**
     * スピナーの？
     * @param adapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
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
