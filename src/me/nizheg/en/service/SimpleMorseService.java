package me.nizheg.en.service;

import java.util.HashMap;
import java.util.Map;

public class SimpleMorseService {

    Map<String, Character> russianAlphabet = new HashMap<String, Character>();
    Map<String, Character> englishAlphabet = new HashMap<String, Character>();
    Map<String, Character> numbers = new HashMap<String, Character>();
    Map<String, Character> symbols = new HashMap<String, Character>();

    public SimpleMorseService() {
        russianAlphabet.put("*-", 'а');
        russianAlphabet.put("-***", 'б');
        russianAlphabet.put("*--", 'в');
        russianAlphabet.put("--*", 'г');
        russianAlphabet.put("-**", 'д');
        russianAlphabet.put("*", 'е');
        russianAlphabet.put("***-", 'ж');
        russianAlphabet.put("--**", 'з');
        russianAlphabet.put("**", 'и');
        russianAlphabet.put("*---", 'й');
        russianAlphabet.put("-*-", 'к');
        russianAlphabet.put("*-**", 'л');
        russianAlphabet.put("--", 'м');
        russianAlphabet.put("-*", 'н');
        russianAlphabet.put("---", 'о');
        russianAlphabet.put("*--*", 'п');
        russianAlphabet.put("*-*", 'р');
        russianAlphabet.put("***", 'с');
        russianAlphabet.put("-", 'т');
        russianAlphabet.put("**-", 'у');
        russianAlphabet.put("**-*", 'ф');
        russianAlphabet.put("****", 'х');
        russianAlphabet.put("-*-*", 'ц');
        russianAlphabet.put("---*", 'ч');
        russianAlphabet.put("----", 'ш');
        russianAlphabet.put("--*-", 'щ');
        russianAlphabet.put("--*--", 'ъ');
        russianAlphabet.put("-*--", 'ы');
        russianAlphabet.put("-**-", 'ь');
        russianAlphabet.put("**-**", 'э');
        russianAlphabet.put("**--", 'ю');
        russianAlphabet.put("*-*-", 'я');

        englishAlphabet.put("*-", 'a');
        englishAlphabet.put("-***", 'b');
        englishAlphabet.put("-*-*", 'c');
        englishAlphabet.put("-**", 'd');
        englishAlphabet.put("*", 'e');
        englishAlphabet.put("**-*", 'f');
        englishAlphabet.put("--*", 'g');
        englishAlphabet.put("****", 'h');
        englishAlphabet.put("**", 'i');
        englishAlphabet.put("*---", 'j');
        englishAlphabet.put("-*-", 'k');
        englishAlphabet.put("*-**", 'l');
        englishAlphabet.put("--", 'm');
        englishAlphabet.put("-*", 'n');
        englishAlphabet.put("---", 'o');
        englishAlphabet.put("*--*", 'p');
        englishAlphabet.put("--*-", 'q');
        englishAlphabet.put("*-*", 'r');
        englishAlphabet.put("***", 's');
        englishAlphabet.put("-", 't');
        englishAlphabet.put("**-", 'u');
        englishAlphabet.put("***-", 'v');
        englishAlphabet.put("*--", 'w');
        englishAlphabet.put("-**-", 'x');
        englishAlphabet.put("-*--", 'y');
        englishAlphabet.put("--**", 'z');

        numbers.put("*----", '1');
        numbers.put("**---", '2');
        numbers.put("***--", '3');
        numbers.put("****-", '4');
        numbers.put("*****", '5');
        numbers.put("-****", '6');
        numbers.put("--***", '7');
        numbers.put("---**", '8');
        numbers.put("----*", '9');
        numbers.put("-----", '0');

        symbols.put("******", '.');
        symbols.put("*-*-*-", ',');
        symbols.put("---***", ':');
        symbols.put("-*-*-*", ';');
        symbols.put("-*--*-", '(');
        symbols.put("*----*", '\'');
        symbols.put("*-**-*", '«');
        symbols.put("-****-", '-');
        symbols.put("-**-*", '/');
        symbols.put("**--**", '?');
        symbols.put("--**--", '!');
    }

    public Character getRussianLetter(String value) {
        return russianAlphabet.get(value);
    }

    public Character getEnglishLetter(String value) {
        return englishAlphabet.get(value);
    }

    public Character getNumber(String value) {
        return numbers.get(value);
    }

    public Character getSymbol(String value) {
        return symbols.get(value);
    }

    public String parseRussianString(String input) {
        input = input.trim();
        if (input.length() == 0) {
            return "";
        }
        String[] parts = input.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            if (russianAlphabet.containsKey(part)) {
                result.append(getRussianLetter(part));
            } else if (symbols.containsKey(part)) {
                result.append(getSymbol(part));
            } else if (numbers.containsKey(part)) {
                result.append(getNumber(part));
            } else {
                result.append("#");
            }
        }
        return result.toString();
    }

    public String parseEnglishString(String input) {
        input = input.trim();
        if (input.length() == 0) {
            return "";
        }
        String[] parts = input.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            if (englishAlphabet.containsKey(part)) {
                result.append(getEnglishLetter(part));
            } else if (symbols.containsKey(part)) {
                result.append(getSymbol(part));
            } else if (numbers.containsKey(part)) {
                result.append(getNumber(part));
            } else {
                result.append("#");
            }
        }
        return result.toString();
    }

    public String invert(String in) {
        StringBuilder result = new StringBuilder();
        for (char ch : in.toCharArray()) {
            if (ch == '*') {
                result.append("-");
            } else if (ch == '-') {
                result.append("*");
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}
