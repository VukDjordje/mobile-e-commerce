package rs.fon.emobill.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rs.fon.emobill.R;
import rs.fon.emobill.home.fragments.OrdersHistoryFragment;
import rs.fon.emobill.models.Cart;
import rs.fon.emobill.models.Orders;
import rs.fon.emobill.utility.ErrorMessageDialog;
import rs.fon.emobill.utility.InputFilterMinMax;
import rs.fon.emobill.utility.SharedPreferenceUtils;
import rs.fon.emobill.utility.VolleyRequestQueue;

public class OrdersRecyclerViewAdapter extends RecyclerView.Adapter<OrdersRecyclerViewAdapter.ViewHolder>{
    private List<Orders> ordersList;
    private Context context;
    private OrdersHistoryFragment ordersFragment;

    public OrdersRecyclerViewAdapter(List<Orders> ordersList, Context context, OrdersHistoryFragment ordersFragment) {
        this.ordersList = ordersList;
        this.context = context;
        this.ordersFragment = ordersFragment;
    }

    private Context getContext(){
        return context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Orders order = ordersList.get(i);
        final Resources res = viewHolder.itemView.getContext().getResources();

        viewHolder.tvTitle.setText(order.getTitle());
        viewHolder.tvInformations.setText(order.toString());
        viewHolder.tvQuantity.setText(order.getQuantity()+"");

        Picasso.with(getContext())
                .load(order.getImage())
                .into(viewHolder.ivImage);

        viewHolder.ivRemoveFromOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItemFromOrders(order, res);
            }
        });
    }

    private void removeItemFromOrders(final Orders order, final Resources res) {
        try {
            String url = "https://emobiletech.000webhostapp.com/api/orders/remove_from_orders.php";

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("order_id", order.getOrderID());

            JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        int status = response.getInt("status");
                        if(status == 0){
                            String message = response.getString("message");
                            showError(message, res);
                        }else{
                            String message = response.getString("message");
                            showSuccess(message);
                            ordersList.remove(order);
                            ordersFragment.notifyAdapter();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    showError(res.getString(R.string.general_error), res);
                }
            });

            VolleyRequestQueue.getInstance(this.getContext()).addToRequestQueue(jor);
        }catch (JSONException ex){
            ex.printStackTrace();
        }
    }

    private void showSuccess(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setCancelable(true);
        builder.setTitle(R.string.success);
        builder.setMessage(message);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void showError(String errorMessage, final Resources res) {
        ErrorMessageDialog.showMessage(res.getString(R.string.error), errorMessage, this.getContext());
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivImage, ivRemoveFromOrders;
        TextView tvTitle, tvInformations, tvQuantity;

        ViewHolder(View v){
            super(v);
            ivImage = (ImageView) v.findViewById(R.id.ivProductImage);
            ivRemoveFromOrders = (ImageView) v.findViewById(R.id.ivRemoveOrdersHistory);
            tvTitle = (TextView) v.findViewById(R.id.tvProductTitle);
            tvQuantity = (TextView) v.findViewById(R.id.tvProductQuantity);
            tvInformations = (TextView) v.findViewById(R.id.tvOrderInformations);
        }
    }
}
