import java.util.ArrayList;
import java.util.Arrays;

public class TSP {
	public static  ArrayList<City> destinationCities = new ArrayList<City>();
	public static void main(String[] args) {
	        destinationCities.addAll(Arrays.asList(
	        		new City(0,200,"Lisbon"),
	        		new City(50,20,"Paris"),
	        		new City(100,90,"London"),
	        		new City(20,130,"Copenhague"),
	        		new City(170,80,"Athens"),
	        		new City(150,120,"Stockholm"),
	        		new City(40,10,"Madrid"),
	        		new City(80,70,"Roma"),
	        		new City(150,30,"Berlin")));

	        // Initialize population
	      /*
	       * 
	       * Question 4-b: To complete
	       * 
	       */

	        Population newPop=new Population(50,true);
	        System.out.println("Fittest initial fitness : "+newPop.getFittest().getFitness());
	        System.out.println("Fittest initial distance : "+newPop.getFittest().getDistance());
	        // Evolve population for 100 generations
	      /*
	       * 
	       * Question 4-c: To complete
	       * 
	       * 
	       */
	        Population newPop2=new Population(50,false);
	        for(int i=0;i<100;i++)
	        {
	        	newPop=GA.evolvePopulation(newPop);
	        }
	        System.out.println("Fittest final fitness : "+newPop.getFittest().getFitness());
	        System.out.println("Fittest final distance : "+newPop.getFittest().getDistance());
	        
	        // Print final results
		/*
		 * 
		 * Questions 4-d and 4-e: To Complete
		 * 
		 */
	        
	    }
		
		
	

}
