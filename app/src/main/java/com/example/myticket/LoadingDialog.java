package com.example.myticket;

import android.app.Dialog;
import android.content.Context;

public class LoadingDialog {
    Context context;
    Dialog dialog;

    public LoadingDialog(Context context) {
        this.context = context;
    }

    public void showDialog(){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.activity_loader);
        dialog.create();
        dialog.show();
    }

    public  void hideDialog(){
        dialog.dismiss();
    }
}
