package com.healthSystem.HealthMonitoring.controllers;

import com.healthSystem.HealthMonitoring.Service.BillService;
import com.healthSystem.HealthMonitoring.models.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {

    // Service dependency
    private final BillService billService;

    // Constructor Injection (BEST PRACTICE)
    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    // GET all generated bills
    @GetMapping
    public List<Bill> getBillDetails() {
        System.out.println("Controller: Fetching all bill details");
        return billService.getAllBills();
    }

    // GET specific bill by id
    @GetMapping("/{id}")
    public Bill getSpecificBill(@PathVariable Long id) {
        System.out.println("Controller: Fetching bill with id " + id);
        return billService.getBillById(id);
    }

    // POST - generate bill
    @PostMapping
    public Bill generateBill(@RequestBody Bill bill) {
        System.out.println("Controller: Generating bill");
        return billService.createBill(bill);
    }

    // PUT - update bill
    @PutMapping("/{id}")
    public Bill updateBill(@PathVariable Long id, @RequestBody Bill bill) {
        System.out.println("Controller: Updating bill with id " + id);
        return billService.updateBill(id, bill);
    }

    // DELETE bill
    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id) {
        System.out.println("Controller: Deleting bill with id " + id);
        billService.deleteBill(id);
    }
}
