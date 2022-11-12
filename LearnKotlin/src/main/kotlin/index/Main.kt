import classes.Employee
import controllers.*
import java.util.*
import kotlin.random.Random

val scanner: Scanner = Scanner(System.`in`)

// TODO : Make separate files

val employees = mutableListOf<Employee>()

val mainMenuOptions = listOf(
	"1. View Employees",
	"2. Add employee(s)",
	"3. Fire employee(s)",
	"4. Exit program",
)

fun insertBreakLine() {
	println("")
}

fun promptOptions() {
	println("Please select one of the options:")
	for (option in mainMenuOptions) {
		println(option)
	}
}

fun getChosenOption(): Int {
	val chosenOption = getIntInput(scanner, "Select option: ")
	
	if (chosenOption > mainMenuOptions.size || chosenOption < 1) {
		println("Please select a valid option 1-${mainMenuOptions.size}")
		return getChosenOption()
	}
	
	return chosenOption
}

fun getYesOrNo(customText: String? = "Enter yes (y) or no (n): "): String {
	var chosenOption = getStringInput(scanner, customText).lowercase()
	
	while (chosenOption != "yes" && chosenOption != "y" && chosenOption != "no" && chosenOption != "n") {
		chosenOption = getStringInput(scanner, customText).lowercase()
	}
	
	return if (chosenOption == "yes" || chosenOption == "y") "y" else "n"
}

fun displayEmployeeTable(array: List<Employee>) {
	var index = 1
	for (employee in array) {
		println("$index. ${employee.firstName} ${employee.lastName} (${employee.companyEmail}) - id ${employee.id}, $${employee.payRate}/hr")
		index++
	}
}

// Add employee functions

fun isEmployeeAlreadyInSystem(ssn: Int): Boolean {
	return employees.find {
		it.ssn == ssn
	} != null
}

fun getEmployee(ssn: Int): Employee? {
	if (!isEmployeeAlreadyInSystem(ssn)) {
		return null
	}
	
	return employees.find {
		it.ssn == ssn
	}
}

// Source: Internet
fun getRandomString(length: Int): String {
	val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
	
	return List(length) { charset.random() }.joinToString("")
}

fun quickGenerateEmployees(max: Int) {
	for (ssn in 1..max) {
		val id = generateRandomEmployeeId()
		val firstName = getRandomString(6)
		val lastName = getRandomString(6)
		employees.add(
			Employee(
				ssn, id, firstName, lastName, "${firstName.lowercase()[0]}${lastName.lowercase()}$id@$COMPANY_NAME.com"
			)
		)
	}
}

fun main() {
	println("Initializing system...")
	println("Welcome to the employee management system")
	println("Version 1.0 \n")
	
	if (GENERATE_FAKE_EMPLOYEES) {
		println("Quick generating employees")
		quickGenerateEmployees(AMOUNT_TO_CREATE)
	}
	
	insertBreakLine()
	
	var shouldContinue = true
	
	while (shouldContinue) {
		promptOptions()
		
		insertBreakLine()
		
		val chosenOption = getChosenOption()
		
		insertBreakLine()
		
		// TODO : See if I can improve this
		when (chosenOption) {
			1 -> {
				viewEmployees(employees)
			}
			
			2 -> {
				addEmployee()
			}
			
			3 -> {
				fireEmployee()
			}
			
			4 -> {
				shouldContinue = false
			}
		}
		
		insertBreakLine()
	}
	
	scanner.close()
	println("End of program")
}
