import java.util.*;
import java.sql.Timestamp;

class DatenImportMalte {

    // erstellt Trackpointliste aus CSV in file
    public TrackpointList ladeStandardCSV(String file){
        String[] trackdata = loadStrings(file);
        TrackpointList trackpointList = new TrackpointList();
        for ( int i=0; i < trackdata.length; i++ ){
            String[] pieces = split(trackdata[i], ";"); // jede Zeile in Array laden
            //time | service | latitude | longitude

            Location loc = new Location ( Float.parseFloat(pieces[5]), Float.parseFloat(pieces[4]));

            int month =  Integer.parseInt(String.valueOf(pieces[0].charAt(0)) + String.valueOf(pieces[0].charAt(1) ));
            int day =  Integer.parseInt( String.valueOf(pieces[0].charAt(3)) + String.valueOf(pieces[0].charAt(4)) );
            int year =  Integer.parseInt( String.valueOf(pieces[0].charAt(6)) + String.valueOf(pieces[0].charAt(7)) +  String.valueOf(pieces[0].charAt(8)) +  String.valueOf(pieces[0].charAt(9)) );
            int hours = Integer.parseInt( String.valueOf(pieces[0].charAt(11)) + String.valueOf(pieces[0].charAt(12)) );
            int minutes =  Integer.parseInt( String.valueOf(pieces[0].charAt(14)) +String.valueOf( pieces[0].charAt(15)) );
            int seconds =  Integer.parseInt( String.valueOf(pieces[0].charAt(17)) + String.valueOf(pieces[0].charAt(18)) );
            Timestamp timestamp = new Timestamp(year, month, day, hours, minutes, seconds, 0);

            Trackpoint trackpoint = new Trackpoint(timestamp, loc, 1, pieces[2]);

            trackpointList.add(trackpoint);
        }
        return trackpointList;
    }
}
