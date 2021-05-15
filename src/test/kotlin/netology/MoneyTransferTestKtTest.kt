package netology

import org.junit.Test

import org.junit.Assert.*

class MoneyTransferTestKtTest {

    @Test
    fun typeOfCard_nonDefault() {
        val cardType = "Maestro"

        val result = typeOfCard(cardType)

        assertEquals(CardType.MAESTRO, result)
    }

    @Test
    fun typeOfCard_default() {
        val cardType = "...."

        val result = typeOfCard(cardType)

        assertEquals(CardType.VKPAY, result)
    }

    @Test
    fun transferInterest_Maestro_Mastercard() {
        val transferAmount = 100
        val previousAmount = 1000

        val result = transferInterest(
            typeOfCard = CardType.MAESTRO,
            previousAmount = previousAmount.toBigDecimal(),
            amount = transferAmount.toBigDecimal()
        )

        assertEquals(0.toFloat(),result.toFloat())
    }

    @Test
    fun transferInterest_Maestro_Mastercard_other() {
        val transferAmount = 10000
        val previousAmount = 150

        val result = transferInterest(
            typeOfCard = CardType.MASTERCARD,
            previousAmount = previousAmount.toBigDecimal(),
            amount = transferAmount.toBigDecimal()
        )

        assertEquals(80.toFloat(),result.toFloat())
    }

    @Test
    fun transferInterest_MIR_Visa() {
        val transferAmount = 100
        val previousAmount = 1000
        val cardType = "Мир"

        val result = transferInterest(
            typeOfCard = typeOfCard(cardType),
            previousAmount = previousAmount.toBigDecimal(),
            amount = transferAmount.toBigDecimal()
        )

        assertEquals(35.toFloat(),result.toFloat())
    }

    @Test
    fun transferInterest_MIR_Visa_other() {
        val transferAmount = 10000
        val previousAmount = 1000
        val cardType = "Мир"

        val result = transferInterest(
            typeOfCard = typeOfCard(cardType),
            previousAmount = previousAmount.toBigDecimal(),
            amount = transferAmount.toBigDecimal()
        )

        assertEquals(75.toFloat(),result.toFloat())
    }
    @Test
    fun transferInterest_VKpay() {
        val transferAmount = 1000
        val previousAmount = 1000
        val cardType = "..."

        val result = transferInterest(
            typeOfCard = typeOfCard(cardType),
            previousAmount = previousAmount.toBigDecimal(),
            amount = transferAmount.toBigDecimal()
        )

        assertEquals(0.toFloat(),result.toFloat())
    }
}