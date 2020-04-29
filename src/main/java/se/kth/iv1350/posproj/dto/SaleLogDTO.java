package se.kth.iv1350.posproj.dto;

import java.util.ArrayList;
import java.util.Date;

/**
 * This would be a log that would include all the available information about the sale.
 */
public final class SaleLogDTO {
    private Date dateAndTime;
    private ArrayList<ItemDTO> soldItems;
    private ArrayList<Integer> soldItemsQuantity;
    private double totalPrice;
    private double vatAmount;
    private double payment;
    private double change;

    /**
     * Create the sale log holding all the information.
     * @param dateAndTime date and time of the sale.
     * @param soldItems collection of the sold items.
     * @param soldItemsQuantity count of each sold item.
     * @param totalPrice total cost of the entire sale.
     * @param vatAmount vat amount of the sale.
     * @param payment money paid.
     * @param change how many cash that would be given back.
     */
    public SaleLogDTO(Date dateAndTime, ArrayList<ItemDTO> soldItems, ArrayList<Integer> soldItemsQuantity,
                      double totalPrice ,double vatAmount, double payment, double change) {
        this.dateAndTime = dateAndTime;
        this.soldItems = soldItems;
        this.soldItemsQuantity = soldItemsQuantity;
        this.totalPrice = totalPrice;
        this.vatAmount = vatAmount;
        this.payment = payment;
        this.change = change;
    }
}