package se.kth.iv1350.posproj.integration;

import se.kth.iv1350.posproj.dto.ItemDTO;
import se.kth.iv1350.posproj.dto.SaleLogDTO;

/**
 * Responsible for keeping all the information about the existing products in stock.
 */
public class InventorySystem {
    private ItemDTO[] products;

    public InventorySystem() {
        /**
         * Create dummy products to serve as a database.
         */
        this.products = new ItemDTO[]
                {
                        new ItemDTO("Milk", 10, 0.06),
                        new ItemDTO("Yogurt", 16, 0.06),
                        new ItemDTO("Eggs", 39, 0.25),
                        new ItemDTO("Cheese", 120, 0.12),
                        new ItemDTO("Bacon", 30, 0.12),
                        new ItemDTO("Meat", 300, 0.25),
                        new ItemDTO("Chips", 27, 0.12),
                        new ItemDTO("Bread", 25, 0.12),
                        new ItemDTO("Butter", 40, 0.25),
                        new ItemDTO("Noodles", 8, 0.06)
                };
    }

    /**
     * Retrieve information about a certain item from the stock.
     * @param itemId unique number related to a certain product.
     * @return <code>ItemDTO</code> which carries out the information of the product.
     */
    public ItemDTO getItem(int itemId) {
        ItemDTO product = products[itemId - 1];
        return product;
    }

    /**
     * Get the new information about the sale to update the stock.
     * @param newSaleLog the log for the ended sale.
     */
    public void updateInventorySystem(SaleLogDTO newSaleLog) {

    }
}