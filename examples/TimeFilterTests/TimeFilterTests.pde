import de.fhpotsdam.unfolding.*;
import locationmapping.*;

void setup() {
  
  Mapper mapper = new StaticMapper(this);
  mapper.setStartZoomLevel(6);
  mapper.init();
  
  TrackpointList data = mapper.importData("../../data/malte_spitz.csv");
  
  TimeFilter earlybirdFilter = new TimeFilter().from("6:00").to("7:30");
  
  data = earlybirdFilter.apply(data);
  
  for ( Trackpoint tp : data ){
    StandardMarker marker = new StandardMarker(tp);
    marker.setSize(data.getFrequency(tp)*100);
    mapper.addMarker(marker);
  }
}

void draw(){}
