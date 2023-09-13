import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class PowerGrid {
    private List<Generator> generators;
    private List<Consumer> consumers;
    private List<DieselGenerator> dieselGenerators;
    private boolean isOutage = false;
    private int outageDuration;

    public PowerGrid() {
        generators = new ArrayList<>();
        consumers = new ArrayList<>();
        dieselGenerators = new ArrayList<>();
    }

    public void addGenerator(Generator generator) {
        generators.add(generator);
    }

    public void addConsumer(Consumer consumer) {
        consumers.add(consumer);
    }

    public void addDieselGenerator(DieselGenerator dieselGenerator) {
        dieselGenerators.add(dieselGenerator);

    }

    public void distributeElectricity() {
        int totalProduction = generators.stream().mapToInt(Generator::getProduction).sum();
        int totalConsumption = consumers.stream().mapToInt(Consumer::getConsumption).sum();

        if (!isOutage) {
            if (totalProduction >= totalConsumption) {
                System.out.println("Electricity supplied!");
                for (Consumer consumer : consumers) {
                    int payment = totalConsumption * 250; // $250 per MWh
                    consumer.pay(payment);
                }
            }
        } else {
            System.out.println("Outage in progress. Main power is offline.");

            for (int hour = 0; hour <= outageDuration; hour++) {
                int totalConsumerPayments = 0;

                System.out.print("Hour " + hour + " : ");
                for (Consumer consumer : consumers) {
                    int payment = consumer.getConsumption() * 275; // $275 per MWh during outage
                    totalConsumerPayments += payment;
                    System.out.print("Supply to " + consumer.toString() + ": " + consumer.getConsumption() + " ");
                }

                int totalDieselCost = dieselGenerators.stream().mapToInt(DieselGenerator::getDieselCapacity).sum();
                System.out.println("Diesel left: " + totalDieselCost);
                for(DieselGenerator obj : dieselGenerators){
                    obj.consumeDiesel();
                }
                if(totalDieselCost ==0)break;

                if (hour < outageDuration) {
                    for (Consumer consumer : consumers) {
                        consumer.pay(totalConsumerPayments);
                    }
                }
            }

            isOutage = false;
            System.out.println("Main power is back online.");
        }
    }
    private void triggerOutage() {
        Random random = new Random();
        outageDuration = random.nextInt(25); // Random outage duration from 0 to 24 hours
        isOutage = true;
        System.out.println("Main power outage. Duration: " + outageDuration + " hours.");
    }

    public int calculateRevenue() {
        int totalConsumerPayments = consumers.stream().mapToInt(Consumer::getPayment).sum();
        int totalDieselCost = dieselGenerators.stream().mapToInt(DieselGenerator::getDieselCapacity).sum() * 5; // $5 per gallon

        return totalConsumerPayments - totalDieselCost;
    }

    public static void main(String[] args) {
        Generator generator1 = new Generator(100);
        Generator generator2 = new Generator(50);
        DieselGenerator dieselGenerator = new DieselGenerator(30, 10000, 1400);

        Consumer consumer1 = new Consumer(30);
        Consumer consumer2 = new Consumer(40);

        PowerGrid powerGrid = new PowerGrid();
        powerGrid.addGenerator(generator1);
        powerGrid.addGenerator(generator2);
        powerGrid.addDieselGenerator(dieselGenerator);
        powerGrid.addConsumer(consumer1);
        powerGrid.addConsumer(consumer2);

        // Simulate electricity production and consumption
        generator1.produce(80);
        generator2.produce(40);
        consumer1.consume(20);
        consumer2.consume(30);


        powerGrid.triggerOutage();
        // Distribute electricity and check for shortages
        powerGrid.distributeElectricity();

        // Print consumer payments during and after the outage
        System.out.println("Consumer 1 Payment: $" + consumer1.getPayment());
        System.out.println("Consumer 2 Payment: $" + consumer2.getPayment());

        // Calculate and print revenue
        int revenue = powerGrid.calculateRevenue();
        System.out.println("Total Revenue: $" + revenue);
    }
}
