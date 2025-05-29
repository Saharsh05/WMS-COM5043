package wms;

import org.junit.jupiter.api.Test;

import wms.FinancialReport;

import static org.junit.jupiter.api.Assertions.*;


public class FinancialReportTest {
    @Test
    public void addRevenueIncreaesRevenue(){
        FinancialReport fReport = new FinancialReport();

        fReport.addRevenue(10.00);
        assertEquals(10, fReport.getTotalRevenue());
    }

    @Test
    public void addExpenseChangesTotalExpense(){
        FinancialReport fReport = new FinancialReport();

        fReport.addExpense(10.00);
        assertEquals(10, fReport.getTotalExpenses());
    }

    @Test
    public void addRevenueNegativeAmount(){
        FinancialReport fReport = new FinancialReport();
        fReport.addRevenue(-10);

        assertEquals(0.0, fReport.getTotalRevenue());
    }

    @Test
    public void addExpenseNegativeAmount(){
        FinancialReport fReport = new FinancialReport();
        fReport.addExpense(-10);

        assertEquals(0.0, fReport.getTotalExpenses());
    }

    @Test
    public void getNetIncomeCorrect(){
        FinancialReport fReport = new FinancialReport();

        fReport.addExpense(35);
        fReport.addRevenue(70);

        assertEquals(35, fReport.getNetIncome());
    }
    @Test
    public void resetReportWorks(){
        FinancialReport fReport = new FinancialReport();

        fReport.addExpense(100);
        fReport.addRevenue(20);

        fReport.resetReport();

        assertEquals(0, fReport.getTotalExpenses());
        assertEquals(0, fReport.getTotalRevenue());


    }
}
