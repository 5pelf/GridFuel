import java.util.Random;
class Generator {
    private boolean isRunning;
    private int startupDelay;
    private int fuelLevel;

    private int fuel_per_Hour;
    public Generator() {
        this.isRunning = false;
        this.startupDelay = 3; // Default startup delay in hours
        this.fuelLevel = 100; // Generator starts with a full tank
        this.fuel_per_Hour = 5;
    }

    public void start() {
        isRunning = true;
        System.out.println("Generator started.");
    }

    public void stop() {
        isRunning = false;
        System.out.println("Generator stopped.");
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void simulateOutage(int duration) {
        // Simulate generator running during an outage
        if (fuelLevel > 0) {
            start();
            for (int i = 0; i < duration; i++) {
                System.out.println("Generator is running. Hour " + (i + 1));
                fuelLevel-=fuel_per_Hour;
            }
            stop();
        } else {
            System.out.println("Generator out of fuel. Cannot start.");
        }
    }

    public void refillFuel() {
        // Simulate refilling generator fuel
        fuelLevel = 100;
        System.out.println("Generator fuel refilled.");
    }
}

class PowerGridSimulator {
    public static void main(String[] args) {
        Generator generator = new Generator();
        Random rand = new Random();

        for (int hour = 1; hour <= 24; hour++) {
            int outageDuration = rand.nextInt(3); // Random outage duration (0-2 hours)

            System.out.println("Hour " + hour + ": Power grid status");

            if (outageDuration == 0) {
                System.out.println("No outage. Power grid stable.");
            } else {
                System.out.println("Outage detected. Duration: " + outageDuration + " hours.");
                generator.simulateOutage(outageDuration);

                // Implement cost-saving logic here for long-term outages.
                // For this simplified example, we assume no advanced logic.

                if (hour < 24) {
                    generator.refillFuel(); // Refill generator fuel daily
                }
            }

            System.out.println();
        }
    }
}