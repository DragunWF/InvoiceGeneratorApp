package com.example.invoicegeneratorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.invoicegeneratorapp.data.Invoice;
import com.example.invoicegeneratorapp.helpers.DatabaseHelper;
import com.example.invoicegeneratorapp.helpers.Utils;
import com.example.invoicegeneratorapp.services.InvoiceService;

public class InvoiceEditor extends AppCompatActivity {
    private Button saveBtn, backBtn;
    private EditText businessNameText, addressText, contactText, quantityText,
                     subtotalText, taxRateText, finalPriceText, dateText, itemNameText;

    private boolean isEditForm;
    private int viewedInvoiceId; // -1 indicates that no invoice is viewed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_editor);

        try {
            saveBtn = findViewById(R.id.saveBtn);
            backBtn = findViewById(R.id.backBtn);

            businessNameText = findViewById(R.id.businessNameText);
            addressText = findViewById(R.id.addressText);
            contactText = findViewById(R.id.contactText);
            quantityText = findViewById(R.id.quantityText);
            subtotalText = findViewById(R.id.subtotalText);
            taxRateText = findViewById(R.id.taxRateText);
            finalPriceText = findViewById(R.id.taxRateText);
            dateText = findViewById(R.id.dateText);
            itemNameText = findViewById(R.id.itemNameText);

            viewedInvoiceId = getIntent().getIntExtra("invoiceId", -1);
            isEditForm = viewedInvoiceId != -1;

            setClickListeners();
        } catch (Exception err) {
            err.printStackTrace();
            Utils.longToast(err.getMessage(), this);
        }
    }

    private void setClickListeners() {
        saveBtn.setOnClickListener(v -> {
            String businessName = Utils.getText(businessNameText);
            String address = Utils.getText(addressText);
            String contact = Utils.getText(contactText);
            String quantityStr = Utils.getText(quantityText);
            String subtotalStr = Utils.getText(subtotalText);
            String taxRateStr = Utils.getText(taxRateText);
            String finalPriceStr = Utils.getText(finalPriceText);
            String date = Utils.getText(dateText);
            String itemName = Utils.getText(itemNameText);

            String[] inputFieldValues = {
                    businessName, address, contact, quantityStr,
                    subtotalStr, taxRateStr, finalPriceStr, date, itemName
            };
            for (String value : inputFieldValues) {
                if (value.isEmpty()) {
                    Utils.longToast("All fields are required!", InvoiceEditor.this);
                    return;
                }
            }

            int quantity = Integer.parseInt(quantityStr);
            double taxRate = Double.parseDouble(taxRateStr);
            double subtotal = Double.parseDouble(subtotalStr);
            double finalPrice = Double.parseDouble(finalPriceStr);

            if (isEditForm) {
                Invoice invoice= DatabaseHelper.getInvoiceBank().get(viewedInvoiceId);
                invoice.setBusinessName(businessName);
                invoice.setAddress(address);
                invoice.setContact(contact);
                invoice.setQuantity(quantity);
                invoice.setSubTotal(subtotal);
                invoice.setTaxRate(taxRate);
                invoice.setFinalPrice(finalPrice);
                invoice.setDate(date);
                invoice.setItemName(itemName);
                InvoiceService.edit(invoice);
                Utils.longToast("Invoice has been successfully edited!", InvoiceEditor.this);
                finish();
            } else {
                InvoiceService.add(
                        new Invoice(businessName, address, contact,
                                date, quantity, subtotal, taxRate, finalPrice, itemName));
                Utils.longToast("Invoice for " + businessName + " has been added!", InvoiceEditor.this);
                finish();
            }
        });
        backBtn.setOnClickListener(v -> {
            finish();
        });

        dateText.setOnClickListener(v -> {

        });
    }
}