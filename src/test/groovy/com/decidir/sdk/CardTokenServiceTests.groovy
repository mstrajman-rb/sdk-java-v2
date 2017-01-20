package com.decidir.sdk

import spock.lang.Specification

/**
 * Created by biandra on 05/10/16.
 */
class CardTokenServiceTests extends Specification {

    public static final String secretAccessToken = '00040407'//'4cf891e492384cdeadf211564aa87007'
    public static final String apiUrl = "http://localhost:9002"

    def decidir

    def setup(){
        decidir = new Decidir(secretAccessToken, apiUrl, 15)
    }

    def "test list cardTokens"() {
        setup:
        def userSiteId = 'biandra'
        def bin = null
        def lastFourDigits = null
        def expirationMonth = null
        def expirationYear = null

        when:
        def result = decidir.getCardTokens(userSiteId, bin, lastFourDigits, expirationMonth, expirationYear)

        then:
        result.status == 200
        result.result.tokens != null
    }

    def "test delete cardTokens"() {
        setup:
        def token = '8487fe7b-7131-44d1-b4e4-92609baa46b3'

        when:
        def result = decidir.deleteCardToken(token)

        then:
        result.status == 204
        result.message ==  'No Content'
    }
}
