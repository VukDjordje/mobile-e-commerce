package rs.fon.emobill.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import rs.fon.emobill.R;
import rs.fon.emobill.utility.ErrorMessageDialog;
import rs.fon.emobill.utility.SharedPreferenceUtils;
import rs.fon.emobill.utility.VolleyRequestQueue;

public class RatingsActivity extends AppCompatActivity{

    ActionBar actionBar;

    RatingBar ratingBar;
    TextView ratingScale;
    EditText feedBack;
    Button sendFeedBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ratingBar = findViewById(R.id.ratingBar);
        ratingScale = findViewById(R.id.tvRatingScale);
        feedBack = findViewById(R.id.etFeedback);
        sendFeedBack = findViewById(R.id.btnSendFeedback);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                ratingScale.setText(String.valueOf(v));
                switch ((int)ratingBar.getRating()){
                    case 1:
                        ratingScale.setText("Very bad");
                        break;
                    case 2:
                        ratingScale.setText("Need some improvement");
                        break;
                    case 3:
                        ratingScale.setText("Good");
                        break;
                    case 4:
                        ratingScale.setText("Great");
                        break;
                    case 5:
                        ratingScale.setText("Awesome. I love it");
                        break;
                    default:
                        ratingScale.setText("");
                }
            }
        });

        sendFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (feedBack.getText().toString().isEmpty()) {
                    Toast.makeText(RatingsActivity.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                    return;
                }

                try{
                    String url = "https://emobiletech.000webhostapp.com/api/rating/comment.php";

                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("user_id", SharedPreferenceUtils.getInstance().getInt("user_id"));
                    jsonBody.put("comment", feedBack.getText().toString());
                    jsonBody.put("rating_scale", ratingScale.getText().toString());

                    JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int status = response.getInt("status");
                                if(status == 0){
                                    Toast.makeText(RatingsActivity.this, "Network error", Toast.LENGTH_LONG).show();
                                }else{
                                    String message = response.getString("message");
                                    showSuccess(message);
                                    feedBack.setText("");
                                    ratingBar.setRating(0);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RatingsActivity.this, "Network error", Toast.LENGTH_LONG).show();
                        }
                    });

                    VolleyRequestQueue.getInstance(RatingsActivity.this).addToRequestQueue(jor);
                }catch (JSONException ex){
                    ex.printStackTrace();
                }

            }
        });
    }

    private void showError(String errorMessage) {
        ErrorMessageDialog.showMessage(getString(R.string.error), errorMessage, this);
    }

    private void showSuccess(String successMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(R.string.success);
        builder.setMessage(successMessage);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(RatingsActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
