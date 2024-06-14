import java.util.*;
import java.util.Scanner;

class BookTickets {
    // static variables to be shared over the objects!
    static int totalSeatsInGold = 0;
    static int totalSeatsInImax = 5;
    static int totalSeatsInGeneral = 10;

    static Date time = new Date(2024, 06, 12);
    static ArrayList<BookTickets> waitingList = new ArrayList<>();
    static ArrayList<BookTickets> bookingList = new ArrayList<>();

    // tickets type
    String category;
    String name;
    String[] bevarages;
    double total;
    int id;

    // constructor to intialize tickets object
    public BookTickets(String category, String name, String[] bevarages, double total, int id) {
        this.id = id;
        this.name = name;
        this.bevarages = bevarages;
        this.total = total;
        this.category = category;
    }

    // main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to book tickets or withdraw? ");
        System.out.println("1 - book");
        System.out.println("2 - cancel");
        int ch = sc.nextInt();
        sc.nextLine();
        if (ch == 1) {
            bookTicket(sc);
        } else {
            System.out.println("Enter you id");
            int id = sc.nextInt();
            cancelTicket(id);
        }

        sc.close();
    }

    // function to cancel tickets
    public static void cancelTicket(int id) {
        boolean flag = false;
        String[] bevarages = {};
        BookTickets bt1 = new BookTickets("IMAX", "Divyanshu", bevarages, 100, 10000);
        waitingList.add(bt1);
        BookTickets bt2 = new BookTickets("IMAX", "Kunal", bevarages, 100, 10001);
        bookingList.add(bt2);
        System.out.println(bookingList.toString());
        System.out.println(waitingList.toString());
        for (int x = 0; x < bookingList.size(); x++) {
            if (bookingList.get(x).id == id) {
                System.out.println(bookingList.get(x));
                flag = true;
                bookingList.remove(x);
                bookingList.add(waitingList.get(0));
                waitingList.remove(0);
            }
        }
        for (int x = 0; x < waitingList.size(); x++) {
            if (waitingList.get(x).id == id) {
                flag = true;
                System.out.println(waitingList.get(x));
                waitingList.remove(x);
            }
        }
        if (flag) {
            System.out.println("Ticket has been succesfully cancelled");
        } else {
            System.out.println("No ticket with this id found");
        }
        System.out.println(bookingList.toString());
        System.out.println(waitingList.toString());
    }

    // function to book ticket
    public static void bookTicket(Scanner scanner) {
        Scanner sc = scanner;

        // take user details as input
        System.out.println("Please enter you name");
        String name = sc.nextLine();
        System.out.println("Please tell us the type of Screen you'll prefer!");
        System.out.println("GOLD, IMAX, GENERAL");
        String screen = sc.next();
        Date currenTime = new Date();
        String[] bevarages = new String[2];
        System.out.println("Do you want popcorns? y - yes, n - no");
        String popcorn = sc.next();
        if (popcorn.equalsIgnoreCase("y")) {
            bevarages[0] = "popcorns";
        }
        System.out.println("Do you want sandwich? y - yes, n - no");
        String sandwich = sc.next();
        if (sandwich.equalsIgnoreCase("y")) {
            bevarages[1] = "sandwich";
        }
        double bookingCost = 0;
        double foodCost = 0;

        // logic to calculate total cost of the user
        if (screen.equalsIgnoreCase("IMAX")) {
            bookingCost = 300;
            if (bevarages[0].equalsIgnoreCase("popcorns")) {
                foodCost += 100;
            }
            if (bevarages[1].equalsIgnoreCase("sandwich")) {
                foodCost += 100;
            }
            foodCost -= 10;
        }
        if (screen.equalsIgnoreCase("GOLD")) {
            bookingCost = 400;
            if (bevarages[0].equalsIgnoreCase("popcorns")) {
                foodCost += 100;
            }
            if (bevarages[1].equalsIgnoreCase("sandwich")) {
                foodCost += 100;
            }
            foodCost -= 20;
        }
        if (screen.equalsIgnoreCase("GENERAL")) {
            bookingCost = 200;
            if (bevarages[0].equalsIgnoreCase("popcorns")) {
                foodCost += 100;
            }
            if (bevarages[1].equalsIgnoreCase("sandwich")) {
                foodCost += 100;
            }
        }
        double total = foodCost + bookingCost;
        System.out.println("Details: ");
        System.out.println("Name: " + name);
        System.out.println("Screen: " + screen);
        System.out.println("Bevarages: " + Arrays.toString(bevarages));
        System.out.println("Total: " + total);
        int range = 100000 - 999999 + 1;
        int id = (int) (Math.random() * range) + 100000;
        System.out.println("Id: " + id);

        BookTickets bt = new BookTickets(screen, name, bevarages, total, id);

        // logic to calculate whether the user should be in waiting list or booking list
        if (screen.equalsIgnoreCase("IMAX") && totalSeatsInImax == 0) {
            if ((time.getTime() - currenTime.getTime()) <= 1800000) {
                System.out.println("Sorry movie has begun");
                System.exit(0);
            }
            System.out.println("Sorry No seats left still we can add you in ");
            waitingList.add(bt);
        }
        if (screen.equalsIgnoreCase("GOLD") && totalSeatsInGold == 0) {
            if ((time.getTime() - currenTime.getTime()) <= 1800000) {
                System.out.println("Sorry movie has begun");
                System.exit(0);
            }
            System.out.println("Sorry No seats left still we can add you in ");
            waitingList.add(bt);
            System.out.println("added to gold");
        }
        if (screen.equalsIgnoreCase("GENERAL") && totalSeatsInGeneral == 0) {
            if ((time.getTime() - currenTime.getTime()) <= 1800000) {
                System.out.println("Sorry movie has begun");
                System.exit(0);
            }
            System.out.println("Sorry No seats left still we can add you in ");
            waitingList.add(bt);
        } else {
            bookingList.add(bt);
        }
        if (screen.equalsIgnoreCase("GENERAL")) {
            totalSeatsInGeneral--;
        }
        if (screen.equalsIgnoreCase("IMAX")) {
            totalSeatsInImax--;
        }
        if (screen.equalsIgnoreCase("GOLD")) {
            totalSeatsInGold--;
        }
        System.out.println("Ticket has been succesfully booked");
    }
}