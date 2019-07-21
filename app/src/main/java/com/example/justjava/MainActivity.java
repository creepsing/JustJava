/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int orderQty = 0;
    private int unitPrice = 5;
    private boolean whippedCream = false;
    private boolean chocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void minusOne(View view){
        if(orderQty >0) orderQty--;
        displayQuantity(orderQty);

    }

    public void plusOne(View view){
        if(orderQty < 100) orderQty++;
        displayQuantity(orderQty);

    }

    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage = orderSummary(price);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.emailheader));
        emailIntent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        emailIntent.setType("plain/text");

//        emailIntent = new Intent(Intent.ACTION_VIEW);
//        emailIntent.setData(Uri.parse("geo:47.6, -122.3"));

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
            Log.v("MainActivity", "Sharing the order summary to email");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Log.d("MainActivity", "No email app installed!");
        }

//        if(emailIntent.resolveActivity(getPackageManager()) != null) {
//            startActivity(emailIntent);
//            Log.i("AA", "intent started");
//        }else
//            Log.i("AA","intent not started");
//        displayMessage(priceMessage);



    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

//    private void displayPrice(String price) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText("$"+price);
//    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(message);
//    }

    private int calculatePrice(){
        int price = 0;
        price =  orderQty* (unitPrice);
        if(whippedCream) price += 1 * orderQty;
        if(chocolate) price += 2 * orderQty;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     * @param price price of total order
     * @return String messages of order summary including customer name, order qty and total price.
     */
    private String orderSummary(int price){
        String name = ((EditText) findViewById(R.id.edittext_name)).getText().toString();
        String summary = getString(R.string.name_v,name) + "\n";
        summary += getString(R.string.whipped_cream_v, whippedCream) + "\n";
        summary += getString(R.string.chocolate_v, chocolate) + "\n";
        summary += getString(R.string.total_v, orderQty, calculatePrice());
        if(orderQty>0) summary += getString(R.string.thankyou);
        return summary;
    }

    public void onCheckboxClicked(View view){
        boolean isChecked = ((CheckBox) view).isChecked();
        if(view.getId()==R.id.checkbox_whipped_cream)
            whippedCream = isChecked;
        if(view.getId()==R.id.checkbox_chocolate)
            chocolate = isChecked;

    }
}