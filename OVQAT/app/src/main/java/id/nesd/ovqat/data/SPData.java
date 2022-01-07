package id.nesd.ovqat.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SPData {

    @SuppressLint("StaticFieldLeak")
    private static SPData mInstance;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private static final String spName = "ovqat";
    private static final String user = "user";

    private SPData(Context context) {
        mContext = context;
    }

    public static synchronized SPData getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SPData(context);
        }
        return mInstance;
    }

    public String getUser() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(user, null);
    }

    public void setUser(String json) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(user, json);
        editor.apply();
    }

    public void clear(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}