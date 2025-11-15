package br.com.arthdroid1.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.arthdroid1.models.Bill;
import br.com.arthdroid1.models.BillStatus;
import br.com.arthdroid1.services.BillService;


@RestController
@RequestMapping("/bills") // plural é mais comum
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        billService.registerBill(bill);
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        Bill bill = billService.findById(id);
        return ResponseEntity.ok(bill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill bill) {
        billService.updatingBill(id, bill);
        return ResponseEntity.ok(billService.findById(id));
    }

    @PatchMapping("/{id}/pay")
    public ResponseEntity<Bill> payBill(@PathVariable Long id, @RequestParam LocalDate payDate) {
        billService.payBill(id, payDate);
        return ResponseEntity.ok(billService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Bill>> listAllBills() {
        return ResponseEntity.ok(billService.listAll());
    }

    @GetMapping("/status")
    public ResponseEntity<List<Bill>> listBillsByStatus(@RequestParam BillStatus status) {
        List<Bill> bills;
        switch (status) {
            case OPEN -> bills = billService.listOpenBills();
            case OVERDUE -> bills = billService.listOverdueBills();
            case PAID -> bills = billService.listPaidBills();
            default -> bills = List.of();
        }
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/total-open")
    public ResponseEntity<Double> showTotalOpenAmount() {
        return ResponseEntity.ok(billService.getTotalOpenAmount());
    }
}

