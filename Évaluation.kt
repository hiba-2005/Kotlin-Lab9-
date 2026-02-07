fun main() {
 
    // PART 1 - UI + account type
   
     println("Welcome to the banking application.")
     println("Please choose the type of account you want to open:")
     println("1. Debit Account")
     println("2. Credit Account")
     println("3. Checking Account")

    var accountType = ""
    var userChoice = 0

    while (accountType == "") {
        println("Choose an option (1, 2 or 3)")
        userChoice = (1..5).random()
        println("The selected option is ${userChoice}.")

        when (userChoice) {
            1 -> accountType = "debit"
            2 -> accountType = "credit"
            3 -> accountType = "checking"
            else -> continue
        }
    }

    println("You have created a ${accountType} account.")

  
    // PART 2 - balances + operations
 
    var accountBalance = (0..1000).random()

    // Make credit logic meaningful (credit = debt = negative balance)
    if (accountType == "credit") {
        accountBalance = -(0..1000).random()
        println("Credit account detected: balance is a debt => ${accountBalance} dollars.")
    }

    println("The current balance is ${accountBalance} dollars.")

    val money = (0..1000).random()
    println("The amount you transferred is ${money} dollars.")

    fun withdraw(amount: Int): Int {
        accountBalance -= amount
       println("Withdrawal completed: ${amount} dollars deducted. New balance: ${accountBalance} dollars.")

        return amount
    }

    fun debitWithdraw(amount: Int): Int {
        if (accountBalance == 0) {
            println("Can't withdraw, no money on this account!")
            return accountBalance
        } else if (amount > accountBalance) {
            println("Not enough money on this account! The current balance is ${accountBalance} dollars.")
            return 0
        } else {
            return withdraw(amount)
        }
    }

    fun deposit(amount: Int): Int {
        accountBalance += amount
        println("You successfully deposited ${amount} dollars. The current balance is ${accountBalance} dollars.")
        return amount
    }

    fun creditDeposit(amount: Int): Int {
        if (accountBalance == 0) {
            println("This credit account has already been fully paid. No deposit is required.")
            return 0
        } else if (accountBalance + amount > 0) {
            println("Deposit rejected: the amount exceeds the remaining credit balance (${accountBalance} dollars).")
            return 0
        } else if (amount == -accountBalance) {
            accountBalance = 0
          println("Credit balance cleared successfully. Your account is now settled.")
            return amount
        } else {
            return deposit(amount)
        }
    }

 
    // PART 3 - transfer + menu
    
    fun transfer(mode: String) {
        val transferAmount: Int

        when (mode) {
            "withdraw" -> {
                transferAmount = if (accountType == "debit") debitWithdraw(money) else withdraw(money)
                println("The amount you withdrew is ${transferAmount} dollars.")
            }
            "deposit" -> {
                transferAmount = if (accountType == "credit") creditDeposit(money) else deposit(money)
                println("The amount you deposited is ${transferAmount} dollars.")
            }
            else -> return
        }
    }

    var isSystemOpen = true
    var option = 0

    while (isSystemOpen) {
          println("Select an operation to perform:")
          println("1. Display current balance")
          println("2. Withdraw funds")
          println("3. Deposit funds")
          println("4. Terminate the application")
          println("Choose an option between 1 and 4:")

        option = (1..5).random()
        println("The selected option is ${option}.")

        when (option) {
            1 -> println("The current balance is ${accountBalance} dollars.")
            2 -> transfer("withdraw")
            3 -> transfer("deposit")
            4 -> {
                isSystemOpen = false
                println("The system is closed")
            }
            else -> continue
        }
    }
}