package appewtc.masterung.myrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ComfirmOrderActivity extends AppCompatActivity {

    //Explicit
    private TextView showOfficer, showDesk;
    private ListView showFoodListView;

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
        String[] foodStrings = objOrderTABLE.readAllFoodOrder();
        String[] itemStrings = objOrderTABLE.readAllItem();
        ComfirmAdapter objComfirmAdapter = new ComfirmAdapter(ComfirmOrderActivity.this, foodStrings, itemStrings);
        showFoodListView.setAdapter(objComfirmAdapter);

    }   // createListview

    public void clickOrderFood(View view) {

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
        String strDesk = getIntent().getExtras().getString("Desk");
        showDesk.setText(strDesk);
    }

    private void showOfficer() {
        String strOfficer = getIntent().getExtras().getString("Officer");
        showOfficer.setText(strOfficer);
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
