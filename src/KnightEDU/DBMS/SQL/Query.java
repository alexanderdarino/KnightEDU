package KnightEDU.DBMS.SQL;

import KnightEDU.DBMS.SQL.DB;
import KnightEDU.DBMS.SQL.Query.CourseID.PNS;
import java.util.Set;

/**
 *
 * @author Alexander Darino
 */
public class Query {

    /**
     *
     */
    protected static class CourseID implements KnightEDU.DBMS.Query.CourseID
    {
        private final DB DBMS;

        /**
         *
         * @param DBMS
         */
        protected CourseID(DB DBMS)
        {
            this.DBMS = DBMS;
        }

        /**
         *
         */
        protected CourseID(){DBMS = null;}

        /**
         *
         */
        public static class PNS extends CourseID implements KnightEDU.DBMS.Query.CourseID
        {
            /**
             *
             */
            protected final DB DBMS;
            /**
             *
             */
            protected final Query.Course builder;
            /**
             *
             */
            /**
             *
             */
            /**
             *
             */
            protected String prefixQuery = "", numberQuery = "", suffixQuery = "";

            /**
             *
             * @param builder
             * @param DBMS
             */
            public PNS(Query.Course builder, DB DBMS)
            {
                this.builder = builder;
                this.DBMS = DBMS;
            }
            /**
             *
             * @param prefix
             @return
             @throws KnightEDU.DBMS.SQL.Query.CourseID.PNS.InvalidPrefixException 
             */
            public KnightEDU.DBMS.SQL.Query.CourseID.PNS containsPrefix(String prefix) throws InvalidPrefixException
            {
                if (!DBMS.isValidCoursePrefix(prefix)) throw new InvalidPrefixException();

                prefixQuery = "prefix = " + prefix;
                return this;
            }
            /**
             *
             * @param suffix
             * @return
             @throws KnightEDU.DBMS.SQL.Query.CourseID.PNS.InvalidSuffixException
             */
            public KnightEDU.DBMS.SQL.Query.CourseID.PNS containsSuffix(String suffix) throws InvalidSuffixException
            {
                if (!DBMS.isValidCourseSuffix(suffix)) throw new InvalidSuffixException();

                suffixQuery = "suffix = " + suffix;
                return this;
            }

            /**
             *
             * @param number
             * @return
             @throws KnightEDU.DBMS.SQL.Query.CourseID.PNS.InvalidNumberException
             */
            public KnightEDU.DBMS.SQL.Query.CourseID.PNS containsNumberEqualTo(String number) throws InvalidNumberException
            {
                return numberCompare(number, "=");
            }

            /**
             *
             * @param number
             @return
             @throws KnightEDU.DBMS.SQL.Query.CourseID.PNS.InvalidNumberException
             */
            public KnightEDU.DBMS.SQL.Query.CourseID.PNS containsNumberLessThan(String number) throws InvalidNumberException
            {
                return numberCompare(number, "<");
            }

            /**
             *
             * @param number
             @return
             @throws KnightEDU.DBMS.SQL.Query.CourseID.PNS.InvalidNumberException
             */
            public KnightEDU.DBMS.SQL.Query.CourseID.PNS containsNumberGreaterThan(String number) throws InvalidNumberException
            {
                return numberCompare(number, ">");
            }

            /**
             *
             * @param number
             * @param operation
             * @return
             @throws KnightEDU.DBMS.SQL.Query.CourseID.PNS.InvalidNumberException
             */
            protected KnightEDU.DBMS.SQL.Query.CourseID.PNS numberCompare(String number, String operation) throws InvalidNumberException
            {
                if (!DBMS.isValidCourseNumber(number)) throw new InvalidNumberException();
                numberQuery = "number " + operation + " "+ number;
                return this;
            }

            /**
             *
             * @return
             */
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

            /**
             *
             */
            public static class InvalidPrefixException extends Exception{}
            /**
             *
             */
            public static class InvalidSuffixException extends Exception{}
            /**
             *
             */
            public static class InvalidNumberException extends Exception{}
        }
    }

    /**
     *
     */
    public static class Course implements KnightEDU.DBMS.Query.Course
    {

        public static class Offering
        {

            public Offering()
            {
            }
        }
        /**
         *
         */
        protected final DB DBMS;
        /**
         *
         */
        /**
         *
         */
        /**
         *
         */
        protected String courseIDQuery = "", nameQuery = "", descriptionQuery ="";

        /**
         *
         * @param DBMS
         */
        public Course(DB DBMS)
        {
            this.DBMS = DBMS;
        }

        /**
         *
         * @return
         */
        public KnightEDU.DBMS.Query.CourseID specifyCourseID()
        {
            return (PNS) new Query.CourseID(DBMS);
        }

        /**
         *
         * @param name
         * @return
         */
        public Query.Course nameContains(String name)
        {
            nameQuery = "name LIKE '%" + name + "%'";
            return this;
        }
        
        /**
         *
         * @param description
         * @return
         */
        public Query.Course descriptionContains(String description)
        {
            descriptionQuery = "description LIKE '%" + description + "%'";
            return this;
        }

        /**
         *
         * @param courseIDQuery
         */
        protected void setCourseIDQuery(String courseIDQuery)
        {
            this.courseIDQuery = courseIDQuery;
        }

        /**
         *
         * @return
         */
        public Set<KnightEDU.Course> execute()
        {
            boolean hasPrevious = false;
            String whereClause = courseIDQuery;
            if (hasPrevious) whereClause += " AND ";
            if (nameQuery != null && !nameQuery.equals(""))
                whereClause += nameQuery;

            if (hasPrevious) whereClause += " AND ";
            if (descriptionQuery != null && !descriptionQuery.equals(""))
                whereClause +=  descriptionQuery;
            return DBMS.queryCourse(whereClause, "", "");
        }

    }

    /**
     *
     */
    public static class Section implements KnightEDU.DBMS.Query.Section
    {
        
    }

    /**
     *
     */
    public static class Class
    {

        /**
         *
         */
        public Class()
        {
        }
    }

    /**
     *
     */
    public static class Component
    {

        /**
         *
         */
        public static class Offering
        {

            /**
             *
             */
            public Offering()
            {
            }
        }

        /**
         *
         */
        public Component()
        {
        }
    }
}
