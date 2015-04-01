import de.fhpotsdam.unfolding.*;
import locationmapping.*;

void setup() {
    LifeMapper mapper = new LifeMapper(this);
    mapper.init(825, 985);
    TrackpointList data = mapper.importData("malte_spitz.csv");
    mapper.load(data);
}



void draw() {}
