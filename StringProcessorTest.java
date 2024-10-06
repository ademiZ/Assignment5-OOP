import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringProcessorTest {

    StringProcessor processor = new StringProcessor();

    @Test
    public void testIsStrongPassword() {
        assertTrue(processor.isStrongPassword("A1b!"), "Strong password");
        assertFalse(processor.isStrongPassword("abc"), "Too weak, no uppercase, digit, or special char");
        assertFalse(processor.isStrongPassword("ABC123"), "Too weak, no lowercase or special char");
        assertFalse(processor.isStrongPassword("!@#"), "Too weak, no letters or digits");
        assertFalse(processor.isStrongPassword(""), "Empty password");
    }

    @Test
    public void testCalculateDigits() {
        assertEquals(2, processor.calculateDigits("I have 2 cats and 1 dog."));
        assertEquals(0, processor.calculateDigits("No digits here!"));
        assertEquals(4, processor.calculateDigits("2022 is a year."));
        assertEquals(10, processor.calculateDigits("Phone: 123-456-7890"));
        assertEquals(0, processor.calculateDigits(""));
    }

    @Test
    public void testCalculateWords() {
        assertEquals(4, processor.calculateWords("This is a test."));
        assertEquals(1, processor.calculateWords("Hello"));
        assertEquals(0, processor.calculateWords(""));
        assertEquals(0, processor.calculateWords("   "));
        assertEquals(3, processor.calculateWords("Count these words"));
    }

    @Test
    public void testCalculateExpression() {
        assertEquals(5.0, processor.calculateExpression("2 + 3"));
        assertEquals(8.0, processor.calculateExpression("4 * 2"));
        assertEquals(1.0, processor.calculateExpression("3 - 2"));
        assertEquals(2.0, processor.calculateExpression("6 / 3"));
        assertEquals(15.0, processor.calculateExpression("(10 + 5) - 0"));
    }
}
