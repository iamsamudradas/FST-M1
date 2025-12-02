package activity;

public class Car {
    String color;
    String transmission;
    int make;
    int tyres;
    int doors;

    // Constructor
    public Car() {
        this.tyres = 4;
        this.doors = 4;
    }
    
    public void displayCharacteristics() {
        System.out.println("Color: " + color);
        System.out.println("Transmission: " + transmission);
        System.out.println("Make: " + make);
        System.out.println("Number of tyres: " + tyres);
        System.out.println("Number of doors: " + doors);
    }

    // Method for accelerating
    public void accelarate() {
        System.out.println("Car is moving forward.");
    }

    // Method for braking
    public void brake() {
        System.out.println("Car has stopped.");
    }
}
