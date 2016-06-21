package com.decidir.api;

import spock.lang.*

import com.decidir.sdk.*
import com.decidir.sdk.dto.*

class DecidirSpec extends Specification {

  def secretAccessToken = '00290815'
  def apiUrl = "http://decidirapi.dev.redbee.io"//'http://172.17.10.177:9002'//"http://decidirapi.dev.redbee.io"

  def "test payment with token"() {

    setup: "create decidir api"
      def decidir = new Decidir(secretAccessToken, apiUrl)

    when:

      def payment = new Payment()

      payment.payment_type = "single"
      payment.currency = "ARS"
      payment.description = ""
      payment.amount = 10010
      payment.token = "558af2a3-2ef9-4ba2-bd23-de299905b323"
      payment.installments = 1
      payment.sub_payments = []
      payment.site_transaction_id = "1234"
      payment.bin = "12342"


    def result = decidir.confirmPayment(payment)

    then:
      result.status == 200
      result.result.amount == 10010
  }

  def "test list of payments"() {

    setup: "create decidir api"
      def decidir = new Decidir(secretAccessToken, apiUrl)

    when:
      def decidirResponse = decidir.payments()

    then:
      decidirResponse != null
      decidirResponse.status == 200
  }

  @Ignore
  def "test get payment"() {

    setup: "create decidir api"
      def decidir = new Decidir(secretAccessToken, apiUrl);

    when:
      def payments = decidir.payments()

      def payment = decidir.getPayment(payments.result.results[0].id)

    then:
      payment.result.amount == payments.result.results[0].amount

  }

  @Ignore
  def "test delete payment"() {

    setup: "create decidir api"
      def decidir = new Decidir(secretAccessToken, apiUrl)

    when:
      def payments = decidir.payments()

      def payment = decidir.cancelPayment(payments.result.results[0].id)

       println "cambiar cuando este el rest real"

    then:
      payment.result.id == payments.result.results[0].id

  }
}
