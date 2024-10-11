package org.example.battle;

import java.io.*;

//import java.util.*;
// File: BattleFileHandler.java
public class BattleFileHandler {
    private String filename;

    public BattleFileHandler(String filename) {
        this.filename = filename;
    }

    public void saveBattle(String battleLog) {
        // Очищуємо файл перед записом нового бою
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) { // Перезапис файлу
            writer.write(battleLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadBattle() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            return "Не вдалося завантажити бій.";
        }
        return sb.toString();
    }
}
