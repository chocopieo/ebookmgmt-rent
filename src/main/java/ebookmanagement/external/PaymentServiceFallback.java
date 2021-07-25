
package ebookmanagement.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class PaymentServiceFallback implements PaymentService {

    @Override
    public void payment(@RequestBody Payment payment) {
        System.out.println("Circuit breaker has been opened. Fallback returned instead.");
    }
}

