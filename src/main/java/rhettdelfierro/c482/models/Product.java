package rhettdelfierro.c482.models;

import javafx.collections.ObservableList;

public class Product {
    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Adds part to associatedParts list.
     *
     * @param part the part to add
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Deletes part from associatedParts list.
     *
     * @param selectedAssociatedPart the part to delete
     * @return boolean if part deleted is successful, false if part is not deleted
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * @return current list of associatedParts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    // getters and setters

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the partId to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the partName
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the partName to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the partPrice
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the partPrice to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the partStock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the partStock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the partMin
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the partMin to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the partMax
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the partMax to set
     */
    public void setMax(int max) {
        this.max = max;
    }
}
