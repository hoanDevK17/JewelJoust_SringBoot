package online.jeweljoust.BE.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.jeweljoust.BE.entity.Account;
import online.jeweljoust.BE.entity.Transaction;
import online.jeweljoust.BE.model.WithdrawRequest;
import online.jeweljoust.BE.service.TransactionService;
import online.jeweljoust.BE.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "api")
@RequestMapping("api")
public class TransactionAPI {
    @Autowired
    TransactionService transactionService;

    @GetMapping("/transactions")
    @PreAuthorize("hasAnyAuthority('MEMBER')")
    public ResponseEntity getAll() {
        List<Transaction> transactions = transactionService.getAll();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/transactions/withdraw")
    @PreAuthorize("hasAnyAuthority('STAFF')")
    public ResponseEntity getAllWithDrawRequest() {
        List<Transaction> transactions = transactionService.getAllWithDrawRequest();
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("/transactions/withdraw/confirm")
    @PreAuthorize("hasAnyAuthority('STAFF')")
    public ResponseEntity getAllWithDrawRequest(@RequestBody long id) {
        Transaction transactions = transactionService.confirmWithDraw(id);
        return ResponseEntity.ok(transactions);
    }

     @PostMapping("/transactions/withdraw")
     // @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'STAFF')")
     public ResponseEntity withdrawMoney(@RequestBody WithdrawRequest
     withdrawRequest) {
     return ResponseEntity.ok(transactionService.withdraw(withdrawRequest));
     }

    @GetMapping("/transactions/paging")
    public ResponseEntity<Page<Transaction>>getTransactionPaging(Pageable pageable) {
        return ResponseEntity.ok(transactionService.getAllTransactions(pageable));
    }
}
