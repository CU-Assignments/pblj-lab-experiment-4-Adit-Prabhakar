import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private final boolean[] seats;
    private final ReentrantLock lock = new ReentrantLock();

    public TicketBookingSystem(int totalSeats) {
        seats = new boolean[totalSeats];
    }

    public void bookSeat(String user, int seatNumber, boolean isVIP) {
        if (seatNumber < 1 || seatNumber > seats.length) {
            System.out.println(user + ": Invalid seat number!");
            return;
        }
        
        lock.lock();
        try {
            if (!seats[seatNumber - 1]) {
                seats[seatNumber - 1] = true;
                System.out.println(user + " booked seat " + seatNumber);
            } else {
                System.out.println(user + ": Seat " + seatNumber + " is already booked!");
            }
        } finally {
            lock.unlock();
        }
    }
}

class UserThread extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String userName;

    public UserThread(TicketBookingSystem system, String userName, int seatNumber, boolean isVIP) {
        this.system = system;
        this.userName = userName;
        this.seatNumber = seatNumber;
        if (isVIP) {
            setPriority(Thread.MAX_PRIORITY);
        }
    }

    @Override
    public void run() {
        system.bookSeat(userName, seatNumber, getPriority() == Thread.MAX_PRIORITY);
    }
}

public class TicketBookingMain {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5);

        Thread user1 = new UserThread(system, "Anish (VIP)", 1, true);
        Thread user2 = new UserThread(system, "Bobby (Regular)", 2, false);
        Thread user3 = new UserThread(system, "Charlie (VIP)", 3, true);
        Thread user4 = new UserThread(system, "Bobby (Regular)", 1, false);
        Thread user5 = new UserThread(system, "Anish (VIP)", 4, true);
        Thread user6 = new UserThread(system, "Bobby (Regular)", 4, false);

        user1.start();
        user2.start();
        user3.start();
        user4.start();
        user5.start();
        user6.start();
    }
}
