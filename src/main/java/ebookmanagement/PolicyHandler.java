package ebookmanagement;

import ebookmanagement.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PolicyHandler{
    @Autowired RentRepository rentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaid_ChangeStatus(@Payload Paid paid){

        if(!paid.validate()) return;

        System.out.println("\n\n##### listener ChangeStatus : " + paid.toJson() + "\n\n");

        Long rentId = paid.getRentId();
        String status = paid.getStatus();

        if("PAID".equals(status)) {
            Rent rent = rentRepository.findById(rentId).get();
            rent.setId(rentId);
            rent.setStatus(status);

            rentRepository.save(rent);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverApproved_ChangeStatus(@Payload Approved approved){

        if(!approved.validate()) return;

        System.out.println("\n\n##### listener ChangeStatus : " + approved.toJson() + "\n\n");

        Long rentId = approved.getRentId();
        String status = approved.getStatus();
        Date approvedDate = approved.getApprovedDate();

        if("APPROVED".equals(status)) {
            Rent rent = rentRepository.findById(rentId).get();
            rent.setId(rentId);
            rent.setStatus(status);
            rent.setRentedDate(approvedDate);

            rentRepository.save(rent);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRefunded_ChangeStatus(@Payload Refunded refunded){

        if(!refunded.validate()) return;

        System.out.println("\n\n##### listener ChangeStatus : " + refunded.toJson() + "\n\n");

        Long rentId = refunded.getRentId();
        String status = refunded.getStatus();

        if("REFUNDED".equals(status)) {
            Rent rent = rentRepository.findById(rentId).get();
            rent.setId(rentId);
            rent.setStatus("CANCELED");
            rent.setCanceledDate(new Date());

            rentRepository.save(rent);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
