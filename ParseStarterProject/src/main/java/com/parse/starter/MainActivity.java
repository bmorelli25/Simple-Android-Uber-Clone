/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

//Simple Uber Clone App. Read along. If you have questions feel free to email bmorelli25@gmail.com

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;



public class MainActivity extends AppCompatActivity {

    //Determines if our user is a Rider or Driver
    Switch riderOrDriverSwitch;

    //When the user hits the GET STARTED Button
    public void getStarted(View view){
        String riderOrDriver = "rider";

        if (riderOrDriverSwitch.isChecked()){
            // **DRIVE MODE**
            riderOrDriver = "driver";

        } else {
            // **RIDER MODE**
            riderOrDriver = "rider";
        }

        //save the users option of rider/driver to parse then we redirectUser()
    ParseUser.getCurrentUser().put("riderOrDriver", riderOrDriver);
        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
              if(e == null){
                  Log.i("MyApp", "user signed up");
                  redirectUser();
              } else {
              }
            }
        });

    }

    //once rider/drive has been determined above, we can redirect the user to the correct class
    public void redirectUser(){

        if ( ParseUser.getCurrentUser().get("riderOrDriver").equals("rider")){
            Intent i = new Intent(getApplicationContext(), YourLocation.class);
            startActivity(i);

        } else {
            Intent i = new Intent(getApplicationContext(), ViewRequests.class);
            startActivity(i);

        }

    }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //set up our swtich for use above
    riderOrDriverSwitch = (Switch) findViewById(R.id.riderOrDriverSwitch);

    //Needed for parse
    ParseAnalytics.trackAppOpenedInBackground(getIntent());

    //Make the app full screen
    getSupportActionBar().hide();

      //Logs in an anonymous user if no one is currently logged in. Logs result
      if(ParseUser.getCurrentUser() == null) {
          ParseAnonymousUtils.logIn(new LogInCallback() {
              @Override
              public void done(ParseUser user, ParseException e) {
                  if (e != null) {
                      Log.i("MyApp", "Anonymous login failed");
                  } else {
                      Log.i("MyApp", "Anonymous logged in");
                  }
              }
          });
      //If a user is already logged in, and rider/driver has been established, we redirect the user to correct Activity using the redirectUser() function.
      } else {
          if (ParseUser.getCurrentUser().get("riderOrDriver") != null){
              Log.i("MyApp", "Redirect user");
              redirectUser();
          }
      }

  }
    //Options menu not used as we are full screen in this app
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

    //Options menu not used as we are full screen in this app
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
