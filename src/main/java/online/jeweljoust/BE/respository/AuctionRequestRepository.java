package online.jeweljoust.BE.respository;

import online.jeweljoust.BE.entity.AuctionRequest;
import online.jeweljoust.BE.entity.InitialValuation;
import online.jeweljoust.BE.enums.AuctionRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AuctionRequestRepository extends JpaRepository<AuctionRequest, Long>
{
     AuctionRequest findAuctionRequestById(long id);
     List<AuctionRequest> findByAccountRequestId(long userid);
     List<AuctionRequest> findByStatus(AuctionRequestStatus status);
     @Query("SELECT ar FROM AuctionRequest ar LEFT JOIN ar.auctionSessions asess WHERE ar.status = 'AGREED' AND asess IS NULL")
     List<AuctionRequest> findByAccountRequestAvailable();
}
