package locationmapping;

import org.joda.time.*;

import de.fhpotsdam.unfolding.geo.Location;

/**
*
*/
public class Trackpoint {
    // Attribute
    /**
    * Ortsinformation, enthält doubles für Längen und Breitengrad
    */
    private Location location;
    /**
    * Zeit- und Datumsinformation, enthält long mit UNIX time in ms
    */
    private DateTime time;
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
    * Information über den benutzten Handy Service (default = "")
    */
    private boolean visible = true;


    /**
    * Konstruktor für Trackpoint Objekte
    *
    * @param time Zeit- und Datumsinformation für Trackpoint
    * @param location Ortsinformation für Trackpoint
    * @param id Identifikationsnummer für Datensatz des Trackpoints
    * @param service Handyserviceinformation
    * @return neues Objekt vom Typ Trackpoint
    */
    public Trackpoint(DateTime time, Location location, int id, String service) {
        this.id = id;
        this.time = time;
        this.location = new Location(location);
        this.service = service;
    }
    /**
    * Konstruktor für Trackpoint Objekte, setzt id auf 0 und service auf ""
    *
    * @param time Zeit- und Datumsinformation für Trackpoint
    * @param location Ortsinformation für Trackpoint
    * @return neues Objekt vom Typ Trackpoint
    */
    public Trackpoint(DateTime time, Location location) {
        this.time = time;
        this.location = new Location(location);
    }

    /**
    * Setzt die Identifikationsnummer des Trackpoint
    *
    * @param id [int]: neue Identifikationsnummer (3 stellige Zahl)
    */
    public void setId(int id){
        this.id = id;
    }

    /**
    * Gibt Identifikationsnummer des Trackpoint aus
    *
    * @return [int]: Identifikationsnummer des Trackpoint
    */
    public int getId(){
        return id;
    }

    /**
    * Gibt Service des Trackpoints aus
    *
    * @return [String] : Service des Trackpoints als String
    */
    public String getService(){
      return service;
    }

    /**
    * Setzt ein Label für den Trackpoint
    *
    * @param label [String]: neues Label für den Trackpoint
    */
    public void setLabel(String label){
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
    * Setzt Sichtbarkeit des Trackpoint
    *
    * @param visibility Wahrheitswert über Sichtbarkeit des Trackpoint
    */
    public void setVisible(boolean visibility){
        this.visible = visibility;
    }
    /**
    * Setzt Trackpoint sichtbar
    */
    public void setVisible(){
        this.visible = true;
    }
    /**
     * Setzt Trackpoint unsichtbar
     */
    public void setInVisible(){
        this.visible = false;
    }

    /**
    * Gibt Sichtbarkeit des Trackpoint aus
    *
    * @return Wahrheitswert über Sichtbarkeit
    */
    public boolean getVisible(){
        return this.visible;
    }

    /**
    * Gibt Zeitobjekt des Trackpoint aus
    *
    * @return DateTime Objekt des Trackpoint
    */
    public DateTime getTime(){
        return time;
    }

    /**
    * Gibt Zeitstempel des Trackpoint in Sekunden aus
    *
    * @return Zeitstempel des Trackpoint in Seconds
    */
    public long getSeconds(){
        return this.time.getMillis()/1000L;
    }

    /**
    * Gibt Jahr des Trackpoint aus
    *
    * @return Jahr der Zeitvariable
    */
    public int getYear(){
        return this.time.getYear();
    }

    /**
    * Gibt Monat im Jahr des Trackpoint aus
    *
    * @return Monat im Jahr der Zeitvariable
    */
    public int getMonth(){
        return this.time.getMonthOfYear();
    }

    /**
    * Gibt Tag im Monat der Zeitvariable des Trackpoint aus
    *
    * @return Tag im Monat der Zeitvariable
    */
    public int getDay(){
        return this.time.getDayOfMonth();
    }

    /**
    * Gibt Stunde am Tag des Trackpoint aus
    *
    * @return Stunde am Tag der Zeitvariable (24h Format)
    */
    public int getHour(){
        return this.time.getHourOfDay();
    }

    /**
    * Gibt Minute der Stunde des Trackpoint aus
    *
    * @return Minute der Stunde der Zeitvariable
    */
    public int getMinute(){
        return this.time.getMinuteOfHour();
    }

    /**
    * Gibt Datum und Uhrzeit des Trackpoint aus
    *
    * @return [String]: String mit Datum und Uhrzeit
    */
    public String getDateTime(){
        return this.time.toString();
    }

    /**
    * Gibt Wochentag der Zeitvariable des Trackpoint aus
    *
    * @return Wochentag der Zeitvariable als Zahl (Su=0, ..., Sa=6)
    */
    public int getDayOfWeek(){
        return this.time.getDayOfWeek();
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
    * @param trackpoint Trackpoint mit dem Zeit verglichen wird
    * @return Zeitdifferenz in Sekunden
    */
    public long timeDistanceTo(Trackpoint trackpoint){
        return this.getSeconds() - trackpoint.getSeconds();
    }
    /**
    * Berechnet zeitlichen Abstand zwischen zwei Trackpoints
    *
    * @param time Zeitpunkt mit dem Zeit des Trackpoint verglichen wird
    * @return Zeitdifferenz in Sekunden
    */
    public long timeDistanceTo(DateTime time){
        return Seconds.secondsBetween(this.time, time).getSeconds();
    }

    /**
    * Überprüft ob zwei Trackpoints innerhalb einer Toleranzgrenze gleichzeitig sind
    *
    * @param trackpoint Trackpoint mit dem Zeit verglichen wird
    * @return Wahrheitswert der Gleichzeitigkeit der Trackpoints innerhalb der Toleranz
    */
    public boolean equalTime(Trackpoint trackpoint){
        return equalTime(trackpoint, 3);
    }
    /**
    * Überprüft ob zwei Trackpoints innerhalb einer Toleranzgrenze gleichzeitig sind
    *
    * @param time Zeitpunkt mit dem Zeit des Trackpoint verglichen wird
    * @return Wahrheitswert der Gleichzeitigkeit der Trackpoints innerhalb der Toleranz
    */
    public boolean equalTime(DateTime time){
        return equalTime(time, 3);
    }
    /**
    * Überprüft ob zwei Trackpoints innerhalb einer Toleranzgrenze gleichzeitig sind
    *
    * @param trackpoint Trackpoint mit dem Zeit verglichen wird
    * @param tolerance Toleranzgrenze für Zeitgleichheit in Sekunden
    * @return Wahrheitswert der Gleichzeitigkeit der Trackpoints innerhalb der Toleranz
    */
    public boolean equalTime(Trackpoint trackpoint, long tolerance){
        return this.timeDistanceTo(trackpoint) <= tolerance;
    }
    /**
    * Überprüft ob zwei Trackpoints innerhalb einer Toleranzgrenze gleichzeitig sind
    *
    * @param time Zeitpunkt mit dem Zeit des Trackpoint verglichen wird
    * @param tolerance Toleranzgrenze für Zeitgleichheit in Sekunden
    * @return Wahrheitswert der Gleichzeitigkeit der Trackpoints innerhalb der Toleranz
    */
    public boolean equalTime(DateTime time, long tolerance){
        return this.timeDistanceTo(time) <= tolerance;
    }

    /**
    * Vergleicht Trackpoints bezüglich ihrer Zeitkoordinate
    *
    * @param trackpoint Trackpoint mit dem Zeit verglichen wird
    * @return Zahl gleich 0 falls gleich, kleiner 0 falls anderer Trackpoint führe, größer 0 falls anderer Trackpoint später
    */
    public int compareTimeTo(Trackpoint trackpoint){
        return compareTimeTo(trackpoint.getTime());
    }
    /**
    * Vergleicht Trackpoints bezüglich ihrer Zeitkoordinate
    *
    * @param time Zeitpunkt mit dem Zeit des TrackpointList verglichen wird
    * @return Zahl gleich 0 falls gleich, kleiner 0 falls anderer Trackpoint führe, größer 0 falls anderer Trackpoint später
    */
    public int compareTimeTo(DateTime time){
        return this.time.compareTo(time);
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
        String out = "Trackpoint(id " + String.format("%03d",this.id) + " | Location " +  this.location.getLat() + " " + this.location.getLon() + " | Timestamp " + this.time;
        if ( !this.service.isEmpty() )
            out += " | Service " + this.service;
        if ( !this.label.isEmpty() )
            out += " | Label '" + this.label + "'";
        return out + ")";
    }
}