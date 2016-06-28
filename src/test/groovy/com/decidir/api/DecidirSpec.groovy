package com.decidir.api;

import spock.lang.*
import com.decidir.sdk.*
import com.decidir.sdk.dto.*

class DecidirSpec extends Specification {

  public static final String REJECTED = "rejected"
  public static final String APPROVED = "approved"
  public static final String secretAccessToken = '00040407'
  public static final String token = "de18b169-317c-420a-86ea-32991edfff36"
  public static final String apiUrl = "http://decidirapi.dev.redbee.io"
  //"http://localhost:9002"//'http://172.17.10.59:9002'
  def decidir
  def billTo
  def purchaseTotals
  def customerInSite
  def ticketingTransactionData

  def setup(){
    decidir = new Decidir(secretAccessToken, apiUrl)

    billTo = new BillingData()
    billTo.city = "Buenos Aires"
    billTo.country = "Argentina"
    billTo.customer_id = "martinid"
    billTo.email = "martin@redb.ee"
    billTo.first_name = "Martin"
    billTo.last_name = "PPP"
    billTo.phone_number = "2322323232"
    billTo.postal_code = "1223"
    billTo.state = "Buenos Aires"
    billTo.street1 = "Italia 1234"

    purchaseTotals = new PurchaseTotals()
    purchaseTotals.currency = Currency.ARS
    purchaseTotals.amount = 124.44

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
    ticketingTItem.total_amount = 2424.24
    ticketingTItem.quantity = 2
    ticketingTItem.unit_price = 1212.12
    ticketingTransactionData.items = Arrays.asList(ticketingTItem)
  }

  def "test payment with black error"() {
    setup:
      def payment = new Payment()
      payment.payment_type = "single"
      payment.currency = Currency.ARS
      payment.description = ""
      payment.amount = 10010
      payment.token = token
      payment.installments = 7
      payment.sub_payments = []
      payment.site_transaction_id = UUID.randomUUID().toString()
      payment.bin = "450799"
      payment.merchant_id= secretAccessToken
      payment.card_brand = Card.VISA

    when:
    def result = decidir.confirmPayment(payment)

    then:
      result.status == 200
      result.result.status == APPROVED
    ((Payment)result.result).fraud_detection.status.decision == "black"
    ((Payment)result.result).fraud_detection.status.reason_code == "X"
    ((Payment)result.result).fraud_detection.status.description == "InvalidRequestError(List(ValidationError(missing,bill_to)))"
  }

  def "test valid payment"() {
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
    payment.amount = 10010
    payment.token = token
    payment.installments = 7
    payment.sub_payments = []
    payment.site_transaction_id = UUID.randomUUID().toString()
    payment.bin = "450799"
    payment.merchant_id= secretAccessToken
    payment.card_brand = Card.VISA
    payment.fraud_detection = fraudDetection

    when:
    def result = decidir.confirmPayment(payment)

    then:
    result.status == 200
    result.result.status == APPROVED
    ((Payment)result.result).fraud_detection.status.decision == "green"
    ((Payment)result.result).fraud_detection.status.reason_code == "100"
    ((Payment)result.result).fraud_detection.status.description == "Decision Manager processing"
  }

  @Ignore
  def "test list of payments"() {
    when:
      def decidirResponse = decidir.payments()

    then:
      decidirResponse != null
      decidirResponse.status == 200
  }

  @Ignore
  def "test get payment"() {
    when:
      def payments = decidir.payments()
      def payment = decidir.getPayment(payments.result.results[0].id)

    then:
      payment.result.amount == payments.result.results[0].amount
  }

  @Ignore
  def "test delete payment"() {
    when:
      def payments = decidir.payments()
      def payment = decidir.cancelPayment(payments.result.results[0].id)

    //println "cambiar cuando este el rest real"

    then:
      payment.result.id == payments.result.results[0].id
  }

}
