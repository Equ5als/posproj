package se.kth.iv1350.posproj.model;

import java.util.ArrayList;
import se.kth.iv1350.posproj.integration.InventorySystem;
import se.kth.iv1350.posproj.dto.ItemDTO;

/**
 * Responsible to hold the purchased products.
 */
public class ItemRegistry {
    private ArrayList<ItemDTO> itemList;
    private ArrayList<Integer> itemQuantity;

    /**
     * Create the item registry.
     */
    public ItemRegistry() {
        itemList = new ArrayList<ItemDTO>();
        itemQuantity = new ArrayList<Integer>();
    }

    /**
     * Add the product entered with an identifier to the sale item list.
     * @param itemId unique number related to a certain product.
     * @param inventorySystem retrieve the information about the existing products in stock.
     * @return <code>product</code> which is the item added if found otherwise it returns nothing (<code>null</code>).
     */
    public ItemDTO addItem(int itemId, InventorySystem inventorySystem) {
        ItemDTO product = inventorySystem.getItem(itemId);
        if(itemList.size() > 0) {
            boolean itemAlreadyAdded = false;
            for(int i = 0; i < itemList.size(); i++) {
                if(product.equals(this.itemList.get(i))) {
                    int newQuantity = itemQuantity.get(i);
                    this.itemQuantity.set(i, newQuantity + 1);
                    itemAlreadyAdded = true;
                    break;
                }
            }
            if(!itemAlreadyAdded) {
                this.itemList.add(product);
                this.itemQuantity.add(1);
            }
        } else {
            this.itemList.add(product);
            this.itemQuantity.add(1);
        }
        return product;
    }

    /**
     * Method to return an attribute {@link ItemList}.
     * @return a list of items that is an attribute.
     */
    public ArrayList<ItemDTO> getItemList() {
        return this.itemList;
    }

    /**
     * Method to return an attribute {@link ItemQuantity}.
     * @return a list of how many of the same item were added.
     */
    public ArrayList<Integer> getItemQuantity() {
        return this.itemQuantity;
    }

    /**
     * The show the items purchased in the sale.
     * @return string array with the products, their count and price.
     */
    public String[] itemRegistryToString() {
        String[] saleItems = new String[this.itemList.size()];
        for(int i = 0; i < saleItems.length; i++) {
            saleItems[i] = itemList.get(i).getItemDescription() + " X " + Integer.toString(itemQuantity.get(i))
                    + "\t" + itemList.get(i).getItemPrice();
        }
        return saleItems;
    }
}