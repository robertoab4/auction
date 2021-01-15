import java.util.ArrayList;
/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }

    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                    lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                System.out.println("Lot number: " + lotNumber +
                    " already has a bid of: " +
                    selectedLot.getHighestBid().getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {
        /*if((lotNumber >= 1) && (lotNumber < nextLotNumber)) {
        // The number seems to be reasonable.
        Lot selectedLot = lots.get(lotNumber - 1);
        // Include a confidence check to be sure we have the
        // right lot.
        if(selectedLot.getNumber() != lotNumber) {
        System.out.println("Internal error: Lot number " +
        selectedLot.getNumber() +
        " was returned instead of " +
        lotNumber);
        // Don't return an invalid lot.
        selectedLot = null;
        }
        return selectedLot;
        }
        else {
        System.out.println("Lot number: " + lotNumber +
        " does not exist.");
        return null;
        }*/
        int numberLot = 0;
        Lot selectedLot = null;
        while(numberLot < lotNumber){
            if(lotNumber <= lots.size()){
                if(lots.get(numberLot).getNumber() == lotNumber){
                    selectedLot = lots.get(numberLot);    
                }
            }
            numberLot++;
        }        
        return selectedLot;
    }

    /**
     * Closes the auction selling the products if they have a bid 
     */
    public void close(){
        for (Lot lot : lots){
            if (lot.getHighestBid() == null){
                System.out.println(lot.getDescription() + " no ha obtenido ninguna puja y no se ha vendido");
            }    
            else{
                System.out.println(lot.getHighestBid().getBidder().getName() + " ha comprado " + lot.getDescription() + " por " + lot.getHighestBid().getValue() + "�");

            }
        }        
    }

    public ArrayList<Lot> getUnsoId(){
        ArrayList<Lot> lotsNotSold = new ArrayList<Lot>();
        int number = 1;
        for (Lot lot : lots){
            if (lot.getHighestBid() == null){
                lotsNotSold.add(lot);
                number++;
                /*lots.add(new Lot(nextLotNumber, description));
                nextLotNumber++;
                 */
                System.out.println(lotsNotSold);
            }
        }
        return lotsNotSold;
    }

    /** 
     * Elimina el lote con el n�mero de lote especificado.
     * @param number El n�mero del lote que hay que eliminar,
     * @return El lote con el n�mero dado o null si no existe tal lote.
     */
    public Lot removeLot(int number){
        int numberLot = 0;
        Lot selectedLot = null;
        while(numberLot < number){
            if(number <= lots.size()){
                if(lots.get(numberLot).getNumber() == number){
                    selectedLot = lots.remove(numberLot);    
                }
            }
            numberLot++;
        }        
        return selectedLot;
    }
}
