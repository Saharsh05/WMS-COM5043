package wms;

public class FinancialReport {
    private double totalRevenue;
    private double totalExpenses;

    public FinancialReport() {
        this.totalExpenses = 0;
        this.totalRevenue = 0;
    }

    public void addRevenue(double amount) {
        if (amount > 0) {
            totalRevenue += amount;
            System.out.println("Revenue updated: £" + totalRevenue);
        } else {
            System.out.println("The revenue should be postive");
        }

    }

    public void addExpense(double amount) {
        if (amount > 0) {
            totalExpenses += amount;
            System.out.println("Expenses updated: £" + totalExpenses);
        } else {
            System.out.println("The amount should be postive");

        }
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getNetIncome() {
        return totalRevenue - totalExpenses;
    }

    public void resetReport() {
        totalExpenses = 0;
        totalRevenue = 0;
    }
}
