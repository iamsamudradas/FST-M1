package activity;

public class activity3 {
    public static void main(String[] args) {

        // Age in seconds
        double ageInSeconds = 1000000000.0;

        // One Earth year in seconds
        double earthYearSeconds = 31557600.0;

        // Calculate age on Earth
        double ageOnEarth = ageInSeconds / earthYearSeconds;

        // Calculate age on other planets (using their orbital period in Earth years)
        double ageOnMercury = ageOnEarth / 0.2408467;
        double ageOnVenus   = ageOnEarth / 0.61519726;
        double ageOnMars    = ageOnEarth / 1.8808158;
        double ageOnJupiter = ageOnEarth / 11.862615;
        double ageOnSaturn  = ageOnEarth / 29.447498;
        double ageOnUranus  = ageOnEarth / 84.016846;
        double ageOnNeptune = ageOnEarth / 164.79132;

        // Print results rounded to 2 decimal places
        System.out.printf("Age on Earth: %.2f years%n", ageOnEarth);
        System.out.printf("Age on Mercury: %.2f years%n", ageOnMercury);
        System.out.printf("Age on Venus: %.2f years%n", ageOnVenus);
        System.out.printf("Age on Mars: %.2f years%n", ageOnMars);
        System.out.printf("Age on Jupiter: %.2f years%n", ageOnJupiter);
        System.out.printf("Age on Saturn: %.2f years%n", ageOnSaturn);
        System.out.printf("Age on Uranus: %.2f years%n", ageOnUranus);
        System.out.printf("Age on Neptune: %.2f years%n", ageOnNeptune);
    }
}
