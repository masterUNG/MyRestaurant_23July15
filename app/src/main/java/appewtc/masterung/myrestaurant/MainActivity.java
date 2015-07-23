package appewtc.masterung.myrestaurant;

import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private UserTABLE objUserTABLE;
    private FoodTABLE objFoodTABLE;
    private OrderTABLE objOrderTABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Request Database
        requestDatabase();

        //Test Add Value
        //testAddValue();

        //Delete All Data
        deleteAllData();

        //Synchronize JSON to SQLite
        synJSONtoSQLite();

    }   // onCreate

    private void synJSONtoSQLite() {

        //0. Change Policy
        StrictMode.ThreadPolicy objPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(objPolicy);

        int intTimes = 0;
        while (intTimes <= 1) {

            InputStream objInputStream = null;
            String strJSON = "";
            String strUserURL = "http://swiftcodingthai.com/23jul/get_data_master.php";
            String strFoodURL = "http://swiftcodingthai.com/23jul/get_data_food.php";
            HttpPost objHttpPost;

            //1. Create InputStream
            try {

                HttpClient objClient = new DefaultHttpClient();

                if (intTimes != 1) {
                    objHttpPost = new HttpPost(strUserURL);
                } else {
                    objHttpPost = new HttpPost(strFoodURL);
                }

                HttpResponse objHttpResponse = objClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();

            } catch (Exception e) {
                Log.d("Rest", "InputStream ==> " + e.toString());
            }


            //2. Create strJSON
            try {

                BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;

                while ((strLine = objBufferedReader.readLine()) != null ) {

                    objStringBuilder.append(strLine);

                }   // while

                objInputStream.close();
                strJSON = objStringBuilder.toString();



            } catch (Exception e) {
                Log.d("Rest", "strJSON ==> " + e.toString());
            }


            //3. Update SQlite
            try {

                final JSONArray objJsonArray = new JSONArray(strJSON);

                for (int i = 0; i < objJsonArray.length(); i++) {

                    JSONObject objJSONObject = objJsonArray.getJSONObject(i);

                    if (intTimes != 1) {

                        //Update userTABLE
                        String strUser = objJSONObject.getString("User");
                        String strPass = objJSONObject.getString("Password");
                        String strName = objJSONObject.getString("Name");
                        objUserTABLE.addNewUser(strUser, strPass, strName);

                    } else {

                        //Update foodTABLE
                        String strFood = objJSONObject.getString("Food");
                        String strPrice = objJSONObject.getString("Price");
                        objFoodTABLE.addValueFood(strFood, strPrice);

                    }


                }   // for


            } catch (Exception e) {
                Log.d("Rest", "Update ==> " + e.toString());
            }




            //Increase intTimes
            intTimes += 1;

        }   // while




    }   // synJSONtoSQLite

    private void deleteAllData() {

        SQLiteDatabase objDatabase = openOrCreateDatabase("Restaurant.db", MODE_PRIVATE, null);
        objDatabase.delete("userTABLE", null, null);
        objDatabase.delete("foodTABLE", null, null);
        objDatabase.delete("orderTABLE", null, null);
    }

    private void testAddValue() {

        objUserTABLE.addNewUser("testUser", "testPass", "ทดสอบ นะคะ");
        objFoodTABLE.addValueFood("ข้าวไข่เจียว", "30");
        objOrderTABLE.addNewOrder("testOfficer", "testDesk", "testFood", "4");

    }

    private void requestDatabase() {
        objUserTABLE = new UserTABLE(this);
        objFoodTABLE = new FoodTABLE(this);
        objOrderTABLE = new OrderTABLE(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}   // Main Class
