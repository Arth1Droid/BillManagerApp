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

import br.com.arthdroid1.dtos.BillRequestDTO;
import br.com.arthdroid1.dtos.BillResponseDTO;
import br.com.arthdroid1.mapper.BillMapper;
import br.com.arthdroid1.models.Bill;
import br.com.arthdroid1.models.BillStatus;
import br.com.arthdroid1.services.BillService;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<BillResponseDTO> createBill(@RequestBody BillRequestDTO request) {
        Bill bill = BillMapper.toEntity(request, null); 
        Bill saved = billService.registerBill(bill);
        return ResponseEntity.ok(BillMapper.toResponse(saved));
    }

    // READ - by ID
    @GetMapping("/{id}")
    public ResponseEntity<BillResponseDTO> getBillById(@PathVariable Long id) {
        Bill bill = billService.findById(id);
        return ResponseEntity.ok(BillMapper.toResponse(bill));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillResponseDTO> updateBill(
            @PathVariable Long id,
            @RequestBody Bill bill) {

        billService.updatingBill(id, bill);
        Bill updated = billService.findById(id);
        return ResponseEntity.ok(BillMapper.toResponse(updated));
    }

    // PAY
    @PatchMapping("/{id}/pay")
    public ResponseEntity<BillResponseDTO> payBill(
            @PathVariable Long id,
            @RequestParam LocalDate payDate) {

        billService.payBill(id, payDate);
        Bill paid = billService.findById(id);
        return ResponseEntity.ok(BillMapper.toResponse(paid));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }

    // LIST ALL
    @GetMapping
    public ResponseEntity<List<BillResponseDTO>> listAllBills() {
        List<Bill> bills = billService.listAll();

        List<BillResponseDTO> dtos = bills.stream()
                .map(BillMapper::toResponse)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    // LIST BY STATUS
    @GetMapping("/status")
    public ResponseEntity<List<BillResponseDTO>> listBillsByStatus(
            @RequestParam BillStatus status) {

        List<Bill> bills = switch (status) {
            case OPEN -> billService.listOpenBills();
            case OVERDUE -> billService.listOverdueBills();
            case PAID -> billService.listPaidBills();
        };

        List<BillResponseDTO> dtos = bills.stream()
                .map(BillMapper::toResponse)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    // TOTAL OPEN
    @GetMapping("/total-open")
    public ResponseEntity<Double> showTotalOpenAmount() {
        return ResponseEntity.ok(billService.getTotalOpenAmount());
    }
}
