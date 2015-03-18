import java.util.*;

class TrackpointList {
  
  private ArrayList<Trackpoint> trackpoints;
  
  TrackpointList() {
     this.trackpoints = new ArrayList<Trackpoint>();
  }
  
  public void add(Trackpoint trackpoint) {
     this.trackpoints.add(trackpoint);
  }
  
  public int size() {
    return this.trackpoints.size();
  }
  
  public Trackpoint get(int pos) {
    return this.trackpoints.get(pos);
  }
  
}
