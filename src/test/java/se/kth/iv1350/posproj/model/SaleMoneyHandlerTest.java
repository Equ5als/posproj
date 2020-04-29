package se.kth.iv1350.posproj.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import se.kth.iv1350.posproj.integration.InventorySystem;

public class SaleMoneyHandlerTest {
    BigDecimal valueFormat;
    InventorySystem inventorySystem;
    ItemRegistry itemRegistry;
    SaleMoneyHandler saleMoneyHandler;
    RegisterMoneyHandler registerMoneyHandler;
    double expectedTotalPrice;
    double expectedVatAmount;

    @BeforeEach
    public void setUp() {
        inventorySystem = new InventorySystem();
        saleMoneyHandler = new SaleMoneyHandler();
        registerMoneyHandler = new RegisterMoneyHandler();
        itemRegistry = new ItemRegistry();
        itemRegistry.addItem(5, inventorySystem);
        itemRegistry.addItem(1, inventorySystem);
        itemRegistry.addItem(8, inventorySystem);
        itemRegistry.addItem(10, inventorySystem);
        itemRegistry.addItem(10, inventorySystem);
        itemRegistry.addItem(10, inventorySystem);
        itemRegistry.addItem(1, inventorySystem);
        itemRegistry.addItem(4, inventorySystem);
        itemRegistry.addItem(4, inventorySystem);
        expectedTotalPrice = 377.04;
        expectedVatAmount = 38.04;
    }

    @AfterEach
    public void tearDown() {
        inventorySystem = null;
        itemRegistry = null;
    }

    @Test
    void totalPriceWithVatCalculation() {
        double actualTotalPrice = saleMoneyHandler.getTotalPriceWithVat(itemRegistry);
        assertEquals(expectedTotalPrice, actualTotalPrice, "The Total price calculation were done incorrectly.");
    }

    @Test
    void changeCalculation() {
        saleMoneyHandler.getTotalPriceWithVat(itemRegistry);
        double payment = 500;
        double expectedChange = payment - expectedTotalPrice;
        double actualChange = saleMoneyHandler.getChange(payment, registerMoneyHandler);
        assertEquals(formatDouble(expectedChange), actualChange, "The vat amount calculation were done incorrectly.");
    }

    @Test
    void vatAmountCalculation() {
        double actualVatAmount = saleMoneyHandler.getVatAmount(itemRegistry);
        assertEquals(expectedVatAmount, actualVatAmount, "The Total price calculation were done incorrectly.");
    }

    private double formatDouble(double value) {
        valueFormat = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
        return valueFormat.doubleValue();
    }
}
