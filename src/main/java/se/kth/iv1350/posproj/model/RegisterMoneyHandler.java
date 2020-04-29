package se.kth.iv1350.posproj.model;

/**
 * Simulate the register device.
 */
public class RegisterMoneyHandler {
    private double registerBalance;

    /**
     * Create the register money handler.
     */
    public RegisterMoneyHandler() {
        this.registerBalance = 0;
    }

    /**
     * Update the amount of the money in the register.
     * @param newBalance new amount added into the register.
     */
    public void updateRegisterBalance(double newBalance) {
        this.registerBalance += newBalance;
    }
}