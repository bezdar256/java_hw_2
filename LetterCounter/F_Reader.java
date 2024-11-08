package LetterCounter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;;

public class F_Reader {
    // метод для запроса имени входного файла и проверки его существования, доступности
    public static File requestInputFile() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя текстового файла, который нужно проанализировать: ");
        String inputFileName = scanner.nextLine();

        File inputFile = new File("inputFiles/" + inputFileName);

        // проверка: существует ли директория 'inputFiles'
        if (!inputFile.getParentFile().exists()) {
            System.out.println("директория 'inputFiles' не найдена");
            System.out.println("пожалуйста, проверьте, её расположение");
            return null;
        }

        // проверка: существует ли файл
        if (!inputFile.exists()) {
            System.out.println("файл '" + inputFileName + "' не существует");
            System.out.println("пожалуйста, проверьте имя файла и его расположение в директории 'inputFiles'");
            return null;
        }

        // проверка: является ли он файлом, а не директорией
        if (!inputFile.isFile()) {
            System.out.println("'" + inputFileName + "' не является файлом");
            System.out.println("пожалуйста, укажите корректный файл в директории 'inputFiles'");
            return null;
        }

        // проверка: файл имеет расширение '.txt'
        if (!inputFileName.endsWith(".txt")) {
            System.out.println("файл '" + inputFileName + "' не имеет расширения '.txt'");
            System.out.println("пожалуйста, укажите текстовый файл с расширением '.txt'");
            return null;
        }

        // проверка: права доступа на чтение файла
        if (!inputFile.canRead()) {
            System.out.println("У Вас недостаточно прав для чтения файла '" + inputFileName + "'");
            System.out.println("пожалуйста, измените права доступа к файлу.");
            return null;
        }

        System.out.println("Файл '" + inputFileName + "' найден. Идёт анализ...");
        return inputFile;
    }

      // метод для подсчета частоты встречи букв в файле
      public static int[] calcLetterFrequency(File file) {
        int[] frequency = new int[52]; // массив для хранения частоты букв (A-Z и a-z)

        try {
            // считываем файл построчно с UTF-8
            for (String line : Files.readAllLines(file.toPath(), StandardCharsets.UTF_8)) {
                for (int i = 0; i < line.length(); i++) {
                    char character = line.charAt(i);

                    if (character >= 'A' && character <= 'Z') {
                        frequency[character - 'A']++;
                    }
                    else if (character >= 'a' && character <= 'z') {
                        frequency[26 + character - 'a']++;
                    }
                }
            }

            // проверка: есть ли какие-либо буквы в файле
            boolean presenceOfLetters = false;
            for (int count : frequency) {
                if (count > 0) {
                    presenceOfLetters = true;
                    break;
                }
            }

            if (!presenceOfLetters) {
                System.out.println("в файле нет букв английского алфавита");
                return null;
            }

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return null;
        }

        return frequency;
    }
}