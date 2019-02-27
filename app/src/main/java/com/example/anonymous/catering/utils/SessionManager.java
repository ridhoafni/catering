package com.example.anonymous.catering.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context _context;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String ID_MEMBER = "id_member";
    public static final String NAMA = "nama_lengkap";
    public static final String JK = "jk";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String AGAMA = "agama";
    public static final String NO_HP = "no_hp";

    public Context get_context(){
        return _context;
    }

    //constructor
    public SessionManager(Context context){
        this._context       = context;
        sharedPreferences   = PreferenceManager.getDefaultSharedPreferences(context);
        editor              = sharedPreferences.edit();
    }

    //session untuk member yang login (catering)
    public void createLoginSessionMember(Integer id, String nama, String no_hp,
                                         String email, String password, String username,
                                         String jk,
                                         String agama){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putInt(ID_MEMBER, id);
        editor.putString(NAMA, nama);
        editor.putString(NO_HP, no_hp);
        editor.putString(EMAIL, email);
        editor.putString(JK, jk);
        editor.putString(PASSWORD, password);
        editor.putString(USERNAME, username);
        editor.putString(AGAMA, agama);
        editor.apply();
    }

    public HashMap<String, String> getMemberProfile(){
        HashMap<String,String> member = new HashMap<>();
        member.put(ID_MEMBER, String.valueOf(sharedPreferences.getInt(ID_MEMBER,0)));
        member.put(NAMA, sharedPreferences.getString(NAMA,null));
        member.put(JK, sharedPreferences.getString(JK,null));
        member.put(USERNAME, sharedPreferences.getString(USERNAME,null));
        member.put(EMAIL, sharedPreferences.getString(EMAIL,null));
        member.put(PASSWORD, sharedPreferences.getString(PASSWORD,null));
        member.put(AGAMA, sharedPreferences.getString(AGAMA,null));
        member.put(NO_HP, sharedPreferences.getString(NO_HP,null));
        return member;
    }

    public void logoutMember(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

}
