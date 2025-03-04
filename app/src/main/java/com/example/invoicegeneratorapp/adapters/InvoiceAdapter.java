package com.example.invoicegeneratorapp.adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.invoicegeneratorapp.R;
import com.example.invoicegeneratorapp.ViewInvoice;
import com.example.invoicegeneratorapp.data.Invoice;
import com.example.invoicegeneratorapp.helpers.DatabaseHelper;
import com.example.invoicegeneratorapp.helpers.Utils;
import com.example.invoicegeneratorapp.services.InvoiceService;

import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {

    private List<Invoice> localDataSet;
    private Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView businessNameText, invoiceIdText, finalPriceText;
        private final Button viewBtn, deleteBtn;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            businessNameText = view.findViewById(R.id.businessNameText);
            invoiceIdText = view.findViewById(R.id.invoiceIdText);
            finalPriceText = view.findViewById(R.id.finalPriceText);

            viewBtn = view.findViewById(R.id.viewBtn);
            deleteBtn = view.findViewById(R.id.deleteBtn);
        }

        public TextView getBusinessNameText() {
            return businessNameText;
        }

        public TextView getInvoiceIdText() {
            return invoiceIdText;
        }

        public TextView getFinalPriceText() {
            return finalPriceText;
        }

        public Button getViewBtn() {
            return viewBtn;
        }

        public Button getDeleteBtn() {
            return deleteBtn;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public InvoiceAdapter(List<Invoice> dataSet, Context context) {
        localDataSet = dataSet;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_invoice, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        // viewHolder.getTextView().setText(localDataSet[position]);
        Invoice invoice = localDataSet.get(position);
        viewHolder.getBusinessNameText().setText(invoice.getBusinessName());
        viewHolder.getInvoiceIdText().setText(String.valueOf(invoice.getId()));
        viewHolder.getFinalPriceText().setText(invoice.getFinalPrice() + " PHP");
        viewHolder.getViewBtn().setOnClickListener(v -> {
            Intent intent = new Intent(context, ViewInvoice.class);
            intent.putExtra("invoiceId", invoice.getId());
            context.startActivity(intent);
        });
        viewHolder.getDeleteBtn().setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            // Add the buttons.
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    InvoiceService.delete(invoice.getId());
                    Utils.longToast("Invoice of ID '" + invoice.getId() + "' has been deleted!", context);
                    updateDataSet();
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
    }

    public void updateDataSet() {
        List<Invoice> invoices = DatabaseHelper.getInvoiceBank().getAll();
        updateDataSet(invoices);
    }

    public void updateDataSet(List<Invoice> dataSet) {
        localDataSet.clear();
        for (Invoice invoice : dataSet) {
            localDataSet.add(invoice);
        }
        notifyDataSetChanged();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
