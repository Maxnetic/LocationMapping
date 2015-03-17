import java.lang.Object


class Trackpoint {
    // Attribute
    /**
    * Ortsinformation, enthält doubles für Längen und Breitengrad
    */
    private Location location;
    /**
    * Timestamp
    */
    private Timestamp timestamp;
    /**
    * Identifikationsnummer für den Datensatz, per Default auf 0 gesetzt
    */
    private int id = 0;
    /**
    * Label, welcher von User festgesetzt werden kann, leer bei default
    */
    private String label = "";

    /**
    *
    */
    public Trackpoint(int id, Timestamp timestamp, Location location){
        this.timestamp = timestamp;
        this.id = id;
        this.location = new Location(location);
    }

    // Konstruktor ohne id
    public Trackpoint(Timestamp timestamp, Location location){
        this.timestamp = timestamp;
        this.location = new Location(location);
    }

    // Set ID
    void setId(int id){
        this.id = id;
    }

    // Get ID
    int getId(){
        return id;
    }

    // Set Label
    void setLabel(String label){
        this.label = label;
    }

    // Get Label
    public String getLabel(){
        return label;
    }

    // Time Methoden
    public Timestamp getTimestamp(){
        return timestamp;
    }

    //Ausgabe Datum und Uhrzeit als String
    public String getDateTime(){
        return timestamp.toString();
    }

    // Zeitdifferenz zweier Trackpoints in Sekunden.
    public long compareTimeTo(Trackpoint trackpoint){
        return Math.abs(this.timestamp.getTime() - trackpoint.getTimestamp().getTime());
     }

    // Location Methoden
    public Location getLocation(){
        return location;
    }

    // Test, ob 2 Trackpoints bis auf die angegebene Toleranzgrenze übereinstimmen
    public boolean equalLocation(double toleranceRange, Trackpoint trackpoint){
        double distance = this.location.distanceTo(trackpoint.getLocation());
        return (distance <= toleranceRange);
    }

    // Rückgabe des Trackpoints als String
    public String toString(){
        return "id: " + this.id + " Location: " +  this.location.toString() + " Timestamp: " + this.timestamp.toString();
    }

   // toleranceRangeTime in Millisekunden, testet ob 2 Trackpoints in Zeit und Ort bis auf Toleranzgrenzen übereinstimmen
   public boolean equals(long toleranceRangeTime, double toleranceRangeLocation, Trackpoint trackpoint) {
        double distanceLocation = this.location.distanceTo(trackpoint.getLocation());
        long distanceTime = Math.abs(this.timestamp.getTime() - trackpoint.getTimestamp().getTime());
        return (distanceLocation <= toleranceRangeLocation && distanceTime <= toleranceRangeTime);
    }
}
