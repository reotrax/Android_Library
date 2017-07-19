package android.library.com.android_library.android.library.com.android_library.fragment.parts;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by reo on 2017/07/19.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Money {
    int money_id;
    String title;
    int money;
    int genre_id;
    int subgenre_id;
    String date;
}