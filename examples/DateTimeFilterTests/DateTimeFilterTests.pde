import de.fhpotsdam.unfolding.*;
import locationmapping.*;

void setup() {
  
  Mapper mapper = new StaticMapper(this);
  mapper.setStartZoomLevel(6);
  mapper.init();
  
  TrackpointList data = mapper.importData("../../data/malte_spitz.csv");
  
  DateTimeFilter januaryFilter = new DateTimeFilter().fromDate("1.1.2010").toDate("31.1.2010");
  DateTimeFilter christmasFilter = new DateTimeFilter().onDate("2009/12/24");
  DateTimeFilter newyearFilter = new DateTimeFilter().forDays("31.12.09, 1.1.10");
  DateTimeFilter holidaysFilter = new DateTimeFilter().betweenDates("24.12.2009 - 26.12.2009").setDay("31.12.2009");
  DateTimeFilter sunDateTimeFilter = new DateTimeFilter().forWeekdays("su");
  DateTimeFilter randomWeekdaysFilter = new DateTimeFilter().forWeekdays("sa-su, mo");
  
  DateTimeFilter earlybirdFilter = new DateTimeFilter().fromTime("6:00").toTime("7:30");
  DateTimeFilter nightFilter = new DateTimeFilter().betweenTimes("3 - 5");
  
    data = januaryFilter.apply(data);
//  data = christmasFilter.apply(data);
//  data = newyearFilter.apply(data);
//  data = holidaysFilter.apply(data);
//  data = sunDateTimeFilter.apply(data);
//  data = randomWeekdaysFilter.apply(data);
  
//  data = earlybirdFilter.apply(data);
//  data = nightFilter.apply(data);
  
  for ( Trackpoint tp : data ){
    StandardMarker marker = new StandardMarker(tp);
    marker.setSize(data.getFrequency(tp)*100);
    mapper.addMarker(marker);
  }
}

void draw(){}
