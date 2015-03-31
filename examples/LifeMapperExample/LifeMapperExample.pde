import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.providers.*;
import locationmapping.*;

void setup() {
  LifeMapper mapper = new LifeMapper(this);
//  mapper.setMapProvider("Hybrid");
//  mapper.setResizableAndDisableOverview(true);
  mapper.init(825, 985);
  TrackpointList data = mapper.importData("../../data/malte_spitz.csv");
  mapper.load(data);
}


void draw(){}
