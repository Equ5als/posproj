package se.kth.iv1350.posproj.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import se.kth.iv1350.posproj.dto.ItemDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public class InventorySystemTest {
    private InventorySystem inventorySystem;

    @BeforeEach
    public void setUp(){
        inventorySystem = new InventorySystem();
    }

    @AfterEach
    public void tearDown() {
        inventorySystem = null;
    }

    @Test
    void retrieveItem() {
        ItemDTO desiredItem = new ItemDTO("Bread", 25, 0.12);
        int desiredItemId = 8;
        ItemDTO actualItem = inventorySystem.getItem(desiredItemId);
        assertEquals(desiredItem, actualItem, "Inventory product was not the same as the desired one.");
    }
}
