package locationmapping;

import org.joda.time.*;

import java.util.*;

import de.fhpotsdam.unfolding.geo.Location;


/**
 *
 */
public class TrackpointList implements Iterable<Trackpoint> {
    /**
    * Trackpoint-Liste, die Trackpoints zu einer Person enthaelt
    */
    private ArrayList<Trackpoint> trackpointList;
    /**
    * Hastable, der Haeufigkeiten der einzelnen Trackpoints enthaelt
    */
    private Hashtable<Location, Integer> locationFrequencies;
    /**
    * Laenge der Trackpointliste
    */
    private int length = 0;
    /**
    * Angabe, ob die Liste bereits sortiert ist
    */
    private boolean isSortedByTime = false;

    /**
    * Konstruktor, erzeugt leere Trackpointliste
    *
    * @return neues leeres TrackpointList Objekt
    */
    public TrackpointList() {
        trackpointList = new ArrayList<Trackpoint>();
        locationFrequencies = new Hashtable<Location,Integer>();
    }

    /**
     * Gibt Anzahl der Elemente der Liste aus
     *
     * @return [int]: Anzahl der Elemente der Liste
     */
    public int size(Location location) {
        return this.length;
    }

    /**
     * Gibt an, ob Liste leer ist
     *
     * @return [boolean]: Wahrheitswert ob Liste leer ist
     */
    public boolean isEmpty(){
        return this.length == 0;
    }

    /**
    * Fuegt Trackpoint in entsprechende Liste ein
    *
    * @param trackpoint einzufuegender Trackpoint
    */
    public void add(Trackpoint trackpoint){
        trackpointList.add(trackpoint);
        length++;
        // update Hashtable, wenn Wert vorhanden, Wert erhoehen (schoenere Variante?),
        // sonst neu anlegen
        Location trackpointLocation = trackpoint.getLocation();
        if (locationFrequencies.containsKey(trackpointLocation)){
            locationFrequencies.put(trackpointLocation, locationFrequencies.get(trackpointLocation)+1);
        } else {
            locationFrequencies.put(trackpointLocation, 1);
        }
        this.isSortedByTime = false;
    }
    /**
    * Fuegt Trackpoints in entsprechende Liste ein
    *
    * @param trackpointList einzufuegende Liste von Trackpoints
    */
    public void add(TrackpointList trackpointList){
        for ( Trackpoint trackpoint : trackpointList )
            this.add(trackpoint);
    }

    /**
     * Loescht Trackpoint aus entsprechender Liste
     *
     * @param trackpoint zu loeschender Trackpoint
     */
    public void remove(Trackpoint trackpoint){
        trackpointList.remove(trackpoint);
        length--;
        // Falls Trackpoint nur noch mit Haeufigkeit 1 vorhanden, Trackpoint loeschen
        // sonst Wert um 1 verringern
        Location trackpointLocation = trackpoint.getLocation();
        if ( locationFrequencies.get(trackpointLocation) == 1 )
            locationFrequencies.remove(trackpointLocation);
        else
            locationFrequencies.put(trackpointLocation, locationFrequencies.get(trackpointLocation)-1);
    }
    /**
     * Loescht Trackpoints aus entsprechender Liste
     *
     * @param trackpointList zu loeschende Liste von Trackpoints
     */
    public void remove(TrackpointList trackpointList){
        for ( Trackpoint trackpoint : trackpointList )
            this.remove(trackpoint);
    }

    /**
     * Binaersuche nach Position eines Trackpoint mit bestimmter Zeit in der Liste
     *
     * @param timestamp Zeitvariable des zu findenden Trackpoint
     * @return Position des Trackpoints in der Liste
     * @throws NoSuchElementException, falls Element nicht in Liste existiert
     */
    private int binarySearch(DateTime timestamp) throws NoSuchElementException {
        this.sortByTime();
        // Binärsuche auf nach Zeit sortierter Liste
        int imin = 0;
        int imax = this.length-1;
        while ( imax >= imin ){
            int imid = (imin + imax)/2;
            Trackpoint midTrackpoint = this.get(imid);
            int cmp = midTrackpoint.compareTimeTo(timestamp);
            if ( cmp > 0 ){
                imax = imid-1;
            } else if ( cmp < 0 ){
                imin = imid+1;
            } else {
                return imid;
            }
        }
        // Trackpoint mit timestamp nicht enthalten
        throw new NoSuchElementException(""+imax);
    }

    /**
     * Findet Trackpoint in Trackpointliste und gibt ihn zurück
     *
     * @param timestamp Zeit zu dem entsprechender Trackpoint gefunden werden soll
     * @return gesuchter Trackpoint
     * @throws RuntimeException, falls Trackpoint nicht enthalten ist
     */
    public boolean contains(DateTime timestamp){
        try {
            int position = binarySearch(timestamp);
            return true;
        } catch(NoSuchElementException e) {
            return false;
        }
    }
    /**
     * Findet Trackpoint in Trackpointliste und gibt ihn zurück
     *
     * @param location Ort zu dem entsprechender Trackpoint gefunden werden soll
     * @return gesuchter Trackpoint
     * @throws RuntimeException, falls Trackpoint nicht enthalten ist
     */
    public boolean contains(Location location){
        for ( Trackpoint trackpoint : this.trackpointList ){
            if ( trackpoint.equals(location) )
                return true;
        }
        return false;
    }

    /**
     * Findet Trackpoint in Trackpointliste und gibt ihn zurück
     *
     * @param timestamp Zeit zu dem entsprechender Trackpoint gefunden werden soll
     * @return gesuchter Trackpoint
     * @throws RuntimeException, falls Trackpoint nicht enthalten ist
     */
    public Trackpoint get(DateTime timestamp){
        try {
            int position = binarySearch(timestamp);
            return this.get(position);
        } catch(NoSuchElementException e) {
            throw new RuntimeException("No trackpoint with " + timestamp + " found");
        }
    }
    /**
     * Findet Trackpoint in Trackpointliste und gibt ihn zurück
     *
     * @param location Ort zu dem entsprechender Trackpoint gefunden werden soll
     * @return gesuchter Trackpoint
     * @throws RuntimeException, falls Trackpoint nicht enthalten ist
     */
    public Trackpoint get(Location location){
        for ( Trackpoint trackpoint : this.trackpointList ){
            if ( trackpoint.equals(location) )
                return trackpoint;
        }
        throw new RuntimeException("No trackpoint at " + location + " found");
    }
    /**
     * Findet Trackpoint in Trackpointliste und gibt ihn zurück
     *
     * @param position Position zu dem entsprechender Trackpoint gefunden werden soll
     * @return gesuchter Trackpoint
     * @throws RuntimeException, falls Trackpoint nicht enthalten ist
     */
    public Trackpoint get(int position){
        if ( position <= this.length )
            return this.trackpointList.get(position);
        throw new RuntimeException("Position " + position + " out of bound");
    }

    // erstes Element der ArrayListe erhalten
    /**
     * Gibt ersten Trackpoint in der Liste zurueck
     *
     * @return [Trackpoint]: erster Trackpoint der Trackpointliste
     */
    public Trackpoint getFirst(){
        return this.get(0);
    }

    // letztes Elemtent der Trackpointliste erhalten
    /**
     * Gibt letzten Trackpoint in der Liste zurueck
     *
     * @return [Trackpoint]: letzter Trackpoint der Trackpointliste
     */
    public Trackpoint getLast(){
        return this.get(this.length-1);
    }

    /**
     * Findet die Position eines Trackpoint in der Trackpointliste und gibt sie zurück
     *
     * @param trackpoint Trackpoint, dessen Position gefunden werden soll
     * @return Position des gesuchten Trackpoint
     * @throws RuntimeException, falls Trackpoint nicht gefunden wurde
     */
    public int getPosition(Trackpoint trackpoint){
        return this.getPosition(trackpoint.getTime());
    }
    /**
     * Findet die Position eines Trackpoint in der Trackpointliste und gibt sie zurück
     *
     * @param timestamp Zeit zu dem entsprechende Trackpoint Position gefunden werden soll
     * @return Position des gesuchten Trackpoint
     * @throws RuntimeException, falls Trackpoint nicht gefunden wurde
     */
    public int getPosition(DateTime timestamp){
       try {
            int position = binarySearch(timestamp);
            return position;
        } catch(NoSuchElementException e) {
            throw new RuntimeException("No trackpoint with " + timestamp + " found");
        }
    }
    /**
     * Findet die Position eines Trackpoint in der Trackpointliste und gibt sie zurück
     *
     * @param location Ort zu dem entsprechende Trackpoint Position gefunden werden soll
     * @return Position des gesuchten Trackpoint
     * @throws RuntimeException, falls Trackpoint nicht gefunden wurde
     */
    public int getPosition(Location location){
        int position = 0;
        for ( Trackpoint trackpoint : this.trackpointList ){
            if ( trackpoint.equals(location) )
                return position;
            position++;
        }
        throw new RuntimeException("No trackpoint at " + location + " found");
    }

    /**
     * Gibt aus wie haeufig ein Ort als Trackpoint vorkommt
     *
     * @param location  Ort dessen Haeufigkeit überprüft werden soll
     * @return Haeufigkeit des Ortes
     */
    public int getFrequency(Location location) {
        return locationFrequencies.get(location);
    }
    /**
     * Gibt aus wie haeufig ein Ort als Trackpoint vorkommt
     *
     * @param trackpoint Trackpoint dessen Haeufigkeit überprüft werden soll
     * @return Haeufigkeit des Ortes
     */
    public int getFrequency(Trackpoint trackpoint) {
        return locationFrequencies.get(trackpoint.getLocation());
    }

    /**
     * Sortiert Liste zeitlich aufsteigend,
     * best case O(1), worst Case O(n^2)
     */
    private void sortByTime(){
        if ( !this.isSortedByTime ){
            for ( int i=1; i<this.length; i++ ){
            Trackpoint key = this.trackpointList.get(i);
            int k = i-1;
            while ( k >= 0 && (this.trackpointList.get(k).compareTimeTo(key) > 0)){
                this.trackpointList.set(k+1,this.trackpointList.get(k));
                k--;
            }
            this.trackpointList.set(k+1,key);
            }
        }
        this.isSortedByTime = true;
    }

    /**
     * Gibt Iterator ueber Trackpointliste zurueck, stellt sortierung nach Zeit sicher
     *
     * @return Iterator ueber Trackpointliste
     */
    public Iterator<Trackpoint> iterator(){
        this.sortByTime();
        return this.trackpointList.iterator();
    }
    /**
     * Gibt Iterator ueber Trackpointliste zurueck, stellt sortierung nach Zeit sicher
     *
     * @param position Position, an der Iterator gestartet werden soll
     * @return Iterator ueber Trackpointliste
     */
    public ListIterator<Trackpoint> iterator(int position){
        this.sortByTime();
        return this.trackpointList.listIterator(position);
    }
    /**
     * Gibt Iterator ueber Trackpointliste zurueck, stellt sortierung nach Zeit sicher
     *
     * @param timestamp Zeitvariable, an deren Position bzw nach deren Position der Iterator gestartet werden soll
     * @return Iterator ueber Trackpointliste
     */
    public ListIterator<Trackpoint> iterator(DateTime timestamp){
        int position;
        try {
            position = this.binarySearch(timestamp);
        } catch(NoSuchElementException e){
            position = Integer.parseInt(e.getMessage());
        }
        return this.iterator(position);
    }
    /**
     * Gibt Iterator ueber Trackpointliste zurueck, stellt sortierung nach Zeit sicher
     *
     * @param trackpoint Trackpoint, an dessen Position der Iterator gestartet werden soll
     * @return Iterator ueber Trackpointliste
     */
    public ListIterator<Trackpoint> iterator(Trackpoint trackpoint){
        return this.iterator(trackpoint.getTime());
    }


    /**
     * Gibt Hashtable mit Ortshaeufigkeiten zurueck
     *
     * @return [Hashtable<Location, Integer>] : Hastable mit Ortshaeufigkeiten der einzelnen Trackpoints
     */
    public Hashtable<Location, Integer> getLocationFrequencies(){
        return locationFrequencies;
    }

    /**
     * Gibt Stringrepraesentation der Trackpointliste aus
     *
     * @return Stringrepraesentation der Trackpointliste
     */
    public String toString(){
        String out = "[";
        for ( Trackpoint trackpoint : this )
            out += trackpoint;
        return out + "]";
    }
}