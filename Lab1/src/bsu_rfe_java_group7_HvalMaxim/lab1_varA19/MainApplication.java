package bsu_rfe_java_group7_HvalMaxim.lab1_varA19;

public class MainApplication
{
    @SuppressWarnings ("unchecked")
    public static void main(String[] args) throws Exception
    {
        Food[] breakfast = new Food[20];
        breakfast[0] = new IceCream("шоколад");
        breakfast[1] = new Apple("средний");
        breakfast[2] = new Cheese();
        breakfast[3] = new IceCream("карамель");
        int itemsSoFar = 0;
        for(String arg:args)
        {
            String parts[] = arg.split("/");
            if (parts[0].equals("Cheese"))
            {
                breakfast[itemsSoFar] = new Cheese();
            } else
            if (parts[0].equals("Apple"))
            {
                breakfast[itemsSoFar] = new Apple(parts[1]);
            } else
            if (parts[0].equals("IceCream"))
            {
                    breakfast[itemsSoFar] = new IceCream(parts[1]);
            }
        }
        int ApplesConsumed = 0;
        int CheeseConsumed = 0;
        int IceCreamConsumed = 0;
        for (Food item: breakfast)
            if (item!=null)
            {
                item.consume();
                if (item.equals(new Apple("")))
                    ApplesConsumed++;
                if (item.equals(new Cheese()))
                    CheeseConsumed++;
                if (item.equals(new IceCream("")))
                    IceCreamConsumed++;
            }
            else
                break;
        System.out.println("\nСъедено яблок:" + ApplesConsumed +
                "\nСъедено порций сыра:" + CheeseConsumed +
                "\nСъедено порций мороженого:" + IceCreamConsumed +
                "\nВсего хорошего!");
    }
}