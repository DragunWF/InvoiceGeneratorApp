package com.example.invoicegeneratorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.invoicegeneratorapp.data.Invoice;
import com.example.invoicegeneratorapp.helpers.DatabaseHelper;
import com.example.invoicegeneratorapp.helpers.Utils;
import com.example.invoicegeneratorapp.services.InvoiceService;

public class ViewInvoice extends AppCompatActivity {

    private TextView businessNameTxt, addressTxt, contactTxt, itemNameTxt, quantityTxt,
            subtotalTxt, taxrateTxt, totalTxt, dateTxt;
    private Button editBtn, deleteBtn, backBtn;

    private int viewedInvoiceId;

    @Override
    protected void onResume() {
        super.onResume();
        DatabaseHelper.initialize(this);
        setData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_invoice);

        try {
            DatabaseHelper.initialize(this);

            businessNameTxt = findViewById(R.id.nameTxt);
            addressTxt = findViewById(R.id.addressTxt);
            contactTxt = findViewById(R.id.numTxt);
            itemNameTxt = findViewById(R.id.itemNameTxt);
            quantityTxt = findViewById(R.id.quantityTxt);
            subtotalTxt = findViewById(R.id.subtotalTxt);
            taxrateTxt = findViewById(R.id.taxRateTxt);
            totalTxt = findViewById(R.id.totalTxt);
            dateTxt = findViewById(R.id.dateTxt);

            editBtn = findViewById(R.id.editBtn);
            deleteBtn = findViewById(R.id.deleteBtn);
            backBtn = findViewById(R.id.backBtn);

            viewedInvoiceId = getIntent().getIntExtra("invoiceId", -1);

            setButtons();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setData() {
        Invoice invoice = DatabaseHelper.getInvoiceBank().get(viewedInvoiceId);
        businessNameTxt.setText(invoice.getBusinessName());
    }

    public void setButtons(){
        editBtn.setOnClickListener(v ->{
            Intent intent = new Intent(ViewInvoice.this, InvoiceEditor.class);
            intent.putExtra("invoiceId", viewedInvoiceId);
            startActivity(intent);
        });
        deleteBtn.setOnClickListener(v ->{
            AlertDialog.Builder builder = new AlertDialog.Builder(ViewInvoice.this);
            // Add the buttons.
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    InvoiceService.delete(viewedInvoiceId);
                    Utils.longToast("Invoice of ID '" + viewedInvoiceId + "' has been deleted!", ViewInvoice.this);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancels the dialog.
                }
            });
            // Set other dialog properties.
            builder.setMessage("Are you sure you want to delete this invoice?");

            // Create the AlertDialog.
            AlertDialog dialog = builder.create();
            dialog.show();
        });
        backBtn.setOnClickListener(v ->{
            finish();
        });
    }
}