package KnightEDU;

/**
 * @author Alexander Darino
 * @version 03/12/11
 */
public class CourseID implements Comparable<CourseID>{

    /**

     @param encoding
     */
    public CourseID(String encoding)
    {
        this.encoding = encoding;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CourseID other = (CourseID) obj;
        if ((this.encoding == null) ? (other.encoding != null) : !this.encoding.equals(other.encoding)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + (this.encoding != null ? this.encoding.hashCode() : 0);
        return hash;
    }

    /**

     */
    protected final String encoding;

    @Override
    public String toString()
    {
        return encoding;
    }

    public int compareTo(CourseID o)
    {
        return encoding.compareTo(o.toString());
    }

    /**
     *
     */
    public static class PNS extends CourseID{
        /**

         */
        /**

         */
        /**

         */
        protected final String prefix, number, suffix;

        /**

         @param prefix
         @param number
         @param suffix
         @return
         */
        public static PNS create(String prefix, String number, String suffix)
        {
            if (!(isValidPrefix(prefix) && isValidNumber(number) && isValidSuffix(suffix)))
                return null;

            return new PNS(prefix, number, suffix);
        }

        /**

         @param prefix
         @param number
         @param suffix
         */
        protected PNS(String prefix, String number, String suffix)
        {
            super (prefix + number + suffix);
            this.prefix = prefix;
            this.number = number;
            this.suffix = suffix;
        }

        /**

         @return
         */
        public String getNumber()
        {
            return number;
        }

        /**

         @return
         */
        public String getPrefix()
        {
            return prefix;
        }

        /**

         @return
         */
        public String getSuffix()
        {
            return suffix;
        }

        /**

         @param s
         @return
         */
        public static boolean isValidPrefix(String s)
        {
            if (s == null) return false;
            for (int i = 0; i < s.length(); i++)
                if (!Character.isLetter(s.charAt(i))) return false;


            return true;
        }

        /**

         @param s
         @return
         */
        public static boolean isValidSuffix(String s)
        {
            if (s == null) return false;
            for (int i = 0; i < s.length(); i++)
                if (!Character.isLetter(s.charAt(i))) return false;
            return true;
        }

        public static boolean isValidNumber(String number)
        {
            if (number == null) return false;

            for (int i = 0; i < number.length(); i++)
                if (!Character.isDigit(number.charAt(i))) return false;

            return true;
        }

    }

}
