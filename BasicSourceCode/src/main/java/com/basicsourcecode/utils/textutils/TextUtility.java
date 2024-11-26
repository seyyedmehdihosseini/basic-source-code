package com.basicsourcecode.utils.textutils;

public final class TextUtility {

    /**
     * @param text The text for convert to capitalize.
     * @EN Every word should start with a capital letter.
     * @FA هر کلمه با حرف بزرگ شروع شود.
     */
    public static String toCapitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        String[] words = splitWords(text);
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            result.append(capitalizeWord(word)).append(" ");
        }

        return result.toString().trim(); // حذف فاصله اضافی انتهایی
    }

    /**
     * @param text The text for convert to camel case.
     * @EN Connect the words and start each new word with a capital letter.
     * @FA کلمات به هم متصل شوند و هر کلمه جدید با حرف بزرگ شروع شود
     */
    public static String toCamelCase(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        // رشته را به کلمات جدا شده با فاصله یا کاراکترهای خاص تقسیم کنید
        String[] words = text.split("[\\s_\\-]+");
        StringBuilder camelCaseString = new StringBuilder();

        // اولین کلمه را با حروف کوچک به نتیجه اضافه کنید
        camelCaseString.append(words[0].toLowerCase());

        // بقیه کلمات را به صورت Capitalize به رشته اضافه کنید
        for (int i = 1; i < words.length; i++) {
            camelCaseString.append(capitalizeWord(words[i]));
        }

        return camelCaseString.toString();
    }

    /**
     * @param text The text for convert to camel case.
     * @EN Connect the words and start each new word with a capital letter
     * @FA کلمات به هم متصل شوند و هر کلمه جدید با حرف بزرگ شروع شود
     */
    public static String titleToCamelCase(String text) {
        if (text == null || text.isEmpty()) return text;
        String[] words = splitWords(text);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            result.append(i == 0 ? word.toLowerCase() : capitalizeWord(word));
        }

        return result.toString();
    }

    /**
     * @param text The name entity class or name model.
     * @EN Words are separated by "_" and all letters are lowercase.
     * @FA کلمات با "_" جدا شوند و همه حروف کوچک شوند.
     */
    public static String toSnakeCase(String text) {
        if (text == null || text.isEmpty()) return text;
        String[] words = splitWords(text);
        return String.join("_", words).toLowerCase();
    }

    /*
    protected static String toSnakeCase(String nameEntityClass) {
        return nameEntityClass.replaceAll("([A-Z])([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z])([A-Z])", "$1_$2");
    }
*/

    /**
     * @param text The text entered method
     * @EN Helper method to break string into words
     * @FA متد کمکی برای شکستن رشته به کلمات
     */
    private static String[] splitWords(String text) {
        return text.trim().split("\\s+");
    }

    /**
     * @param text The text entered to method.
     * @EN An auxiliary method for capitalizing a word: the first letter is capitalized, the rest are small.
     * @FA متد کمکی برای Capitalizing یک کلمه: حرف اول بزرگ، باقی کوچک.
     */
    private static String capitalizeWord(String text) {
        if (text == null || text.isEmpty()) return "";
        return Character.toUpperCase(text.charAt(0)) + text.substring(1).toLowerCase();
    }

}
