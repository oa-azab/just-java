package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    boolean hasWhippedCream = false;
    boolean hasChocolate = false;
    String mClientName = "";

    EditText mEditTextName;
    CheckBox mCheckBoxWhippedCream, mCheckBoxChocolate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find UI
        mCheckBoxWhippedCream = (CheckBox) findViewById(R.id.boxWhippedCream);
        mCheckBoxChocolate = (CheckBox) findViewById(R.id.boxChocolate);
        mEditTextName = (EditText) findViewById(R.id.edittxt_client_name);
    }

    /**
     * Calculate the total price of an order.
     *
     * @return total price
     */
    public int calculatePrice() {
        return quantity * 5;
    }

    /**
     * Create summary message of the order
     *
     * @param price total price of the order
     * @param hasWhippedCream whipped cream add on
     * @param hasChocolate Chocolate add on
     * @param ClientName name of the client
     * @return message of the summary
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String ClientName) {
        String summaryMessage = "Name: "+ClientName;
        summaryMessage += "\nAdd Whipped cream? " + hasWhippedCream;
        summaryMessage += "\nAdd Chocolate? " + hasChocolate;
        summaryMessage += "\nQuantity: " + quantity;
        summaryMessage += "\nTotal: $" + price;
        summaryMessage += "\nThank you!";
        return summaryMessage;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        hasWhippedCream = mCheckBoxWhippedCream.isChecked();
        hasChocolate = mCheckBoxChocolate.isChecked();
        mClientName = mEditTextName.getText().toString();
        String summaryMessage = createOrderSummary(calculatePrice(),hasWhippedCream,hasChocolate,mClientName);
        displayMessage(summaryMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method increment the number of coffees.
     */
    public void increment(View view) {
        display(++quantity);
    }

    /**
     * This method decrement the number of coffees.
     */
    public void decrement(View view) {
        display(--quantity);
    }
}
