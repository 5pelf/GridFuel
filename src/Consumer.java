public class Consumer {
    private int consumption;
    private int payment;

    public Consumer(int consumption) {
        this.consumption = consumption;
        this.payment = 0;
    }

    public int getConsumption() {
        return consumption;
    }

    public void consume(int amount) {
        consumption = Math.min(consumption, amount);
    }

    public void pay(int amount) {
        payment += amount;
    }

    public int getPayment() {
        return payment;
    }
}
