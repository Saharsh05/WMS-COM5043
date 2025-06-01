package wms;

// This class keeps track of total revenue and expenses and calculates net income.
public class FinancialReport {
    private double totalRevenue;
    private double totalExpenses;

    // Constructor initialise revenue and expenses to zero
    public FinancialReport() {
        this.totalExpenses = 0.0;
        this.totalRevenue = 0.0;
    }

    // Adds a positive revenue amount to the total revenue
    public void addRevenue(double amount) {
        if (amount > 0) {
            totalRevenue += amount;
            System.out.println("Revenue updated: £" + totalRevenue);
        } else {
            System.out.println("The revenue should be postive");
        }

    }

    // Adds a positive expense amount to the total expenses
    public void addExpense(double amount) {
        if (amount > 0) {
            totalExpenses += amount;
            System.out.println("Expenses updated: £" + totalExpenses);
        } else {
            System.out.println("The amount should be postive");

        }
    }

    // Returns the current total revenue
    public double getTotalRevenue() {
        return totalRevenue;
    }

    // Returns the current total expenses
    public double getTotalExpenses() {
        return totalExpenses;
    }

    // Calculates and returns the net income (revenue - expenses)
    public double getNetIncome() {
        return totalRevenue - totalExpenses;
    }

    // Resets the financial report values to zero
    public void resetReport() {
        totalExpenses = 0;
        totalRevenue = 0;
    }
}
