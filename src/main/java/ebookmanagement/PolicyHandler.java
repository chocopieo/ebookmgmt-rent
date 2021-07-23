package ebookmanagement;

import ebookmanagement.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

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

        if("APPROVED".equals(status)) {
            Rent rent = rentRepository.findById(rentId).get();
            rent.setId(rentId);
            rent.setStatus(status);

            rentRepository.save(rent);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRejected_ChangeStatus(@Payload Rejected rejected){

        if(!rejected.validate()) return;

        System.out.println("\n\n##### listener ChangeStatus : " + rejected.toJson() + "\n\n");

        Long rentId = rejected.getRentId();
        String status = rejected.getStatus();

        if("REJECTED".equals(status)) {
            Rent rent = rentRepository.findById(rentId).get();
            rent.setId(rentId);
            rent.setStatus(status);

            rentRepository.save(rent);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
