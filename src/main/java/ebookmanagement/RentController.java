package ebookmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RepositoryRestController
public class RentController {

    @Autowired
    RentRepository rentRepository;

    @PatchMapping(value="/rents/return/{rentId}")
    @ResponseBody
    public Rent returnRent(@PathVariable Long rentId) {

        System.out.println("\n\n ##### rentId : " + rentId + " 반납 처리 ##### \n\n");

        String status = "RETURNED";

        Rent rent = rentRepository.findById(rentId).get();
        rent.setStatus(status);
        rent.setReturnedDate(new Date());

        return rentRepository.save(rent);
    }
}
