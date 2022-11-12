package classes

import FEDERAL_MIN_WAGE


// TODO: Input a (changeable) pay rate (int) field
class Employee(val ssn: Int, val id: Int, val firstName: String, val lastName: String, val companyEmail: String) {
  var payRate: Double = FEDERAL_MIN_WAGE
    set(value) {
      if (value < FEDERAL_MIN_WAGE) {
        throw Exception("Can't set an employees pay rate lower than the federal minimum wage")
      } else {
        field = value
      }
    }
}