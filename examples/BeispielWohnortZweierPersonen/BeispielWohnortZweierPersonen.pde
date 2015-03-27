import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;


StaticMapper mapper = new StaticMapper(this);
  

void setup() {
  hint(ENABLE_RETINA_PIXELS);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList list1;
  list1 = mapper.importData("malte_spitz.csv");
  TrackpointList list2;
  list2 = mapper.importData("max_mittel.csv");
    
  DateTimeFilter wohnortfilter = new DateTimeFilter();
  wohnortfilter.setStartTime("02:00");
  wohnortfilter.setEndTime("05:00");
  LocationFilter frequencyfilter = new LocationFilter();
  frequencyfilter.setMinFrequency(2000);
  TrackpointList moeglicheWohnorteMalte;
  
  
  moeglicheWohnorteMalte = wohnortfilter.apply(list1);
  moeglicheWohnorteMalte = frequencyfilter.apply(moeglicheWohnorteMalte);
  
  DateTimeFilter jsonwohnortfilter = new DateTimeFilter();
  jsonwohnortfilter.setStartTime("02:00");
  jsonwohnortfilter.setEndTime("05:00");
  TrackpointList moeglicheWohnorteMax;
  
  moeglicheWohnorteMax = jsonwohnortfilter.apply(list2);
  moeglicheWohnorteMax = frequencyfilter.apply(moeglicheWohnorteMax);
  
    
  for ( Trackpoint tp : moeglicheWohnorteMalte ) {
    MarkerLabeled wohnortMalte = new MarkerLabeled(tp);
    wohnortMalte.setLabel("  möglicher Wohnort von Malte");
    wohnortMalte.setColor(200,0,200);
    wohnortMalte.setTransparency(50);
    mapper.addMarker(wohnortMalte);
  }
  for (Trackpoint tp : moeglicheWohnorteMax){
    MarkerLabeled wohnortMax = new MarkerLabeled(tp);
    wohnortMax.setColor(0,200,200);
    wohnortMax.setTransparency(50);
    wohnortMax.setLabel("  möglicher Wohnort von Max");
    mapper.addMarker(wohnortMax);
  }
    
    
      
}

void draw() {
      
}

