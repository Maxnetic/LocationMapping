import de.fhpotsdam.unfolding.geo.Location;
import java.sql.Timestamp;
import java.util.*;

class TrackpointList implements Iterable<Trackpoint> {

    // Attribute
<<<<<<< HEAD
	/**
	 * ArrayList trackpointList enthaelt einzelne Trackpoints
	 */
    ArrayList<Trackpoint> trackpointList;
    /**
     * Hashtable locationFrequencies enthaelt H�ufigkeiten der einzelnen Trackpoints
     */
    Hashtable<Location, Integer> locationFrequencies;
    /**
     * L�nge der trackpointListe
     */
    int length = 0;
    
    /**
     * Konstruktor fuer
     * @return Objekt vom Typ ArrayList<Trackpoint>
     */
=======
    private ArrayList<Trackpoint> trackpointList;
    private Hashtable<Location, Integer> locationFrequencies;
    private int length = 0;

>>>>>>> 852c908006b5d19407c895e6f8651bcd67eedd3d
    // Konstruktor erstellt leere liste
    TrackpointList() {
         trackpointList = new ArrayList<Trackpoint>();
     }

    // Methoden

    //gibt Anzahl der Trackpoints in der Naehe dieser Location an
    /**
     * Gibt aus wie haufig ein Ort als Trackpoint vorkommt
     * @param Location location : Location Objekt
     * @return Objekt vom Typ Int
     */
    int getFrequency(Location location) {
        return locationFrequencies.get(location);
    }
<<<<<<< HEAD
    
    // testet ob schon nach Zeit sortiert ist, "vorwaerts"-Sortierung
    /**
     * Testet, ob eine Liste bereits aufsteigend nach Datum sortiert ist
     * @return Objekt vom Typ Boolean
     */
    boolean isSortedByTime(){
        for (int i = 0; i < this.length-1, i++){
            if (this.get(i).getTimestamp().after(this.get(i+1).getTimestamp())){
                    return false;
            }
        }
        return true;
    }

    // sortiert Trackpoints in der Liste nach Zeit mit Insertionsort
    /**
     * Sortiert Liste nach Zeit
     */
    void sortByTime(){
    	for (int i = 1, i < this.length, i++){
    		Trackpoint key = this.get(i);
    		int k = i-1;
    		while ( k >= 0 && (this.get(k).getTimestamp().compareTo(key.getTimestamp()) > 0)){
    			this.set(k+1,this.get(k));
    			k = k-1;
    		}
    		this.set(k+1,key);
    	}
    }


    

    // fuegt Trackpoint am Ende der Liste ein
    /**
     * Testet, ob eine Liste bereits aufsteigend nach Datum sortiert ist
     * @param Trackpoint tp : Trackpoint Objekt
     */
    addTrackpoint(Trackpoint tp){
=======

    // fuegt Trackpoint am Ende der Liste ein
    void add(Trackpoint tp){
>>>>>>> 852c908006b5d19407c895e6f8651bcd67eedd3d
        Location trackpointLocation = tp.getLocation();
        trackpointList.add(tp);
        length++;
        // update Hashtable, wenn Wert vorhanden, Wert erhoehen (schoenere Variante?),
        // sonst neu anlegen
<<<<<<< HEAD
        if (locationFrequencies.contains(trackpointLocation)){
            locationFrequencies.put(trackpointLocation, locationFrequencies.get(trackpointLocation)+1);
        } else {
            locationFrequencies.put(trackpointLocation, 1);
        }
        if(!(this.isSorted())){
        	this.sortByTime();
        }
=======
//        if (locationFrequencies.contains(trackpointLocation)){
//            locationFrequencies.put(trackpointLocation, locationFrequencies.get(trackpointLocation)+1);
//        } else {
//            locationFrequencies.put(trackpointLocation, 1);
//        }
>>>>>>> 852c908006b5d19407c895e6f8651bcd67eedd3d
    }


    //loescht Trackpoint aus der Liste
    void deleteTrackpoint(Trackpoint tp){
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
    public Trackpoint find(Timestamp timestamp){
        for ( int i=0; i < this.length; i++ ){
            if ( this.trackpointList.get(i).getTimestamp() == timestamp ){
                return this.trackpointList.get(i);
            }
        }
        return null;
    }
    public Trackpoint find(Location location){
        for (int i=0; i < this.length; i++){
            if (this.trackpointList.get(i).getLocation() == location){
                return this.trackpointList.get(i);
            }
        }
        return null;
    }

    // erstes Element der ArrayListe erhalten
    Trackpoint getFirt(){
        return this.trackpointList.get(0);
    }

    // letztes Elemtent der Trackpointliste erhalten
    Trackpoint getLast(){
        return this.trackpointList.get(this.length-1);
    }

   // Iterator ueber TrackpointList ohne Startwert
    public Iterator<Trackpoint> iterator(){
        return this.trackpointList.iterator();
    }

    // gibt Hashtable zurueck
    Hashtable<Location, Integer> getLocationFrequencies(){
        return locationFrequencies;
    }
}

