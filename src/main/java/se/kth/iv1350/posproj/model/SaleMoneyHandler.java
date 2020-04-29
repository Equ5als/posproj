package se.kth.iv1350.posproj.model;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;
import se.kth.iv1350.posproj.dto.ItemDTO;

/**
 * Takes responsibility on the money handled in the sale from total price, vat amount and payment.
 */
public class SaleMoneyHandler {
    private BigDecimal valueFormat;
    private double totalPrice;
    private double vatAmount;
    private double change;

    /**
     * Create the money handler.
     */
    public SaleMoneyHandler() {
        this.totalPrice = 0;
        this.vatAmount = 0;
        this.change = 0;
    }

    /**
     * Calculates the total price for the sale with the items added.
     * @param itemRegistry holds the products that are in the sale.
     * @return the {@link totalPrice} but in string format.
     */
    public double getTotalPriceWithVat(ItemRegistry itemRegistry) {
        ArrayList<ItemDTO> itemList = itemRegistry.getItemList();
        ArrayList<Integer> itemQuantity = itemRegistry.getItemQuantity();
        this.totalPrice = 0;
        for(int i = 0; i < itemList.size();i ++) {
            this.totalPrice += ((itemList.get(i).getItemPrice() * (1 + itemList.get(i).getVatRate())) * itemQuantity.get(i));
        }
        return formatDouble(this.totalPrice);
    }

    /**
     * Calculate how much that should be given to the customer .
     * @param payment money paid.
     * @param registerMoneyHandler simulated register device.
     * @return {@link change} amount in string format.
     */
    public double getChange(double payment, RegisterMoneyHandler registerMoneyHandler) {
        this.change = payment - this.totalPrice;
        registerMoneyHandler.updateRegisterBalance(this.totalPrice);
        return formatDouble(this.change);
    }

    /**
     * Calculate the vat cost of the sale.
     * @param itemRegistry holds the products that are in the sale.
     * @return {@link vatAmount} in string format.
     */
    public double getVatAmount(ItemRegistry itemRegistry) {
        ArrayList<ItemDTO> itemList = itemRegistry.getItemList();
        ArrayList<Integer> itemQuantity = itemRegistry.getItemQuantity();
        for(int i = 0; i < itemList.size(); i++) {
            this.vatAmount += ((itemList.get(i).getItemPrice() * itemList.get(i).getVatRate() * itemQuantity.get(i)));
        }
        return formatDouble(this.vatAmount);
    }

    private double formatDouble(double value) {
        valueFormat = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
        return valueFormat.doubleValue();
    }
}