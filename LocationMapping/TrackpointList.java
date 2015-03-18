import de.fhpotsdam.unfolding.geo.Location;
import java.sql.Timestamp;
import java.util.*;

class TrackpointList implements Iterable<Trackpoint> {

    // Attribute
    private ArrayList<Trackpoint> trackpointList;
    private Hashtable<Location, Integer> locationFrequencies;
    private int length = 0;

    // Konstruktor erstellt leere liste
    TrackpointList() {
         trackpointList = new ArrayList<Trackpoint>();
     }

    // Methoden

    //gibt Anzahl der Trackpoints in der Naehe dieser Location an
    int getFrequency(Location location) {
        return locationFrequencies.get(location);
    }

    // fuegt Trackpoint am Ende der Liste ein
    void add(Trackpoint tp){
        Location trackpointLocation = tp.getLocation();
        trackpointList.add(tp);
        length++;
        // update Hashtable, wenn Wert vorhanden, Wert erhoehen (schoenere Variante?),
        // sonst neu anlegen
//        if (locationFrequencies.contains(trackpointLocation)){
//            locationFrequencies.put(trackpointLocation, locationFrequencies.get(trackpointLocation)+1);
//        } else {
//            locationFrequencies.put(trackpointLocation, 1);
//        }
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


