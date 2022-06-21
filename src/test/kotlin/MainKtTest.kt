import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calculateCommissionVkPay() {
        val cardType = "VK_Pay"
        val previousPayments = 0
        val transfer = 15_000

        val result = calculateCommission(cardType, previousPayments, transfer)

        assertEquals(0, result)
    }
}