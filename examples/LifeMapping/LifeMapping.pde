import de.fhpotsdam.unfolding.*;
import locationmapping.*;

void setup() {
    LifeMapper mapper = new LifeMapper(this);
    mapper.init(1280, 800);
    TrackpointList data = mapper.importData("malte_spitz.csv");
    mapper.load(data);
}



void draw() {}
