import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.providers.*;
import locationmapping.*;

void setup() {
  LifeMapper mapper = new LifeMapper(this);
  // Mapper mapper = new StaticMapper(this);
  mapper.init();  
  TrackpointList data = mapper.importData("../../data/malte_spitz.csv");
  mapper.load(data);
}

void draw() {
}
