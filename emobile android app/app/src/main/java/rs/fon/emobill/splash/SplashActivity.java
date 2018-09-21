package rs.fon.emobill.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import rs.fon.emobill.R;
import rs.fon.emobill.home.HomeActivity;
import rs.fon.emobill.login.SignInActivity;
import rs.fon.emobill.utility.SharedPreferenceUtils;

public class SplashActivity extends AppCompatActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // is user logged in
                if(SharedPreferenceUtils.getInstance().getString("email").equalsIgnoreCase("")){
                    // fo to signin activity
                    Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(intent);
                }else{
                    // go to home activity
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 3000);
    }
}
