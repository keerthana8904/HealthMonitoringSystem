package com.healthSystem.HealthMonitoring.Service;

import com.healthSystem.HealthMonitoring.models.Bill;
import com.healthSystem.HealthMonitoring.repository.BillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Page<Bill> getAllBills(int page, int size) {
        try {
            return billRepository.findAll(PageRequest.of(page, size));
        } catch (Exception e) {
            log.error("Error fetching bills", e);
            return Page.empty();
        }
    }

    public Bill getBillById(Long id) {
        try {
            return billRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error("Error fetching bill by id", e);
            return null;
        }
    }

    public Bill createBill(Bill bill) {
        try {
            if (bill == null ||
                    bill.getPatientId() == null ||
                    bill.getAmount() <= 0) {
                return null;
            }
            return billRepository.save(bill);
        } catch (Exception e) {
            log.error("Error creating bill", e);
            return null;
        }
    }

    public Bill updateBill(Long id, Bill updatedBill) {
        try {
            Bill existing = billRepository.findById(id).orElse(null);

            if (existing == null || updatedBill == null) {
                return null;
            }

            existing.setPatientId(updatedBill.getPatientId());
            existing.setAmount(updatedBill.getAmount());
            existing.setStatus(updatedBill.getStatus());

            return billRepository.save(existing);
        } catch (Exception e) {
            log.error("Error updating bill", e);
            return null;
        }
    }

    public void deleteBill(Long id) {
        try {
            billRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting bill", e);
        }
    }
}
