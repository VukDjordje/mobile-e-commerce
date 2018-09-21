package rs.fon.emobill.home.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rs.fon.emobill.R;
import rs.fon.emobill.adapters.OrdersRecyclerViewAdapter;
import rs.fon.emobill.models.Orders;
import rs.fon.emobill.utility.ErrorMessageDialog;
import rs.fon.emobill.utility.SharedPreferenceUtils;
import rs.fon.emobill.utility.VolleyRequestQueue;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersHistoryFragment extends Fragment {
    private List<Orders> ordersList;
    private RecyclerView rvOrders;
    private OrdersRecyclerViewAdapter adapter;

    public OrdersHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.order_history_title);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders_history, container, false);

        rvOrders = (RecyclerView) view.findViewById(R.id.rvOrdersHistory);

        initData();

        adapter = new OrdersRecyclerViewAdapter(ordersList, this.getContext(), this);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());

        rvOrders.setHasFixedSize(true);
        rvOrders.setLayoutManager(llm);
        rvOrders.setAdapter(adapter);

        return view;
    }

    public void initData() {
        ordersList = new ArrayList<>();
        String url = "https://emobiletech.000webhostapp.com/api/orders/orders.php?user_id="+ SharedPreferenceUtils.getInstance().getInt("user_id");

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int status = response.getInt("status");
                    if(status == 0){
                        String message = response.getString("message");
                        showError(message);
                    }else{
                        JSONArray data = response.getJSONArray("data");
                        for(int i = 0; i < data.length(); i++){

                            JSONObject orderObj = data.getJSONObject(i);

                            Orders order = new Orders(orderObj.getInt("order_id"), orderObj.getInt("cart_id"),
                                    orderObj.getString("title"), orderObj.getString("image"), orderObj.getString("city"),
                                    orderObj.getInt("zip_code"), orderObj.getString("address"), orderObj.getString("phone"),
                                    orderObj.getInt("quantity"));
                            ordersList.add(order);
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
    }

}
