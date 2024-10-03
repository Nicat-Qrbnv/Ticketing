import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Random;

public class Ticket {
    private String id;
    private String concertHall;
    private Short eventCode;
    private Instant time;
    private Boolean isPromo;
    private Character stadiumSector;
    private Float allowedWeightInKg;
    private BigDecimal price;

    /**
     * Constructor for full control on initialization
     *
     * @param id                - instance id
     * @param concertHall       - Venue of the event
     * @param eventCode         - event code
     * @param time              - event time in epoch
     * @param isPromo           - information about if ticket promotional
     * @param stadiumSector     - sector/location of the seat in stadium
     * @param allowedWeightInKg - allowed maximum weight of the backpack to carry in
     */
    public Ticket(String id, String concertHall, Short eventCode, Instant time, Boolean isPromo, Character stadiumSector, Float allowedWeightInKg, BigDecimal price) {
        setId(id);
        setConcertHall(concertHall);
        setEventCode(eventCode);
        setTime(time);
        setPromo(isPromo);
        setStadiumSector(stadiumSector);
        setAllowedWeightInKg(allowedWeightInKg);
        setPrice(price);
    }

    /**
     * Constructor for partial initialization accepting:
     * Provides random id for the instance
     * @param concertHall - Venue of the event
     * @param eventCode   - event code
     * @param time        - ticket creation time in epoch
     */
    public Ticket(String concertHall, Short eventCode, Instant time) {
        this(generateRandomId(), concertHall, eventCode, time, null, null, null, null);
    }

    /**
     * Empty constructor calling partial constructor with null values
     */
    public Ticket() {
        this (null, null, null);
    }

    /**
     * Random id generator.
     * @return - an id containing random uppercase English letters. Example, ABCD.
     */
    private static String generateRandomId() {
        Random random = new Random();
        StringBuilder uniqueId = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            uniqueId.append((char) (random.nextInt(26) + 'A'));
        }
        return uniqueId.toString();
    }

    /**
     * Sets provided id. Checks:
     * - if id is not null, otherwise throws "Id cannot be null".
     * - if contains only 4 characters within A to Z range.
     * @param id - a valid id to set for instance
     */
    public void setId(String id) {
        if (id != null) {
            if (id.length() == 4 && id.matches("[A-Z]+")) {
                this.id = id;
            } else {
                System.out.println("Incorrect id format");
            }
        } else {
            System.out.println("Id cannot be null");
        }
    }

    /**
     * Sets the venue for the ticket. Checks if input is not longer than 10 characters.
     * @param concertHall - the venue.
     */
    public void setConcertHall(String concertHall) {
        if (concertHall == null || concertHall.length() <= 10) {
            this.concertHall = concertHall;
        } else {
            System.out.println("Maximum 10 characters are allowed");
        }
    }

    /**
     * Sets the code of the event.
     * @param eventCode - a valid code should be within 1 to 999 range.
     */
    public void setEventCode(Short eventCode) {
        if (eventCode == null || (eventCode > 0 && eventCode <= 999)) {
            this.eventCode = eventCode;
        } else {
            System.out.println("Incorrect event code format");
        }
    }

    /**
     * Sets event time. If no time provided then chooses ticket creation time as the time.
     * @param time - event time in epoch format
     */
    public void setTime(Instant time) {
        this.time = time != null ? time : Instant.now();
    }

    /**
     * sets promo status of the ticket.
     * @param isPromo - promo status of the ticket.
     */
    public void setPromo(Boolean isPromo) {
        this.isPromo = isPromo;
    }

    /**
     * Sets the sector for the ticket.
     * @param stadiumSector - a valid sector which should be within Z to C range.
     */
    public void setStadiumSector(Character stadiumSector) {
        if (stadiumSector == null || (stadiumSector >= 'A' && stadiumSector <= 'C')) {
            this.stadiumSector = stadiumSector;
        } else {
            System.out.println("Invalid stadium sector");
        }
    }

    /**
     * Sets allowed maximum weight for backpacks.
     * @param allowedWeightInKg - a valid value should not be negative.
     */
    public void setAllowedWeightInKg(Float allowedWeightInKg) {
        if (allowedWeightInKg == null || allowedWeightInKg >= 0) {
            this.allowedWeightInKg = allowedWeightInKg;
        }
    }

    public void setPrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) >= 0) {
            this.price = price;
        } else {
            System.out.println("Price can not be negative");
        }
    }

    /**
     * A method to turn null values into "N/A" value.
     * @param field - the field to turn
     * @return - returns either actual value or "N/A" if it is null.
     * @param <T> - the type. In case of floats the value will be returned rounded to 2 decimals.
     */
    private <T> String getStringValue(T field) {
        if (field instanceof Float) {
            float value = (float) field;
            return "%.2f".formatted(((int) (value * 100)) / 100f);
        } else {
            return field == null ? "N/A" : field.toString();
        }
    }

    public void getInformationAboutTicketsBySector(Character stadiumSector, List<Ticket> listTickets){
        listTickets.stream().filter(ticket -> ticket.getStadiumSector().equals(stadiumSector)).forEach(System.out::println);
    }

    public Character getStadiumSector() {
        return stadiumSector;
    }

    /**
     * @return - formatted view of ticket details
     */
    @Override
    public String toString() {

        return """
                %s
                Ticket id: %s | EventCode: %s
                Date: %s UTC | Venue: %-10s | Sector: %s
                is%sPromo | Price: %s
                Allowed Weight: %s kg
                """.formatted(
                "-".repeat(6 + getStringValue(time).length() + 10 + 10 + 11 + getStringValue(stadiumSector).length()),
                getStringValue(id), getStringValue(eventCode),
                getStringValue(time), getStringValue(concertHall), getStringValue(stadiumSector),
                (isPromo == null || !isPromo) ? " not " : " ", getStringValue(price),
                getStringValue(allowedWeightInKg)
        );
    }
}