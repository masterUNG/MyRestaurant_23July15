package appewtc.masterung.myrestaurant;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class ComfirmOrderActivity extends AppCompatActivity {

    //Explicit
    private TextView showOfficer, showDesk;
    private ListView showFoodListView;
    private String officerString, deskString, foodString[], itemString[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirm_order);

        //Bind Widget
        bindWidget();

        //Show Officer
        showOfficer();

        //Show Desk
        showDesk();

        //Create Listview
        createListview();

    }   // onCreate

    private void createListview() {

        OrderTABLE objOrderTABLE = new OrderTABLE(this);
        foodString = objOrderTABLE.readAllFoodOrder();
        itemString = objOrderTABLE.readAllItem();
        ComfirmAdapter objComfirmAdapter = new ComfirmAdapter(ComfirmOrderActivity.this, foodString, itemString);
        showFoodListView.setAdapter(objComfirmAdapter);

    }   // createListview

    public void clickOrderFood(View view) {

        //Setup Policy
        StrictMode.ThreadPolicy objPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(objPolicy);

        for (int i = 0; i < foodString.length; i++) {

            try {

                ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
                objNameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
                objNameValuePairs.add(new BasicNameValuePair("Officer", officerString));
                objNameValuePairs.add(new BasicNameValuePair("Desk", deskString));
                objNameValuePairs.add(new BasicNameValuePair("Food", foodString[i]));
                objNameValuePairs.add(new BasicNameValuePair("Item", itemString[i]));

                HttpClient objHttpClient = new DefaultHttpClient();
                HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/23jul/add_data_restaurant.php");
                objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
                objHttpClient.execute(objHttpPost);

            } catch (Exception e) {
                Toast.makeText(ComfirmOrderActivity.this, "Error Cannot Upload Order", Toast.LENGTH_SHORT).show();
            }

        }   // for

        Toast.makeText(ComfirmOrderActivity.this, "Upload Finish", Toast.LENGTH_SHORT).show();

        //Delete All Order Table
        deleteAllOrder();

        finish();


    }   // clickOrderFood

    private void deleteAllOrder() {
        SQLiteDatabase objDatabase = openOrCreateDatabase("Restaurant.db", MODE_PRIVATE, null);
        objDatabase.delete("orderTABLE", null, null);
    }

    public void clickAddMore(View view) {
        finish();
    }

    private void bindWidget() {
        showOfficer = (TextView) findViewById(R.id.txtShowOfficerOrder);
        showDesk = (TextView) findViewById(R.id.txtShowDesk);
        showFoodListView = (ListView) findViewById(R.id.listView2);
    }


    private void showDesk() {
        deskString = getIntent().getExtras().getString("Desk");
        showDesk.setText(deskString);
    }

    private void showOfficer() {
        officerString = getIntent().getExtras().getString("Officer");
        showOfficer.setText(officerString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comfirm_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
