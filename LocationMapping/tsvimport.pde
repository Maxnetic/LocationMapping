import java.util.*;
import java.sql.Timestamp;

class tsvimport {
    // Erstellt Trackpointliste aus TSV-File
    // Erwartetes Format
    // ....
    
    public TrackpointList import_fireflies_tsv(String file){
        String[] trackdata = loadStrings(file);
        TrackpointList trackpointList = new TrackpointList();
        for (int i = 1; i < trackdata.length; i++ ){
          // MCC | MMNC | LAC | CI | Timestamp | Latitude | Longitude | ...  
          String[] pieces = split(trackdata[i], "\t");
          //time | service | latitude | longitude
          Location loc = new Location (Float.parseFloat(pieces[5]), Float.parseFloat(pieces[6]));
          long time = Long.valueOf(pieces[4].substring(0, 1) + pieces[4].substring(2, 10));
          Timestamp timestamp = new Timestamp(time);

          Trackpoint trackpoint = new Trackpoint(timestamp, loc, 1, pieces[2]);

          trackpointList.add(trackpoint);
        }
        return trackpointList;
    }
}
