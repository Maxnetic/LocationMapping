import de.fhpotsdam.unfolding.geo.Location;
import java.sql.Timestamp;

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
    * Service Information
    */
    private String service = "";



    /**
    * Konstruktor für
    *
    * @param id [int] (optional, default = 0): Identifikationsnummer für Datensatz
    * @param Timestamp timestamp: Timestamp Objekt mit Datum und Zeit
    * @param Location location: Location Object
    * @param String service (optional, default = ""):
    * @return Objekt vom Typ Trackpoint
    */
    public Trackpoint(int id, Timestamp timestamp, Location location, String service) {
        this.id = id;
        this.timestamp = timestamp;
        this.location = new Location(location);
        this.service = service;
    }
    public Trackpoint(Timestamp timestamp, Location location) {
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



    // Location Methoden
    public Location getLocation(){
        return location;
    }
    public float getLatitude(){
        return location.getLat();
    }
    public float getLongitude(){
        return location.getLon();
    }



    // Rückgabe des Trackpoints als String
    public String toString(){
        String out = "Trackpoint(id " + String.format("%03d",this.id) + " | Location " +  this.location.getLat() + " " + this.location.getLon() + " | Timestamp " + this.timestamp;
        if ( !this.label.isEmpty() )
            out += " | Label '" + this.label + "'";
        return out + ")";
    }

    // Zeit Vergleich und Differenz

    public long timeDistanceTo(Trackpoint trackpoint){
        return this.timestamp.getTime() - trackpoint.getTimestamp().getTime();
    }

    public boolean equalTime(Trackpoint trackpoint){
        return equalTime(trackpoint, 3000);
    }
    public boolean equalTime(Trackpoint trackpoint, long tolerance){
        return this.timeDistanceTo(trackpoint) <= tolerance;
    }

    public int compareTimeTo(Trackpoint trackpoint){
        return (int) this.timeDistanceTo(trackpoint);
    }


    // Test, ob 2 Trackpoints bis auf die angegebene Toleranzgrenze übereinstimmen
    public double locationDistanceTo(Trackpoint trackpoint){
        return this.location.getDistance(trackpoint.getLocation());
    }

    public boolean equalLocation(Trackpoint trackpoint){
        return equalLocation(trackpoint, 0.00009);
    }
    public boolean equalLocation(Trackpoint trackpoint, double tolerance){
        return (this.locationDistanceTo(trackpoint) <= tolerance);
    }



   // toleranceRangeTime in Millisekunden, testet ob 2 Trackpoints in Zeit und Ort bis auf Toleranzgrenzen übereinstimmen
   public boolean equals(Trackpoint trackpoint) {
        return this.equalLocation(trackpoint) && this.equalTime(trackpoint);
    }
    public boolean equals(Trackpoint trackpoint, long timeTolerance, double locationTolerance) {
        return this.equalLocation(trackpoint, locationTolerance) && this.equalTime(trackpoint, timeTolerance);
    }
}
