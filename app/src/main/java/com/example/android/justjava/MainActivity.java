package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
     * @param hasWhippedCream whether it has whippedcream topping or not
     * @param hasChocolate whether it has chocolate topping or not
     * @return total price
     */
    public int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int price = 5;
        if (hasWhippedCream) {
            price++;
        }
        if (hasChocolate) {
            price = price + 2;
        }
        return quantity * price;
    }

    /**
     * Create summary message of the order
     *
     * @param price           total price of the order
     * @param hasWhippedCream whipped cream add on
     * @param hasChocolate    Chocolate add on
     * @param ClientName      name of the client
     * @return message of the summary
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String ClientName) {
        String summaryMessage = getString(R.string.order_summary_name, ClientName);
        summaryMessage += getString(R.string.order_summary_whipped_cream, hasWhippedCream);
        summaryMessage += getString(R.string.order_summary_chocolate, hasChocolate);
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
        String summaryMessage = createOrderSummary(calculatePrice(hasWhippedCream,hasChocolate), hasWhippedCream, hasChocolate, mClientName);
        //displayMessage(summaryMessage);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"JustJava order for "+mEditTextName.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT,summaryMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method increment the number of coffees.
     */
    public void increment(View view) {
        if( quantity == 100){
            //Show error message as a Toast
            Toast.makeText(this, "Wow that's a lot coffee !", Toast.LENGTH_SHORT).show();
            //Exit method
            return;
        }
        display(++quantity);
    }

    /**
     * This method decrement the number of coffees.
     */
    public void decrement(View view) {
        if( quantity == 1){
            //Show error message as a Toast
            Toast.makeText(this, "you can't have less than one coffee !", Toast.LENGTH_SHORT).show();
            //Exit method
            return;
        }
        display(--quantity);
    }
}
