package controllers

import classes.Employee
import displayEmployeeTable
import employees
import getEmployee
import getIntInput
import getYesOrNo
import insertBreakLine
import scanner

// TODO: Eventually I need toa adopt the whole services type thing, where this is the "front" end and we just pass along the information to the service.
// This would allow us to run this function without having to deal with the questions.
fun fireEmployee() {
	println("Employee Management - Fire Employee")
	
	val employeesToFire = mutableListOf<Employee>()
	
	fun manageEmployeesToFire(bypassRemoveFromListPrompt: Boolean = false) {
		var isDoneSelecting = false
		
		while (!isDoneSelecting) {
			//TODO: Input validation, SSNs must have 10 numbers (so make it a string and check)
			val ssn = getIntInput(scanner, "Input the employee's SSN you wish to fire: ")
			
			val employee = getEmployee(ssn)
			
			insertBreakLine()
			
			if (employee == null) {
				println("Cannot find an employee with the given SSN")
			} else {
				val employeeAlreadyIn = employeesToFire.find { it.ssn == employee.ssn }
				
				if (employeeAlreadyIn != null) {
					println("Employee already added to the list")
					
					if (!bypassRemoveFromListPrompt) {
						val chosenOption =
							getYesOrNo("Would you like to remove this employee from the list? Enter yes (y) or no (n): ")
						
						if (chosenOption == "y") {
							println("Removing employee from the list")
							employeesToFire.remove(employee)
						}
					} else {
						println("Removing employee from the list")
						employeesToFire.remove(employee)
					}
					// TODO: Ask user if they want the employee removed
				} else {
					employeesToFire.add(employee)
					println("Added employee to firing list")
				}
			}
			
			insertBreakLine()
			
			val chosenOption = getYesOrNo("Done selecting? Enter yes (y) or no (n): ")
			
			if (chosenOption == "y") {
				isDoneSelecting = true
			}
		}
	}
	
	manageEmployeesToFire()
	
	if (employeesToFire.size == 0) {
		insertBreakLine()
		println("No employees selected to fire")
		return
	}
	
	insertBreakLine()
	
	println("Are you sure you want to fire employees:")
	
	displayEmployeeTable(employeesToFire)
	
	val chosenOption = getYesOrNo("Enter yes (y) or no (n) to edit the list: ")
	
	if (chosenOption == "n") {
		// TODO : When this gets executed and the function is over, it immediately jumps back to here (annoyingly), causing the extra review not to happen.
		manageEmployeesToFire()
	}
	
	println("Firing employee(s)..")
	
	for (employee in employeesToFire) {
		employees.remove(employee)
	}
	
	println("Employee(s) fired")
}