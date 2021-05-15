package netology

import java.math.BigDecimal
import java.math.RoundingMode

enum class CardType(val nameOfCard: String) {
    MAESTRO("Maestro"),
    MASTERCARD("Mastercard"),
    VISA("Visa"),
    MIR("Мир"),
    VKPAY("VK Pay")
}

fun typeOfCard(type: String?): CardType {
    val allTypes = CardType.values()
    for (t in allTypes) {
        if (t.nameOfCard.equals(type)) return t
    }
    return CardType.VKPAY

}

fun transferInterest(
    typeOfCard: CardType = CardType.VKPAY,
    previousAmount: BigDecimal = 0.toBigDecimal(),
    amount: BigDecimal
): BigDecimal =
    when (typeOfCard) {
        CardType.MAESTRO, CardType.MASTERCARD -> {
            if (previousAmount in 300.toBigDecimal()..75000.toBigDecimal()) {
                0.toBigDecimal()
            } else amount.multiply(0.006.toBigDecimal()) + 20.toBigDecimal()
        }
        CardType.MIR, CardType.VISA -> {
            val visaPercentage = amount.multiply(0.0075.toBigDecimal())
            if (visaPercentage.compareTo(25.toBigDecimal()) > 0) {
                visaPercentage
            } else 35.toBigDecimal()
        }
        else -> 0.toBigDecimal()
    }

fun main() {
    val transferAmount = 1000
    val previousAmount = 150
    val cardType = "Mastercard"
    val result = transferInterest(
        typeOfCard = typeOfCard(cardType),
        previousAmount = previousAmount.toBigDecimal(),
        amount = transferAmount.toBigDecimal()
    ).setScale(2,RoundingMode.HALF_UP)
    println("Сумма перевода - $transferAmount рублей.  Сумма комиссии - $result рублей.")
}