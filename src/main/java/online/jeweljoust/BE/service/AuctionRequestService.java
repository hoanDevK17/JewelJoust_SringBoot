package online.jeweljoust.BE.service;

import online.jeweljoust.BE.entity.AuctionRequest;
import online.jeweljoust.BE.enums.AuctionRequestStatus;
import online.jeweljoust.BE.model.AuctionRequestReponse;
import online.jeweljoust.BE.respository.AuctionRepository;
import online.jeweljoust.BE.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class AuctionRequestService {


    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    AccountUtils accountUtils;

    public AuctionRequest requestSale(AuctionRequestReponse auctionRequestReponse){
        AuctionRequest auctionRequest = new AuctionRequest();
            LocalDateTime now = LocalDateTime.now();
            auctionRequest.setId(accountUtils.getAccountCurrent().getId());
            auctionRequest.setRequestdate(now);
            auctionRequest.setJewelryname(auctionRequestReponse.getJewelryName());
            auctionRequest.setJewelrydescription(auctionRequestReponse.getJewelryDescription());
            auctionRequest.setJewelryinitialprice(0);
            auctionRequest.setStatus(AuctionRequestStatus.PENDING.name());
        return auctionRepository.save(auctionRequest);
    }

//    public List<AuctionRequest> getAuctionRequest() {
//        long userid = accountUtils.getAccountCurrent().getId();
//        return auctionRepository.findByAccountId(userid);
//    }

    public AuctionRequest cancelRequest(long auctionrequestid) {
        AuctionRequest auctionRequest = auctionRepository.findById(auctionrequestid);
        auctionRequest.setStatus(AuctionRequestStatus.CANCEL.name());
        return auctionRepository.save(auctionRequest);
    }

    public List<AuctionRequest> getAuctionRequestByStatus(String status) {
        return auctionRepository.findByStatus(status);
    }
}
