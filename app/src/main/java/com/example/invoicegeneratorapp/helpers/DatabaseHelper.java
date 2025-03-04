package com.example.invoicegeneratorapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.invoicegeneratorapp.data.Invoice;
import com.example.invoicegeneratorapp.services.InvoiceService;

public class DatabaseHelper {
    private static final String FILE_KEY = "db";

    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;

    private static ModelBank<Invoice> invoiceBank;

    public static void initialize(Context context) {
        sharedPref = context.getSharedPreferences(FILE_KEY, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        invoiceBank = new ModelBank<>(sharedPref, editor, "invoices", Invoice.class);
    }

    public static ModelBank<Invoice> getInvoiceBank() {
        return invoiceBank;
    }

    public static void addDummyData(){
        Invoice data1 = new Invoice("JE's Eggs","somewhere far far away","092323121",
                "04/03/2025",120,1440,15,1656"Eggs");
        Invoice data2 = new Invoice("Loraine's Lying Business",
                "San Fernando Pampanga blk 2 something something street, barangay 123","0934834934",
                "02/03/2025",5,2000,15,2300, "Lying Services");
        Invoice data3 = new Invoice("Marshall's Lomi",
                "Corectional Mandaluyong City blah blah blah blah blah blah","092567521",
                "03/05/2025",120,1440,15,1656, "Cars");
        Invoice data4 = new Invoice("Sykiel's PC","London England","0156534321",
                "02/12/2025",120,1440,15,1656, "Keyboard");
        Invoice data5 = new Invoice("Buddy's Beverages",
                "A-5 RSG1 Townhomes Calbayog St Highway Hills Mandaluyong City","092323121",
                "01/24/2025",120,1440,15,1656, "Iced Tea");

        InvoiceService.add(data1);
        InvoiceService.add(data2);
        InvoiceService.add(data3);
        InvoiceService.add(data4);
        InvoiceService.add(data5);
    }
}
