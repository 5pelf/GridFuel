import java.util.Random;
import java.util.Scanner;


class PowerGridSimulator {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        Generator generator = new Generator(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextFloat());
        Random rand = new Random();
        generator.overload();

        for (int hour = 1; hour <= 48; hour++) {
            int outageDuration = rand.nextInt(10); // Random outage duration (0-240 hours)

            System.out.println("Hour " + hour + ": Power grid status");

            if (outageDuration == 0) {
                System.out.println("No outage. Power grid stable.");
            } else {
                System.out.println("Outage detected. Duration: " + outageDuration + " hours.");
                generator.simulateOutage(outageDuration);

                // Implement cost-saving logic here for long-term outages.
                // For this simplified example, we assume no advanced logic.

                if (hour < 48) {
                    generator.refillFuel(); // Refill generator fuel daily
                }
            }

            System.out.println();
        }
    }
}