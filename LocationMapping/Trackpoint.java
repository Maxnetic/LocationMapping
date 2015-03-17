class Trackpoint {
    // Attribute
    Location location;
    Timestamp timestamp;

    // Konstruktor
    public Trackpoint(int year, int month, int date, int hour, int minute, int second, double longitude, double lattitude){
        timestamp = new Timestamp(year, month, date, hour, minute,second, 0);
        location = new Location(longitude, latitude);
    };

    // Time Methoden
    double getTimestamp();
    String getDateTime();
    int compareTimeTo();

    // Location Methoden
    double getLongitude();
    double getLattitude();
    boolean equalLocation(); // epsilon bei GPS??

    String toString();
    boolean equals(); // epsilon bei GPS und Time??
}
