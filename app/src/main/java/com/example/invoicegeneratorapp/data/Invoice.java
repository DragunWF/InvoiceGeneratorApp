package com.example.invoicegeneratorapp.data;

public class Invoice extends Model {
    private String businessName, address, contact, date, itemName;
    private int quantity;
    private double subTotal, taxRate, finalPrice;

    public Invoice(String businessName, String address, String contact, String date, int quantity, double subTotal, double taxRate, double finalPrice, String itemName) {
        this.businessName = businessName;
        this.address = address;
        this.contact = contact;
        this.date = date;
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.taxRate = taxRate;
        this.finalPrice = finalPrice;
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "businessName='" + businessName + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", date='" + date + '\'' +
                ", quantity=" + quantity +
                ", subTotal=" + subTotal +
                ", taxRate=" + taxRate +
                ", finalPrice=" + finalPrice +
                ", id=" + id +
                '}';
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
