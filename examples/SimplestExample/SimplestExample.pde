import de.fhpotsdam.unfolding.*;
import org.joda.time.*;
import locationmapping.*;

import de.fhpotsdam.unfolding.providers.*;

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

