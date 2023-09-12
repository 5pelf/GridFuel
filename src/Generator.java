class Generator {
    private boolean isRunning;
    private int startupDelay;
    private int generator_power;
    private int fuelLevel;

    private float fuel_on_full_load;
    public Generator(int D, int GP, int FL, float FoFL) {
        this.isRunning = false;
        this.startupDelay = D; // Default startup delay in hours
        this.generator_power = GP; // measure is kW
        this.fuelLevel = FL; // Generator starts with a full tank in US gallons
        this.fuel_on_full_load = FoFL; // gall/hour
    }

    public void start() {
        isRunning = true;
        System.out.println("Generator started.");
    }

    public void stop() {
        isRunning = false;
        System.out.println("Generator stopped.");
    }
    public void overload() {
        UPS ups = new UPS(200,100,300, 1f);
        int over = generator_power - ups.load;
        if(over < 0)fuel_on_full_load = fuel_on_full_load + ((over*-1)%2);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void simulateOutage(int duration) {
        // Simulate generator running during an outage
        if (fuelLevel > 0) {
            start();
            for (int i = 0; i < duration; i++) {
                System.out.println("Generator is running on full load. Hour " + (i + 1));
                fuelLevel-=fuel_on_full_load;
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