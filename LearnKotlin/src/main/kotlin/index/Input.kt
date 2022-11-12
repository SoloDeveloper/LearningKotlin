import java.util.*

const val defaultPrompt = "Enter input: "

fun getStringInput(scanner: Scanner, prompt: String?): String {
	prompt ?: defaultPrompt
	print(prompt)
	return scanner.next()
}

fun getIntInput(scanner: Scanner, prompt: String?): Int {
	prompt ?: defaultPrompt
	
	var returnValue: Int? = null
	
	print(prompt)
	while (returnValue == null) {
		try {
			returnValue = scanner.nextInt()
		} catch (e: InputMismatchException) {
			print(prompt)
			scanner.nextLine()
		}
	}
	
	return returnValue
}

fun getDoubleInput(scanner: Scanner, prompt: String?): Double {
	prompt ?: defaultPrompt
	
	var returnValue: Double? = null
	
	print(prompt)
	while (returnValue == null) {
		try {
			returnValue = scanner.nextDouble()
		} catch (e: InputMismatchException) {
			print(prompt)
			scanner.nextLine()
		}
	}
	
	return returnValue
}