class TrackpointList implements Iterable<Trackpoint> {

    // Attribute
    ArrayList<Trackpoint> trackpointList;
    Hashtable<Location, Integer> locationFrequencies;
    int length = 0;

    // Konstruktor erstellt leere liste
    TrackpointList() {
         trackpointList = new ArrayList<Trackpoints>();
     }

    // Methoden

    //gibt Anzahl der Trackpoints in der Nähe dieser Location an
    int getFrequency(Location location) {
        return locationFrequencies.get(location);
    }

     // testet ob schon nach Zeit sortiert ist, "vorwärts"-Sortierung
    boolean isSortedByTime(){
        for (int i = 0; i < this.length-1, i++){
            if (trackpointList.get(i).getTimestamp().after(trackpointList.get(i+1).getTimestamp())){
                    return false;
            }
        }
        return true;
    }

    void sortByTime();



    int getFrequency(Location location){
        return locationFrequencies.get(location);
    };

    // fügt Trackpoint am Ende der Liste ein
    addTrackpoint(Trackpoint tp){
        Location trackpointLocation = tp.getLocation();
        trackpointList.add(tp);
        lenghth++;
        // update Hashtable, wenn Wert vorhanden, Wert erhöhen (schönere Variante?),
        // sonst neu anlegen
        if (locationFrequencies.contains(trackpointLocation)){
            locationFrequencies.put(trackpointLocation, locationFrequencies.get(trackpointLocation)+1)
        } else {
            locationFrequencies.put(trackpointLocation, 1);
        }
    };


    //löscht Trackpoint aus der Liste
    deleteTrackpoint(Trackpoint tp){
        Location trackpointLocation = tp.getLocation();
        trackpointList.remove(tp);
        length--;
        // Falls Trackpoint nur noch mit Häufigkeit 1 vorhanden, Trackpoint löschen
        // sonst Wert um 1 verringern
        if (locationFrequencies.get(trackpointLocation) == 1){
            locationFrequencies.remove(trackpointLocation);
        } else {
            locationFrequencies.put(trackpointLocation, locationFrequencies.get(trackpointLocation)-1)
        }
    };

    // finde Trackpoint nach Timestamp
    Trackpoint find(Timestamp timestamp){

    }

    // finde Trackpoint nach Ort
    Trackpoint find(Location location){

    }

    // Iterator über TrackpointList ohne Startwert
    public Iterator<Trackpoint> iterator(){
        return new timeOrderedIterator(first);
    }

    // Iterator über TrackpointList mit Startwert
    public Iterator<Trackpoint> iterator(Trackpoint start);

    Hashtable<Location, Integer> getLocationFrequencies(){
        return locationFrequencies;
    }
}


