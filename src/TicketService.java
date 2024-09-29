import java.time.Instant;

public class TicketService {
    public static void main(String[] args) {
        Ticket emptyTicket = new Ticket();
        System.out.println(emptyTicket);
        Ticket partialTicket = new Ticket("Part Hall", (short) 123, Instant.parse("2024-09-29T10:00:00.00Z"));
        System.out.println(partialTicket);
        Ticket fullTicket = new Ticket("RFDS","Full Hall", (short) 456, Instant.parse("2024-10-30T11:00:00.00Z"), true, 'C', 10.5f);
        System.out.println(fullTicket);
    }
}
