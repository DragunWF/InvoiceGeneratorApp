package com.example.invoicegeneratorapp.services;

import com.example.invoicegeneratorapp.data.Invoice;
import com.example.invoicegeneratorapp.data.Model;
import com.example.invoicegeneratorapp.helpers.DatabaseHelper;
import com.example.invoicegeneratorapp.helpers.ModelBank;

public class InvoiceService {
    public static void add(Invoice invoice) {
        ModelBank<Invoice> bank = DatabaseHelper.getInvoiceBank();
        bank.add(invoice);
    }

    public static void delete(int id) {
        ModelBank<Invoice> bank = DatabaseHelper.getInvoiceBank();
        bank.delete(id);
    }

    public static void edit(Invoice invoice) {
        ModelBank<Invoice> bank = DatabaseHelper.getInvoiceBank();
        bank.update(invoice);
    }
}
