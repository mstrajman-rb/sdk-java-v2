package com.decidir.sdk

import com.decidir.sdk.dto.BillingData
import com.decidir.sdk.dto.Channel
import com.decidir.sdk.dto.Currency
import com.decidir.sdk.dto.Customer
import com.decidir.sdk.dto.CustomerInSite
import com.decidir.sdk.dto.FraudDetectionDataResponse
import com.decidir.sdk.dto.Item
import com.decidir.sdk.dto.OfflinePaymentRequest
import com.decidir.sdk.dto.PaymentRequest
import com.decidir.sdk.dto.PaymentType
import com.decidir.sdk.dto.PurchaseTotals
import com.decidir.sdk.dto.Status
import com.decidir.sdk.dto.TicketingFraudDetectionData
import com.decidir.sdk.dto.TicketingTransactionData
import com.decidir.sdk.exceptions.PaymentException
import spock.lang.Specification

class OfflinePaymentServiceTests extends Specification {

    public static final String secretAccessToken = '00040407'//'660b244e41bf404b821789c0113c6337'
    public static final String token = "2252e216-ba8c-44cd-8044-37a230a79e73"
    public static final String apiUrl = "http://localhost:9002"

    def decidir

    def setup() {
        decidir = new Decidir(secretAccessToken, apiUrl, 30)
    }

    def "test PMC payment"() {
        setup:

        def payment = new PaymentRequest()
        payment.site_transaction_id = UUID.randomUUID().toString()
        payment.token = token
        payment.payment_method_id = 41
        payment.amount = 5000
        payment.currency = Currency.ARS
        payment.payment_type = PaymentType.SINGLE
        payment.email = "persona@email.com"
        payment.bank_code = "ISEL"
        payment.invoice_expiration = "311219 2359"

        when:
        def result = decidir.payment(payment)

        then:
        result.status == 201
        result.result.status == Status.INVOICE_GENERATED
    }

    def "test offline payment"() {
        setup:
        def offline = new OfflinePaymentRequest()
        offline.site_transaction_id = UUID.randomUUID().toString()
        offline.token = token
        offline.payment_method_id = 25
        offline.amount = 5000
        offline.currency = Currency.ARS
        offline.payment_type = PaymentType.SINGLE
        offline.email = "persona@email.com"
        offline.invoice_expiration = "191223"
        offline.cod_p1 = "123"
        offline.cod_p2 = "1123"
        offline.cod_p3 = "11"
        offline.cod_p4 = "123"
        offline.client = "12345678"
        offline.surcharge = 100l

        when:
        def result = decidir.offlinePayment(offline)

        then:
        result.status == 201
        result.result.status == Status.INVOICE_GENERATED
        //Si no se cambia los datos previos
        result.result.barcode == "12311231234567823968498191223000050001100001001234"
    }
}
