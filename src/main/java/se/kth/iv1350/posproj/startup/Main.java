package se.kth.iv1350.posproj.startup;

import se.kth.iv1350.posproj.controller.*;
import se.kth.iv1350.posproj.view.*;
import se.kth.iv1350.posproj.integration.*;
import se.kth.iv1350.posproj.model.*;

/**
 * Responsible to start up the whole software.
 */
public class Main {
    public static void main(String[] args) {
        InventorySystem inventorySystem = new InventorySystem();
        AccountingSystem accountingSystem = new AccountingSystem();
        RegisterMoneyHandler registerMoneyHandler = new RegisterMoneyHandler();
        Printer printer = new Printer();
        Controller controller = new Controller(inventorySystem, accountingSystem, printer, registerMoneyHandler);
        View view = new View(controller);
        view.getSaleStarted();
    }
}