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
}
