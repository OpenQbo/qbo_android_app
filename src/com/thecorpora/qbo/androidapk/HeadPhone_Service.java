/*
* Software License Agreement (GPLv2 License)
* 
* Copyright (c) 2011 Thecorpora, S.L.
*
* This program is free software; you can redistribute it and/or 
* modify it under the terms of the GNU General Public License as 
* published by the Free Software Foundation; either version 2 of
* the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
* See the GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License 
* along with this program; if not, write to the Free Software 
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
* MA 02110-1301, USA.
*
* Author: Daniel Cuadrado Sánchez <daniel.cuadrado@openqbo.com>
*/
package com.thecorpora.qbo.androidapk;



import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;

public class HeadPhone_Service extends Service {

	private HeadPhones_Receiver apr;
	private final IBinder mBinder = new MyBinder();

	@Override
	public void onCreate() {
		/* register receiver */		
		apr = new HeadPhones_Receiver(); 
		IntentFilter inf = new IntentFilter();
		inf.addAction("android.intent.action.HEADSET_PLUG");
		registerReceiver(apr, inf);		
	}

	@Override
	public void onDestroy() {		
		unregisterReceiver(apr);
	}

	// We return the binder class upon a call of bindService
	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}

	public class MyBinder extends Binder {
		HeadPhone_Service getService() {
			return HeadPhone_Service.this;
		}
	}



}