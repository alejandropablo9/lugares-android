package mx.com.tejate.testservice.Config;

import android.content.Context;

public class Resources {

    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }

}
