

val totalYearlyIncome:(Int,Int) => Int =  (income, bonus) => income + bonus
val totalYearlyIncomeCurried: Int => (Int => Int) = totalYearlyIncome.curried

val partialTotalYearlyIncome: Int => Int = totalYearlyIncomeCurried(10000)

partialTotalYearlyIncome(100)


def minus(left: Int, right: Int) = left - right











