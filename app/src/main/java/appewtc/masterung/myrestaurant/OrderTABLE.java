package appewtc.masterung.myrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 7/23/15 AD.
 */
public class OrderTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeDatabase, readDatabase;
    public static final String ORDER_TABLE = "orderTABLE";
    public static final String COLUMN_ID_ORDER = "_id";
    public static final String COLUMN_OFFICER = "Officer";
    public static final String COLUMN_DESK = "Desk";
    public static final String COLUMN_FOOD = "Food";
    public static final String COLUMN_ITME = "Item";

    public OrderTABLE(Context context) {

        //Connected Table
        objMyOpenHelper = new MyOpenHelper(context);
        writeDatabase = objMyOpenHelper.getWritableDatabase();
        readDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    public String[] readAllItem() {

        String[] strItem = null;
        Cursor objCursor = readDatabase.query(ORDER_TABLE,
                new String[]{COLUMN_ID_ORDER, COLUMN_ITME},
                null, null, null, null, null);

        if (objCursor != null) {
            strItem = new String[objCursor.getCount()];
            objCursor.moveToFirst();
            for (int i = 0; i < objCursor.getCount(); i++) {
                strItem[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_ITME));
                objCursor.moveToNext();
            }
        }

        return strItem;
    }

    public String[] readAllFoodOrder() {

        String[] strFood = null;
        Cursor objCursor = readDatabase.query(ORDER_TABLE,
                new String[]{COLUMN_ID_ORDER, COLUMN_FOOD},
                null, null, null, null, null);

        if (objCursor != null) {
            strFood = new String[objCursor.getCount()];
            objCursor.moveToFirst();
            for (int i = 0; i < objCursor.getCount(); i++) {
                strFood[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_FOOD));
                objCursor.moveToNext();
            }
        }   // if

        return strFood;
    }

    public long addNewOrder(String strOfficer, String strDesk, String strFood, String strItem) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_OFFICER, strOfficer);
        objContentValues.put(COLUMN_DESK, strDesk);
        objContentValues.put(COLUMN_FOOD, strFood);
        objContentValues.put(COLUMN_ITME, strItem);

        return writeDatabase.insert(ORDER_TABLE, null, objContentValues);
    }

}   // Main Class
