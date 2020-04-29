package se.kth.iv1350.posproj.controller;

import se.kth.iv1350.posproj.model.*;
import se.kth.iv1350.posproj.integration.*;
import se.kth.iv1350.posproj.dto.*;

/**
 * Responsible to call the appropriate classes and methods to carry out the functionality of the program.
 */
public class Controller {
    private Sale currentSale;
    private InventorySystem inventorySystem;
    private AccountingSystem accountingSystem;
    private RegisterMoneyHandler registerMoneyHandler;
    private Printer printer;
    private ItemRegistry itemRegistry;
    private SaleMoneyHandler saleMoneyHandler;

    /**
     * Setting up the systems needed to start the software.
     * @param inventorySystem give the ability to get a hold on the items in the store's inventory.
     * @param accountingSystem give the ability to record the process of sale.
     * @param printer to print out the evidence of the sale ({@link ReceiptDTO}).
     * @param registerMoneyHandler Simulate the register device.
     */
    public Controller(InventorySystem inventorySystem, AccountingSystem accountingSystem, Printer printer, RegisterMoneyHandler registerMoneyHandler) {
        this.inventorySystem = inventorySystem;
        this.accountingSystem = accountingSystem;
        this.registerMoneyHandler = registerMoneyHandler;
        this.printer = printer;
    }

    /**
     * Marks a start for a new sale, beside preparing the components that manages the sale.
     * @return a <code>String</code> to show that the sale has began.
     */
    public String startNewSale() {
        this.itemRegistry = new ItemRegistry();
        this.saleMoneyHandler = new SaleMoneyHandler();
        this.currentSale = new Sale();
        return "\nNew sale has started.\n";
    }

    /**
     * add the <code>product</code> to the sale. if the <code>product</code> is not existed,
     * an invalid message will be displayed otherwise the information about the added item will be displayed.
     * @param itemId is needed to be able to retrieve the desired <code>product</code> information from the database.
     * @return a <code>String</code> message whether it is added or not.
     */
    public String registerItem(int itemId) {
        ItemDTO product = this.itemRegistry.addItem(itemId, inventorySystem);
        String registerItemInfo = "";
        currentSale.updateSaleStatus(itemRegistry, saleMoneyHandler);
        registerItemInfo = "Product : " + product.getItemDescription() + ", " +
                "Cost: " + Double.toString(product.getItemPrice()) + " kr"
                + ", total price: " + Double.toString(getTotalPriceWithVat()) + " kr";
        return registerItemInfo;
    }

    /**
     * Shows the total price of the products purchased including vat.
     * @return the value of the {@link totalPrice}.
     */
    public String endSale() {
        return "Total cost: " + Double.toString(getTotalPriceWithVat());
    }

    /**
     * Marks the end of the current sale and wraps up the payment.
     * @param payment is amount paid by the customer.
     */
    public void enterPayment(double payment) {
        currentSale.endSale(payment, this.registerMoneyHandler, this.inventorySystem, this.accountingSystem, this.printer);
    }

    private double getTotalPriceWithVat() {
        double totalPrice = this.saleMoneyHandler.getTotalPriceWithVat(this.itemRegistry);
        return totalPrice;
    }
}