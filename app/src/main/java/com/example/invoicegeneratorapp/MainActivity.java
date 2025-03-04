package com.example.invoicegeneratorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.SearchView;

import com.example.invoicegeneratorapp.adapters.InvoiceAdapter;
import com.example.invoicegeneratorapp.data.Invoice;
import com.example.invoicegeneratorapp.helpers.DatabaseHelper;
import com.example.invoicegeneratorapp.helpers.Utils;
import com.example.invoicegeneratorapp.services.InvoiceService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button addButton;
    private SearchView searchBar;

    private RecyclerView invoiceRecycler;
    private InvoiceAdapter invoiceAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onResume() {
        super.onResume();
        invoiceAdapter.updateDataSet();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            DatabaseHelper.initialize(this);
            DatabaseHelper.addDummyData();

            DatabaseHelper.getInvoiceBank().log();
            System.out.println(DatabaseHelper.getInvoiceBank().getAll().size());

            addButton = findViewById(R.id.addBtn);
            searchBar = findViewById(R.id.searchBar);

            invoiceRecycler = findViewById(R.id.invoiceRecycler);

            setClickListeners();
            setSearch();
            setRecycler();
        }
        catch (Exception e){
            e.printStackTrace();
            Utils.longToast(e.getMessage(), this);
        }
    }

    private void setClickListeners() {
        addButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InvoiceEditor.class));
        });
    }

    private void setSearch() {
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                update(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                update(s);
                return false;
            }

            public void update(String query) {
                List<Invoice> invoices = DatabaseHelper.getInvoiceBank().getAll();
                List<Invoice> results = new ArrayList<>();

                query = query.toLowerCase();
                for (Invoice invoice : invoices) {
                    String businessName = invoice.getBusinessName().toLowerCase();
                    if (businessName.contains(query)) {
                        results.add(invoice);
                    }
                }

                invoiceAdapter.updateDataSet(invoices);
            }
        });
    }

    private void setRecycler() {
        invoiceRecycler.setHasFixedSize(false);

        invoiceAdapter = new InvoiceAdapter(DatabaseHelper.getInvoiceBank().getAll(), this);
        invoiceRecycler.setAdapter(invoiceAdapter);

        layoutManager = new LinearLayoutManager(this);
        invoiceRecycler.setLayoutManager(layoutManager);
    }
}