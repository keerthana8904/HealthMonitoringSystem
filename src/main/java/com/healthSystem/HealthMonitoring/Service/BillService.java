package com.healthSystem.HealthMonitoring.Service;

import com.healthSystem.HealthMonitoring.models.Bill;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {

    // temporary in-memory storage (until repository is created)
    private final List<Bill> bills = new ArrayList<>();

    // GET all bills
    public List<Bill> getAllBills() {
        try {
            System.out.println("Service: Fetching all bills");
            return bills;
        } catch (Exception e) {
            System.out.println("Error while fetching bills: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // GET bill by id
    public Bill getBillById(Long id) {
        try {
            System.out.println("Service: Fetching bill with id " + id);

            for (Bill bill : bills) {
                if (bill.getId().equals(id)) {
                    return bill;
                }
            }
            return null; // bill not found
        } catch (Exception e) {
            System.out.println("Error while fetching bill: " + e.getMessage());
            return null;
        }
    }

    // CREATE bill
    public Bill createBill(Bill bill) {
        try {
            System.out.println("Service: Creating bill");
            bills.add(bill);
            return bill;
        } catch (Exception e) {
            System.out.println("Error while creating bill: " + e.getMessage());
            return null;
        }
    }

    // UPDATE bill
    public Bill updateBill(Long id, Bill updatedBill) {
        try {
            System.out.println("Service: Updating bill with id " + id);

            for (int i = 0; i < bills.size(); i++) {
                if (bills.get(i).getId().equals(id)) {
                    bills.set(i, updatedBill);
                    return updatedBill;
                }
            }
            return null; // bill not found
        } catch (Exception e) {
            System.out.println("Error while updating bill: " + e.getMessage());
            return null;
        }
    }

    // DELETE bill
    public void deleteBill(Long id) {
        try {
            System.out.println("Service: Deleting bill with id " + id);
            bills.removeIf(bill -> bill.getId().equals(id));
        } catch (Exception e) {
            System.out.println("Error while deleting bill: " + e.getMessage());
        }
    }
}
