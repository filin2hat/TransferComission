import org.junit.Assert.assertEquals
import org.junit.Test

class MainKtTest {
    @Test
    fun testMain() {
        main()
    }

    @Test
    fun calculateCommissionMastercardPromo() {
        val cardType = "MASTERCARD"
        val previousPayments = 10_000_00
        val transfer = 10_000_00

        val result = calculateCommission(cardType, previousPayments, transfer)
        assertEquals(0, result)
    }

    @Test
    fun calculateCommissionMastercardNoPromo() {
        val cardType = "MASTERCARD"
        val previousPayments = 10_000_00
        val transfer = 200_00

        val result = calculateCommission(cardType, previousPayments, transfer)
        assertEquals(2120, result)
    }

    @Test
    fun calculateCommissionMaestroPromo() {
        val cardType = "MAESTRO"
        val previousPayments = 10_000_00
        val transfer = 10_000_00

        val result = calculateCommission(cardType, previousPayments, transfer)
        assertEquals(0, result)
    }

    @Test
    fun calculateCommissionMaestroNoPromo() {
        val cardType = "MASTERCARD"
        val previousPayments = 100_000_00
        val transfer = 20_000_00

        val result = calculateCommission(cardType, previousPayments, transfer)
        assertEquals(14000, result)
    }

    @Test
    fun calculateCommissionVisa() {
        val cardType = "VISA"
        val previousPayments = 10_000_00
        val transfer = 10_000_00

        val result = calculateCommission(cardType, previousPayments, transfer)
        assertEquals(7500, result)
    }

    @Test
    fun calculateCommissionMirMinCommission() {
        val cardType = "MIR"
        val previousPayments = 10_000_00
        val transfer = 10_00

        val result = calculateCommission(cardType, previousPayments, transfer)
        assertEquals(3500, result)
    }

    @Test
    fun calculateCommissionVkPayNoCommission() {
        val cardType = "VK_Pay"
        val previousPayments = 10_000_00
        val transfer = 1_000_00

        val result = calculateCommission(cardType, previousPayments, transfer)
        assertEquals(0, result)
    }

    @Test
    fun calculateCommissionVkPayNoCommissionDayLimit() {
        val cardType = "VK_Pay"
        val previousPayments = 10_000_00
        val transfer = 16_000_00

        val result = calculateCommission(cardType, previousPayments, transfer)
        assertEquals(0, result)
    }

    @Test
    fun calculateCommissionVkPayNoCommissionMonthLimit() {
        val cardType = "VK_Pay"
        val previousPayments = 40_000_00
        val transfer = 10_000_00

        val result = calculateCommission(cardType, previousPayments, transfer)
        assertEquals(0, result)
    }
}