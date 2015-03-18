import java.lang.*;
import java.util.*;
import de.fhpotsdam.unfolding.geo.*;

class TrackpointList implements Iterable<Trackpoint> {

    // Attribute
    ArrayList<Trackpoint> trackpointList;
    Hashtable<Location, Integer> locationFrequencies;
    int length = 0;

    // Konstruktor erstellt leere liste
    TrackpointList() {
         trackpointList = new ArrayList<Trackpoint>();
     }

    // Methoden

    //gibt Anzahl der Trackpoints in der Naehe dieser Location an
    int getFrequency(Location location) {
        return locationFrequencies.get(location);
    }

     // testet ob schon nach Zeit sortiert ist, "vorwaerts"-Sortierung
    boolean isSortedByTime(){
        for (int i = 0; i < this.length-1, i++){
            if (this.get(i).getTimestamp().after(this.get(i+1).getTimestamp())){
                    return false;
            }
        }
        return true;
    }

    void sortByTime();



    int getFrequency(Location location){
        return locationFrequencies.get(location);
    }

    // fuegt Trackpoint am Ende der Liste ein
    addTrackpoint(Trackpoint tp){
        Location trackpointLocation = tp.getLocation();
        trackpointList.add(tp);
        lenghth++;
        // update Hashtable, wenn Wert vorhanden, Wert erhoehen (schoenere Variante?),
        // sonst neu anlegen
        if (locationFrequencies.contains(trackpointLocation)){
            locationFrequencies.put(trackpointLocation, locationFrequencies.get(trackpointLocation)+1);
        } else {
            locationFrequencies.put(trackpointLocation, 1);
        }
    }


    //loescht Trackpoint aus der Liste
    deleteTrackpoint(Trackpoint tp){
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
    Trackpoint find(Timestamp timestamp){
    	for (int i = 0, i < this.length, i++){
    		if (this.get(i).getTimestamp() == timestamp){
    			return this.get(i);
    		}
    	}
    	return null;
    }

    // finde Trackpoint nach Ort
    Trackpoint find(Location location){
    	for (int i = 0,i < this.length, i++){
    		if (this.get(i).getLocation() == location){
    			return this.get(i);
    		}
    	}
    	return null;
    }
    
    // erstes Element der ArrayListe erhalten
    Trackpoint getFirt(){
    	return this.get(0);
    }
    
    // letztes Elemtent der Trackpointliste erhalten
    Trackpoint getLast(){
    	return this.get(this.lenght-1);
    }
    
   // Iterator ueber TrackpointList ohne Startwert
    public Iterator<Trackpoint> iterator(){
        return new timeOrderedIterator(this.getFirst());
    }

    // Iterator ueber TrackpointList mit Startwert
    public Iterator<Trackpoint> iterator(Trackpoint start);
    
    // gibt Hashtable zurueck
    Hashtable<Location, Integer> getLocationFrequencies(){
        return locationFrequencies;
    }
}


