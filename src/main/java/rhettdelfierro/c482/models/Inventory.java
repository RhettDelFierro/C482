package rhettdelfierro.c482.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Inventory data store. This will be used to store all parts and products.
 * FUTURE ENHANCEMENT: Maybe have it so we use overloaded methods for addProduct/addPart as addItem. Same for update.
 */
public class Inventory {
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * Adds part to inventory data store.
     *
     * @param part the part we want to add
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /**
     * Adds product to inventory data store.
     *
     * @param product the product we want to add
     */
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    /**
     * Searches for part by id.
     *
     * @param partId part id
     * @return part if exists
     */
    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /**
     * Search for product by id.
     *
     * @param productId product id
     * @return product if exists
     */
    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     * Search for part by matching name.
     *
     * @param partName the part name to search for
     * @return part if exists
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().contains(partName)) {
                partsFound.add(part);
            }
        }
        return partsFound;
    }

    /**
     * Searches for product by name.
     *
     * @param productName the product name to search for
     * @return product if exists
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productsFound = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().contains(productName)) {
                productsFound.add(product);
            }
        }
        return productsFound;
    }

    /**
     * Updates part in inventory data store.
     *
     * @param index the index of the part in the observable list to update
     * @param selectedPart the new part to replace it with
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * Updates product in inventory data store.
     *
     * @param index the index of the product in the observable list to update
     * @param newProduct the new product to replace it with
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * Deletes part from inventory data store.
     *
     * @param selectedPart the part to delete
     * @return boolean if part deleted is successful, false if part is not deleted
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * Deletes product from inventory data store.
     *
     * @param selectedProduct the product to delete
     * @return boolean if product deleted is successful, false if product is not deleted
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * @return current list of allParts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * @return current list of allProducts
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
