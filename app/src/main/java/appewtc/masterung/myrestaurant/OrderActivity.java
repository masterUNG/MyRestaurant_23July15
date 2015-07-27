package appewtc.masterung.myrestaurant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    //Explicit
    private TextView showOfficerTextView;
    private Spinner deskSpinner;
    private ListView foodListView;
    private String officerString, deskString, foodString, itemString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //Bind Widget
        bindWidget();

        //Show Officer
        showOfficer();

        //Create Spinner
        createSpinner();

        //Create ListView
        createListView();

    }   // onCreate

    public void clickConfirm(View view) {
        Intent objIntent = new Intent(OrderActivity.this, ComfirmOrderActivity.class);
        objIntent.putExtra("Officer", officerString);
        objIntent.putExtra("Desk", deskString);
        startActivity(objIntent);
    }

    private void createListView() {

        FoodTABLE objFoodTABLE = new FoodTABLE(this);
        final String[] strFood = objFoodTABLE.readAllFood();
        String[] strPrice = objFoodTABLE.readAllPrice();
        int[] intDrawable = {R.drawable.food1, R.drawable.food2, R.drawable.food3,
                R.drawable.food4, R.drawable.food5, R.drawable.food6, R.drawable.food7,
                R.drawable.food8, R.drawable.food9, R.drawable.food10, R.drawable.food11,
                R.drawable.food12, R.drawable.food13, R.drawable.food14, R.drawable.food15,
                R.drawable.food16, R.drawable.food17, R.drawable.food18, R.drawable.food19,
                R.drawable.food20, R.drawable.food21, R.drawable.food22, R.drawable.food23,
                R.drawable.food24, R.drawable.food25, R.drawable.food26, R.drawable.food27,
                R.drawable.food28, R.drawable.food29, R.drawable.food30, R.drawable.food31,
                R.drawable.food32, R.drawable.food33, R.drawable.food34, R.drawable.food35,
                R.drawable.food36, R.drawable.food37, R.drawable.food38, R.drawable.food39,
                R.drawable.food40, R.drawable.food41, R.drawable.food42, R.drawable.food43,
                R.drawable.food44, R.drawable.food45, R.drawable.food46, R.drawable.food47,
                R.drawable.food48, R.drawable.food49, R.drawable.food50};
        MyAdapter objMyAdapter = new MyAdapter(OrderActivity.this, strFood, strPrice, intDrawable);
        foodListView.setAdapter(objMyAdapter);

        //Active when Click on ListView
        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                foodString = strFood[i];

                //Choose Item
                chooseItem();

            }
        });

    }   // createListView

    private void chooseItem() {

        final CharSequence[] objSequence = {"1 set", "2 set", "3 set", "4 set", "5 set"};
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setTitle(foodString);
        objBuilder.setSingleChoiceItems(objSequence, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                switch (i) {
                    case 0:
                        itemString = "1";
                        break;
                    case 1:
                        itemString = "2";
                        break;
                    case 2:
                        itemString = "3";
                        break;
                    case 3:
                        itemString = "4";
                        break;
                    case 4:
                        itemString = "5";
                        break;
                }   // switch

                //Upload to SQLite
                uploadToSQLite();
                dialogInterface.dismiss();

            }   //event
        });
        objBuilder.show();

    }   //chooseItem

    private void uploadToSQLite() {

        OrderTABLE objOrderTABLE = new OrderTABLE(this);
        objOrderTABLE.addNewOrder(officerString, deskString, foodString, itemString);
        Log.d("Rest", "Add ==> " + foodString + " to my SQLite");

    } //uploadToSQLite

    private void createSpinner() {

        final String[] strShowSpinner = {"โต้ะ 1", "โต้ะ 2", "โต้ะ 3", "โต้ะ 4", "โต้ะ 5", "โต้ะ 6",
                "โต้ะ 7", "โต้ะ 8", "โต้ะ 9", "โต้ะ 10", "โต้ะ 11", "โต้ะ 12", "โต้ะ 13", "โต้ะ 14",
                "โต้ะ 15"};
        ArrayAdapter<String> objAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strShowSpinner);
        deskSpinner.setAdapter(objAdapter);

        deskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                deskString = strShowSpinner[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                deskString = strShowSpinner[0];
            }
        });

    }   // createSpinner

    private void showOfficer() {

        officerString = getIntent().getExtras().getString("Officer");
        showOfficerTextView.setText(officerString);

    }

    private void bindWidget() {
        showOfficerTextView = (TextView) findViewById(R.id.txtShowofficer);
        deskSpinner = (Spinner) findViewById(R.id.spinner);
        foodListView = (ListView) findViewById(R.id.listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order, menu);
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
