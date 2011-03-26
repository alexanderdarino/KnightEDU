package KnightEDU;

/**
 * Represents the grade earned in a class.
 * @author Alexander Darino
 */
public abstract class Grade implements Comparable<Grade>{

    /**
     * Returns the numerically equivalent value for this grade.
     * @return numerically equivalent value for this grade
     */
    public abstract double numericEquivalent();

    /**
     * Compares two grades based on their numerically equivalent values
     * @param o grade to be compared
     * @return
     * <ul><li>1 if this grade is greater than the argument</li>
     * <li>0 if the grades are the same</li>
     * <li>-1 if this grade is less than the argument</li></ul>
     */
    public int compareTo(Grade o) {
        return Double.compare(numericEquivalent(), o.numericEquivalent());
    }

    /**
    The type of grade assigned
     */
    public static enum Type
    {
        /**
         Letter grade (including +/-)
         */
        LETTER,
        /**
         Satisfactory/Unsatisfactory (also known as Pass/Fail)
         */
        SU
    }

    /**
    Represents a letter grade.
     */
    public static class Letter extends Grade
    {
        /**
        The letter grade
         */
        protected char letter;
        /**
        An optional plus/minus letter grade modifier
         */
        protected char suffix;

        /**
        Constructs a new letter grade
         @param letter the letter grade
         @param suffix a plus/minus letter grade modifier
         @return a new Grade.Letter object. If the letter is not A-F, or if the suffix is not
         '+', '-', or '\0', then <code>null</code> is returned
         */
        public static Letter create(char letter, char suffix)
        {
            char upperLetter = Character.toUpperCase(letter);
            if (upperLetter < 'A' || upperLetter > 'F') return null;
            if (!(suffix == '\0' || suffix == '+' || suffix == '-')) return null;
            if (upperLetter == 'A' && suffix == '+') return null;// no F+/

            return new Letter(letter, suffix);
        }

        /**
        Constructs a new letter grade
         @param letter the letter grade
         @return a new Grade.Letter object. If the letter is not A-F,
         then <code>null</code> is returned
         */
        public static Letter create(char letter)
        {
            return create(letter, '\0');
        }

        /**
         Constructs a new Grade.Letter object
         @param letter the letter grade
         @param suffix a plus/minus letter grade modifier
         */
        protected Letter(char letter, char suffix)
        {
            this.letter = letter;
            this.suffix = suffix;
        }

        /**
         Returns the numeric equivalent of this grade.
         @return <ul>
         <li>A: 4.0</li>
         <li>A-: 3.75</li>
         <li>B+: 3.25</li>
         <li>B: 3.0</li>
         <li>B-: 2.75</li>
         <li>C+: 2.25</li>
         <li>C: 2.0</li>
         <li>C-: 1.75</li>
         <li>D+: 1.25</li>
         <li>D: 1.0</li>
         <li>D-: 0.75</li>
         <li>F: 0.0</li></ul>
         */
        @Override
        public double numericEquivalent() {
            double base = 4 - (letter - 'A');
            if (suffix == '+') base += .25;
            else if(suffix == '-') base += .25;
            return base;
        }

        /**
         Returns the concatenation of the letter grade and plus/minus modifier.
         @return letter + suffix
         */
        @Override
        public String toString() {
            return String.valueOf(letter) + suffix;
        }
    }

    /**
    Represents a Satisfactory/Unsatisfactory grade
     */
    public static class SU extends Grade
    {
        /**
        Represents whether the grade is satisfactory
         */
        protected boolean satisfactory;

        /**
        Constructs a new Grade.SU object
         @param satisfactory whether the grade is satisfactory
         */
        public SU(boolean satisfactory)
        {
            this.satisfactory = satisfactory;
        }

        /**
         Returns <code>true</code> if the grade is satisfactory.
         @return <code>true</code> if the grade is satisfactory
         */
        public boolean isSatisfactory()
        {
            return satisfactory;
        }

        /**
         Returns the numeric equivalent of this grade.
         @return <ul>
         <li>Satisfactory: 4.0</li>
         <li>Unsatisfactory: 0.0</li></ul>
         */
        @Override
        public double numericEquivalent() {
            if (satisfactory) return 4.0;
            return 0;
        }

        /**
         Returns a string representation of this grade.
         @return <ul>
         <li>"S" if the grade is satisfactory</li>
         <li>"U" if the grade is unsatisfactory</li></ul>
         */
        @Override
        public String toString() {
            return satisfactory ? "S" : "U";
        }
    }
}
