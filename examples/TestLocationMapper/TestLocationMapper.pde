import de.fhpotsdam.unfolding.*;
import locationmapping.*;

LocationMapper mapper = new LocationMapper(this);

void setup() {
    mapper.init();
}

void draw() {
    mapper.update();
}

