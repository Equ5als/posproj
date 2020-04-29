package se.kth.iv1350.posproj.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.posproj.dto.ItemDTO;
import se.kth.iv1350.posproj.integration.InventorySystem;

public class ItemRegistryTest {
    private ItemRegistry itemRegistry;
    private InventorySystem inventorySystem;

    @BeforeEach
    public void setUp(){
        itemRegistry = new ItemRegistry();
        inventorySystem = new InventorySystem();
    }

    @AfterEach
    public void tearDown(){
        itemRegistry = null;
        inventorySystem = null;
    }

    @Test
    void addItemToList() {
        ArrayList<ItemDTO> desiredItemList = new ArrayList<ItemDTO>();
        desiredItemList.add(new ItemDTO("Noodles", 8, 0.06));
        desiredItemList.add(new ItemDTO("Eggs", 39, 0.25));
        desiredItemList.add(new ItemDTO("Butter", 40, 0.25));
        itemRegistry.addItem(10, inventorySystem);
        itemRegistry.addItem(3, inventorySystem);
        itemRegistry.addItem(9, inventorySystem);
        ArrayList<ItemDTO> actualItemList = itemRegistry.getItemList();
        assertEquals(desiredItemList, actualItemList, "The Lists did not match.");
    }

    @Test
    void checkItemCount() {
        itemRegistry.addItem(10, inventorySystem);
        itemRegistry.addItem(10, inventorySystem);
        itemRegistry.addItem(5, inventorySystem);
        itemRegistry.addItem(5, inventorySystem);
        itemRegistry.addItem(5, inventorySystem);
        itemRegistry.addItem(7, inventorySystem);
        ArrayList<Integer> expectedItemCount = new ArrayList<Integer>();
        expectedItemCount.add(2);
        expectedItemCount.add(3);
        expectedItemCount.add(1);
        ArrayList<Integer> actualItemCount = itemRegistry.getItemQuantity();
        assertEquals(expectedItemCount, actualItemCount, "The item count does not match.");
    }

    @Test
    void showItemList() {
        String[] expectedPrintOutList = new String[] {
                "Bacon X 3\t30.0",
                "Milk X 2\t10.0",
                "Eggs X 1\t39.0"
        };
        itemRegistry.addItem(5, inventorySystem);
        itemRegistry.addItem(1, inventorySystem);
        itemRegistry.addItem(3, inventorySystem);
        itemRegistry.addItem(5, inventorySystem);
        itemRegistry.addItem(5, inventorySystem);
        itemRegistry.addItem(1, inventorySystem);
        String[] actualPrintOutList = itemRegistry.itemRegistryToString();
        boolean result = false;
        for(int i = 0; i < 3; i++) {
            if(!expectedPrintOutList[i].equals(actualPrintOutList[i])) {
                result = false;
                break;
            }
            result = true;
        }
        assertTrue(result, "The two lists print out were different.");
    }
}
