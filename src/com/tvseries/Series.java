package com.tvseries;

import java.util.ArrayList;
import java.util.Scanner;

public class Series {
    private ArrayList<SeriesModel> seriesList = new ArrayList<>();
    private Scanner scanner;
    
    public Series() {
        this.scanner = new Scanner(System.in);
    }
    
    // Constructor for testing (allows injecting mock scanner)
    public Series(Scanner scanner) {
        this.scanner = scanner;
    }
    
    // Getter for testing
    public ArrayList<SeriesModel> getSeriesList() {
        return seriesList;
    }
    
    // Add series method for testing
    public void addSeriesForTest(String id, String name, String age, String episodes) {
        SeriesModel newSeries = new SeriesModel(id, name, age, episodes);
        seriesList.add(newSeries);
    }
    
    // Search series method that returns the series for testing
    public SeriesModel searchSeriesById(String id) {
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(id)) {
                return series;
            }
        }
        return null;
    }
    
    // Update series method
    public boolean updateSeries(String id, String newName, String newAge, String newEpisodes) {
        SeriesModel series = searchSeriesById(id);
        if (series != null) {
            if (newName != null) series.setSeriesName(newName);
            if (newAge != null) series.setSeriesAge(newAge);
            if (newEpisodes != null) series.setSeriesNumberOfEpisodes(newEpisodes);
            return true;
        }
        return false;
    }
    
    // Delete series method
    public boolean deleteSeries(String id) {
        SeriesModel series = searchSeriesById(id);
        if (series != null) {
            seriesList.remove(series);
            return true;
        }
        return false;
    }
    
    // Age validation method (public for testing)
    public boolean validateAgeRestriction(String ageInput) {
        try {
            int age = Integer.parseInt(ageInput);
            return age >= 2 && age <= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // Method to run the application
    public void runApplication() {
        displayWelcomeScreen();
    }
    
    private void displayWelcomeScreen() {
        System.out.println("LATEST SERIES - 2025");
        System.out.println("***********************");
        System.out.print("Enter (1) to launch menu or any other key to exit: ");
        
        String input = scanner.nextLine();
        if (input.equals("1")) {
            displayMainMenu();
        } else {
            ExitSeriesApplication();
        }
    }
    
    private void displayMainMenu() {
        while (true) {
            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new series.");
            System.out.println("(2) Search for a series.");
            System.out.println("(3) Update series age restriction");
            System.out.println("(4) Delete a series.");
            System.out.println("(5) Print series report - 2025");
            System.out.println("(6) Exit Application.");
            System.out.print("Your choice: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    CaptureSeries();
                    break;
                case "2":
                    SearchSeries();
                    break;
                case "3":
                    UpdateSeriesAgeRestriction();
                    break;
                case "4":
                    DeleteSeries();
                    break;
                case "5":
                    SeriesReport();
                    break;
                case "6":
                    ExitSeriesApplication();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            
            System.out.print("\nEnter (1) to launch menu or any other key to exit: ");
            String input = scanner.nextLine();
            if (!input.equals("1")) {
                ExitSeriesApplication();
            }
        }
    }
    
    // Capture a new series with validation
    public void CaptureSeries() {
        System.out.println("\nCAPTURE A NEW SERIES");
        System.out.println("***************");
        
        System.out.print("Enter the series id: ");
        String id = scanner.nextLine();
        
        // Check if series ID already exists
        for (SeriesModel series : seriesList) {
            if (series.getSeriesId().equals(id)) {
                System.out.println("Series with this ID already exists!");
                return;
            }
        }
        
        System.out.print("Enter the series name: ");
        String name = scanner.nextLine();
        
        // Validate age restriction
        String age = validateAgeRestrictionInteractive();
        if (age == null) return; // Exit if validation failed
        
        System.out.print("Enter the number of episodes for " + name + ": ");
        String episodes = scanner.nextLine();
        
        // Create new series and add to list
        SeriesModel newSeries = new SeriesModel(id, name, age, episodes);
        seriesList.add(newSeries);
        
        System.out.println("Series processed successfully!!!");
    }
    
    // Interactive age validation for user input
    private String validateAgeRestrictionInteractive() {
        while (true) {
            System.out.print("Enter the series age restriction: ");
            String ageInput = scanner.nextLine();
            
            if (validateAgeRestriction(ageInput)) {
                return ageInput;
            } else {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
            }
        }
    }
    
    // Search for a series by ID (interactive version)
    public void SearchSeries() {
        System.out.println("\nSEARCH FOR A SERIES");
        System.out.println("***************");
        
        if (seriesList.isEmpty()) {
            System.out.println("No series available to search.");
            return;
        }
        
        System.out.print("Enter the series id to search: ");
        String searchId = scanner.nextLine();
        
        SeriesModel series = searchSeriesById(searchId);
        if (series != null) {
            System.out.println("---");
            System.out.println("SERIES ID: " + series.getSeriesId());
            System.out.println("SERIES NAME: " + series.getSeriesName());
            System.out.println("SERIES AGE RESTRICTION: " + series.getSeriesAge());
            System.out.println("SERIES NUMBER OF EPISODES: " + series.getSeriesNumberOfEpisodes());
            System.out.println("---");
        } else {
            System.out.println("---");
            System.out.println("Series with Series Id: " + searchId + " was not found!");
            System.out.println("---");
        }
    }
    
    // Update only the age restriction (interactive)
    public void UpdateSeriesAgeRestriction() {
        System.out.println("\nUPDATE SERIES AGE RESTRICTION");
        System.out.println("***************");
        
        if (seriesList.isEmpty()) {
            System.out.println("No series available to update.");
            return;
        }
        
        System.out.print("Enter series ID to update: ");
        String id = scanner.nextLine();
        
        SeriesModel series = searchSeriesById(id);
        if (series != null) {
            System.out.println("Current age restriction: " + series.getSeriesAge());
            String newAge = validateAgeRestrictionInteractive();
            series.setSeriesAge(newAge);
            System.out.println("Age restriction updated successfully!");
        } else {
            System.out.println("Series not found!");
        }
    }
    
    // Delete a series (interactive)
    public void DeleteSeries() {
        System.out.println("\nDELETE A SERIES");
        System.out.println("***************");
        
        if (seriesList.isEmpty()) {
            System.out.println("No series available to delete.");
            return;
        }
        
        System.out.print("Enter series ID to delete: ");
        String id = scanner.nextLine();
        
        SeriesModel series = searchSeriesById(id);
        if (series != null) {
            seriesList.remove(series);
            System.out.println("Series '" + series.getSeriesName() + "' deleted successfully!");
        } else {
            System.out.println("Series not found!");
        }
    }
    
    // Print series report
    public void SeriesReport() {
        System.out.println("\nSERIES REPORT - 2025");
        System.out.println("***************");
        
        if (seriesList.isEmpty()) {
            System.out.println("No series available to display.");
            return;
        }
        
        for (int i = 0; i < seriesList.size(); i++) {
            SeriesModel series = seriesList.get(i);
            System.out.println("Series " + (i + 1));
            System.out.println("---");
            System.out.println("SERIES ID: " + series.getSeriesId());
            System.out.println("SERIES NAME: " + series.getSeriesName());
            System.out.println("SERIES AGE RESTRICTION: " + series.getSeriesAge());
            System.out.println("NUMBER OF EPISODES: " + series.getSeriesNumberOfEpisodes());
            System.out.println("---");
        }
    }
    
    // Exit the application
    public void ExitSeriesApplication() {
        System.out.println("Exiting application. Goodbye!");
        System.exit(0);
    }
}