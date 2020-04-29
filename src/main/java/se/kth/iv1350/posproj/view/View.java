package se.kth.iv1350.posproj.view;

import se.kth.iv1350.posproj.controller.Controller;

/**
 * Responsible to get the input from the user and pass them.
 */
public class View {
    private Controller controller;

    /**
     * Create the interface for the user to start using the software.
     * @param controller call the appropriate methods and pass the input.
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Hard-code simulating a user usage in real life.
     */
    public void getSaleStarted() {
        System.out.println(controller.startNewSale());
        System.out.println(controller.registerItem(5));
        System.out.println(controller.registerItem(7));
        System.out.println(controller.registerItem(1));
        System.out.println(controller.registerItem(3));
        System.out.println(controller.registerItem(1));
        System.out.println(controller.registerItem(5));
        System.out.println(controller.registerItem(8));
        System.out.println(controller.registerItem(1));
        System.out.println(controller.registerItem(10));
        System.out.println("\n" + controller.endSale() + "\n");
        controller.enterPayment(500);
    }
}