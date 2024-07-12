package online.jeweljoust.BE.api;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


import online.jeweljoust.BE.entity.AuctionRegistration;
import online.jeweljoust.BE.entity.AuctionSession;
import online.jeweljoust.BE.enums.AuctionSessionStatus;
import online.jeweljoust.BE.model.AuctionSessionDetailResponse;
import online.jeweljoust.BE.model.AuctionSessionRequest;
import online.jeweljoust.BE.respository.AuctionRegistrationRepository;
import online.jeweljoust.BE.respository.AuctionSessionRepository;
import online.jeweljoust.BE.respository.AuthenticationRepository;
import online.jeweljoust.BE.service.AuctionSessionService;
import online.jeweljoust.BE.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@SecurityRequirement(name = "api")
public class AuctionSessionAPI {
    @Autowired
    AuthenticationRepository authenticationRepository;
    AuctionSessionRepository auctionSessionRepository;
    @Autowired
    AuctionSessionService auctionSessionService;
    @Autowired
    AccountUtils accountUtils;
    @Autowired
    AuctionRegistrationRepository auctionRegistrationRepository;
    @PostMapping("/auctionSessions")
    public ResponseEntity<AuctionSession> createAuctionsSession(@RequestBody AuctionSessionRequest auctionSessionRequest) {

        AuctionSession auctionSession = auctionSessionService.addAuctionSessions(auctionSessionRequest);

        return ResponseEntity.ok(auctionSession);
    }
//    getAll
    @GetMapping("/auctionSessions")
    public ResponseEntity<List<AuctionSession>> getAllAuctionSessions() {
        List<AuctionSession> auctionSession = auctionSessionService.getAllAuctionSessions();
        return ResponseEntity.ok(auctionSession);
    }
    @GetMapping("/auctionSessions/{status}")
    public ResponseEntity<List<AuctionSession>> getAuctionSessionsbyStatus(@PathVariable AuctionSessionStatus status) {
        System.out.println(status);
        List<AuctionSession> auctionSession = auctionSessionService.getAuctionSessionsByStatus(status);
        return ResponseEntity.ok(auctionSession);
    }
    @GetMapping("/auctionSessions/detail/{id}")
    public ResponseEntity<AuctionSessionDetailResponse> getAuctionSessionByID(@PathVariable long id, @RequestParam(required = false)  long userId) {
        AuctionSessionDetailResponse auctionSession = auctionSessionService.getAuctionSessionByID(id,userId);
        return ResponseEntity.ok(auctionSession);
    }
//    update
    @PutMapping("/auctionSessions/{id}")
    public ResponseEntity<AuctionSession> updateAuctionSessions(@PathVariable Long id,@RequestBody AuctionSessionRequest auctionSessionRequest) {
        AuctionSession auctionSession =  auctionSessionService.updateAuctionSession(id, auctionSessionRequest);
        return ResponseEntity.ok(auctionSession);
    }
    @GetMapping("/auctionSessions/name/{name}")
    public ResponseEntity<List<AuctionSession>>findAuctionSessionByName(@PathVariable String name) {
        return ResponseEntity.ok(auctionSessionRepository.findByNameSession(name));
    }

    @PutMapping("/auctionSessions/stop")
    public ResponseEntity<AuctionSession> stopAuctionSessions(@PathVariable Long id) {
        AuctionSession auctionSession =  auctionSessionService.stopAuctionSession(id);
        return ResponseEntity.ok(auctionSession);
    }

    @GetMapping("/auctionSessions/3days")
    public ResponseEntity<List<AuctionSession>> getAuctionSession3days() {
        List<AuctionSession> auctionSession =  auctionSessionService.getAuctionSession3days();
        return ResponseEntity.ok(auctionSession);
    }

//    @DeleteMapping("/auctionSessions/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        auctionSessionService.deleteSession(id);
//        return ResponseEntity.noContent().build(); // Trả về HTTP 204 No Content
//    }
//  mở phiên khi đến giờ ( staff)
//   kết thúc phiên khi đến giờ (staff)
    // dừng phiên khẩn cấp để tí tiếp tucj lại

//    @GetMapping("/abc/{id}")
//    public ResponseEntity<List<AuctionRegistration>> abc(@PathVariable long id) {
//        return ResponseEntity.ok(auctionRegistrationRepository.findAuctionRegistrationByAuctionSessionId(id));
//    }


}
