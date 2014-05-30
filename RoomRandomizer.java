import java.util.HashMap;
import java.util.Random;

public class RoomRandomizer {
    /** The seed to stop the random. */
    private static Long seed = null;
    /** The room array. */
    private Room[] aTabRoom;
    /** The Random object**/
    private Random aRandom;
    
    /**
     * Instantiates a new room randomizer.
     * It convert a HashMap in a array.
     *
     * @param pListeRoom the liste room
     */
    public RoomRandomizer(final HashMap <String,Room> pListRoom)
    {
    	if (seed == null)
    		this.aRandom = new Random();
    	else
    		this.aRandom = new Random(seed);
        this.aTabRoom = new Room [pListRoom.size()];
        int vI = 0;
        for (String vS : pListRoom.keySet ())
        {
            this.aTabRoom[vI] = pListRoom.get (vS);
            vI = vI + 1;
        }
    }
    
    /**
     * Next room.
     *
     * @return a random Room inside the room list
     */
    public Room nextRoom()
    {
    	if (seed != null)
    		this.aRandom = new Random(seed);
        return this.aTabRoom[aRandom.nextInt(this.aTabRoom.length)];
    }
    
    /**
     * Sets the seeed.
     *
     * @param pSeed the new seed
     */
    public static void setSeed(final Long pSeed)
    {
    	RoomRandomizer.seed = pSeed;
    }
}
