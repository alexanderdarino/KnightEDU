package KnightEDU;

/**
 *
 * @author Alexander Darino
 */
public abstract class Location implements Comparable<Location>{

    public int compareTo(Location o)
    {
        return toString().compareTo(o.toString());
    }

    public abstract String toString();

    /**
     *
     */
    public class BR extends Location
    {
        /**
         *
         */
        protected String location;

        /**
         *
         * @param building
         * @param room
         */
        public BR(String building, String room)
        {
            location = building + "-" + room;
        }
        @Override
        public String toString()
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
