import de.fhpotsdam.unfolding.geo.Location;
import java.sql.Timestamp;
import java.util.*;

/**
 *
 */
class TrackpointList implements Iterable<Trackpoint> {
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
    * Fuegt Trackpoint in entsprechende Liste ein
    *
    * @param trackpoint/trackpointList [Trackpoint/TrackpointList]: einzufuegender Trackpoint oder Liste von Trackpoints
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
    }
    public void add(TrackpointList trackpointList){
        for ( Trackpoint trackpoint : trackpointList ){
            this.trackpointList.add(trackpoint);
        }
    }

    /**
     * Loescht Trackpoint aus entsprechende Liste ein
     *
     * @param trackpoint [Trackpoint] : zu loeschender Trackpoint
     */
    public void delete(Trackpoint trackpoint){
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
     * Findet Trackpoint in Trackpointliste und gibt ihn zurück
     *
     * @param timestamp/location/position [Timestamp/Location/int] : Zeit, Ort oder Position zu dem entsprechender Trackpoint gefunden werden soll
     * @return [Trackpoint]: gesuchter Trackpoint
     * @throws RuntimeException, falls Trackpoint nicht enthalten ist
     */
    public Trackpoint get(Timestamp timestamp){
        this.sortByTime();
        // Binärsuche auf nach Zeit sortierter Liste
        int imin = 0;
        int imax = this.length-1;
        while ( imax >= imin ){
            int imid = (imin + imax)/2;
            Trackpoint midTrackpoint = this.get(imid);
            int cmp = midTrackpoint.compareTimeTo(timestamp);
            if ( cmp > 0 )
                imax = imid-1;
            if ( cmp < 0 )
                imin = imid+1;
            else
                return midTrackpoint;
        }
        // Trackpoint mit timestamp nicht enthalten
        throw new RuntimeException("No trackpoint with " + timestamp + " found");
    }
    public Trackpoint get(Location location){
        for ( Trackpoint trackpoint : this.trackpointList ){
            if ( trackpoint.equals(location) )
                return trackpoint;
        }
        throw new RuntimeException("No trackpoint at " + location + " found");
    }
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
    private Trackpoint getFirt(){
        return this.get(0);
    }

    // letztes Elemtent der Trackpointliste erhalten
    /**
     * Gibt letzten Trackpoint in der Liste zurueck
     *
     * @return [Trackpoint]: letzter Trackpoint der Trackpointliste
     */
    private Trackpoint getLast(){
        return this.get(this.length-1);
    }

    /**
     * Findet die Position eines Trackpoint in der Trackpointliste und gibt sie zurück
     *
     * @param timestamp/location [Timestamp/Location]: Zeit oder Ort zu dem entsprechende Trackpoint Position gefunden werden soll
     * @return [int]: Position des gesuchten Trackpoint
     * @throws RuntimeException, falls Trackpoint nicht gefunden wurde
     */
    public int getPosition(Timestamp timestamp){
        this.sortByTime();
        // Binärsuche auf nach Zeit sortierter Liste
        int imin = 0;
        int imax = this.length-1;
        while ( imax >= imin ){
            int imid = (imin + imax)/2;
            Trackpoint midTrackpoint = this.get(imid);
            int cmp = midTrackpoint.compareTimeTo(timestamp);
            if ( cmp > 0 )
                imax = imid-1;
            if ( cmp < 0 )
                imin = imid+1;
            else
                return imid;
        }
        // Trackpoint mit timestamp nicht enthalten
        throw new RuntimeException("No trackpoint with " + timestamp + " found");
    }
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
     * @param trackpoint/location [Trackpoint/Location]: Ort/Trackpoint dessen Häufigkeit überprüft werden soll
     * @return [int]: Haeufigkeit des Ortes
     */
    public int getFrequency(Location location) {
        return locationFrequencies.get(location);
    }
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
            while ( k >= 0 && (this.trackpointList.get(k).getTimestamp().compareTo(key.getTimestamp()) > 0)){
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
     * @return [Iterator] : Iterator ueber Trackpointliste
     */
    public Iterator<Trackpoint> iterator(){
        this.sortByTime();
        return this.trackpointList.iterator();
    }

    /**
     * Gibt Hashtable mit Ortshaeufigkeiten zurueck
     *
     * @return [Hashtable<Location, Integer>] : Hastable mit Ortshaeufigkeiten der einzelnen Trackpoints
     */
    public Hashtable<Location, Integer> getLocationFrequencies(){
        return locationFrequencies;
    }
}


