package edu.uw.basickotlin

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(anything: Any): String {
    return when (anything) {
        "Hello" -> "world"
        "Bonjour", "Howdy" -> "Say what?"
        is String -> "I don't understand"
        0 -> "zero"
        1 -> "one"
        in 2..10 -> "low number"
        is Int -> "a number"
        else -> "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(num1: Int, num2: Int): Int {
    return num1 + num2
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(num1: Int, num2: Int): Int {
    return num1 - num2
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(num1: Int, num2: Int, func: (Int, Int) -> Int): Int {
    return func(num1, num2)
}

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int) {
    val debugString: String = "[Person firstName:$firstName lastName:$lastName age:$age]"
}

// write a class "Money" with amount and currency, and define a convert() method and the "+" operator
enum class Currency(val currName: String) {
    USD("USD"), EUR("EUR"), CAN("CAN"), GBP("GBP")
}

class Money(amount: Int, currency: String) {
    var amount: Int = amount
    
    val currency: String = currency

    fun convert(newCurr: String): Money {
        /*
        val convertCurr = when (newCurr) {
            "USD" -> Currency.USD
            "EUR" -> Currency.EUR
            "CAN" -> Currency.CAN
            "GBP" -> Currency.GBP
            else -> throw IllegalArgumentException("Currency is invalid")
        }
        */

        val newCurrStr = Currency.valueOf(newCurr)

        val convertedAmount: Int = when (this.currency) {
            "USD" -> {
                when (newCurrStr.currName) {
                    "GBP" -> this.amount / 2
                    "EUR" -> (this.amount / 2) * 3
                    "CAN" -> (this.amount / 4) * 5
                    else -> this.amount
                }
            }

            "EUR" -> {
                when (newCurrStr.currName) {
                    "GBP" -> this.amount / 3
                    "CAN" -> (this.amount / 6) * 5
                    "USD" -> (this.amount / 3) * 2
                    else -> this.amount
                }
            }

            "CAN" -> {
                when (newCurrStr.currName) {
                    "GBP" -> (this.amount / 5) * 2
                    "EUR" -> (this.amount / 5) * 6
                    "USD" -> (this.amount / 5) * 4
                    else -> this.amount
                }
            }

            "GBP" -> {
                when (newCurrStr.currName) {
                    "EUR" -> this.amount * 3
                    "CAN"-> (this.amount / 2) * 5
                    "USD" -> this.amount * 2
                    else -> this.amount
                }
            }

            else -> throw IllegalArgumentException("Current currency is invalid")
        }

        return Money(convertedAmount, newCurrStr.currName)
    }

    operator fun plus(newMoney: Money): Money {
        val convertedMoney = newMoney.convert(this.currency)
        val newAmount = convertedMoney.amount + this.amount
        return Money(newAmount,this.currency)
    }

    init {
        if (this.amount < 0) {
            throw IllegalArgumentException("Amount cannot be negative")
        } else if (this.currency != "USD" && this.currency != "EUR" && this.currency != "CAN" && this.currency != "GBP") {
            throw IllegalArgumentException("Currency is invalid")
        }
    }
    
}
