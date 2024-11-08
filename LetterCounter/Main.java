package LetterCounter;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        startProgram();
    }

    public static void startProgram() {
        // запрашиваем у пользователя файл для чтения
        File fileToRead = F_Reader.requestInputFile();
        if (fileToRead == null) {
            System.out.println("программа завершена");
            return;
        }
        // подсчитываем частоту букв в файле
        int[] letterFrequency = F_Reader.calcLetterFrequency(fileToRead);
        if (letterFrequency == null) {
            // если возырвщается null, значит файл пустой или не содержит английских букв
            System.out.println("нет данных для записи результатов");
            System.out.println("программа завершена");
            return;
        }

        F_Writer.outputResults(letterFrequency, fileToRead.getName());
    }
}
