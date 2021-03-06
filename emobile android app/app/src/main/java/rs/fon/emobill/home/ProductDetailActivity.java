package rs.fon.emobill.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.daimajia.slider.library.SliderLayout;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rs.fon.emobill.R;
import rs.fon.emobill.adapters.CommentRecyclerViewAdapter;
import rs.fon.emobill.adapters.ProductRecyclerViewAdapter;
import rs.fon.emobill.databinding.ActivityProductDetailBinding;
import rs.fon.emobill.models.Banner;
import rs.fon.emobill.models.Comment;
import rs.fon.emobill.models.Product;
import rs.fon.emobill.utility.ErrorMessageDialog;
import rs.fon.emobill.utility.SharedPreferenceUtils;
import rs.fon.emobill.utility.VolleyRequestQueue;

import static rs.fon.emobill.MyApp.getContext;

public class ProductDetailActivity extends AppCompatActivity {
    private ActivityProductDetailBinding binding;
    private Product product;
    private ActionBar actionBar;
    private RecyclerView rvComment;
    private CommentRecyclerViewAdapter adapter;
    private List<Comment> comments;
    private TextView tvEmpty;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            product = (Product) extras.getSerializable("PRODUCT");
            binding.tvTitle.setText(product.getTitle());
            binding.tvDescription.setText(product.getDescription());
            binding.tvPrice.setText(product.getPriceS() + " din.");
            Picasso.with(this)
                    .load(product.getImage())
                    .into(binding.ivImage);
        }

        binding.tvAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(product);
            }
        });

        rvComment = binding.rvComment;
        tvEmpty = binding.tvEmpty;
        comments = new ArrayList<>();

        initData();
        adapter = new CommentRecyclerViewAdapter(comments, ProductDetailActivity.this);
        LinearLayoutManager llm = new LinearLayoutManager(ProductDetailActivity.this);

        rvComment.setHasFixedSize(true);
        rvComment.setLayoutManager(llm);
        rvComment.setNestedScrollingEnabled(false);

        rvComment.setAdapter(adapter);

        binding.btnSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addComment();
            }
        });
    }

    private void addComment() {
        if (binding.rbProductRating.getRating() == 0 || binding.etComment.getText().toString().isEmpty()) {
            showError(getString(R.string.comment_error));
            return;
        }

        try {
            String url = "https://emobiletech.000webhostapp.com/api/comment/save_comment.php";

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("user_id", SharedPreferenceUtils.getInstance().getInt("user_id"));
            jsonBody.put("product_id", product.getProductID());
            jsonBody.put("rating", Math.round(binding.rbProductRating.getRating()));
            jsonBody.put("comment", binding.etComment.getText().toString());

            JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        int status = response.getInt("status");
                        if (status == 0) {
                            String message = response.getString("message");
                            showError(message);
                        } else {
                            String message = response.getString("message");
                            showSuccess(message);
                            binding.rbProductRating.setRating(0);
                            binding.etComment.setText("");
                            initData();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    showError(getString(R.string.general_error));
                }
            });

            VolleyRequestQueue.getInstance(this).addToRequestQueue(jor);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    private void initData() {
        String url = "https://emobiletech.000webhostapp.com/api/comment/comments.php?prod_id="+product.getProductID();

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int status = response.getInt("status");
                    if(status == 0){
                        String message = response.getString("message");
                        rvComment.setVisibility(View.GONE);
                        tvEmpty.setVisibility(View.VISIBLE);
                        tvEmpty.setText(message);
                    }else{
                        comments.clear();
                        rvComment.setVisibility(View.VISIBLE);
                        tvEmpty.setVisibility(View.GONE);
                        JSONArray data = response.getJSONArray("data");
                        for(int i = 0; i < data.length(); i++){

                            JSONObject commentObj = data.getJSONObject(i);

                            Comment c = new Comment(commentObj.getInt("comment_id"), commentObj.getString("full_name"),
                                    commentObj.getInt("rating"),commentObj.getString("comment"));
                            comments.add(c);
                        }
                        adapter.notifyDataSetChanged();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showError(getString(R.string.general_error));
                rvComment.setVisibility(View.GONE);
                tvEmpty.setVisibility(View.VISIBLE);
                tvEmpty.setText(R.string.general_error);
            }
        });

        VolleyRequestQueue.getInstance(getContext()).addToRequestQueue(jor);
    }

    private void addToCart(Product product) {
        try {
            String url = "https://emobiletech.000webhostapp.com/api/cart/add_to_cart.php";

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("user_id", SharedPreferenceUtils.getInstance().getInt("user_id"));
            jsonBody.put("product_id", product.getProductID());

            JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        int status = response.getInt("status");
                        if (status == 0) {
                            String message = response.getString("message");
                            showError(message);
                        } else {
                            String message = response.getString("message");
                            showSuccess(message);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    showError(getString(R.string.general_error));
                }
            });

            VolleyRequestQueue.getInstance(getApplicationContext()).addToRequestQueue(jor);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
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
