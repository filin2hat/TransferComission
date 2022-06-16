fun main() {
    val cardType = "MIR"
    val previousPayments = 114_000_00
    val transfer = 80_000_00

    val commission = calculateCommission(cardType = "VK_Pay", previousPayments, transfer)
    println(
        """|Платежная система: $cardType.
        |Предыдущие переводы: ${previousPayments.toDouble() / 100} рублей.
        |Сумма текущего перевода: ${transfer.toDouble() / 100} рублей.
        |Размер комиссии за текущий перевод: ${commission.toDouble() / 100} рублей.
    """.trimMargin()
    )
}

fun calculateCommission(cardType: String, previousPayments: Int, transfer: Int): Int {
    val dayLimit = 150_000_00
    val monthLimit = 600_000_00
    val dayLimitVK = 15_000_00
    val monthLimitVK = 40_000_00

    val minCommission = 35_00
    val percentCommission = 0.0075
    val promoCommission = 20_00
    val promoPercentCommission = 0.006

    val minPromoTransfer = 300_00
    val maxPromoPreviousPayments = 75_000_00

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