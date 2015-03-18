import de.fhpotsdam.unfolding.geo.Location;
import java.sql.Timestamp;

/**
*
*/
class Trackpoint {
    // Attribute
    /**
    * Ortsinformation, enthält doubles für Längen und Breitengrad
    */
    private Location location;
    /**
    * Zeit- und Datumsinformation, enthält long mit UNIX timestamp in ms
    */
    private Timestamp timestamp;
    /**
    * Identifikationsnummer für den Datensatz (default = 0)
    */
    private int id = 0;
    /**
    * Label, welches von User festgesetzt werden kann (default = "")
    */
    private String label = "";
    /**
    * Information über den benutzten Handy Service (default = "")
    */
    private String service = "";


    /**
    * Konstruktor für Trackpoint Objekte
    *
    * @param timestamp [Timestamp]: Zeit- und Datumsinformation für Trackpoint
    * @param location [Location]: Ortsinformation für Trackpoint
    * @param id [int]: (default = 0): Identifikationsnummer für Datensatz des Trackpoints
    * @param service [String]: (default = ""): Handyserviceinformation
    * @return neues Objekt vom Typ Trackpoint
    */
    public Trackpoint(Timestamp timestamp, Location location, int id, String service) {
        this.id = id;
        this.timestamp = timestamp;
        this.location = new Location(location);
        this.service = service;
    }
    public Trackpoint(Timestamp timestamp, Location location) {
        this.timestamp = timestamp;
        this.location = new Location(location);
    }

    /**
    * Setzt die Identifikationsnummer des Trackpoint
    *
    * @param id [int]: neue Identifikationsnummer (3 stellige Zahl)
    */
    void setId(int id){
        this.id = id;
    }

    /**
    * Gibt Identifikationsnummer des Trackpoint aus
    *
    * @return [int]: Identifikationsnummer des Trackpoint
    */
    int getId(){
        return id;
    }

    /**
    * Setzt ein Label für den Trackpoint
    *
    * @param label [String]: neues Label für den Trackpoint
    */
    void setLabel(String label){
        this.label = label;
    }

    /**
    * Gibt das Label des Trackpoint aus
    *
    * @return [String]: Label des Trackpoint
    */
    public String getLabel(){
        return label;
    }

    /**
    * Gibt Zeitstempel des Trackpoint aus
    *
    * @return [Timestamp]: Zeitstempel des Trackpoint
    */
    public Timestamp getTimestamp(){
        return timestamp;
    }

    /**
    * Gibt Zeitstempel des Trackpoint in Sekunden aus
    *
    * @return [long]: Zeitstempel des Trackpoint
    */
    public long getSeconds(){
        return timestamp.getTime()/1000L;
    }

    /**
    * Gibt Datum und Uhrzeit des Trackpoint aus
    *
    * @return [String]: String mit Datum und Uhrzeit
    */
    public String getDateTime(){
        return timestamp.toString();
    }



    /**
    * Gibt Ort des Trackpoint aus
    *
    * @return [Location]: Ortsdaten des Trackpoint
    */
    public Location getLocation(){
        return location;
    }

    /**
    * Gibt Längengrad des Trackpoint aus
    *
    * @return [float]: Längengrad des Trackpoint
    */
    public float getLatitude(){
        return location.getLat();
    }

    /**
    * Gibt Breitengrad des Trackpoint aus
    *
    * @return [float] Breitengrad des Trackpoint
    */
    public float getLongitude(){
        return location.getLon();
    }


    /**
    * Berechnet zeitlichen Abstand zwischen zwei Trackpoints
    *
    * @param trackpoint/timestamp [Trackpoint/Timestamp]: Trackpoint/Timestamp mit dem Zeit verglichen wird
    * @return [long]: Zeitdifferenz in Sekunden
    */
    public long timeDistanceTo(Trackpoint trackpoint){
        return this.getSeconds() - trackpoint.getSeconds();
    }
    public long timeDistanceTo(Timestamp timestamp){
        return this.getSeconds() - (timestamp.getTime()/1000L);
    }

    /**
    * Überprüft ob zwei Trackpoints innerhalb einer Toleranzgrenze gleichzeitig sind
    *
    * @param trackpoint/timestamp [Trackpoint/Timestamp]: Trackpoint/Timestamp mit dem Zeit verglichen wird
    * @param tolerance [long]: (default = 3s) Toleranzgrenze für Zeitgleichheit in Sekunden
    * @return [boolean]: Wahrheitswert der Gleichzeitigkeit der Trackpoints innerhalb der Toleranz
    */
    public boolean equalTime(Trackpoint trackpoint){
        return equalTime(trackpoint, 3);
    }
    public boolean equalTime(Timestamp timestamp){
        return equalTime(timestamp, 3);
    }
    public boolean equalTime(Trackpoint trackpoint, long tolerance){
        return this.timeDistanceTo(trackpoint) <= tolerance;
    }
    public boolean equalTime(Timestamp timestamp, long tolerance){
        return this.timeDistanceTo(timestamp) <= tolerance;
    }

    /**
    * Vergleicht Trackpoints bezüglich ihrer Zeitkoordinate
    *
    * @param trackpoint/timestamp [Trackpoint/Timestamp]: Trackpoint/Timestamp mit dem Zeit verglichen wird
    * @return [int]: Zahl gleich 0 falls gleich, kleiner 0 falls anderer Trackpoint führe, größer 0 falls anderer Trackpoint später
    */
    public int compareTimeTo(Trackpoint trackpoint){
        return (int) this.timeDistanceTo(trackpoint);
    }
    public int compareTimeTo(Timestamp timestamp){
        return (int) this.timeDistanceTo(timestamp);
    }


    /**
    * Berechnet örtlichen Abstand zwischen zwei Trackpoints
    *
    * @param trackpoint/location [Trackpoint/Location]: Trackpoint/Location zu dem Abstand berechnet wird
    * @return [double]: Abstand zwischen den Trackpoints in Grad
    */
    public double locationDistanceTo(Trackpoint trackpoint){
        return this.location.getDistance(trackpoint.getLocation());
    }
    public double locationDistanceTo(Location location){
        return this.location.getDistance(location);
    }

    /**
    * Überprüft ob zwei Trackpoints innerhalb einer Toleranzgrenze am gleichen Ort sind
    *
    * @param trackpoint/location [Trackpoint/Location]: Trackpoint/Location mit dem Ort berechnet wird
    * @param tolerance [double]: (default = 0.00009˚) Toleranzgrenze für Ortsgleichheit in Grad
    * @return [boolean]: Wahrheitswert der Ortsgleichheit der Trackpoints innerhalb der Toleranz
    */
    public boolean equalLocation(Trackpoint trackpoint){
        return equalLocation(trackpoint, 0.00009);
    }
    public boolean equalLocation(Location location){
        return equalLocation(location, 0.00009);
    }
    public boolean equalLocation(Trackpoint trackpoint, double tolerance){
        return (this.locationDistanceTo(trackpoint) <= tolerance);
    }
    public boolean equalLocation(Location location, double tolerance){
        return (this.locationDistanceTo(location) <= tolerance);
    }

    /**
    * Überprüft ob zwei Trackpoints innerhalb einer Toleranzgrenze gleichzeitig und am gleichen Ort sind
    *
    * @param trackpoint [Trackpoint]: Trackpoint mit dem vergliche wird
    * @param tolerance [long]: (default = 3s) Toleranzgrenze für Zeitgleichheit in Sekunden
    * @param tolerance [double]: (default = 0.00009˚) Toleranzgrenze für Ortsgleichheit in Grad
    * @return [boolean]: Wahrheitswert der Gleichzeitigkeit und Ortsgleichheit der Trackpoints innerhalb der Toleranzen
    */
   public boolean equals(Trackpoint trackpoint) {
        return this.equalLocation(trackpoint) && this.equalTime(trackpoint);
    }
    public boolean equals(Trackpoint trackpoint, long timeTolerance, double locationTolerance) {
        return this.equalLocation(trackpoint, locationTolerance) && this.equalTime(trackpoint, timeTolerance);
    }


    /**
    * Gibt Stringrepräsentation des Trackpoint zurück
    *
    * @return [String]: Stringrepräsentation des Trackpoint
    */
    public String toString(){
        String out = "Trackpoint(id " + String.format("%03d",this.id) + " | Location " +  this.location.getLat() + " " + this.location.getLon() + " | Timestamp " + this.timestamp;
        if ( !this.service.isEmpty() )
            out += " | Service " + this.service;
        if ( !this.label.isEmpty() )
            out += " | Label '" + this.label + "'";
        return out + ")";
    }
}
