package se.kth.iv1350.posproj.dto;

/**
 * Responsible to hold the information of a product and transfer it through out the software.
 */
public final class ItemDTO {
    private String itemDescription;
    private double itemPrice;
    private double vatRate;

    /**
     * create an <code>itemDTO</code> with the specified information passed
     * @param itemDescription some description of the product as a string.
     * @param itemPrice how much the product costs.
     * @param vatRate the vat rate related to that product.
     */
    public ItemDTO(String itemDescription, double itemPrice, double vatRate) {
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.vatRate = vatRate;
    }

    /**
     * Method to return an attribute {@link itemDescription}.
     * @return a String with the object attribute.
     */
    public String getItemDescription() {
        return this.itemDescription;
    }

    /**
     * Method to return an attribute {@link itemPrice}.
     * @return a double with the object attribute.
     */
    public double getItemPrice() {
        return this.itemPrice;
    }

    /**
     * Method to return an attribute {@link vatRate}.
     * @return a double with the object attribute.
     */
    public double getVatRate() {
        return this.vatRate;
    }

    /**
     * Make it possible to compare two items together by comparing their attribute.
     * @param an object type that can be anything but in out case it would be <code>itemDTO</code>.
     * @return boolean whether the item is the same or not.
     */
    @Override
    public boolean equals(Object obj) {
        if(this.getClass() == obj.getClass()) {
            ItemDTO compareItem = (ItemDTO)obj;
            if(this.itemDescription.equals(compareItem.getItemDescription()) &&
                    this.itemPrice == compareItem.getItemPrice() && this.vatRate == compareItem.getVatRate()){
                return true;
            }
        }
        return false;
    }
}