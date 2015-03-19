public class FrequencyFilter {
    private TrackpointList filteredList = new TrackpointList();
    private int minFrequency = 1;
    private double radius = 0;

    static double round(double number, double increment){
        return ((double) Math.round(number/increment))*increment;
    }

    public TrackpointList apply(TrackpointList trackpointList){
        for ( Trackpoint trackpoint : trackpointList ){
            if ( !this.filteredList.contains(trackpoint) ){
                filteredList.add(trackpoint);
                filteredList.setFrequency(trackpoint, trackpointList.getFrequency(trackpoint));
            }
        }
        return this.filteredList;
    }
}