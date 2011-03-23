package KnightEDU.courses;

/**
 * @author Alexander Darino
 * @version 03/12/11
 */
public abstract class CourseID implements Comparable<CourseID>{

    /**

     @param encoding
     */
    protected CourseID(String encoding)
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

}
