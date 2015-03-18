class TrackpointList implements Iterable<Trackpoint> {

    // Attribute
	/**
	 * ArrayList trackpointList enthaelt einzelne Trackpoints
	 */
    ArrayList<Trackpoint> trackpointList;
    /**
     * Hashtable locationFrequencies enthaelt Häufigkeiten der einzelnen Trackpoints
     */
    Hashtable<Location, Integer> locationFrequencies;
    /**
     * Länge der trackpointListe
     */
    int length = 0;
    
    /**
     * Konstruktor fuer
     * @return Objekt vom Typ ArrayList<Trackpoint>
     */
    // Konstruktor erstellt leere liste
    TrackpointList() {
         trackpointList = new ArrayList<Trackpoints>();
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
        if(!(this.isSorted())){
        	this.sortByTime();
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


