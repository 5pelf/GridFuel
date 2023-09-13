public class Generator {
    private int capacity;
    private int production;

    public Generator(int capacity) {
        this.capacity = capacity;
        this.production = 0;
    }

    public int getProduction() {
        return production;
    }

    public void produce(int amount) {
        production = Math.min(capacity, production + amount);
    }
}
