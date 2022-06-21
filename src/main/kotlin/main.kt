const val dayLimit = 150_000_00
const val monthLimit = 600_000_00
const val dayLimitVK = 15_000_00
const val monthLimitVK = 40_000_00
const val minCommission = 35_00
const val percentCommission = 0.0075
const val promoCommission = 20_00
const val promoPercentCommission = 0.006
const val minPromoTransfer = 300_00
const val maxPromoPreviousPayments = 75_000_00

fun main() {
    val cardType = "MAESTRO"
    val previousPayments = 80_000_00
    val transfer = 160_000_00

    val commission = calculateCommission(cardType, previousPayments, transfer)
    println(
        """|Платежная система: $cardType.
        |Предыдущие переводы: ${previousPayments.toDouble() / 100} рублей.
        |Сумма текущего перевода: ${transfer.toDouble() / 100} рублей.
        |Размер комиссии за текущий перевод: ${commission.toDouble() / 100} рублей.
    """.trimMargin()
    )
}

fun calculateCommission(cardType: String = "VK_Pay", previousPayments: Int = 0, transfer: Int): Int {
    when {
        //проверка лимитов VK Pay
        cardType == "VK_Pay" && transfer > dayLimitVK -> println("!!!Превышен дневной лимит по карте VK Pay!!!")
        cardType == "VK_Pay" && (previousPayments + transfer) > monthLimitVK -> println("!!!Превышен месячный лимит по карте VK Pay!!!")

        //проверка лимитов остальных карт
        transfer > dayLimit -> println("Превышен дневной лимит по карте $cardType")
        (previousPayments + transfer) > monthLimit -> println("Превышен месячный лимит по карте $cardType")
    }

    //расчет комиссии
    return when (cardType) {
        "MASTERCARD", "MAESTRO" -> if (transfer >= minPromoTransfer && previousPayments < maxPromoPreviousPayments) 0 else (transfer * promoPercentCommission + promoCommission).toInt()
        "VISA", "MIR" -> if (transfer * percentCommission > minCommission) (transfer * percentCommission).toInt() else minCommission
        else -> 0
    }
}