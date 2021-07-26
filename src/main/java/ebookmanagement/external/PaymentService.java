
package ebookmanagement.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@FeignClient(name="ebookmgmt-payment", url="http://localhost:8082")//, fallback = PaymentServiceFallback.class)
@FeignClient(name="user18-ebookmgmt-payment", url="http://user18-ebookmgmt-payment:8080")//, fallback = PaymentServiceFallback.class)
public interface PaymentService {

    @RequestMapping(method= RequestMethod.POST, path="/payments")
    public void payment(@RequestBody Payment payment);

}

