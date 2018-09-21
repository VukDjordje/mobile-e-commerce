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
import rs.fon.emobill.models.Comment;


public class CommentRecyclerViewAdapter extends RecyclerView.Adapter<CommentRecyclerViewAdapter.ViewHolder>{
    private List<Comment> comments;
    private Context context;

    public CommentRecyclerViewAdapter(List<Comment> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    private Context getContext(){
        return context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Comment comment = comments.get(i);
        Resources res = viewHolder.itemView.getContext().getResources();
        viewHolder.tvFullName.setText(res.getString(R.string.full_name_placeholder, comment.getFullName()));
        viewHolder.tvRating.setText(res.getString(R.string.rating_placeholder, comment.getRating()+""));
        viewHolder.tvComment.setText(res.getString(R.string.comment_placeholder, comment.getComment()));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvFullName, tvRating, tvComment;

        ViewHolder(View v){
            super(v);
            tvFullName = v.findViewById(R.id.tvFullName);
            tvRating = v.findViewById(R.id.tvRating);
            tvComment = v.findViewById(R.id.tvComment);
        }
    }
}

