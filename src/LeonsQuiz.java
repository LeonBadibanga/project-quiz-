import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LeonsQuiz {
    private static final String QUESTIONS_FILE_PATH = "questions.txt";
    private static final String ANSWERS_FILE_PATH = "answers.txt";

    private List<String> questionsList;
    private List<String[]> optionsList;
    private List<Integer> answersList;

    private int score;
    private Scanner scanner;

    public LeonsQuiz() {
        questionsList = new ArrayList<>();
        optionsList = new ArrayList<>();
        answersList = new ArrayList<>();
        score = 0;
        scanner = new Scanner(System.in);
        readQuestionsAndAnswers();
    }

    public void startQuiz() {
        List<Integer> questionIndexes = new ArrayList<>();
        for (int i = 0; i < questionsList.size(); i++) {
            questionIndexes.add(i);
        }
        Collections.shuffle(questionIndexes);

        for (int i = 0; i < questionsList.size(); i++) {
            int index = questionIndexes.get(i);
            String question = questionsList.get(index);
            String[] options = optionsList.get(index);

            System.out.println("Vraag " + (i + 1) + ": " + question);
            for (int j = 0; j < options.length; j++) {
                System.out.println((j + 1) + ") " + options[j]);
            }

            int userAnswer = getUserAnswer(options.length);

            if (userAnswer == answersList.get(index)) {
                score++;
            }
        }

        double percentage = (double) score / questionsList.size() * 100;

        System.out.println("\nScore: " + score + " / " + questionsList.size());
        System.out.println("Percentage: " + percentage + "%");

        if (percentage > 70) {
            System.out.println("Gefeliciteerd! Je bent geslaagd.");
        } else {
            System.out.println("Helaas, je bent niet geslaagd.");
        }
    }

    private int getUserAnswer(int maxOptions) {
        int userAnswer;
        do {
            System.out.print("Antwoord (1-" + maxOptions + "): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Ongeldige invoer. Voer een nummer in.");
                scanner.next();
            }
            userAnswer = scanner.nextInt();
        } while (userAnswer < 1 || userAnswer > maxOptions);
        return userAnswer;
    }

    private void readQuestionsAndAnswers() {
        try (
                FileReader questionsFileReader = new FileReader(QUESTIONS_FILE_PATH);
                BufferedReader questionsBufferedReader = new BufferedReader(questionsFileReader);
                FileReader answersFileReader = new FileReader(ANSWERS_FILE_PATH);
                BufferedReader answersBufferedReader = new BufferedReader(answersFileReader)
        ) {
            String question;
            while ((question = questionsBufferedReader.readLine()) != null) {
                questionsList.add(question);
            }

            String answer;
            while ((answer = answersBufferedReader.readLine()) != null) {
                String[] parts = answer.trim().split("->");
                if (parts.length >= 2) {
                    String[] options = parts[0].split(",");
                    optionsList.add(options);
                    answersList.add(Integer.parseInt(parts[1].trim()));
                } else {
                    // Voeg een fallback toe voor het geval dat de regel niet correct is opgemaakt
                    System.out.println("Ongeldige regel in het antwoordbestand: " + answer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        LeonsQuiz quiz = new LeonsQuiz();
        quiz.startQuiz();
    }
}