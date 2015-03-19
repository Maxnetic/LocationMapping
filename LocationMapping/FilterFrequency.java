import de.fhpotsdam.unfolding.geo.Location;

class FilterFrequency extends Filter{
    private TrackpointList filteredList = new TrackpointList();
    /**
    * Mindesthaeufigkeit, nach der gefiltert werden soll
    */
    private int minFrequency = 1;
    /**
    * Radius für
    */
    private double accuracy = 0.00001;

    /**
    * Setzt Mindesthaeufigkeit
    *
    * @param minFrequency [int] : Mindesthaeufigkeit
    */
    public void setMinFrequency(int minFrequency){
        this.minFrequency = minFrequency;
    }

    /**
    * Setzt Mindesthaeufigkeit
    *
    * @param minFrequency [int] : Mindesthaeufigkeit
    */
    public void setAccuracy(double accuracy){
        this.accuracy = accuracy;
    }

    /**
    * Rundet Zahl auf ganzzahliges vielfaches eines Inkrements
    *
    * @param number [double]: zu rundende Zahl
    * @param increment [double]: Inkrement zu dessen vielfachem gerundet werden soll
    */
    static double round(double number, double increment){
        return ((double) Math.round(number/increment))*increment;
    }

    /**
    * Filter, der alle Trackpoints filtert, die oefter als angegebene Mindesthaeufigkeit besucht wurden
    *
    * @param trackpointList [TrackpointList]: die zu filternde Trackpointliste
    * @return [TrackpointList]: gefilterte Trackpointliste
    */
    public TrackpointList apply(TrackpointList trackpointList){
        System.out.println(accuracy);
        System.out.println(minFrequency);
        // Runde Location bei allen Trackpoints
        TrackpointList roundedList = new TrackpointList();
        for ( Trackpoint trackpoint : trackpointList ){
            Location roundedLocation = new Location(round(trackpoint.getLattitude(), this.accuracy), round(trackpoint.getLongitude(), this.accuracy));
            Trackpoint roundedTrackpoint = new Trackpoint(trackpoint.getTimestamp(), roundedLocation, trackpoint.getId(), trackpoint.getService());
            roundedTrackpoint.setLabel(trackpoint.getLabel());
            roundedList.add(roundedTrackpoint);
        }
        // Eliminiere doppelte Tackpoints aus Liste
        for ( Trackpoint trackpoint : roundedList ){
            if ( !this.filteredList.contains(trackpoint) && roundedList.getFrequency(trackpoint) >= minFrequency ){
                this.filteredList.add(trackpoint);
                this.filteredList.setFrequency(trackpoint, roundedList.getFrequency(trackpoint));
            }
        }
        return this.filteredList;
//        return roundedList;
    }
}
