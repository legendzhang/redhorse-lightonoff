package com.redhorse.lightonoff;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.view.WindowManager;
import android.widget.Toast;

public class lightonoff extends Activity {

    protected Dialog onCreateDialog(int id) {
        return new AlertDialog.Builder(lightonoff.this)
        .setTitle("选择亮度")
        .setItems(R.array.select_dialog_items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                /* User clicked so do some stuff */
                String[] items = getResources().getStringArray(R.array.select_dialog_items);
                new AlertDialog.Builder(lightonoff.this)
                        .setMessage("You selected: " + which + " , " + items[which] + getBrightness())
                        .show();
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.screenBrightness = 255;
                getWindow().setAttributes(lp);
            }
        })
        .create();   	
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		setContentView(R.layout.main);
        showDialog(0);
    }
    private int getBrightness()  
    {  
        int brightness = 0;  
        try  
        {  
            brightness = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);  
        }  
        catch(SettingNotFoundException snfe)  
        {  
            brightness = 0;  
        }  
        return brightness;  
    }  

    private void setBrightness(int brightness)  
    {  
        WindowManager.LayoutParams lp = getWindow().getAttributes();  
        lp.screenBrightness = brightness;  
        getWindow().setAttributes(lp);  
    }      

}