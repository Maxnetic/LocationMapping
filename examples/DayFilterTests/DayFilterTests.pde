import de.fhpotsdam.unfolding.*;
import locationmapping.*;

void setup() {
  
  Mapper mapper = new StaticMapper(this);
  mapper.setStartZoomLevel(6);
  mapper.init();
  
  TrackpointList data = mapper.importData("../../data/malte_spitz.csv");
  
  DayFilter januaryFilter = new DayFilter().from("1.1.2010").to("31.1.2010");
  DayFilter christmasFilter = new DayFilter().on("2009/12/24");
  DayFilter newyearFilter = new DayFilter().forDays("31.12.09, 1.1.10");
  DayFilter holidaysFilter = new DayFilter().between("24.12.2009 - 26.12.2009").setDay("31.12.2009");
  DayFilter sundayFilter = new DayFilter().forWeekdays("su");
  DayFilter randomWeekdaysFilter = new DayFilter().forWeekdays("sa-su, mo");
  
  data = januaryFilter.apply(data);
//  data = christmasFilter.apply(data);
//  data = newyearFilter.apply(data);
//  data = holidaysFilter.apply(data);
//  data = sundayFilter.apply(data);
//  data = randomWeekdaysFilter.apply(data);
  
  for ( Trackpoint tp : data ){
    StandardMarker marker = new StandardMarker(tp);
    marker.setSize(data.getFrequency(tp)*100);
    mapper.addMarker(marker);
  }
}

void draw(){}
