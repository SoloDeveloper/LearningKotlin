package controllers

import classes.Employee
import displayEmployeeTable

fun viewEmployees(employees: List<Employee>) {
	println("Employee Management - View Employees")
	displayEmployeeTable(employees)
}