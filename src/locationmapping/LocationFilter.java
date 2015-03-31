package locationmapping;

import java.util.*;

import de.fhpotsdam.unfolding.geo.*;

/**
 * Die Klasse LocationFilter stellt Filterattribute zur Verfuegung, 
 * die zum Filtern nach oertlichen Angaben benoetigt werden.
 * Es kann nach Ortsangaben, Radien und Ortshaeufigkeiten gefiltert werden.
 * Die einzelnen Attribute koennen durch Methoden gesetzt 
 * und dann mit der apply-Methode auf eine TrackpointList angewandt werden.
 * LocationFilter erweitert Filter.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class LocationFilter extends Filter {
    /**
     * Mindesthaeufigkeit für den Haeufigkeitsfilter
     */
    int minfrequency = 0;
    /**
    * Ort, nach dem gefiltert wird
    */
    Location location;
    /**
    * Radius um Ort, der im Radiusfilter genutzt werden
    */
    int radius = 0;
    /**
    * Service, der im Servicefilter gefiltert wird (SMS, Internet, GPRS)
    */
    String service;
	/**
	* Anzahl der am haeufigsten besuchten Orte
	*/
	//int mostVisited = 0;


    /**
    * Setzt die Mindesthaeufigkeit
	*
    * @param minf Mindesthaeufigkeit
    */
    public void setMinFrequency(int minf){
        minfrequency = minf;
    }
	
	/**
	* Setzt die Anzahl der am haeufigsten besuchten Orte
	*
	* @param mostVisited Anzahl
	*/
	/*public void setMostVisited(int mostVisited){
		this.mostVisited = mostVisited;
	}*/

    /**
    * Setzt den Radius
    *
    * @param radius Radius, um den gefiltert wird
    */
    public void setRadius(int radius){
        this.radius = radius;
    }

    /**
    * Setzt den Ort
    *
    * @param location Ort, um den gefiltert wird
    */
    public void setLocation(Location location){
        this.location = location;
    }

    /**
    * Setzt den Service
    *
    * @param service Service, nach dem gefiltert werden soll
    */
    public void setService(String service){
        this.service = service;
    }

    /**
 	 * Vergleicht zwei TrackpointLists auf aehnliche Orte
	 *
	 * @param tpl1 erste TrackpointList zum Vergleichen
	 * @param tpl2 zweite TrackpointList zum Vergleichen
	 * @param radius Radius, um Ueberschneidungen in beiden TrackpointLists zu finden
	 * @return Liste mit Trackpoints, die innerhalb des Radius waren 
	 */
    public TrackpointList compareLocation(TrackpointList tpl1, TrackpointList tpl2, int radius){
        TrackpointList newtpl = new TrackpointList();
        for ( Trackpoint tp1 : tpl1 ){
           for ( Trackpoint tp2 : tpl2 ){
                if ( tp1.locationDistanceTo(tp2.getLocation()) <= radius ){
                    newtpl.add(tp1);
                    break;
                }
            }
        }
        return newtpl;
    }

   	/**
 	 * Vergleicht zwei TrackpointLists auf aehnliche Orte zu aehnlichen Zeiten
	 *
	 * @param tpl1 erste TrackpointList zum Vergleichen
	 * @param tpl2 zweite TrackpointList zum Vergleichen
	 * @param radius Radius, um Ueberschneidungen in beiden TrackpointLists zu finden
	 * @param minutes Minuten, die eine Ueberschneidung auseinander liegen darf
	 * @return Liste mit Trackpoints, die innerhalb des Radius waren 
	 */
    public TrackpointList compareLocationAndTime(TrackpointList tpl1, TrackpointList tpl2, int radius, int minutes){
        TrackpointList newtpl = new TrackpointList();
        for (Trackpoint tp1 : tpl1){
            for (Trackpoint tp2 : tpl2){
                if (  (tp1.locationDistanceTo(tp2.getLocation()) <= radius) && (tp1.locationDistanceTo(tp2) < minutes * 60) ){
                    newtpl.add(tp1);
                 break;
                }
            }
        }
        return newtpl;
    }


    /**
    * Filtert nach Haeufigkeit
	*
    * @param trackpointlist Liste, die gefiltert werden soll
    */
    private void frequencyFilter(TrackpointList trackpointlist){
        for(Trackpoint tp : trackpointlist){
            if(trackpointlist.getFrequency(tp) < minfrequency)
                tp.setVisible(false);
        }
    }
	
	/**
	* Berechnet die n = mostVisited am haeufigsten besuchten Orte
	*
	* @param trackpointlist Trackpointliste in der die n haeufigsten Orte gefunden werden sollen
	*/
	/*private void mostVisitedFilter(TrackpointList trackpointlist){
		if (mostVisited > 0) {
			Array[] mostVisitedTp = new Array[mostVisited];
			Set<Location> set = locationFrequencies.keySet();
			Iterator<Location> itr = set.iterator();
			for(int i = 0;i<mostVisited,i++){
				if(itr.hastNext()){
					mostVisitedTp[i] = itr.next();
				} else {
					break;
				}
			}
			while(itr.hasNext()){
				Trackpoint curr = itr.next();
				if(findMin(mostVisitedTp).getFrequency() < curr.getFrequency()){
					mostVisitedTp[findIndex(mostVisitedTp)] = curr;
				}
			}	
		}
	}*/
	
	/**
	* Findet Trackpoint mit kleinster Haeufigkeit in einem Array
	*
	* @param tp Array in dem Trackpoint gefunden werden soll
	* @return Trackpoint mit der kleinsten Haeufigkeit
	*/
	/*private Trackpoint findMin(Array[] tp){
		Trackpoint min = new Trackpoint();
		min = tp[0];
		for (int i = 1; i < tp.length(); i++){
			if (tp[i].getFrequency() < min.getFrequency()){
				min = tp[i];
			}
		}
		return min;
	}*/
	
	/**
	* Berechnet den Index des Trackpoints mit der kleinsten Haeufigkeit
	*
	* @param tp Array in dem Index gefunden werden soll
	* @return Index
	*/
	/*private int findIndex(Array[] tp){
		int index = 0;
		Trackpoint min = new Trackpoint();
		min = tp[0];
		for (int i = 0; i < tp.length(); i++){
			if (tp[i].getFrequency() < min.getFrequency()){
				index = i;
			}
		}
		return index;
	}*/

    /**
    * Filtert nach Location und Radius
	*
    * @param trackpointlist Liste, die gefiltert werden soll
    */
    private void locationFilter(TrackpointList trackpointlist){
        for( Trackpoint tp : trackpointlist ){
            if (tp.locationDistanceTo(location) > radius)
                tp.setVisible(false);
        }
    }

    /**
    * Filtert nach Service
	*
    * @param trackpointlist Liste, die gefiltert werden soll
    */
    private void serviceFilter(TrackpointList trackpointlist){
        for(Trackpoint tp : trackpointlist){
            if(!(tp.getService().equals(service))){
                tp.setVisible(false);
            }
        }
    }


    /**
     * Filtert uebergebene TrackpointList je nachdem welche Attribute zuvor gesetzt wurden
     *
     * @param trackpointlist zu filternde Trackpointliste, wird nicht veraendert
     * @return gefilterte TrackpointList
    */
    public TrackpointList apply(TrackpointList trackpointlist) {

     // Frequenzfilter
     if (minfrequency > 1) {
        frequencyFilter(trackpointlist);
     }

     // Radiusfilter
     if (radius != 0 && location != null) {
        locationFilter(trackpointlist);
     }

     //Servicefilter
     if (service != null) {
        serviceFilter(trackpointlist);
     }

     //ueberschreiben der Rauszufilternden Punkte
     TrackpointList filteredtpl = new TrackpointList();
     for(Trackpoint tp : trackpointlist){
         if(tp.getVisible() == true){
             filteredtpl.add(tp);
         }else{
             tp.setVisible(true);
         }
     }
     return filteredtpl;
    }
}
