package movieticketbookingservice.paymentservice.services.paymentgateway;

import com.dodopayments.api.client.DodoPaymentsClient;
import com.dodopayments.api.client.okhttp.DodoPaymentsOkHttpClient;
import com.dodopayments.api.models.misc.CountryCode;
import com.dodopayments.api.models.payments.BillingAddress;
import com.dodopayments.api.models.payments.AttachExistingCustomer;
import com.dodopayments.api.models.payments.OneTimeProductCartItem;
import com.dodopayments.api.models.payments.PaymentCreateParams;
import com.dodopayments.api.models.payments.PaymentCreateResponse;

import com.movie.ticket.booking.service.commons.dtos.BookingDTO;
import com.movie.ticket.booking.service.commons.dtos.PaymentResultDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DodoPaymentService implements PaymentGateway {

    @Value("${dodo.key}")
    private String secretKey;  // the API key / bearer token from your config

    @Value("${dodo.base-url:#{null}}")
    private String baseUrl;  // optional if using non-default environment

    /**
     * Processes a one-time payment via Dodo Payments.
     *
     * @param bookingDTO the booking details, amount, etc.
     * @return PaymentResultDTO result containing status, payment id etc.
     */
    public BookingDTO makePayment(BookingDTO bookingDTO) {
        try {
            // 1. Build the client
            DodoPaymentsClient client;
            if (baseUrl != null && !baseUrl.isBlank()) {
                client = DodoPaymentsOkHttpClient.builder()
                        .bearerToken(secretKey)
                        .baseUrl(baseUrl)
                        .build();
            } else {
                client = DodoPaymentsOkHttpClient.builder()
                        .bearerToken(secretKey)
                        // will use default base URL (test or live depending on SDK default or env)
                        .build();
            }

            // 2. Build the payment parameters
            PaymentCreateParams.Builder paramsBuilder = PaymentCreateParams.builder()
                    // billing address is often required by payment systems; you might set basic dummy fields or real customer address
                    .billing(
                            BillingAddress.builder()
                                    .street("Unknown") // replace with real if available
                                    .city("CityName")
                                    .state("StateName")
                                    .country(CountryCode.IN)  // if India or appropriate
                                    .zipcode("000000")
                                    .build()
                    )
                    // optionally attach existing customer if you have customer ID
                    .customer( AttachExistingCustomer.builder()
                            .customerId(bookingDTO.getUserId()) // if this matches your user/customer in Dodo
                            .build()
                    )
                    // oneTimeProductCart: at least one product or item
                    .addProductCart(
                            OneTimeProductCartItem.builder()
                                    // productId must be an existing product in Dodo, else you may need to create product or use dynamic product creation
                                    .productId("your_product_id_here")
                                    .quantity(1)
                                    .build()
                    )
                    // might want metadata, description etc depending on what SDK supports
                    ;
            // You might also set returnUrl, currency, etc., if supported in paramsBuilder

            PaymentCreateParams params = paramsBuilder.build();

            // 3. Call SDK
            PaymentCreateResponse response = client.payments().create(params);

            // 4. Interpret response
            PaymentResultDTO result = new PaymentResultDTO();
            result.setPaymentId(response.paymentId());
//            result.setStatus(response.getStatus().toString()); // depending on response.getStatus() type
//            // success if status is 'succeeded' or whatever the SDK uses
//            boolean isSuccess = "succeeded".equalsIgnoreCase(response.getStatus().toString())
//                    || response.getStatus().isFinalSucceeded(); // or use whatever enum or helper exists
//            result.setSuccess(isSuccess);
//            result.setMessage(isSuccess ? "Payment succeeded" : "Payment in non-success status: " + response.getStatus());

            return new BookingDTO();

        } catch (Exception ex) {
            // Log exception here
            PaymentResultDTO errorResult = new PaymentResultDTO();
            errorResult.setSuccess(false);
            errorResult.setPaymentId(null);
            errorResult.setStatus("failed");
            errorResult.setMessage("Exception during payment: " + ex.getMessage());
            return new BookingDTO();
        }
    }
}
