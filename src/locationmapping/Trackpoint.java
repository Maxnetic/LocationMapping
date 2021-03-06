package locationmapping;

import org.joda.time.*;

import de.fhpotsdam.unfolding.geo.Location;

/**
 * Ein Trackpoint beinhaltet Informationen ueber einen gespeicherten Ortungspunkt. 
 * Konkret speichert ein Trackpoint dabei Orts-, Service- und Zeitinformationen.
 * Ausserdem kann jedem Trackpoint eine ID zugeordnet werden.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class Trackpoint {
    // Attribute
    /**
    * Ortsinformation, enthaelt doubles fuer Laengen und Breitengrad
    */
    private Location location;
    /**
    * Zeit- und Datumsinformation, enthaelt long mit UNIX time in ms
    */
    private DateTime time;
    /**
    * Identifikationsnummer fuer den Datensatz (default = 0)
    */
    private int id = 0;
    /**
    * Label, welches von User festgesetzt werden kann (default = "")
    */
    private String label = "";
    /**
    * Information ueber den benutzten Handy Service (default = "")
    */
    private String service = "";
    /**
    * Information ueber die Sichtbarkeit des Trackpoints
    */
    private boolean visible = true;


    /**
    * Konstruktor fuer Trackpoint Objekte
    *
    * @param time Zeit- und Datumsinformation fuer Trackpoint mit UNIX time in ms
    * @param location Ortsinformation fuer Trackpoint
    * @param id Identifikationsnummer fuer Datensatz des Trackpoints
    * @param service Handyserviceinformation
    */
    public Trackpoint(DateTime time, Location location, int id, String service) {
        this.id = id;
        this.time = time;
        this.location = new Location(location);
        this.service = service;
    }
    /**
    * Konstruktor fuer Trackpoint Objekte, setzt id auf 0 und service auf ""
    *
    * @param time Zeit- und Datumsinformation fuer Trackpoint mit UNIX time in ms
    * @param location Ortsinformation fuer Trackpoint
    */
    public Trackpoint(DateTime time, Location location) {
        this.time = time;
        this.location = new Location(location);
    }

    /**
    * Setzt die Identifikationsnummer des Trackpoint
    *
    * @param id neue Identifikationsnummer (3 stellige Zahl)
    */
    public void setId(int id){
        this.id = id;
    }

    /**
    * Gibt Identifikationsnummer des Trackpoint aus
    *
    * @return Identifikationsnummer des Trackpoint
    */
    public int getId(){
        return id;
    }

    /**
    * Gibt Service des Trackpoints aus
    *
    * @return Service des Trackpoints als String
    */
    public String getService(){
      return service;
    }

    /**
    * Setzt ein Label fuer den Trackpoint
    *
    * @param label Text des Labels fuer den Trackpoint
    */
    public void setLabel(String label){
        this.label = label;
    }

    /**
    * Gibt das Label des Trackpoint aus
    *
    * @return Label des Trackpoint
    */
    public String getLabel(){
        return label;
    }

    /**
    * Setzt Sichtbarkeit des Trackpoint
    *
    * @param visibility Wahrheitswert ueber Sichtbarkeit des Trackpoint
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
    * @return Wahrheitswert ueber Sichtbarkeit
    */
    public boolean getVisible(){
        return this.visible;
    }

    /**
    * Gibt Zeitobjekt des Trackpoint aus
    *
    * @return Zeit- und Datumsinformation, enthaelt long mit UNIX time in ms
    */
    public DateTime getTime(){
        return time;
    }

    /**
    * Gibt Zeitobjekt des Trackpoint aus
    *
    * @return Zeitstempel des Trackpoints in ms
    */
    public long getTimestamp(){
        return time.getMillis();
    }

    /**
    * Gibt Zeitstempel des Trackpoint in Sekunden aus
    *
    * @return Zeitstempel des Trackpoint in s
    */
    public long getSeconds(){
        return this.time.getMillis()/1000L;
    }

    /**
    * Gibt Jahr des Trackpoint aus
    *
    * @return Jahr des Zeitstempels des Trackpoints
    */
    public int getYear(){
        return this.time.getYear();
    }

    /**
    * Gibt Monat im Jahr des Trackpoint aus
    *
    * @return Monat des Zeitstempels des Trackpoints
    */
    public int getMonth(){
        return this.time.getMonthOfYear();
    }

    /**
    * Gibt Tag im Monat der Zeitvariable des Trackpoint aus
    *
    * @return Tag des Zeitstempels des Trackpoints
    */
    public int getDay(){
        return this.time.getDayOfMonth();
    }

    /**
    * Gibt Stunde am Tag des Trackpoint aus
    *
    * @return Stunde des Zeitstempels des Trackpoints (24h Format)
    */
    public int getHour(){
        return this.time.getHourOfDay();
    }

    /**
    * Gibt Minute der Stunde des Trackpoint aus
    *
    * @return Minute des Zeitstempels des Trackpoints
    */
    public int getMinute(){
        return this.time.getMinuteOfHour();
    }

    /**
    * Gibt Sekunde der Minute des Trackpoint aus
    *
    * @return Sekunde des Zeitstempels des Trackpoints
    */
    public int getSecond(){
        return this.time.getSecondOfMinute();
    }

    /**
    * Gibt Datum und Uhrzeit des Trackpoint aus
    *
    * @return String mit Datum und Uhrzeit des Trackpoints
    */
    public String getDateTime(){
        return this.time.toString();
    }

    /**
    * Gibt Wochentag der Zeitvariable des Trackpoint aus
    *
    * @return Wochentag des Zeitstempels des Trackpoints als Zahl (Su=0, ..., Sa=6)
    */
    public int getDayOfWeek(){
        return this.time.getDayOfWeek();
    }

    /**
    * Gibt Ort des Trackpoint aus
    *
    * @return Ortsdaten des Trackpoint 
    */
    public Location getLocation(){
        return location;
    }

    /**
    * Gibt Laengengrad des Trackpoint aus
    *
    * @return Laengengrad des Trackpoint
    */
    public float getLatitude(){
        return location.getLat();
    }

    /**
    * Gibt Breitengrad des Trackpoint aus
    *
    * @return Breitengrad des Trackpoint
    */
    public float getLongitude(){
        return location.getLon();
    }


    /**
    * Berechnet zeitlichen Abstand zwischen zwei Trackpoints
    *
    * @param trackpoint zweiter Trackpoint mit dem Trackpoint hinsichtlich der Zeit verglichen wird
    * @return Zeitdifferenz der Trackpoints in s
    */
    public long timeDistanceTo(Trackpoint trackpoint){
        return this.getSeconds() - trackpoint.getSeconds();
    }
    /**
    * Berechnet zeitlichen Abstand zwischen einem Trackpoint und einem Zeitpunkt
    *
    * @param time Zeitpunkt mit dem Zeit des Trackpoint verglichen wird als DateTime-Objekt
    * @return Zeitdifferenz in s
    */
    public long timeDistanceTo(DateTime time){
        return Seconds.secondsBetween(this.time, time).getSeconds();
    }

    /**
    * Ueberprueft ob zwei Trackpoints innerhalb einer Toleranzgrenze gleichzeitig sind
    *
    * @param trackpoint zweiter Trackpoint mit dem Trackpoint hinsichtlich der Zeit verglichen wird,Toleranzgrenze : default = 3 s
    * @return Wahrheitswert der Gleichzeitigkeit der Trackpoints innerhalb der Toleranz
    */
    public boolean equalTime(Trackpoint trackpoint){
        return equalTime(trackpoint, 3);
    }
    /**
    * Ueberprueft ob Trackpoint innerhalb einer Toleranzgrenze gleichzeitig mit einem Zeitpunkt ist
    *
    * @param time Zeitpunkt mit dem Zeit des Trackpoint verglichen wird als DateTime-Objekt, Toleranzgrenze : default = 3 s
    * @return Wahrheitswert der Gleichzeitigkeit der Trackpoints innerhalb der Toleranz
    */
    public boolean equalTime(DateTime time){
        return equalTime(time, 3);
    }
    /**
    * Ueberprueft ob zwei Trackpoints innerhalb einer Toleranzgrenze gleichzeitig sind
    *
    * @param trackpoint zweiter Trackpoint mit dem Trackpoint hinsichtlich der Zeit verglichen wird
    * @param tolerance Toleranzgrenze fuer Zeitgleichheit in s
    * @return Wahrheitswert der Gleichzeitigkeit der Trackpoints innerhalb der Toleranz
    */
    public boolean equalTime(Trackpoint trackpoint, long tolerance){
        return this.timeDistanceTo(trackpoint) <= tolerance;
    }
    /**
    * Ueberprueft ob ein Trackpoint innerhalb einer Toleranzgrenze gleichzeitig mit einem Zeitpunkt ist
    *
    * @param time Zeitpunkt mit dem Zeit des Trackpoint verglichen wird als DateTime-Objekt
    * @param tolerance Toleranzgrenze fuer Zeitgleichheit in s
    * @return Wahrheitswert der Gleichzeitigkeit der Trackpoints innerhalb der Toleranz
    */
    public boolean equalTime(DateTime time, long tolerance){
        return this.timeDistanceTo(time) <= tolerance;
    }

    /**
    * Vergleicht Trackpoints bezueglich ihrer Zeitkoordinate
    *
    * @param trackpoint Trackpoint mit dem Trackpoint verglichen wird
    * @return Zahl gleich 0 falls die Trackpoints zeitlich gleich sind, kleiner 0 falls anderer Trackpoint frueher, groesser 0 falls anderer Trackpoint spaeter
    */
    public int compareTimeTo(Trackpoint trackpoint){
        return compareTimeTo(trackpoint.getTime());
    }
    /**
    * Vergleicht Trackpoint mit einem Zeitpunkt
    *
    * @param time Zeitpunkt mit dem Zeit des TrackpointList verglichen wird als DateTime-Objekt
    * @return Zahl gleich 0 falls Trackpoint zeitlich mit Zeitpunkt, kleiner 0 falls anderer Zeitpunkt frueher, groesser 0 falls Zeitpunkt spaeter
    */
    public int compareTimeTo(DateTime time){
        return this.time.compareTo(time);
    }


    /**
    * Berechnet oertlichen Abstand zwischen zwei Trackpoints
    *
    * @param trackpoint Trackpoint zu dem Abstand berechnet wird
    * @return Abstand zwischen den Trackpoints in km
	*/
    public double locationDistanceTo(Trackpoint trackpoint){
        return this.location.getDistance(trackpoint.getLocation());
    }

    /**
     * Berechnet oertlichen Abstand zwischen Trackpoint und einem Ort
     *
     * @param location Location zu dem Abstand berechnet wird
     * @return Abstand zwischen dem Trackpoint und der Location in km
     */
    public double locationDistanceTo(Location location){
        return this.location.getDistance(location);
    }

    /**
    * Ueberprueft ob zwei Trackpoints innerhalb einer Toleranzgrenze (default 0.0009 km) am gleichen Ort sind
    *
    * @param trackpoint Trackpoint mit dem Ort berechnet wird
    * @return Wahrheitswert der Ortsgleichheit der Trackpoints innerhalb der Toleranz
    */
    public boolean equalLocation(Trackpoint trackpoint){
        return equalLocation(trackpoint, 0.00009);
    }

    /**
     * Ueberprueft ob ein Trackpoint innerhalb einer Toleranzgrenze (default 0.0009 km) an einem Ort ist
     *
     * @param location Location mit dem Ort berechnet wird
     * @return Wahrheitswert der Ortsgleichheit des Trackpoints mit dem Ort innerhalb der Toleranz
     */
    public boolean equalLocation(Location location){
        return equalLocation(location, 0.00009);
    }

    /**
     * Ueberprueft ob zwei Trackpoints innerhalb einer Toleranzgrenze am gleichen Ort sind
     *
     * @param trackpoint Trackpoint mit dem verglichen wird
     * @param tolerance Toleranzgrenze fuer Ortsgleichheit in km
     * @return Wahrheitswert der Ortsgleichheit der Trackpoints innerhalb der Toleranz
     */
    public boolean equalLocation(Trackpoint trackpoint, double tolerance){
        return (this.locationDistanceTo(trackpoint) <= tolerance);
    }

    /**
     * Ueberprueft ob ein Trackpoint innerhalb einer Toleranzgrenze an einem Ort ist
     *
     * @param location Location mit dem Ort berechnet wird
     * @param tolerance Toleranzgrenze fuer Ortsgleichheit in km
     * @return Wahrheitswert der Ortsgleichheit der Trackpoints innerhalb der Toleranz
     */
    public boolean equalLocation(Location location, double tolerance){
        return (this.locationDistanceTo(location) <= tolerance);
    }

    /**
    * Ueberprueft ob zwei Trackpoints innerhalb einer Toleranzgrenze gleichzeitig und am gleichen Ort sind
    *
    * @param trackpoint  Trackpoint mit dem vergliche wird, Toleranzgrenze Zeit: 3s, Toleranzgrenze Ort : 0.0009 km
    * @return Wahrheitswert der Gleichzeitigkeit und Ortsgleichheit der Trackpoints innerhalb der Toleranzen
    */
   public boolean equals(Trackpoint trackpoint) {
        return this.equalLocation(trackpoint) && this.equalTime(trackpoint);
    }

   /**
    * Ueberprueft ob zwei Trackpoints innerhalb einer Toleranzgrenze gleichzeitig und am gleichen Ort sind
    *
    * @param trackpoint Trackpoint mit dem verglichen wird
    * @param timeTolerance Toleranzgrenze fuer Zeitgleichheit in 
    * @param locationTolerance Toleranzgrenze fuer Ortsgleichheit in km
    * @return Wahrheitswert der Gleichzeitigkeit und Ortsgleichheit der Trackpoints innerhalb der Toleranzen
    */
    public boolean equals(Trackpoint trackpoint, long timeTolerance, double locationTolerance) {
        return this.equalLocation(trackpoint, locationTolerance) && this.equalTime(trackpoint, timeTolerance);
    }


    /**
    * Gibt Stringrepraesentation des Trackpoint zurueck, beinhaltet ID,Ort,Zeit,Service,Label
    *
    * @return Stringrepraesentation des Trackpoint
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