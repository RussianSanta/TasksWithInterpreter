package SecondTask;

import helpers.FileHelper;

public class Answerer {
    public static void answer() {
        String outputText = FileHelper.getTextFromFile("OUTPUT.txt");
        String logsText = FileHelper.getTextFromFile("LOGS.txt");
        String resultMessage = outputText.split(" - ")[2];
        StringBuilder answers = new StringBuilder();

        answers.append("1. ").append(getCount(resultMessage, "u"));
        answers.append("\n2. ").append(getCount(resultMessage, "uvv"));
        answers.append("\n3. ").append(getCount(getSelectedIterations(logsText,"шаг - 1"), "u"));
        answers.append("\n4. ").append(getCount(getSelectedIterations(logsText, "u"), "Итерация - 10")).append(",")
                .append(getCount(getSelectedIterations(logsText,"u"), "Итерация - 20")).append(",")
                .append(getCount(getSelectedIterations(logsText,"u"), "Итерация - 30"));
        answers.append("\n5. ").append(getMaxCount(resultMessage,'u'));
        answers.append("\n6. ").append(getSelectedIterations(getSelectedIterations(logsText,"v"),"шаг - 2")
        .split("\\.")[0]);
        answers.append("\n7. ").append(sevenQuestion(logsText));
        answers.append("\n8. ").append("u ").append(getCount(resultMessage, "u"))
                .append(".v ").append(getCount(resultMessage, "v"))
                .append(".h ").append(getCount(resultMessage, "h"))
                .append(".p ").append(getCount(resultMessage, "p"))
                .append(".o ").append(getCount(resultMessage, "o"));

        System.out.println(answers);
    }

    private static int getCount(String text, String substring) {
        if (text.contains(substring)) {
            String newString = text.replace(substring,"");
            return (text.length()-newString.length())/substring.length();
        } else return 0;
    }

    private static String getSelectedIterations(String text, String substring) {
        String[] iterations = text.split(";");
        String result = "";
        for (String iteration:iterations) {
            if (iteration.contains(substring)) result += iteration + "\n";
        }
        return result;
    }

    private static int getMaxCount(String text, char letter) {
        if (text.contains(String.valueOf(letter))) {
            int max = 0;
            int count = 0;
            for (char a:text.toCharArray()) {
                if (a == letter) {
                    count++;
                    if (count>max) max = count;
                } else count = 0;
            }
            return max;
        } else return 0;
    }

    private static String sevenQuestion(String text) {
        boolean isTrue = text.contains("шаг - 1") && text.contains("шаг - 2") && text.contains("шаг - 3")
                && text.contains("шаг - 4") && text.contains("u") && text.contains("v") && text.contains("h")
                && text.contains("o")&& text.contains("p");
        if (isTrue) return "Да";
        else return "Нет";
    }
}
