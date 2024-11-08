package LetterCounter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class F_Writer {
    // метод для записи результатов в выходной файл
    public static void outputResults(int[] frequency, String origFileName) {

        File resultFile = prepareOutputFile(origFileName);
        if (resultFile == null) {
            System.out.println("программа завершена");
            return;
        }

        // записываем результаты в файл
        try (FileWriter writer = new FileWriter(resultFile)) {
            // обработка букв A-Z
            char letter = 'A';
            for (int index = 0; index < 26; index++, letter++) {
                int count = frequency[index];
                // пропускаем буквы с нулевой частотой
                if (count == 0) {
                    continue;
                }
                writer.write(letter + ": " + count + System.lineSeparator());
            }

            // обработка букв a-z
            letter = 'a';
            for (int index = 26; index < 52; index++, letter++) {
                int count = frequency[index];
                // пропускаем буквы с нулевой частотой
                if (count == 0) {
                    continue;
                }
                writer.write(letter + ": " + count + System.lineSeparator());
            }

            System.out.println("результаты записаны в файл: " + resultFile.getPath());
        } catch (IOException e) {
            System.out.println("ошибка при записи результатов: " + e.getMessage());
        }
    }

    // метод для подготовки выходного файла
    private static File prepareOutputFile(String origFileName) {
        File outputFile = new File("outputFiles/result_" + origFileName);

        // проверка: существует ли директория 'outputFiles'
        if (!outputFile.getParentFile().exists()) {
            System.out.println("директория 'outputFiles' не найдена");
            System.out.println("пожалуйста, проверьте, её расположение");
            return null;
        }

        // проверка: права доступа на запись в директорию
        if (!outputFile.getParentFile().canWrite()) {
            System.out.println("недостаточно прав для записи в директорию 'outputFiles'");
            System.out.println("пожалуйста, проверьте права доступа к директории");
            return null;
        }

        return outputFile;
    }
}
