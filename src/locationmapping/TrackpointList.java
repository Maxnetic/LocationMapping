package locationmapping;

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
    * Konstruktor, erzeugt leere Trackpointliste
    *
    * @return neues leeres TrackpointList Objekt
    */
    public TrackpointList() {
        trackpointList = new ArrayList<Trackpoint>();
        locationFrequencies = new Hashtable<Location,Integer>();
    }

    /**
    * Fuegt Trackpoint in entsprechende Liste ein
    *
    * @param trackpoint [Trackpoint] : einzufuegender Trackpoint
    */
    public void add(Trackpoint trackpoint){
        Location trackpointLocation = trackpoint.getLocation();
        trackpointList.add(trackpoint);
        length++;
        // update Hashtable, wenn Wert vorhanden, Wert erhoehen (schoenere Variante?),
        // sonst neu anlegen
        if (locationFrequencies.containsKey(trackpointLocation)){
            locationFrequencies.put(trackpointLocation, locationFrequencies.get(trackpointLocation)+1);
        } else {
            locationFrequencies.put(trackpointLocation, 1);
        }
    }

    /**
     * Findet Trackpoint in Trackpointliste und gibt ihn zurück
     *
     * @param timestamp/location/position [Timestamp/Location/int] : Zeit, Ort oder Position zu dem entsprechender Trackpoint gefunden werden soll
     * @return [Trackpoint]: gesuchter Trackpoint
     * @throws RuntimeException, falls Trackpoint nicht gefunden wurde
     */
    public Trackpoint get(Timestamp timestamp){
        for ( Trackpoint trackpoint : this.trackpointList ){
            if ( trackpoint.equals(timestamp) )
                return trackpoint;
        }
        throw new RuntimeException("trackpoint with " + timestamp + " not found");
    }
    public Trackpoint get(Location location){
        for ( Trackpoint trackpoint : this.trackpointList ){
            if ( trackpoint.equals(location) )
                return trackpoint;
        }
        throw new RuntimeException("trackpoint at " + location + " not found");
    }
    public Trackpoint get(int position){
        if ( position <= this.length )
            return this.trackpointList.get(position);
        throw new RuntimeException("position " + position + " out of bound");
    }

    /**
     * Findet die Position eines Trackpoint in der Trackpointliste und gibt sie zurück
     *
     * @param timestamp/location [Timestamp/Location]: Zeit oder Ort zu dem entsprechende Trackpoint Position gefunden werden soll
     * @return [int]: Position des gesuchten Trackpoint
     * @throws RuntimeException, falls Trackpoint nicht gefunden wurde
     */
    public int getPosition(Timestamp timestamp){
        int position = 0;
        for ( Trackpoint trackpoint : this.trackpointList ){
            if ( trackpoint.equals(timestamp) )
                return position;
            position++;
        }
        throw new RuntimeException("trackpoint with " + timestamp + " not found");
    }
    public int getPosition(Location location){
        int position = 0;
        for ( Trackpoint trackpoint : this.trackpointList ){
            if ( trackpoint.equals(location) )
                return position;
            position++;
        }
        throw new RuntimeException("trackpoint at " + location + " not found");
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
     * Testet, ob eine Liste bereits aufsteigend nach Datum sortiert ist
     *
     * @return [boolean]: Wahrheitswert der aufsteigenden Sortierung der Liste nach Zeitwerten
     */
    private boolean isSortedByTime(){
        for (int i=0; i<this.length-1; i++){
            if (this.trackpointList.get(i).getTimestamp().after(this.trackpointList.get(i+1).getTimestamp())){
                    return false;
            }
        }
        return true;
    }

    /**
     * Sortiert Liste zeitlich aufsteigend
     */
    private void sortByTime(){
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
    
    public int getLength(){
      return(length);
    }
    
    
    /**
     * Loescht Trackpoint aus entsprechende Liste ein
     *
     * @param trackpoint [Trackpoint] : zu loeschender Trackpoint
     */
    public void deleteTrackpoint(Trackpoint trackpoint){
        if(!(trackpointList.contains(trackpoint))){
          return;
        }
        Location trackpointLocation = trackpoint.getLocation();
        trackpointList.remove(trackpoint);
        length--;
        // Falls Trackpoint nur noch mit Haeufigkeit 1 vorhanden, Trackpoint loeschen
        // sonst Wert um 1 verringern
        if (locationFrequencies.containsKey(trackpoint) && locationFrequencies.get(trackpointLocation) == 1)
            locationFrequencies.remove(trackpointLocation);
        else
            locationFrequencies.put(trackpointLocation, locationFrequencies.get(trackpointLocation)-1);
    }

    // erstes Element der ArrayListe erhalten
    /**
     * Gibt ersten Trackpoint in der Liste zurueck
     *
     * @return [Trackpoint] : erster Trackpoint der Trackpointliste
     */
    private Trackpoint getFirt(){
        return this.trackpointList.get(0);
    }

    // letztes Elemtent der Trackpointliste erhalten
    /**
     * Gibt letzten Trackpoint in der Liste zurueck
     *
     * @return [Trackpoint] : letzter Trackpoint der Trackpointliste
     */
    private Trackpoint getLast(){
        return this.trackpointList.get(this.length-1);
    }

    /**
     * Gibt Iterator ueber Trackpointliste zurueck, stellt sortierung nach Zeit sicher
     *
     * @return [Iterator] : Iterator ueber Trackpointliste
     */
    public Iterator<Trackpoint> iterator(){
        if ( !this.isSortedByTime() )
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


