package mx.com.tejate.testservice.Config;

import android.content.Context;
import android.widget.Toast;

public class Util {

    public static void showMessage(String mensaje, Context context ){
        Toast.makeText(context,mensaje,Toast.LENGTH_SHORT).show();
    }
}
