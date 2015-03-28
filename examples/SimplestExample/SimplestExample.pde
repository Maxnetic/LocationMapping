import de.fhpotsdam.unfolding.*;
import locationmapping.*;

void setup() {
  Mapper mapper = new DynamicMapper(this);
  
  mapper.init();

  TrackpointList data = mapper.importData("malte_spitz.csv");

  for ( Trackpoint tp : data ) {
    mapper.addMarker(new StandardMarker(tp));
  }
}

void draw() {
}

