package appewtc.masterung.myrestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by masterUNG on 7/24/15 AD.
 */
public class MyAdapter extends BaseAdapter{

    //Explicit
    private Context objContext;
    private String[] foodStrings, priceStrings, sourceStrings;
    private int[] imageInts;

    public MyAdapter(Context objContext, String[] foodStrings, String[] priceStrings,  String[] sourceStrings, int[] imageInts) {
        this.objContext = objContext;
        this.foodStrings = foodStrings;
        this.priceStrings = priceStrings;
        this.sourceStrings = sourceStrings;
        this.imageInts = imageInts;
    }

    @Override
    public int getCount() {
        return foodStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = objLayoutInflater.inflate(R.layout.my_listview, viewGroup, false);

        //Show Food
        TextView foodTextView = (TextView) view1.findViewById(R.id.txtShowFood);
        foodTextView.setText(foodStrings[i]);

        //Show Price
        TextView priceTextView = (TextView) view1.findViewById(R.id.txtShowPrice);
        priceTextView.setText(priceStrings[i]);

        //Show Image
        ImageView foodImageView = (ImageView) view1.findViewById(R.id.imvFood);
        //foodImageView.setBackgroundResource(imageInts[i]);
        Picasso.with(objContext).load(sourceStrings[i]).resize(250, 250).into(foodImageView);


        return view1;
    }
}   // Main Class
