package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    boolean hasWhippedCream = false;

    CheckBox mCheckBoxWhippedCream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
     * @return message of the summary
     */
    private String createOrderSummary(int price, boolean hasWhippedCream) {
        String summaryMessage = "Name: Omar Ahmed";
        summaryMessage += "\nAdd Whipped cream? " + hasWhippedCream;
        summaryMessage += "\nQuantity: " + quantity;
        summaryMessage += "\nTotal: $" + price;
        summaryMessage += "\nThank you!";
        return summaryMessage;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        mCheckBoxWhippedCream = (CheckBox) findViewById(R.id.boxWhippedCream);
        hasWhippedCream = mCheckBoxWhippedCream.isChecked();
        String summaryMessage = createOrderSummary(calculatePrice(),hasWhippedCream);
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
