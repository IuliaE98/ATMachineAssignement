# ATMachineAssignement

should initialize with the following accounts: Account Number PIN Opening Balance Overdraft 123456789 1234 800 200 987654321 4321 1230 150 

should initialize with €1500 made up of 10 x €50s, 30 x €20s, 30 x €10s and 20 x €5s 

should not dispense funds if the pin is incorrect 

cannot dispense more money than it holds 

cannot dispense more funds than customer have access to 

should not expose the customer balance if the pin is incorrect 

should only dispense the exact amounts requested 

should dispense the minimum number of notes per withdrawal 

User (assume any rest client – curl, postman, browser) should be able to request a balance check along with maximum withdrawal amount (if any) 

User should be able to request a withdrawal. If successful - details of the notes that would be dispensed along with remaining balance 

If anything goes wrong, user should receive meaningful message, and there should be no changes in user’s account. 

Provide Dockerfile 

1 – Code working as described in requirements 

2 – Application is building with simple javac, mvn install or gradle build command (java)

3 - Unit tests are included. Coverage level depends on time you have left to complete the Assignment, but we would like to see business logic (service layer) coverage at 60% 

4 – Other things you would like to implement for this project 


URIs
Check balance

/account/{account_no}/{pin_no}

Withdraw
/account/{account_no}/{pin_no}/{amount}

