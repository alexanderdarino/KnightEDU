package KnightEDU.DBMS;

public class Query {

    public static class CourseID
    {
        private final KnightEDU.DBMS.Course DBMS;

        protected CourseID(KnightEDU.DBMS.Course DBMS)
        {
            this.DBMS = DBMS;
        }

        protected CourseID(){DBMS = null;}

        protected static class PNS extends CourseID
        {
            protected final KnightEDU.DBMS.Course.PNS DBMS;
            protected final Query.Course builder;
            protected String prefixQuery = "", numberQuery = "", suffixQuery = "";

            public PNS(Query.Course builder, KnightEDU.DBMS.Course.PNS DBMS)
            {
                this.builder = builder;
                this.DBMS = DBMS;
            }
            public PNS containsPrefix(String prefix) throws InvalidPrefixException
            {
                if (!DBMS.isValidCoursePrefix(prefix)) throw new InvalidPrefixException();

                prefixQuery = "prefix = " + prefix;
                return this;
            }
            public PNS containsSuffix(String suffix) throws InvalidSuffixException
            {
                if (!DBMS.isValidCourseSuffix(suffix)) throw new InvalidSuffixException();

                suffixQuery = "suffix = " + suffix;
                return this;
            }

            public PNS containsNumberEqualTo(String number) throws InvalidNumberException
            {
                return numberCompare(number, "=");
            }

            public PNS containsNumberLessThan(String number) throws InvalidNumberException
            {
                return numberCompare(number, "<");
            }

            public PNS containsNumberGreaterThan(String number) throws InvalidNumberException
            {
                return numberCompare(number, ">");
            }

            protected PNS numberCompare(String number, String operation) throws InvalidNumberException
            {
                if (!DBMS.isValidCourseNumber(number)) throw new InvalidNumberException();
                numberQuery = "number " + operation + " "+ number;
                return this;
            }

            public Query.Course build()
            {
                StringBuilder courseIDQueryBuilder = new StringBuilder();
                if (!prefixQuery.equals(""))
                    courseIDQueryBuilder.append(prefixQuery);
                if (!numberQuery.equals(""))
                    courseIDQueryBuilder.append(" AND ").append(numberQuery);
                if (!suffixQuery.equals(""))
                    courseIDQueryBuilder.append(" AND ").append(suffixQuery);

                builder.setCourseIDQuery(courseIDQueryBuilder.toString());
                return builder;
            }

            public static class InvalidPrefixException extends Exception{}
            public static class InvalidSuffixException extends Exception{}
            public static class InvalidNumberException extends Exception{}
        }
    }

    public static class Course
    {
        protected final KnightEDU.DBMS.Course DBMS;
        protected String courseIDQuery, nameQuery, descriptionQuery;

        protected Course(KnightEDU.DBMS.Course DBMS)
        {
            this.DBMS = DBMS;
        }

        public Query.CourseID specifyCourseID()
        {
            return new Query.CourseID(DBMS);
        }

        public Query.Course nameContains(String name)
        {
            nameQuery = "name LIKE '%" + name + "%'";
            return this;
        }
        
        public Query.Course descriptionContains(String description)
        {
            nameQuery = "name LIKE '%" + description + "%'";
            return this;
        }

        protected void setCourseIDQuery(String courseIDQuery)
        {
            this.courseIDQuery = courseIDQuery;
        }

        public String build()
        {
            throw new UnsupportedOperationException("Not yet implemented.");
        }
    }

    public static class Section
    {
        
    }
}
