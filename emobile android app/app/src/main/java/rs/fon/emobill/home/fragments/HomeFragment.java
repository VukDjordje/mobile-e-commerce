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
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rs.fon.emobill.R;
import rs.fon.emobill.adapters.ProductRecyclerViewAdapter;
import rs.fon.emobill.models.Banner;
import rs.fon.emobill.models.Product;
import rs.fon.emobill.utility.ErrorMessageDialog;
import rs.fon.emobill.utility.VolleyRequestQueue;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private List<Product> products;
    private RecyclerView recyclerView;
    private String TAG = "HomeFragment";
    ProductRecyclerViewAdapter adapter;
    private SliderLayout slider;
    private List<Banner> bannerList;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.home_title);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvFeaturedProducts);
        slider = (SliderLayout) view.findViewById(R.id.slHeader);
        bannerList = new ArrayList<>();

        initData();
        adapter = new ProductRecyclerViewAdapter(this.getContext(), products);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);
        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setAdapter(adapter);



        return view;
    }

    private void initData() {
        products = new ArrayList<>();
        String url = "https://emobiletech.000webhostapp.com/api/product/featured_products.php";

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

                            JSONObject productObj = data.getJSONObject(i);

                            Product product = new Product(productObj.getInt("product_id"), productObj.getString("title"),
                                    productObj.getDouble("price"), productObj.getInt("brand_id"),
                                    productObj.getInt("category_id"), productObj.getString("image"),
                                    productObj.getString("description"), productObj.getInt("featured"),
                                    productObj.getInt("quantity"));
                            products.add(product);
                        }
                        adapter.notifyDataSetChanged();
                        JSONArray banner = response.getJSONArray("banner");
                        for(int i = 0; i < banner.length(); i++){
                            JSONObject bannerObj = banner.getJSONObject(i);

                            Banner b = new Banner(bannerObj.getInt("banner_id"), bannerObj.getString("image"));
                            bannerList.add(b);
                        }
                        setupSlider();

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

        VolleyRequestQueue.getInstance(getContext()).addToRequestQueue(jor);
    }

    private void showError(String errorMessage) {
        ErrorMessageDialog.showMessage(getString(R.string.error), errorMessage, this.getContext());
    }

    private void setupSlider() {



        for (Banner b : bannerList){
            final TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView
                    .image(b.getImage())
                    .setScaleType(BaseSliderView.ScaleType.Fit);


            slider.addSlider(textSliderView);
        }

        slider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setDuration(5000);
    }

    @Override
    public void onStop() {
        super.onStop();
        slider.stopAutoCycle();
    }
}


