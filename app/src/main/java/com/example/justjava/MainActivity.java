/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int orderQty = 0;
    private int unitPrice = 5;

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
        displayQuantity(++orderQty);

    }

    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage = orderSummary(price);
        displayMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(String price) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("$"+price);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    private int calculatePrice(){
        return orderQty* unitPrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     * @param price price of total order
     * @return String messages of order summary including customer name, order qty and total price.
     */
    private String orderSummary(int price){
        String summary = "name:성윤식\nQuantity:" + orderQty + "\nTotal: $" + orderQty * unitPrice;
        if(orderQty>0) summary += "\nThank you!";
        return summary;
    }
}