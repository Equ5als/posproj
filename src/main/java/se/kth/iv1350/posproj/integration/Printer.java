package se.kth.iv1350.posproj.integration;

import se.kth.iv1350.posproj.dto.ReceiptDTO;

public class Printer {
    /**
     * Create the simulated printer.
     */
    public Printer() {
    }

    /**
     * Print out the receipt as a simulation of the real life paper.
     * @param receipt carrying the information about the sale.
     */
    public void printReceipt(ReceiptDTO receipt) {
        System.out.println("*********\n*Receipt*\n*********");
        System.out.println(receipt.toString());
    }
}