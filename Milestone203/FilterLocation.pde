




class FilterLocation extends Filter{
  
  //Attribute
  
 int radius;

 Location location;
 
 
 
 public FilterLocation(){
     super();
 }
 
 
 public void setRadius(int radius){
    this.radius = radius;
  }
  
  public void setLocation(Location location){
    this.location = location;
  }
  
  /*
   * Filter für einen bestimmten Radius
   * @param location
   * @param radius in km
   */
   public TrackpointList apply(TrackpointList trackpointlist){
     
     for( Trackpoint tp : trackpointlist ){       
       if (tp.locationDistanceTo(location) <= radius)
         filteredtpl.add(tp);
     }
     return filteredtpl;
   }
   
 
  
  
}
