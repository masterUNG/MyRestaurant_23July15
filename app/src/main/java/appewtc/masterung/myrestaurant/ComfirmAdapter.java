package appewtc.masterung.myrestaurant;

import android.content.Context;

/**
 * Created by masterUNG on 7/27/15 AD.
 */
public class ComfirmAdapter {

    //Explicit
    private Context objContext;
    private String[] foodStrings, itemStrings;

    public ComfirmAdapter(Context objContext, String[] foodStrings, String[] itemStrings) {
        this.objContext = objContext;
        this.foodStrings = foodStrings;
        this.itemStrings = itemStrings;
    }   // Constructor
}   // Main Class
