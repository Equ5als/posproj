package se.kth.iv1350.posproj.model;

import java.util.ArrayList;
import java.util.Date;
import se.kth.iv1350.posproj.dto.*;
import se.kth.iv1350.posproj.integration.*;

/**
 * Represents the process of sale that is happening at that moment.
 */
public class Sale {
    private Date dateAndTime;
    private ItemRegistry itemRegistry;
    private SaleMoneyHandler saleMoneyHandler;

    /**
     * Start the sale by creating an instance with date and time stamp.
     */
    public Sale() {
        this.dateAndTime = new Date();
    }

    /**
     * Keep the sale information up to date with the shop.
     * @param itemRegistry keeps a hold on the added items.
     * @param saleMoneyHandler the money handle place.
     */
    public void updateSaleStatus(ItemRegistry itemRegistry, SaleMoneyHandler saleMoneyHandler) {
        this.itemRegistry = itemRegistry;
        this.saleMoneyHandler = saleMoneyHandler;
    }

    /**
     * Marks the end of a sale and sends out the demanded information. {@link SaleLogDTO} and {@link ReceiptDTO}.
     * @param payment money paid.
     * @param registerMoneyHandler simulation of the register.
     * @param inventorySystem all the information of the products in the stock.
     * @param accountingSystem records the sale.
     * @param printer simulates the printer device.
     */
    public void endSale(double payment, RegisterMoneyHandler registerMoneyHandler
            , InventorySystem inventorySystem, AccountingSystem accountingSystem, Printer printer) {
        SaleLogDTO newSaleLog = prepareSaleLog(payment, registerMoneyHandler);
        inventorySystem.updateInventorySystem(newSaleLog);
        accountingSystem.updateAccountingSystem(newSaleLog);
        ReceiptDTO saleReceipt = prepareReceipt(payment, registerMoneyHandler);
        printer.printReceipt(saleReceipt);
    }

    private SaleLogDTO prepareSaleLog(double payment, RegisterMoneyHandler registerMoneyHandler) {
        double change = saleMoneyHandler.getChange(payment, registerMoneyHandler);
        ArrayList<ItemDTO> saleItems = itemRegistry.getItemList();
        ArrayList<Integer> soldItemsQuantity = itemRegistry.getItemQuantity();
        double totalPrice = saleMoneyHandler.getTotalPriceWithVat(this.itemRegistry);
        double totalVat = saleMoneyHandler.getVatAmount(this.itemRegistry);
        SaleLogDTO saleLog = new SaleLogDTO(this.dateAndTime, saleItems, soldItemsQuantity, totalPrice, totalVat, payment, change);
        return saleLog;
    }

    private ReceiptDTO prepareReceipt(double payment, RegisterMoneyHandler registerMoneyHandler) {
        double change = saleMoneyHandler.getChange(payment, registerMoneyHandler);
        String[] saleItems = itemRegistry.itemRegistryToString();
        double totalPrice = saleMoneyHandler.getTotalPriceWithVat(this.itemRegistry);
        double totalVat = saleMoneyHandler.getVatAmount(this.itemRegistry);
        ReceiptDTO receipt = new ReceiptDTO(this.dateAndTime, "Heavenly Nourishment", "Heaven Area 1, 000001\nNirvana, Paradise",
                saleItems, totalPrice, totalVat, payment, change);
        return receipt;
    }
}