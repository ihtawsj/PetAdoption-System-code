import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Pet {
    private static int nextId = 1;
    private int id;
    private String name;
    private String species;
    private String breed;
    private String vet;
    private boolean isVaccinated;
    private int price;

    public Pet(String name, String species, String breed, String vet, boolean isVaccinated, int price) {
        this.id = nextId++;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.vet = vet;
        this.isVaccinated = isVaccinated;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public String getVet() {
        return vet;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public int getPrice() {
        return price;
    }

    public void displayPetDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Species: " + species);
        System.out.println("Breed: " + breed);
        System.out.println("Vet: " + vet);
        System.out.println("Vaccinated: " + (isVaccinated ? "Yes" : "No"));
        System.out.println("Price: $" + price);
    }
}

class Dog extends Pet {
    public Dog(String name, String breed, String vet, boolean isVaccinated, int price) {
        super(name, "Dog", breed, vet, isVaccinated, price);
    }
}

class Cat extends Pet {
    public Cat(String name, String breed, String vet, boolean isVaccinated, int price) {
        super(name, "Cat", breed, vet, isVaccinated, price);
    }
}

class Bird extends Pet {
    public Bird(String name, String breed, String vet, boolean isVaccinated, int price) {
        super(name, "Bird", breed, vet, isVaccinated, price);
    }
}

class Shelter {
    private String name;
    private List<Pet> petsAvailable;

    public Shelter(String name) {
        this.name = name;
        this.petsAvailable = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addPet(Pet pet) {
        petsAvailable.add(pet);
    }

    public void removePet(Pet pet) {
        petsAvailable.remove(pet);
    }

    public void displayAvailablePets(String species) {
        System.out.println("Available " + species + "s in " + name + ":");
        for (Pet pet : petsAvailable) {
            if (pet.getSpecies().equalsIgnoreCase(species)) {
                System.out.println("ID: " + pet.getId() + " - " + pet.getName() + " - " + pet.getBreed() + " ($" + pet.getPrice() + ")");
            }
        }
    }

    public Pet getPetDetails(int id) {
        for (Pet pet : petsAvailable) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        return null;
    }
}

public class PetAdoptionSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Shelter shelter = new Shelter("Happy Tails Shelter");

        shelter.addPet(new Dog("Buddy", "Golden Retriever", "Dr. Smith", true, 23000));
        shelter.addPet(new Dog("Max", "German Shepherd", "Dr. Johnson", false, 10000));
        shelter.addPet(new Cat("Whiskers", "Siamese", "Dr. Lee", true, 15000));
        shelter.addPet(new Cat("Fluffy", "Persian", "Dr. Patel", false, 12000));
        shelter.addPet(new Bird("Polly", "Parrot", "Dr. Garcia", true, 14000));

        System.out.println("Welcome to " + shelter.getName());

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. View available pets");
            System.out.println("2. Add a new pet");
            System.out.println("3. Adopt a pet");
            System.out.println("4. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (option) {
                case 1:
                    System.out.println("Choose a species:");
                    System.out.println("1. Dog");
                    System.out.println("2. Cat");
                    System.out.println("3. Bird");
                    int speciesChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    String species = "";
                    switch (speciesChoice) {
                        case 1:
                            species = "Dog";
                            break;
                        case 2:
                            species = "Cat";
                            break;
                        case 3:
                            species = "Bird";
                            break;
                        default:
                            System.out.println("Invalid choice");
                            continue; // Restart the loop to ask for species choice again
                    }
                    shelter.displayAvailablePets(species);
                    break;
                case 2:
                    System.out.println("Enter pet details:");
                    System.out.print("Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Species: ");
                    String newSpecies = scanner.nextLine();
                    System.out.print("Breed: ");
                    String newBreed = scanner.nextLine();
                    System.out.print("Vet: ");
                    String newVet = scanner.nextLine();
                    System.out.print("Is Vaccinated? (true/false): ");
                    boolean newIsVaccinated = scanner.nextBoolean();
                    System.out.print("Price: ");
                    int newPrice = scanner.nextInt();
                    Pet newPet;
                    switch (newSpecies.toLowerCase()) {
                        case "dog":
                            newPet = new Dog(newName, newBreed, newVet, newIsVaccinated, newPrice);
                            break;
                        case "cat":
                            newPet = new Cat(newName, newBreed, newVet, newIsVaccinated, newPrice);
                            break;
                        case "bird":
                            newPet = new Bird(newName, newBreed, newVet, newIsVaccinated, newPrice);
                            break;
                        default:
                            System.out.println("Invalid species");
                            continue; // Restart the loop to ask for pet details again
                    }
                    shelter.addPet(newPet);
                    System.out.println(newSpecies + " added successfully");
                    break;
                case 3:
                    System.out.print("Enter the ID of the pet you want to adopt: ");
                    int petId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Pet selectedPet = shelter.getPetDetails(petId);
                    if (selectedPet != null) {
                        System.out.println("Pet details:");
                        selectedPet.displayPetDetails();
                        System.out.println("Do you want to adopt this pet? (yes/no)");
                        String adoptChoice = scanner.nextLine();
                        if (adoptChoice.equalsIgnoreCase("yes")) {
                            shelter.removePet(selectedPet);
                            System.out.println("Congratulations! You've adopted " + selectedPet.getName());
                        }
                    } else {
                        System.out.println("Pet not found with ID: " + petId);
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
