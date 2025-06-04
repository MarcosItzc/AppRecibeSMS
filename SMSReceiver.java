package net.n_21011018.recibirsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        Bundle bundle = intent.getExtras();
        SmsMessage [] smsMessages = null;
        if(bundle != null){
            String informacion = "SMS recibido desde: ";
            Object [] pdus = (Object []) bundle.get("pdus");
            smsMessages = new SmsMessage[pdus.length];
            for(int i = 0; i < smsMessages.length; i++){
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    String format = bundle.getString("format"); // Necesario para versiones modernas
                    smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }

                informacion = informacion + smsMessages[i].getOriginatingAddress() + "\n";
                informacion = informacion + "*** Mensaje ***\n";
                informacion = informacion + smsMessages[i].getMessageBody() + "\n";
            }
            Toast.makeText(context, informacion, Toast.LENGTH_SHORT).show();
        }
    }

}
