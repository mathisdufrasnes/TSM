
public class City {
	
	/*
	* City.java
	* Models a city
	*/

	    int x;
	    int y;
	    String Name;
	    
	    // Constructs a randomly placed city
	    public City(){
	        this.x = (int)(Math.random()*200);
	        this.y = (int)(Math.random()*200);
	        this.Name="Unknown";
	    }
	    
	    // Constructs a city at chosen x, y location
	    public City(int x, int y, String Name){
	        this.x = x;
	        this.y = y;
	        this.Name=Name;
	    }
	    
	    // Gets city's x coordinate
	    public int getX(){
	        return this.x;
	    }
	    
	    // Gets city's y coordinate
	    public int getY(){
	        return this.y;
	    }
	    
	    // Gets city's name
	    public String getName(){
	        return this.Name;
	    }
	    
	    // Gets the distance to given city distance=sqrt((x1-x2)^2+(y1-y2)^2)
	    public double distanceTo(City city){
	        int xDistance = Math.abs(getX() - city.getX());
	        int yDistance = Math.abs(getY() - city.getY());
	        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
	        
	        return distance;
	    }
	    
	    @Override
	    public String toString(){
	        return getName()+"("+getX()+", "+getY()+")";
	    }
	}


