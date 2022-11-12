package controllers

import COMPANY_NAME
import FEDERAL_MIN_WAGE
import MAX_ID_NUMBER
import MIN_ID_NUMBER
import classes.Employee
import employees
import getDoubleInput
import getIntInput
import getStringInput
import insertBreakLine
import isEmployeeAlreadyInSystem
import scanner
import kotlin.random.Random

// TODO: Causes stack overflow
fun generateRandomEmployeeId(): Int {
//	var id = Random.nextInt(MIN_ID_NUMBER, MAX_ID_NUMBER)
//	employees.find { it.id == id } ?: return generateRandomEmployeeId()
//
	return Random.nextInt(MIN_ID_NUMBER, MAX_ID_NUMBER)
}

fun addEmployee() {
	println("Employee Management - Add Employee")
	
	//TODO: Input validation, SSNs must have 10 numbers (so make it a string and check)
	val ssn = getIntInput(scanner, "Input new employees SSN: ")
	
	if (isEmployeeAlreadyInSystem(ssn)) {
		println("This employee is already in the system.")
		return
	}
	
	val firstName = getStringInput(scanner, "Input employees first name: ")
	
	val lastName = getStringInput(scanner, "Input employees last name: ")
	
	var payRate = getDoubleInput(scanner, "Input employees pay rate (Federal Min Wage is $FEDERAL_MIN_WAGE): ")
	
	while (payRate < FEDERAL_MIN_WAGE) {
		payRate = getDoubleInput(scanner, "Input employees pay rate (Federal Min Wage is $FEDERAL_MIN_WAGE): ")
	}
	
	insertBreakLine()
	
	println("Generating automated company information...")
	val id: Int = generateRandomEmployeeId()
	val companyEmail = "${firstName.lowercase()[0]}${lastName.lowercase()}$id@$COMPANY_NAME.com"
	val fullName = "$firstName $lastName"
	
	println("$fullName's additional information:")
	println("Employee id: $id")
	println("Company email: $companyEmail")
	
	val employee = Employee(ssn, id, firstName, lastName, companyEmail)
	try {
		employee.payRate = payRate
	} catch (e: Exception) {
		println(e)
	}
	
	employees.add(employee)
	
	insertBreakLine()
	
	println("Employee successfully added!")
}