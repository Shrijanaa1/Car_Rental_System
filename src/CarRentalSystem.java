import java.util.ArrayList;
import java.util.Scanner;

class CarRentalSystem {
    private ArrayList<Car> cars;
    private ArrayList<Customer> customers;
    private ArrayList<Rental> rentals;

    public CarRentalSystem(){
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days){
        if(car.isAvailable()){
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            System.out.println("Sorry! Car isn't available for rent. :(");
        }
    }

    public void returnCar(Car car){
        Rental rentalToRemove = null;
        for(Rental rental: rentals){
            if(rental.getCar() == car){
                rentalToRemove = rental;
                break;
            }
        }

        if(rentalToRemove != null){
            rentals.remove(rentalToRemove);
        }else {
            System.out.println("Car was not rented.");
        }
        car.returnCar();
    }

    public void menu(){
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Welcome to our CAR RENTAL SYSTEM ;) ");
            System.out.println("1. Rent a car ");
            System.out.println("2. Return a car ");
            System.out.println("3. Exit ");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 1){
                System.out.println(" \n ***** Rent a CAR ***** ");
                System.out.println(" Please enter your name: ");
                String customerName = scanner.nextLine();

                System.out.println(" \n Cars we have: ");
                for(Car car: cars){
                    if(car.isAvailable()){
                        System.out.println(car.getCarId() + "--" + car.getCarBrand()+ "--" + car.getCarModel());
                    }
                }

                System.out.println(" \n Enter the carId you want to rent: ");
                String carId = scanner.nextLine();

                System.out.println("Enter the number of days you want to rent car: ");
                int noOfRentalDays = scanner.nextInt();
                scanner.nextLine(); //print new line

                Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
                addCustomer(newCustomer);

                Car selectedCar = null;
                for(Car car: cars){
                    if(car.getCarId(). equals(carId) && car.isAvailable()){
                        selectedCar = car;
                        break;
                    }
                }

                if(selectedCar != null){
                    double totalAmount = selectedCar.calculatePrice(noOfRentalDays);
                    System.out.println(" \n ***** Rental Information ***** \n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getCustomerName());
                    System.out.println("Car: " + selectedCar.getCarBrand() + " " + selectedCar.getCarModel());
                    System.out.println("Rental Days: " + noOfRentalDays);
                    System.out.println("Total amount: " + totalAmount);

                    System.out.println(" \n Do you want to confirm rental (Yes/No): ");
                    String confirm = scanner.nextLine();

                    if(confirm.equalsIgnoreCase("Yes")){
                        rentCar(selectedCar, newCustomer, noOfRentalDays);
                        System.out.println("\n Car rented successfully.");
                    }else {
                        System.out.println("\n Sorry rental is cancelled.");
                    }
                }else {
                    System.out.println("\n Either car is not available or invalid car selection. ");
                }

            }else if (choice == 2){
                System.out.println("\n ***** Return a car ***** \n ");
                System.out.println("Enter the car ID you want to return: ");
                String carId = scanner.nextLine();

                Car carToReturn = null;
                for (Car car: cars){
                    if(car.getCarId().equals(carId) && !car.isAvailable()){
                        carToReturn = car;
                        break;
                    }
                }

                if(carToReturn != null){
                    Customer customer = null;
                    for(Rental rental: rentals){
                        if(rental.getCar() == carToReturn){
                            customer = rental.getCustomer();
                            break;
                        }
                    }

                    if(customer != null){
                        returnCar(carToReturn);
                        System.out.println("Car returned successfully by" + customer.getCustomerName());
                    }else {
                        System.out.println("Car was not rented or rental information is missing.");
                    }

                }else {
                    System.out.println("Invalid carId or car is not rented.");
                }

            }else if (choice == 3){
                break;
            }else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }

        }

        System.out.println("Thank you for using our system. ");
    }

}
