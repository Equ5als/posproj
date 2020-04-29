package se.kth.iv1350.posproj.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Responsible to carry the information for the receipt.
 */
public final class ReceiptDTO {
    private Date dateAndTime;
    private String storeName;
    private String storeAddress;
    private String[] saleItems;
    private double totalPrice;
    private double totalVat;
    private double payment;
    private double change;

    /**
     * Create the Receipt for the sale.
     * @param dateAndTime date and time of the sale.
     * @param storeName string that holds the store's name.
     * @param storeAddress string that holds the store's address.
     * @param saleItems collection of the sold items.
     * @param totalPrice total cost of the entire sale.
     * @param totalVat vat amount of the sale.
     * @param payment money paid.
     * @param change how many cash that would be given back.
     */
    public ReceiptDTO(Date dateAndTime, String storeName, String storeAddress,
                      String[] saleItems, double totalPrice, double totalVat, double payment, double change) {
        this.dateAndTime = dateAndTime;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.saleItems = saleItems;
        this.totalPrice = totalPrice;
        this.payment = payment;
        this.change = change;
        this.totalVat = totalVat;
    }

    /**
     * Transform the object into a string so that could be displayed to simulate a real life receipt paper.
     * @return receipt string with all the information demanded.
     */
    public String toString() {
        DateFormat dateAndTimeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String receiptInfo = "\n" + dateAndTimeFormat.format(dateAndTime) + "\n";
        receiptInfo += this.storeName + "\n";
        receiptInfo += this.storeAddress + "\n\n";
        receiptInfo += "Items purchased: \n";
        for(int i = 0; i < saleItems.length; i++) {
            receiptInfo += saleItems[i] + " kr\n";
        }
        receiptInfo += "Total cost: " + Double.toString(this.totalPrice) + " kr\n";
        receiptInfo += "Total VAT: " + Double.toString(this.totalVat) + "kr \n";
        receiptInfo += "Amount paid: " + Double.toString(this.payment) + " kr\n";
        receiptInfo += "Change: " + Double.toString(this.change) + " kr\n";
        return receiptInfo;
    }
}