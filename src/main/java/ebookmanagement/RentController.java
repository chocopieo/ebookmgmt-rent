package ebookmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class RentController {

//     @Autowired
//     RentRepository rentRepository;

//     @PatchMapping(value="/rent/return/{id}")
//     public Rent returnRequest(@PathVariable Long id) throws Exception {
//         System.out.println("##### /rent/return  called #####");
//
//         Rent rent = rentRepository.findById(id).orElse(null);
//         if(rent == null) {
//              throw new Exception("예약번호를 찾을 수 없습니다.");
//         }
//
//         String status = rent.getStatus();
//         if (!"RENT".equals(status)) {
//              throw new Exception("에약이 확정되지 않았습니다.");
//         }
//
//         return rent;
//     }

}
