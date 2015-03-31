package locationmapping;

/**
 * Filter Oberklasse
 * Filter ermoeglichen aus Auswaehlen von einzelnen Trackpoints aus einer TrackpointList, 
 * die bestimmten Anforderungen genuegen. 
 * Die ausgewaehlten Trackpoints werden in einer neuen TrackpointList gespeichert.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public abstract class Filter {
    /**
     * gefilterte TrackpointListe
     */
    TrackpointList filteredList = new TrackpointList();
}
