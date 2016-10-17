package main;

import org.junit.Test;

import java.util.Map;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ModelTest {

    private Model model;

    public ModelTest() {
        model = new Model();
    }

    @Test
    public void wordListShouldContainSpecificWords() {
        Map<Integer, String> list = model.getWordList();
        assertEquals("zupka", list.get(66625));
        assertEquals("krepon", list.get(34324));
        assertEquals("orkisz", list.get(44451));
    }

    @Test
    public void generatedPasswordShouldContainSpecificSeparator() {
        String password = model.generatePassword();
        int counter = 0;
        for(int i = 0; i < password.length(); i++) {
            if(password.charAt(i) == model.getSeparator()) counter++;
        }
        assertEquals(counter, model.getWordNumber() - 1);
        assertNotEquals(password.charAt(0), model.getSeparator());
        assertNotEquals(password.charAt(password.length() - 1), model.getSeparator());
    }

    @Test
    public void generatedPasswordShouldContainSpecificNumberOfWords() {
        String[] words = splitPassword(model.getSeparator(), model.generatePassword());
        assertEquals(7, words.length);

        model.setWordNumber(4);
        words = splitPassword(model.getSeparator(), model.generatePassword());
        assertEquals(4, words.length);

        model.setWordNumber(10);
        words = splitPassword(model.getSeparator(), model.generatePassword());
        assertEquals(10, words.length);
    }

    private String[] splitPassword(char separator, String password) {
        return password.split(
            Pattern.quote(
                    String.valueOf(separator)
            )
        );
    }

}
