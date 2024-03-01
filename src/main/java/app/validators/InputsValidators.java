package app.validators;

public abstract class InputsValidators {
	public void stringValidator(String string, String element) throws Exception {
		if (string == null || string.isEmpty())
			throw new Exception(element + " no es un valor valido.");
	}

	public int integerValidator(String number, String element) throws Exception {
		stringValidator(number, element);
		try {
			return Integer.parseInt(number);
		} catch (Exception e) {
			throw new Exception(element + " no es un valor valido.");
		}
	}
	
	public long longValidator(String number, String element) throws Exception {
		stringValidator(number, element);
		try {
			return Long.parseLong(number);
		} catch (Exception e) {
			throw new Exception(element + " no es un valor valido.");
		}
	}
	
	public double doubleValidator(String number, String element) throws Exception {
		stringValidator(number, element);
		try {
			return Double.parseDouble(number);
		} catch (Exception e) {
			throw new Exception(element + " no es un valor valido.");
		}
	}
}
