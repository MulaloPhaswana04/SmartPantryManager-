package com.richfield.smartpantrymanager;

public class PantryItem implements java.io.Serializable {
    private int id;
    private String name;
    private int quantity;
    private String unit;
    private String expiryDate;

    public PantryItem(int id, String name, int quantity, String unit, String expiryDate) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.expiryDate = expiryDate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public String getUnit() { return unit; }
    public String getExpiryDate() { return expiryDate; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
    public void setName(String name){ this.name = name; }
    public void setQuantity(int qty){ this.quantity = qty; }
    public void setExpiryDate(String expiry){ this.expiryDate = expiry; }
}