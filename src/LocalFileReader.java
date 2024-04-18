import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LocalFileReader {
    public static void main(String[] args) {
        // Het pad naar de tekstbestanden die moeten worden gelezen.
        String questionsFilePath = "questions.txt";
        String answersFilePath = "answers.txt";

        // Bestanden lezen met try-with-resources om automatisch resources te sluiten.
        try (
                // Bestand voor vragen lezen.
                FileReader questionsFileReader = new FileReader(questionsFilePath);
                BufferedReader questionsBufferedReader = new BufferedReader(questionsFileReader);
                // Bestand voor antwoorden lezen.
                FileReader answersFileReader = new FileReader(answersFilePath);
                BufferedReader answersBufferedReader = new BufferedReader(answersFileReader)
        ) {
            // Vragen op het scherm weergeven.
            System.out.println("Vragen:");
            String questionLine;
            while ((questionLine = questionsBufferedReader.readLine()) != null) {
                System.out.println(questionLine); // Toon elke regel op het scherm.
            }

            // Antwoorden op het scherm weergeven.
            System.out.println("\nAntwoorden:");
            String answerLine;
            while ((answerLine = answersBufferedReader.readLine()) != null) {
                System.out.println(answerLine); // Toon elke regel op het scherm.
            }

        } catch (IOException e) {
            e.printStackTrace(); //(debug)
        }
    }
}