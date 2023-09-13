public class DieselGenerator {
    private int capacity;
    private int production;
    private int dieselCapacity;
    private int dieselConsumptionRate;

    public DieselGenerator(int capacity, int dieselCapacity, int dieselConsumptionRate) {
        this.capacity = capacity;
        this.production = 0;
        this.dieselCapacity = dieselCapacity;
        this.dieselConsumptionRate = dieselConsumptionRate;
    }
    public void consumeDiesel() {
        int consumption = Math.min(dieselCapacity, dieselConsumptionRate);
        production -= consumption;
        dieselCapacity -= consumption;
    }

    public int getDieselCapacity() {
        return dieselCapacity;
    }
}
