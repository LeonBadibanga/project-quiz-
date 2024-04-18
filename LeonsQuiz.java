import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LeonsQuiz {
    private static final String QUESTIONS_FILE_PATH = "questions.txt";
    private static final String ANSWERS_FILE_PATH = "answers.txt";
    private List<String> questionsList = new ArrayList();
    private List<String[]> optionsList = new ArrayList();
    private List<Integer> answersList = new ArrayList();
    private int score = 0;
    private Scanner scanner;

    public LeonsQuiz() {
        this.scanner = new Scanner(System.in);
        this.readQuestionsAndAnswers(QUESTIONS_FILE_PATH, ANSWERS_FILE_PATH);
    }

    public void startQuiz() {
        List<Integer> questionIndexes = new ArrayList();

        int i;
        for(i = 0; i < this.questionsList.size(); ++i) {
            questionIndexes.add(i);
        }

        Collections.shuffle(questionIndexes);

        for(i = 0; i < this.questionsList.size(); ++i) {
            int index = (Integer)questionIndexes.get(i);
            String question = (String)this.questionsList.get(index);
            String[] options = (String[])this.optionsList.get(index);
            System.out.println("Vraag " + (i + 1) + ": " + question);

            int userAnswer;
            for(userAnswer = 0; userAnswer < options.length; ++userAnswer) {
                System.out.println(userAnswer + 1 + ") " + options[userAnswer]);
            }

            userAnswer = this.getUserAnswer(options.length);
            if (userAnswer == (Integer)this.answersList.get(index)) {
                ++this.score;
            }
        }

        double percentage = (double)this.score / (double)this.questionsList.size() * 100.0;
        int var10001 = this.score;
        System.out.println("\nScore: " + var10001 + " / " + this.questionsList.size());
        System.out.println("Percentage: " + percentage + "%");
        if (percentage > 70.0) {
            System.out.println("Gefeliciteerd! Je bent geslaagd.");
        } else {
            System.out.println("Helaas, je bent niet geslaagd.");
        }

    }

    private int getUserAnswer(int maxOptions) {
        int userAnswer;
        do {
            System.out.print("Antwoord (1-" + maxOptions + "): ");

            while(!this.scanner.hasNextInt()) {
                System.out.println("Ongeldige invoer. Voer een nummer in.");
                this.scanner.next();
            }

            userAnswer = this.scanner.nextInt();
        } while(userAnswer < 1 || userAnswer > maxOptions);

        return userAnswer;
    }

    private void readQuestionsAndAnswers(String questions, String answers) {
        try {
            FileReader questionsFileReader = new FileReader(questions);

            try {
                BufferedReader questionsBufferedReader = new BufferedReader(questionsFileReader);

                try {
                    FileReader answersFileReader = new FileReader(answers);

                    try {
                        BufferedReader answersBufferedReader = new BufferedReader(answersFileReader);

                        try {
                            String question;
                            while((question = questionsBufferedReader.readLine()) != null) {
                                this.questionsList.add(question);
                            }

                            String answer;
                            while((answer = answersBufferedReader.readLine()) != null) {
                                String[] parts = answer.trim().split("->");
                                if (parts.length >= 2) {
                                    String[] options = parts[0].split(",");
                                    this.optionsList.add(options);
                                    this.answersList.add(Integer.parseInt(parts[1].trim()));
                                } else {
                                    System.out.println("Ongeldige regel in het antwoordbestand: " + answer);
                                }
                            }
                        } catch (Throwable var13) {
                            try {
                                answersBufferedReader.close();
                            } catch (Throwable var12) {
                                var13.addSuppressed(var12);
                            }

                            throw var13;
                        }

                        answersBufferedReader.close();
                    } catch (Throwable var14) {
                        try {
                            answersFileReader.close();
                        } catch (Throwable var11) {
                            var14.addSuppressed(var11);
                        }

                        throw var14;
                    }

                    answersFileReader.close();
                } catch (Throwable var15) {
                    try {
                        questionsBufferedReader.close();
                    } catch (Throwable var10) {
                        var15.addSuppressed(var10);
                    }

                    throw var15;
                }

                questionsBufferedReader.close();
            } catch (Throwable var16) {
                try {
                    questionsFileReader.close();
                } catch (Throwable var9) {
                    var16.addSuppressed(var9);
                }

                throw var16;
            }

            questionsFileReader.close();
        } catch (IOException var17) {
            IOException e = var17;
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        LeonsQuiz quiz = new LeonsQuiz();
        quiz.startQuiz();
    }
}
