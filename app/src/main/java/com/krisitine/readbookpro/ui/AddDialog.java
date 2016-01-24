package com.krisitine.readbookpro.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.krisitine.readbookpro.R;

/**
 * Created by tyq on 2015/9/17.
 */
public class AddDialog extends Dialog {

    private ImageButton btn_add;

    public AddDialog(Context context, int themeResId) {
        super(context, themeResId);
        setCustomDialog();

    }
    private void setCustomDialog(){
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.add_dialog, null);
        super.setContentView(mView);

        Window dialogWindow = this.getWindow();

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        lp.width = 800;
        lp.height = 800;
        btn_add = (ImageButton) findViewById(R.id.positiveButton);
    }

    public View getPosBtn(){
        return btn_add;
    }
}
