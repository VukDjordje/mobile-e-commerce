package rs.fon.emobill.home.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import rs.fon.emobill.R;
import rs.fon.emobill.adapters.CartRecyclerViewAdapter;
import rs.fon.emobill.home.CategoryProductsActivity;
import rs.fon.emobill.home.OrderActivity;
import rs.fon.emobill.models.Cart;
import rs.fon.emobill.models.Product;
import rs.fon.emobill.utility.ErrorMessageDialog;
import rs.fon.emobill.utility.SharedPreferenceUtils;
import rs.fon.emobill.utility.VolleyRequestQueue;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    private List<Cart> cartList;
    private RecyclerView rvCart;
    private String TAG = "CartFragment";
    private CartRecyclerViewAdapter adapter;
    private TextView tvTotal, tvPlaceOrder;
    private DecimalFormat df = new DecimalFormat("#0.00");
    private double total;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.cart_title);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        rvCart = (RecyclerView) view.findViewById(R.id.rvCart);
        tvTotal = (TextView) view.findViewById(R.id.tvInTotal);
        tvPlaceOrder = (TextView) view.findViewById(R.id.tvPlaceOrder);

        initData();

        adapter = new CartRecyclerViewAdapter(cartList, this.getContext(), this);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());

        rvCart.setHasFixedSize(true);
        rvCart.setLayoutManager(llm);

        rvCart.setAdapter(adapter);

        tvPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartList.isEmpty()){
                    showError(getString(R.string.empty_cart_error));
                }else {
                    Intent intent = new Intent(getContext(), OrderActivity.class);
                    intent.putExtra("TOTAL", total);
                    String quantity = "";
                    for (int i = 0; i < cartList.size(); i++){
                        Cart c = cartList.get(i);
                        if(i == cartList.size()-1){
                            quantity += c.getOrderedQuantity();
                        }else{
                            quantity += c.getOrderedQuantity()+";";
                        }
                    }
                    intent.putExtra("QUANTITY", quantity);
                    getContext().startActivity(intent);
                }
            }
        });

        return view;
    }

    public void evaluateTotal() {
        total = 0;
        for(Cart c : cartList){
            total += c.getProductPrice()*c.getOrderedQuantity();
        }
        tvTotal.setText(df.format(total));
    }

    public void initData() {
        cartList = new ArrayList<>();
        String url = "https://emobiletech.000webhostapp.com/api/cart/cart.php?user_id="+ SharedPreferenceUtils.getInstance().getInt("user_id");

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int status = response.getInt("status");
                    if(status == 0){
                        String message = response.getString("message");
                        showError(message);
                        evaluateTotal();
                    }else{
                        JSONArray data = response.getJSONArray("data");
                        for(int i = 0; i < data.length(); i++){

                            JSONObject cartObj = data.getJSONObject(i);

                            Cart cart = new Cart(cartObj.getInt("cart_id"), cartObj.getInt("user_id"),
                                    cartObj.getInt("product_id"), cartObj.getString("title"),
                                    cartObj.getDouble("price"), cartObj.getString("image"), cartObj.getInt("quantity"), 1);
                            cartList.add(cart);
                        }
                        adapter.notifyDataSetChanged();
                        evaluateTotal();
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
        jor.setShouldCache(false);
        VolleyRequestQueue.getInstance(getContext()).addToRequestQueue(jor);
    }

    private void showError(String errorMessage) {
        ErrorMessageDialog.showMessage(getString(R.string.error), errorMessage, this.getContext());
    }

    public void notifyAdapter(){
        adapter.notifyDataSetChanged();
        evaluateTotal();
    }
}
