public class PersonDemo {
    public static void main(String[] args) {
        Person withAddress = new Person("Tariro", new Address("12 Samora Machel Ave", "Harare"));
        // chained dot expression: person -> address -> city
        System.out.println(withAddress.name + " lives in " + withAddress.address.city);

        Person noAddress = new Person("Farai", null);
        try {
            System.out.println(noAddress.address.city);   // address is null
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + noAddress.name
                    + " has no address, so address.city cannot be read.");
        }

        // Safe version: check for null first
        String city = (noAddress.address != null) ? noAddress.address.city : "unknown";
        System.out.println("Safe lookup for " + noAddress.name + ": " + city);
    }
}
