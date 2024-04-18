import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LocalFileReader {
    public LocalFileReader() {
    }

    public static void main(String[] args) {
        String questionsFilePath = "./questions.txt";
        String answersFilePath = "./answers.txt";

        try {
            FileReader questionsFileReader = new FileReader(questionsFilePath);

            try {
                BufferedReader questionsBufferedReader = new BufferedReader(questionsFileReader);

                try {
                    FileReader answersFileReader = new FileReader(answersFilePath);

                    try {
                        BufferedReader answersBufferedReader = new BufferedReader(answersFileReader);

                        try {
                            System.out.println("Vragen:");

                            String questionLine;
                            while((questionLine = questionsBufferedReader.readLine()) != null) {
                                System.out.println(questionLine);
                            }

                            System.out.println("\nAntwoorden:");

                            String answerLine;
                            while((answerLine = answersBufferedReader.readLine()) != null) {
                                System.out.println(answerLine);
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
}