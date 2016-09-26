package com.decidir.sdk.dto;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by ezequiel on 3/6/16.
 */
public enum Card {

  MASTERCARD("mastercard"),
  AMEX("amex"),
  CABAL("cabal"),
  VISA("visa");
  /*TARSHOP(8),
  //DINERS(3),
  TARJETANARANJA(9),
  VISAMOBILE(13),
  ITALCRED(14),
  ARGENCARD(15),
  VISADEBITO(16),
  VISARECARGABLE(17),
  VISADEBEXTERIOR(18),
  COOPEPLUS(19),
  ITALCREDMASTER(20),
  ARCASH(21),
  NEXO(22),
  CREDIMAS(23),
  NEVADA(24),
  BANELCO(26),
  NATIVA(27),
  TARJETAMAS(28),
  CETELEM(29),
  NACIONPYMES(30),
  PAYSAFECARD(31),
  MONEDERO(32),
  VISAAGRO(33),
  PATAGONIA365(40),
  PAGONIA365(41),
  MUSICRED(42);*/

  private String cardId;

  private Card(final String cardId) {
    this.cardId = cardId;
  }

  @JsonValue
  public String getCardId() {
    return cardId;
  }
}
