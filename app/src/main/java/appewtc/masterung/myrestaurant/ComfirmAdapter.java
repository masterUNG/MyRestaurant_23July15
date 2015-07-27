package appewtc.masterung.myrestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by masterUNG on 7/27/15 AD.
 */
public class ComfirmAdapter extends BaseAdapter{

    //Explicit
    private Context objContext;
    private String[] foodStrings, itemStrings;

    public ComfirmAdapter(Context objContext, String[] foodStrings, String[] itemStrings) {
        this.objContext = objContext;
        this.foodStrings = foodStrings;
        this.itemStrings = itemStrings;
    }   // Constructor

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
        View view1 = objLayoutInflater.inflate(R.layout.list_order_confirm, viewGroup, false);

        //Show Food
        TextView foodTextView = (TextView) view1.findViewById(R.id.txtFoodOrder);
        foodTextView.setText(foodStrings[i]);

        //Show Item
        TextView itemTextView = (TextView) view1.findViewById(R.id.txtItemOrder);
        itemTextView.setText(itemStrings[i]);

        return view1;
    }   // getView
}   // Main Class
