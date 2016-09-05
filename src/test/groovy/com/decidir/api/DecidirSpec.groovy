package com.decidir.api

import com.decidir.sdk.exceptions.PaymentException
import com.decidir.sdk.exceptions.ValidateException
import spock.lang.*
import com.decidir.sdk.*
import com.decidir.sdk.dto.*

class DecidirSpec extends Specification {

  public static final String secretAccessToken = '4cf891e492384cdeadf211564aa87007'
  public static final String token = "899d5feb-5085-4028-81e6-78c6da602124"
  public static final String apiUrl = "http://localhost:29002"
//  public static final String apiUrl = "http://decidirapi.dev.redbee.io"
  //"http://localhost:29002"//'https://api.decidir.com'//"http://decidirapi.dev.redbee.io"
  def decidir
  def billTo
  def purchaseTotals
  def customerInSite
  def ticketingTransactionData
  //def subPayment

  def setup(){
    decidir = new Decidir(secretAccessToken, apiUrl, 15)

    billTo = new BillingData()
    billTo.city = "Buenos Aires"
    billTo.country = "AR"
    billTo.customer_id = "martinid"
    billTo.email = "accept@decidir.com.ar"
    billTo.first_name = "Maxi"
    billTo.last_name = "Biandratti"
    billTo.phone_number = "1547766329"
    billTo.postal_code = "1414"
    billTo.state = "BA"
    billTo.street1 = "THAMES 677"
    billTo.ip_address = "190.210.214.252"

    purchaseTotals = new PurchaseTotals()
    purchaseTotals.currency = Currency.ARS
    purchaseTotals.amount = 12444

    customerInSite = new CustomerInSite()
    customerInSite.days_in_site = 243
    customerInSite.is_guest = false
    customerInSite.password = "abracadabra"
    customerInSite.num_of_transactions = 1
    customerInSite.cellphone_number = "12121"

    ticketingTransactionData = new TicketingTransactionData()
    ticketingTransactionData.days_to_event = 55
    ticketingTransactionData.delivery_type = "Pick up"
    def ticketingTItem = new TicketingTItem()
    ticketingTItem.code = "popblacksabbat2016"
    ticketingTItem.description = "Popular Black Sabbath 2016"
    ticketingTItem.name = "popblacksabbat2016ss"
    ticketingTItem.sku = "asas"
    ticketingTItem.total_amount = 242424
    ticketingTItem.quantity = 2
    ticketingTItem.unit_price = 121212
    ticketingTransactionData.items = Arrays.asList(ticketingTItem)

    /*subPayment = new SubPayment()
    subPayment.site_id = "1"
    subPayment.installments = 2
    subPayment.amount = 3*/
  }

  def "test payment with black error"() {
    setup:
    def fraudDetection = new FraudDetectionData()
    fraudDetection.purchase_totals = purchaseTotals
    fraudDetection.channel = Channel.WEB
    fraudDetection.customer_in_site = customerInSite
    fraudDetection.device_unique_id = "devicefingerprintid"
    fraudDetection.ticketing_transaction_data = ticketingTransactionData

    def payment = new Payment()
    payment.payment_type = "single"
    payment.currency = Currency.ARS
    payment.description = ""
    payment.amount = 5
    payment.token = token
    payment.installments = 7
    payment.sub_payments = []
    payment.site_transaction_id = UUID.randomUUID().toString()
    payment.bin = "450799"
    payment.card_brand = Card.VISA
    payment.fraud_detection = fraudDetection

    when:
    decidir.confirmPayment(payment)

    then:
    def exception = thrown(PaymentException)
    exception.status == 402
    exception.payment.status == Status.REJECTED
    exception.payment.fraud_detection.status.decision == "black"
    exception.payment.fraud_detection.status.reason_code == "-1"
    exception.payment.fraud_detection.status.description == "validation"
    exception.payment.fraud_detection.status.details != null
  }

  def "test confirmPayment valid"() {
    setup:
    def fraudDetection = new FraudDetectionData()
    fraudDetection.bill_to = billTo
    fraudDetection.purchase_totals = purchaseTotals
    fraudDetection.channel = Channel.WEB
    fraudDetection.customer_in_site = customerInSite
    fraudDetection.device_unique_id = "devicefingerprintid"
    fraudDetection.ticketing_transaction_data = ticketingTransactionData

    def payment = new Payment()
    payment.payment_type = "single"
    payment.currency = Currency.ARS
    payment.description = ""
    payment.amount = 5
    payment.token = token
    payment.installments = 7
    payment.sub_payments = []
    payment.site_transaction_id = UUID.randomUUID().toString()
    payment.bin = "450799"
    //payment.merchant_id=
    payment.card_brand = Card.VISA
    payment.fraud_detection = fraudDetection

    when:
    def result = decidir.confirmPayment(payment)

    then:
    result.status == 200
    result.result.status == Status.APPROVED
    result.result.fraud_detection.status.decision == "green"
    result.result.fraud_detection.status.reason_code == "100"
    result.result.fraud_detection.status.description == "Decision Manager processing"
  }

  def "test confirmPayment with ValidateException"() {
    setup:
    def fraudDetection = new FraudDetectionData()
    fraudDetection.bill_to = billTo
    fraudDetection.purchase_totals = purchaseTotals
    fraudDetection.channel = Channel.WEB
    fraudDetection.customer_in_site = customerInSite
    fraudDetection.device_unique_id = "devicefingerprintid"
    fraudDetection.ticketing_transaction_data = ticketingTransactionData

    def payment = new Payment()
    payment.payment_type = "single"
    payment.currency = Currency.ARS
    payment.description = ""
    payment.amount = 5
    payment.token = token
    payment.installments = 7
    payment.sub_payments = []
    payment.site_transaction_id = UUID.randomUUID().toString()
    payment.bin = "450793"
    payment.card_brand = Card.VISA
    payment.fraud_detection = fraudDetection

    when:
    decidir.confirmPayment(payment)

    then:
    def exception = thrown(ValidateException)
    exception.status == 400
    exception.errorDetail.error_type == "invalid_request_error"
    exception.errorDetail.validation_errors.get(0).code == "Payment"
    exception.errorDetail.validation_errors.get(0).param == "bin"
    exception.message == "Bad Request"
  }

  def "test confirmPayment with PaymentException"() {
    setup:
    def fraudDetection = new FraudDetectionData()
    fraudDetection.bill_to = billTo
    fraudDetection.purchase_totals = purchaseTotals
    fraudDetection.channel = Channel.WEB
    fraudDetection.customer_in_site = customerInSite
    fraudDetection.device_unique_id = "devicefingerprintid"
    fraudDetection.ticketing_transaction_data = ticketingTransactionData

    def payment = new Payment()
    payment.payment_type = "single"
    payment.currency = Currency.ARS
    payment.description = ""
    payment.amount = 5
    payment.token = token
    payment.installments = 7
    payment.sub_payments = []
    payment.site_transaction_id = UUID.randomUUID().toString()
    payment.bin = "455617"
    payment.card_brand = Card.VISA
    payment.fraud_detection = fraudDetection

    when:
    decidir.confirmPayment(payment)

    then:
    def exception = thrown(PaymentException)
    exception.status == 402
    exception.payment.status_details.error_type.cardErrorCodeId == "invalid_number"
    exception.message == "Payment Required"
  }

  def "test list of payments"() {
    when:
      def decidirResponse = decidir.getPayments(null, null)

    then:
      decidirResponse.status == 200
      decidirResponse.result != null
      decidirResponse.message == "OK"
  }

  @Ignore
  def "test get payment"() {
    when:
      def payments = decidir.payments()
      def payment = decidir.getPayment(payments.result.results[0].id)

    then:
      payment.result.amount == payments.result.results[0].amount
  }

  def "test refund payment"() {
    when:
      def payments = decidir.getPayments(1000, 500)
     // def payment = decidir.refundPayment(payments.result.results[0].id)

    then:
      payments.result.id == payments.result.results[0].id
  }

 /* def "test confirmPaymentAsync valid"() {
    setup:
    def fraudDetection = new FraudDetectionData()
    fraudDetection.bill_to = billTo
    fraudDetection.purchase_totals = purchaseTotals
    fraudDetection.channel = Channel.WEB
    fraudDetection.customer_in_site = customerInSite
    fraudDetection.device_unique_id = "devicefingerprintid"
    fraudDetection.ticketing_transaction_data = ticketingTransactionData

    def payment = new Payment()
    payment.payment_type = "single"
    payment.currency = Currency.ARS
    payment.description = ""
    payment.amount = 5
    payment.token = token
    payment.installments = 7
    payment.sub_payments = []
    payment.site_transaction_id = UUID.randomUUID().toString()
    payment.bin = "450799"
    //payment.merchant_id=
    payment.card_brand = Card.VISA
    payment.fraud_detection = fraudDetection
    def listener = new DecidirListener<DecidirResponse>(){

      @Override
      void getResponse(DecidirResponse response) {
        println(response.message)
      }

      @Override
      void getFailure(Throwable t) {
        println(t.getMessage())
      }
    }

    when:
    decidir.confirmPayment(payment, listener)

    then:
    0 == 0
  }*/

}
