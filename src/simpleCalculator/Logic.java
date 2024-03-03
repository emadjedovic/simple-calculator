package simpleCalculator;

public class Logic {
	
	public double evaluate(String expression) {
	    String[] tokens = expression.split("(?=[-+*/])|(?<=[-+*/])");
	    double result = 0;
	    char operator = '+';
	    for (String token : tokens) {
	        if (token.matches("[\\+\\-\\*\\/]")) {
	            operator = token.charAt(0);
	        } else {
	            double operand = Double.parseDouble(token);
	            switch (operator) {
	                case '+':
	                    result += operand;
	                    break;
	                case '-':
	                    result -= operand;
	                    break;
	                case '*':
	                    result *= operand;
	                    break;
	                case '/':
	                    if (operand == 0) {
	                        throw new IllegalArgumentException("Cannot divide by zero");
	                    }
	                    result /= operand;
	                    break;
	            }
	        }
	    }
	    return result;
	}

}
