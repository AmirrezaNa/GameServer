package controller.data;

import controller.gameController.Constants;
import model.Player;

import java.io.*;
import java.util.Scanner;

public class DataManager {
    static File dataFile = new File(Constants.PLAYER_DATA_FILE_PATH);
    Scanner scanner = new Scanner(System.in);

    public static boolean checkPlayerExists(String name) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(dataFile);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length >= 12 && parts[0].equals(name)) {
                fileScanner.close();
                return true;
            }
        }
        fileScanner.close();
        return false;
    }

    public static void loadPlayerData(String name, Player player) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(dataFile);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length >= 12 && parts[0].equals(name)) {
                if (player != null) {
                    player.setName(parts[0]);
                    player.setXP(Integer.parseInt(parts[1]));

                    player.setWritOfAres(Boolean.parseBoolean(parts[2]));
                    player.setWritOfAstrape(Boolean.parseBoolean(parts[3]));
                    player.setWritOfCerberus(Boolean.parseBoolean(parts[4]));

                    player.setWritOfAceso(Boolean.parseBoolean(parts[5]));
                    player.setWritOfMelampus(Boolean.parseBoolean(parts[6]));
                    player.setWritOfChiron(Boolean.parseBoolean(parts[7]));
                    player.setWritOfAthena(Boolean.parseBoolean(parts[8]));

                    player.setWritOfProteus(Boolean.parseBoolean(parts[9]));
                    player.setWritOfEmpusa(Boolean.parseBoolean(parts[10]));
                    player.setWritOfDolus(Boolean.parseBoolean(parts[11]));

                }

            }
        }
    }

    public static void createPlayerData(Player player) throws IOException {
        FileWriter writer = new FileWriter(dataFile, true);
        writer.write(player.getName() + "," + player.getXP() + ","
                + player.isWritOfAres() + "," + player.isWritOfAstrape() + "," + player.isWritOfCerberus() + ","
                + player.isWritOfAceso() + "," + player.isWritOfMelampus() + "," + player.isWritOfChiron() + "," + player.isWritOfAthena() + ","
                + player.isWritOfProteus() + "," + player.isWritOfEmpusa() + "," + player.isWritOfDolus()+ "\n");
        writer.close();
    }

    public static void updatePlayerData(Player player) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(dataFile));
        String line;
        StringBuilder content = new StringBuilder();
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 2 && parts[0].equals(player.getName())) {
                parts[1] = Integer.toString(player.getXP());

                parts[2] = String.valueOf(Boolean.parseBoolean(String.valueOf(player.isWritOfAres())));
                parts[3] = String.valueOf(Boolean.parseBoolean(String.valueOf(player.isWritOfAstrape())));
                parts[4] = String.valueOf(Boolean.parseBoolean(String.valueOf(player.isWritOfCerberus())));

                parts[5] = String.valueOf(Boolean.parseBoolean(String.valueOf(player.isWritOfAceso())));
                parts[6] = String.valueOf(Boolean.parseBoolean(String.valueOf(player.isWritOfMelampus())));
                parts[7] = String.valueOf(Boolean.parseBoolean(String.valueOf(player.isWritOfChiron())));
                parts[8] = String.valueOf(Boolean.parseBoolean(String.valueOf(player.isWritOfAthena())));

                parts[9] = String.valueOf(Boolean.parseBoolean(String.valueOf(player.isWritOfProteus())));
                parts[10] = String.valueOf(Boolean.parseBoolean(String.valueOf(player.isWritOfEmpusa())));
                parts[11] = String.valueOf(Boolean.parseBoolean(String.valueOf(player.isWritOfDolus())));
            }
            content.append(String.join(",", parts)).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile));
        bw.write(content.toString());
        bw.close();
        br.close();
    }
}
