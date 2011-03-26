package KnightEDU;

/**
 * Represents the quantity of credits that can be earned in a course
 * @author Alexander Darino
 */
public abstract class Credits
{
    /**
     Minimum number of credits that can be earned.
     */
    protected int creditsMin;

     /**
     Creates a fixed-credit Credits object.
     @param credits quantity of credits that can be earned
     @return a fixed-credit Credits object; if credits < 0, returns <code>null</code>
     */
    public static Credits createCredits(int credits)
    {
        if (credits < 0) return null;
        return new Credits.Fixed(credits);
    }

    /**
    Creates a variable-credit Credits object.
     @param creditsMin minimum quantity number of credits that can be earned
     @param creditsMax maximum quantity number of credits that can be earned
     @return a fixed-credit Credits object; if credits &lt; 0 or creditsMax &lt; creditsMin, returns <code>null</code>
     */
    public static Credits createCredits(int creditsMin, int creditsMax)
    {
        if (creditsMin < 0 || creditsMax < creditsMin) return null;
        return new Credits.Variable(creditsMin, creditsMax);
    }

    /**
    Constructs a new Credits object specifying the minimum quantity of credits that can be earned.
     @param creditsMin minimum quantity number of credits that can be earned
     */
    protected Credits(int creditsMin)
    {
        this.creditsMin = creditsMin;
    }

    /**
     Returns the minimum quantity number of credits that can be earned.
     @return the minimum quantity number of credits that can be earned
     */
    public int getMinCredits()
    {
        return creditsMin;
    }

    /**
     Returns the maximum quantity number of credits that can be earned.
     @return the maximum quantity number of credits that can be earned
     */
    public abstract int getMaxCredits();

    /**
     Represents a fixed-credit Credits object
     */
    protected static class Fixed extends Credits
    {

        /**
         Constructs a new Credits.Fixed object specifying the quantity of credits that can be earned.
         @param credits quantity of credits that can be earned
         */
        protected Fixed(int credits)
        {
            super(credits);
        }

        /**
         Returns the quantity of credits that can be earned
         @return quantity of credits that can be earned
         */
        @Override
        public int getMaxCredits()
        {
            return creditsMin;
        }
    }

    /**
    Represents a variable-credit Credits object
     */
    protected static class Variable extends Credits
    {
        /**
        Maximum number of credits that can be earned.
         */
        protected int creditsMax;

        /**
        Constructs a new Credits.Variable object specifying the minimum and maximum quantity of credits that can be earned.
         @param creditsMin minimum quantity of credits that can be earned
         @param creditsMax maximum quantity of credits that can be earned
         */
        protected Variable(int creditsMin, int creditsMax)
        {
            super(creditsMin);
            this.creditsMax = creditsMax;
        }

        /**
         Returns the maximum quantity of credits that can be earned
         @return maximum quantity of credits that can be earned
         */
        @Override
        public int getMaxCredits()
        {
            return creditsMax;
        }
    }
}
