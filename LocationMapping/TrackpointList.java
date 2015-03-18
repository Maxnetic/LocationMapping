import de.fhpotsdam.unfolding.geo.Location;
import java.sql.Timestamp;
import java.util.*;

/**
 *
 */
class TrackpointList implements Iterable<Trackpoint> {
    // Attribute
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

    // Konstruktor

    // Konstruktor erstellt leere liste
    /**
     * Konstruktor, erzeugt leere Trackpointliste
     *
     * @return [ArrayList<Trackpoint>] : leere Trackpointliste
     */
    public TrackpointList() {
         trackpointList = new ArrayList<Trackpoint>();
         locationFrequencies = new Hashtable<Location,Integer>();
     }

    /**
     * Gibt aus wie haeufig ein Ort als Trackpoint vorkommt
     *
     * @param location [Location] : Ortsinformation fuer Trackpoint
     * @return [int] : Haeufigkeit des Ortes
     */
    public int getFrequency(Location location) {
        return locationFrequencies.get(location);
    }


    // testet ob schon nach Zeit sortiert ist, "vorwaerts"-Sortierung
    /**
     * Testet, ob eine Liste bereits aufsteigend nach Datum sortiert ist
     *
     * @return [boolean]: Wahrheitswert der Sortierung der Liste
     */
    private boolean isSortedByTime(){
        for (int i=0; i<this.length-1; i++){
            if (this.trackpointList.get(i).getTimestamp().after(this.trackpointList.get(i+1).getTimestamp())){
                    return false;
            }
        }
        return true;
    }

    // sortiert Trackpoints in der Liste nach Zeit mit Insertionsort
    /**
     * Sortiert Liste nach Zeit
     */
    private void sortByTime(){
        for (int i=1; i<this.length; i++){
            Trackpoint key = this.trackpointList.get(i);
            int k = i-1;
            while ( k >= 0 && (this.trackpointList.get(k).getTimestamp().compareTo(key.getTimestamp()) > 0)){
                this.trackpointList.set(k+1,this.trackpointList.get(k));
                k = k-1;
            }
            this.trackpointList.set(k+1,key);
        }
    }

    // fuegt Trackpoint am Ende der Liste ein
    /**
    * Fuegt Trackpoint in entsprechende Liste ein
    *
    * @param tp [Trackpoint] : einzufuegender Trackpoint
    */
    public void add(Trackpoint tp){
        Location trackpointLocation = tp.getLocation();
        trackpointList.add(tp);
        length++;
        // update Hashtable, wenn Wert vorhanden, Wert erhoehen (schoenere Variante?),
        // sonst neu anlegen
        if (locationFrequencies.containsKey(trackpointLocation)){
            locationFrequencies.put(trackpointLocation, locationFrequencies.get(trackpointLocation)+1);
        } else {
            locationFrequencies.put(trackpointLocation, 1);
        }
    }


    //loescht Trackpoint aus der Liste
    /**
     * Loescht Trackpoint aus entsprechende Liste ein
     *
     * @param tp [Trackpoint] : zu loeschender Trackpoint
     */
    public void deleteTrackpoint(Trackpoint tp){
        Location trackpointLocation = tp.getLocation();
        trackpointList.remove(tp);
        length--;
        // Falls Trackpoint nur noch mit Haeufigkeit 1 vorhanden, Trackpoint loeschen
        // sonst Wert um 1 verringern
      if (locationFrequencies.get(trackpointLocation) == 1){
        locationFrequencies.remove(trackpointLocation);
       } else {
        locationFrequencies.put(trackpointLocation, locationFrequencies.get(trackpointLocation)-1);
       }
    }

    // finde Trackpoint nach Timestamp
    /**
     * Findet Trackpoint in Trackpointliste nach Timestamp
     *
     * @param timestamp [Timestamp] : Timestamp,zu dem entsprechender Trackpoint gefunden werden soll
     * @return [Trackpoint] : Trackpoint, der zu uebergebenem Timestamp in der Trackliste steht
     */
    public Trackpoint find(Timestamp timestamp){
        for ( int i=0; i < this.length; i++ ){
            if ( this.trackpointList.get(i).getTimestamp() == timestamp ){
                return this.trackpointList.get(i);
            }
        }
        return null;
    }

    // finde Trackpoint nach Ort
    /**
     * Findet Trackpoint in Trackpointliste nach Ortsangabe
     *
     * @param location [Location] : Ort,zu dem entsprechender Trackpoint gefunden werden soll
     * @return [Trackpoint] : Trackpoint, der zu uebergebenem Ort in der Trackliste steht
     */
    public Trackpoint find(Location location){
        for (int i=0; i < this.length; i++){
            if (this.trackpointList.get(i).getLocation() == location){
                return this.trackpointList.get(i);
            }
        }
        return null;
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

   // Iterator ueber TrackpointList ohne Startwert
    /**
     * Gibt Iterator ueber Trackpointliste zurueck
     *
     * @return [Iterator] : Iterator ueber Trackpointliste
     */
    public Iterator<Trackpoint> iterator(){
        return this.trackpointList.iterator();
    }

    // gibt Hashtable zurueck
    /**
     * Gibt Hashtable mit Ortshaeufigkeiten zurueck
     *
     * @return [Hashtable] : Hastable mit Ortshaeufigkeiten der einzelnen Trackpoints
     */
    public Hashtable<Location, Integer> getLocationFrequencies(){
        return locationFrequencies;
    }
}


