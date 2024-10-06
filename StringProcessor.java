import java.util.regex.Pattern;

public class StringProcessor {

    // Checks if the password is strong
    public boolean isStrongPassword(String password) {
        return password != null && password.length() >= 8 &&
               Pattern.compile("[A-Z]").matcher(password).find() &&
               Pattern.compile("[a-z]").matcher(password).find() &&
               Pattern.compile("[0-9]").matcher(password).find() &&
               Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
    }

    // Counts digits in a sentence
    public int calculateDigits(String sentence) {
        return (int) (sentence == null ? 0 : sentence.chars().filter(Character::isDigit).count());
    }

    // Calculates words in a sentence
    public int calculateWords(String sentence) {
        return (sentence == null || sentence.trim().isEmpty()) ? 0 : sentence.trim().split("\\s+").length;
    }

    // Calculates a mathematical expression
    public double calculateExpression(String expression) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() { ch = (++pos < expression.length()) ? expression.charAt(pos) : -1; }
            boolean eat(int charToEat) { while (ch == ' ') nextChar(); if (ch == charToEat) { nextChar(); return true; } return false; }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                while (true) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                while (true) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if (Character.isDigit(ch)) {
                    while (Character.isDigit(ch) || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                return x;
            }
        }.parse();
    }
}
