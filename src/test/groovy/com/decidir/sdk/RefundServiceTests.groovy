package com.decidir.sdk

import spock.lang.*
import com.decidir.sdk.dto.*

class RefundServiceTests extends Specification {

  public static final String secretAccessToken = '00040407'//'4cf891e492384cdeadf211564aa87007'
  public static final String apiUrl = "http://localhost:9002"

  def decidir

  def setup(){
    decidir = new Decidir(secretAccessToken, apiUrl, 15)
  }

  def "test payment annulled"() {
    setup:
    def paymentId = 1382
    def refundPayment = new RefundPayment()

    when:
    def result = decidir.refundPayment(paymentId, refundPayment)

    then:
    result.status == 200
    result.result.id != null
    result.result.amount != null
    result.result.status == Status.ANNULLED
  }

  def "test payment refunded"() {
    setup:
    def paymentId = 1383
    def refundPayment = new RefundPayment()
    refundPayment.amount = 3

    when:
    def result = decidir.refundPayment(paymentId, refundPayment)

    then:
    result.status == 200
    result.result.id != null
    result.result.amount != null
    result.result.status == Status.APPROVED
  }

  def "test payment cancelRefund"() {
    setup:
    def paymentId = 1383
    def refundId = 201

    when:
    def result = decidir.cancelRefund(paymentId, refundId)

    then:
    result.status == 200
    result.result.amount != null
    result.result.status == Status.APPROVED
  }

  def "test get refunds"() {
    setup:
    def paymentId = 1385

    when:
    def result = decidir.getRefunds(paymentId)

    then:
    result.status == 200
    result.result.parent.history.get(0) != null
  }

}
