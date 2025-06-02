package wms;

public class FinanceManager {
    private FinancialReport report;

    public FinanceManager() {
        this.report = new FinancialReport();
    }

    public void addRevenue(double amount) {
        report.addRevenue(amount);
    }

    public void addExpense(double amount) {
        report.addExpense(amount);
    }

    public double getTotalRevenue() {
        return report.getTotalRevenue();
    }

    public double getTotalExpenses() {
        return report.getTotalExpenses();
    }

    public double getNetIncome() {
        return report.getNetIncome();
    }

    public FinancialReport getReport() {
        return report;
    }
}
