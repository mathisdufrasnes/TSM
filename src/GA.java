import java.util.ArrayList;

public class GA {
	/* GA parameters */
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    // Evolves a population over one generation
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);
        for(int i=0;i<(newPopulation.populationSize()/2);i++)
        {
	        Tour t=tournamentSelection(pop);
	    	Tour t2=tournamentSelection(pop);
	    	newPopulation.saveTour(i*2,crossover(t,t2));
	    	newPopulation.saveTour((i*2)+1,crossover(t2,t));
	    	mutate(newPopulation.getTour(i*2));
	    	mutate(newPopulation.getTour((i*2)+1));
        }
        /*for(int i=0;i<(newPopulation.populationSize()/2)-1;i++)
        {
        	newPopulation.saveTour(i*2,crossover(pop.getTour(i*2),pop.getTour((i*2)+1)));
        	newPopulation.saveTour((i*2)+1,crossover(pop.getTour((i*2)+1),pop.getTour(i*2)));
        	mutate(newPopulation.getTour(i*2));
        	mutate(newPopulation.getTour((i*2)+1));
        }*/
        return newPopulation;
    }
    public static Population evolvePopulationRWS(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);
        for(int i=0;i<(newPopulation.populationSize()/2);i++)
        {
	        Tour t=RWS(pop);
	    	Tour t2=RWS(pop);
	    	newPopulation.saveTour(i*2,crossover(t,t2));
	    	newPopulation.saveTour((i*2)+1,crossover(t2,t));
	    	mutate(newPopulation.getTour(i*2));
	    	mutate(newPopulation.getTour((i*2)+1));
        }
        /*for(int i=0;i<(newPopulation.populationSize()/2)-1;i++)
        {
        	newPopulation.saveTour(i*2,crossover(pop.getTour(i*2),pop.getTour((i*2)+1)));
        	newPopulation.saveTour((i*2)+1,crossover(pop.getTour((i*2)+1),pop.getTour(i*2)));
        	mutate(newPopulation.getTour(i*2));
        	mutate(newPopulation.getTour((i*2)+1));
        }*/
        return newPopulation;
    }
    // Applies crossover to a set of parents and creates offspring : Davi's order 
    public static Tour crossover(Tour parent1, Tour parent2) {
        // Create new child tour
        Tour child = new Tour();
        int limit1 = (int) (Math.random() * parent1.tourSize());
        int limit2 = (int) (Math.random() * parent1.tourSize());
        if(limit1>limit2)
        {
        	int temp=limit1;
        	limit1=limit2;
        	limit2=temp;
        }
        ArrayList<City> CitiesInsideLimits=new ArrayList<City>();
        for(int i=limit1;i<=limit2;i++)
        {
        	CitiesInsideLimits.add(parent1.getCity(i));
        }
        
        int i=0,j=0;
        while(i<parent1.tourSize())
        {
        	if(i<limit1 || i>limit2)
        	{
        		if(!(CitiesInsideLimits.contains(parent2.getCity(j)))) 
        		{
        			child.setCity(i, parent2.getCity(j));
        			i++;
        		}
        		j++;
        	}
        	else if(i>=limit1 && i<=limit2)
        	{
        		child.setCity(i, parent1.getCity(i));
        		i++;
        	}
        }
        return child;
    }

    // Mutate a tour using swap mutation
    private static void mutate(Tour tour) {
        for(int i=0;i<tour.tourSize();i++)
        {
        	double random=(double) (Math.random() * 100);
        	if(random<1.5)
        	{
        		int random2=(int)(Math.random() * tour.tourSize());
        		while(random2==i)
        		{
        			random2=(int)(Math.random() * tour.tourSize());
        		}
        		City temp=tour.getCity(random2);
        		tour.setCity(random2,tour.getCity(i));
        		tour.setCity(i, temp);
        	}
        }
    }

    // Selects candidate tour for crossover using tournament method
    private static Tour tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveTour(i, pop.getTour(randomId));
           
        }
             // Get the fittest tour
        Tour fittest = tournament.getFittest();
        return fittest;
    }
    
    //Select candidate tour for crossover using a RWS 
    private static Tour RWS(Population pop) {
    	ArrayList<Double> fitness_share=new ArrayList<Double>();
    	double sum=0;
    	Tour candidate= new Tour();
    	//Calculate sum of fitness values of all individuals in the population
    	for(int i=0;i<pop.populationSize();i++) {
    		sum=sum+pop.getTour(i).getFitness();
    	}
    	for(int i=0;i<pop.populationSize();i++) {
    		fitness_share.add((pop.getTour(i).getFitness())/sum);
    	}
    	//Calculate and Save fitness share for all individuals in population
    	double randomId = Math.random();
    	double cumul=fitness_share.get(0);
    	int i=0;
    	while(randomId>cumul)
    	{
    		i++;
    		cumul=cumul+fitness_share.get(i);
    	}
    	//Select individual based on its fitness share value and random generated value between 0 and 1
    	candidate=pop.getTour(i);
    	return candidate;
    }

}
