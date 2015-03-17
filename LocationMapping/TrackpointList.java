class TrackpointList implements Iterable<Trackpoint>, Iterable<Pair<Location, Integer>> { //?? iterators seltsam
    // Attribute
    ArrayList<Trackpoint> trackpointList;
    Hashtable<Location, Integer> locationFrequencies;
    int length;
    double locationAccuracy;
    String name; // oder int id
    Trackpoint first;
    Trackpoint last;

    // Konstruktor
    TrackpointList(); // erstellt leere liste

    // Methoden
    getFrequency(double longitude, double lattitude);
    getFrequency(Location location);

    addTrackpoint(Trackpoint tp); // muss auch dictionary updaten
    deleteTrackpoint(Trackpoint tp);

    Trackpoint find(Timestamp timestamp);
    Trackpoint find(Location location);
    Trackpoint find(double longitude, double lattitude);

    public Iterator<Trackpoint> iterator(){
        return new timeOrderedIterator(first);
    }
    public Iterator<Trackpoint> timeOrderedIterator(Trackpoint start);
    public Iterator<Pair<Location, Integer>> locationFrequencyIterator();
}