public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        Car car1 = new Car("C0001", "ABC", "ss", 60.0);
        Car car2 = new Car("C0001", "ABC", "ss", 60.0);

        rentalSystem.addCar(car1);

        rentalSystem.menu();
    }
}