
package ebookmanagement.external;

import org.springframework.web.bind.annotation.RequestBody;

public class PaymentServiceFallback implements PaymentService {

    @Override
    public void payment(@RequestBody Payment payment) {
        System.out.println("Circuit breaker has been opened. Fallback returned instead.");
    }
}

