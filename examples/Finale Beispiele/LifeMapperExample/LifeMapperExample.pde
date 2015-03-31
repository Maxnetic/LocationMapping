import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.providers.*;
import locationmapping.*;

/**
 * In diesem Beispiel wird gezeigt, wie man eine Karte benutzt, die
 * stets nur maximal einen Marker anzeigt.
 */

void setup() {
  LifeMapper mapper = new LifeMapper(this);
//  mapper.setMapProvider("Hybrid");
//  mapper.setResizableAndDisableOverview(true);
  mapper.init(825, 985);
  TrackpointList data = mapper.importData("../../data/personX.csv");
  mapper.load(data);
}


void draw() {}
