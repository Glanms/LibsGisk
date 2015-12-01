package com.base.list.libsgisk.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by Administrator on 2015/11/30.
 * 多个BroadcastReceiver设置
 */
public class BroadcastHelper  {


    public class SmsReceiver extends BroadcastReceiver{
        private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
        private static final String TAG = "ReceivingSMSReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
             if(intent.getAction().equals(SMS_RECEIVED)){
                 T.showShort(context,"收到SMS消息");
                 Bundle bundle = intent.getExtras();
                 if(null != bundle) {
                     Object[] pduObj = (Object[]) bundle.get("sMsg");
                     SmsMessage[] smsMessages = new SmsMessage[pduObj.length];
                     for(int i=0;i<pduObj.length;i++){
                         smsMessages[i] = SmsMessage.createFromPdu((byte[])pduObj[i]);
                     }
                     for (SmsMessage message:smsMessages){
                         String msg = message.getMessageBody();
                         LogHelper.d(TAG,"Broadcast Message Info:"+msg);
                         String to = message.getOriginatingAddress();
                         message.getServiceCenterAddress();
                     }
                 }
             }
        }
    }
}
