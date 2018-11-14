package com.example.lalalas.myapp.helper;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import com.example.lalalas.myapp.fragments.RestoranListFragment;

public class MyFragmentUtils {
    public static void openAsReplace(Activity activity, int id, Fragment fragment) {

        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void openAsDialog(Activity activity, DialogFragment dlg) {
        FragmentManager fm = activity.getFragmentManager();
        dlg.show(fm, "nekitag");
    }
}
