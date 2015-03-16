// Aus Framework Version 1

// Klasse um die Handydaten von Malte Spitz zu speichern
// Jeder Trackpoint ist eine Position an der sich Malte Spitz befand
// Es enhält das Datum (wann er dort war), die Position (wo er war) und den Service (was er mit seinem Handy gemacht hat)

class Trackpoint {
  Date time; // Datum
  String service; // Service
  public Location location; // Position

  //time | service | latitude | longitude
  public Trackpoint(String[] pieces) {
    //8/31/09 8:09
    SimpleDateFormat track_format = new SimpleDateFormat("MM/dd/yy HH:mm");

    try {
      this.time = track_format.parse(pieces[0]);
    }
    catch(ParseException e) {
    }
    this.service = pieces[2];
    this.location = new Location(float(pieces[5]), float(pieces[4]));
  }
  
  public Location getLocation() {
    return this.location;
  }
}
