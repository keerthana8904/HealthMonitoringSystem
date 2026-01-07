package com.healthSystem.HealthMonitoring.controllers;

import com.healthSystem.HealthMonitoring.Service.BillService;
import com.healthSystem.HealthMonitoring.models.Bill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {

    private static final Logger logger =
            LoggerFactory.getLogger(BillController.class);

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    // GET all bills
    @GetMapping
    public ResponseEntity<Page<Bill>> getAllBills(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        try {
            logger.info("Fetching all bills");
            return ResponseEntity.ok(billService.getAllBills(page,size));
        } catch (Exception e) {
            logger.error("Error fetching bills", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET bill by id
    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        try {
            logger.info("Fetching bill with id {}", id);
            Bill bill = billService.getBillById(id);

            if (bill == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(bill);
        } catch (Exception e) {
            logger.error("Error fetching bill", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // CREATE bill
    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        try {
            logger.info("Creating bill");
            Bill createdBill = billService.createBill(bill);

            if (createdBill == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBill);
        } catch (Exception e) {
            logger.error("Error creating bill", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // UPDATE bill
    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(
            @PathVariable Long id,
            @RequestBody Bill bill) {
        try {
            logger.info("Updating bill with id {}", id);
            Bill updatedBill = billService.updateBill(id, bill);

            if (updatedBill == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedBill);
        } catch (Exception e) {
            logger.error("Error updating bill", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE bill
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        try {
            logger.info("Deleting bill with id {}", id);
            billService.deleteBill(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error deleting bill", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
