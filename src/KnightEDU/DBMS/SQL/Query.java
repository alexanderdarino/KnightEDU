package KnightEDU.DBMS.SQL;

import KnightEDU.DBMS.SQL.Query.CourseID.PNS;
import KnightEDU.Grade.Type;
import KnightEDU.Prerequisites;
import KnightEDU.Term;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        protected final KnightEDU.DBMS.Query.Course courseQuery;

        /**
         *
         * @param DBMS
         */
        protected CourseID(KnightEDU.DBMS.Query.Course courseQuery)
        {
            this.courseQuery = courseQuery;
        }

        /**
         *
         */
        public static class PNS extends CourseID implements KnightEDU.DBMS.Query.CourseID.PNS
        {
            protected final int PREFIX_LENGTH, NUMBER_LENGTH, SUFFIX_LENGTH;

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

            public PNS(KnightEDU.DBMS.Query.Course courseQuery, int PREFIX_LENGTH, int NUMBER_LENGTH, int SUFFIX_LENGTH)
            {
                super(courseQuery);
                this.PREFIX_LENGTH = PREFIX_LENGTH;
                this.NUMBER_LENGTH = NUMBER_LENGTH;
                this.SUFFIX_LENGTH = SUFFIX_LENGTH;
            }

//            /**
//             *
//             * @param builder
//             * @param DBMS
//             */
//            public PNS(KnightEDU.DBMS.Query.Course courseQuery, int PREFIX_LENGTH)
//            {
//                super(courseQuery);
//            }
            /**
             *
             * @param prefix
             @return
             @throws KnightEDU.DBMS.SQL.Query.CourseID.PNS.InvalidPrefixException 
             */
            public KnightEDU.DBMS.SQL.Query.CourseID.PNS containsPrefix(String prefix) throws InvalidPrefixException
            {
                //if (!DBMS.isValidCoursePrefix(prefix)) throw new InvalidPrefixException();
                if (!KnightEDU.CourseID.PNS.isValidPrefix(prefix) || prefix.length() != NUMBER_LENGTH) throw new InvalidPrefixException();

                //prefixQuery = "prefix = " + prefix;
                prefixQuery = "SUBSTRING(1, 3) = " + prefix;
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
                if (!KnightEDU.CourseID.PNS.isValidSuffix(suffix) || suffix.length() != NUMBER_LENGTH) throw new InvalidSuffixException();

                //suffixQuery = "suffix = " + suffix;
                suffixQuery = "SUBSTRING(8, 8) = " + suffix;
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
                if (!KnightEDU.CourseID.PNS.isValidNumber(number) || number.length() != NUMBER_LENGTH) throw new InvalidNumberException();

                numberQuery = "SUBSTRING(4, 7) " + operation + " " + number;
                return this;
            }

            /**
             *
             * @return
             */
            public KnightEDU.DBMS.Query.Course build()
            {
                StringBuilder courseIDQueryBuilder = new StringBuilder();
                if (!prefixQuery.equals(""))
                    courseIDQueryBuilder.append(prefixQuery);
                if (!numberQuery.equals(""))
                    courseIDQueryBuilder.append(" AND ").append(numberQuery);
                if (!suffixQuery.equals(""))
                    courseIDQueryBuilder.append(" AND ").append(suffixQuery);

                courseQuery.setCourseIDQuery(courseIDQueryBuilder.toString());
                return courseQuery;
            }

            public KnightEDU.DBMS.Query.Course invoke()
            {

//                                ResultSet resultSet = db.query("CourseOfferings", whereClauseBuilder.toString(), "", "");
//                try {
//                    while (resultSet.next()) {
//                        KnightEDU.CourseID courseID = new KnightEDU.CourseID(resultSet.getString("courseID"));
//                        KnightEDU.Term term = KnightEDU.Term.values()[resultSet.getInt("term")];
//                        int yearOffered = resultSet.getInt("yearOffered");
//                        int primaryComponentGroup = resultSet.getInt("primaryComponentID");
//                        r_val.add(new KnightEDU.Course.Offering(courseID, yearOffered, term, primaryComponentGroup));
//                    }
//                } catch (SQLException ex) {
//                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                return r_val;

                StringBuilder queryBuilder = new StringBuilder();
                if (prefixQuery.length() > 0) queryBuilder.append(prefixQuery);
                if (numberQuery.length() > 0) queryBuilder.append(" AND ").append(numberQuery);
                if (suffixQuery.length() > 0) queryBuilder.append(" AND ").append(suffixQuery);

            }
//
//            /**
//             *
//             */
//            public static class InvalidPrefixException extends Exception{}
//            /**
//             *
//             */
//            public static class InvalidSuffixException extends Exception{}
//            /**
//             *
//             */
//            public static class InvalidNumberException extends Exception{}
        }
    }

    /**
     *
     */
    public static class Course implements KnightEDU.DBMS.Query.Course
    {

        public static class Offering implements KnightEDU.DBMS.Query.Course.Offering
        {

            protected final Set<String> courseIDs = new HashSet();
            protected final Set<Term> terms = new HashSet();
            protected final Set<Integer> years = new HashSet();
            protected final DB db;
            public Offering(DB db)
            {
                this.db = db;
            }

            public Offering specifyCourseID(String courseID)
            {
                courseIDs.add(courseID);
                return this;
            }

            public Offering offeredTerm(Term term)
            {
                this.terms.add(term);
                return this;
            }

            public Offering offeredYear(int year)
            {
                this.years.add(year);
                return this;
            }

            public Set<KnightEDU.Course.Offering> invoke()
            {
                Set<KnightEDU.Course.Offering> r_val = new HashSet();
                StringBuilder whereClauseBuilder = new StringBuilder();
                if(!courseIDs.isEmpty())
                {
                    for (String i : courseIDs)
                    {
                        whereClauseBuilder.append("CourseOfferings.id=").append(i).append(" OR ");
                    }
                    whereClauseBuilder.delete(whereClauseBuilder.length()-4, whereClauseBuilder.length()-1);
                }
                if(!terms.isEmpty())
                {
                    if(whereClauseBuilder.length() > 0) whereClauseBuilder.append(" OR ");
                    for (KnightEDU.Term i : terms)
                    {
                        whereClauseBuilder.append("CourseOfferings.term=").append(i.ordinal()).append(" OR ");
                    }
                    whereClauseBuilder.delete(whereClauseBuilder.length()-4, whereClauseBuilder.length()-1);
                }
                if(!years.isEmpty())
                {
                    if(whereClauseBuilder.length() > 0) whereClauseBuilder.append(" OR ");
                    for (Integer i : years)
                    {
                        whereClauseBuilder.append("CourseOfferings.year=").append(i).append(" OR ");
                    }
                    whereClauseBuilder.delete(whereClauseBuilder.length()-4, whereClauseBuilder.length()-1);
                }


                ResultSet resultSet = db.query("CourseOfferings", whereClauseBuilder.toString(), "", "");
                try {
                    while (resultSet.next()) {
                        KnightEDU.CourseID courseID = new KnightEDU.CourseID(resultSet.getString("courseID"));
                        KnightEDU.Term term = KnightEDU.Term.values()[resultSet.getInt("term")];
                        int yearOffered = resultSet.getInt("yearOffered");
                        int primaryComponentGroup = resultSet.getInt("primaryComponentID");
                        r_val.add(new KnightEDU.Course.Offering(courseID, yearOffered, term, primaryComponentGroup));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }

                return r_val;
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
        public KnightEDU.DBMS.Query.CourseID.PNS specifyCourseID()
        {
            return new Query.CourseID.PNS(this, DBMS.getCoursePrefixLength(), DBMS.getCourseNumberLength(), DBMS.getCourseSuffixMaxLength());
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
        public Set<KnightEDU.Course> invoke()
        {
            boolean hasPrevious = false;
            String whereClause = courseIDQuery;
            if (hasPrevious) whereClause += " AND ";
            if (nameQuery != null && !nameQuery.equals(""))
                whereClause += nameQuery;

            if (hasPrevious) whereClause += " AND ";
            if (descriptionQuery != null && !descriptionQuery.equals(""))
                whereClause +=  descriptionQuery;
            //return DBMS.queryCourse(whereClause, "", "");
            //return null;

           ResultSet resultSet = DBMS.query("Courses", whereClause, "", "");
           Set<KnightEDU.Course> r_val = new HashSet();
            try {
                while (resultSet.next()) {
                    int minCredit = resultSet.getInt("CREDITSMIN");
                    int maxCredit = resultSet.getInt("CREDITSMAX");
                    String prefix = resultSet.getString("ID").substring(0,3);
                    String number = resultSet.getString("ID").substring(3,7);
                    KnightEDU.CourseID courseID = KnightEDU.CourseID.PNS.create(prefix,number,"");
                    Prerequisites prerequisites = new Prerequisites(resultSet.getString("prerequisites"));
                    Set<KnightEDU.Course.Schedule> schedule = DBMS.getCourseSchedules(courseID);
                    KnightEDU.Course thisCourse = KnightEDU.Course.create(
                            courseID,resultSet.getString("NAME"),
                            resultSet.getString("DESCRIPTION"),
                            KnightEDU.Credits.createCredits(minCredit, maxCredit),
                            Type.LETTER, prerequisites, schedule);
                    r_val.add(thisCourse);

                }
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }
            return r_val;

        }

    }

    /**
     *
     */
    public static class Section implements KnightEDU.DBMS.Query.Section
    {
        KnightEDU.DBMS.SQL.DB db;

        protected Section( KnightEDU.DBMS.SQL.DB db)
        {
            this.db = db;
        }
//        public KnightEDU.Section getSection(int sectionID) {
//            ResultSet resultSet = db.query("Sections", "id=" + sectionID, "", "");
//
//            KnightEDU.Section r_val = new KnightEDU.Section(sectionID, resultSet.getString("days"))
//        }

    }

    /**
     *
     */
    public static class Class implements KnightEDU.DBMS.Query.Class
    {
        KnightEDU.DBMS.SQL.DB db;
        /**
         *
         */
        public Class(KnightEDU.DBMS.SQL.DB db)
        {
            this.db = db;
        }

        
    }

    /**
     *
     */
    public static class Component implements KnightEDU.DBMS.Query.Component
    {
        protected final KnightEDU.DBMS.SQL.DB db;


        /**
         *
         */
        public Component(KnightEDU.DBMS.SQL.DB db)
        {
            this.db = db;
        }

        public Set<Integer> getComponentIDs(int primaryComponentID) {
            ResultSet resultSet = db.query("ComponentGroups", "primaryComponentID=" + primaryComponentID, "", "");
            Set<Integer> r_val = new HashSet();
            r_val.add(primaryComponentID);
            try {
                while (resultSet.next()) {
                    r_val.add(resultSet.getInt("additionalComponentID"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }

            return r_val;
        }
        public Set<Integer> getClassIDs(int componentID) {
            ResultSet resultSet = db.query("ComponentClasses", "componentID=" + componentID, "", "");
            Set<Integer> r_val = new HashSet();
            try {
                while (resultSet.next()) {
                    r_val.add(resultSet.getInt("classID"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }
            return r_val;
        }
    }
}
