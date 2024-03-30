package util;

import model.SharedData;

import java.io.*;

public class SharedDataFileHandler {
    private static final String FILE_PATH = "shared_data.txt";

    public static SharedData readSharedDataFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (SharedData) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Shared data file not found. Returning null.");
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading shared data from file: " + e.getMessage());
            return null;
        }
    }

    public static void writeSharedDataToFile(SharedData sharedData) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(sharedData);
            System.out.println("Shared data written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing shared data to file: " + e.getMessage());
        }
    }
}
