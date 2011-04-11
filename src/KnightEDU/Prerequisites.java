package KnightEDU;

/**
 * Represents a prerequisite course.
 *
 * NOTE: This class will likely undergo extensive redesign to accommodate for
 * a more flexible scheme to define prerequisites.
 * @author Alexander Darino
 * @version 03/07/11
 */
public class Prerequisites{

    /**

     */
    protected final String encoding;

    /**

     */
    public Prerequisites()
    {
        encoding = "";
    }

    /**

     @param encoding
     */
    public Prerequisites(String encoding)
    {
        this.encoding = encoding;
    }

    @Override
    public String toString()
    {
        return encoding;
    }

    /**

     */
    protected static class Course implements Encodable
    {
        /**

         */
        protected CourseID id;
        /**

         */
        protected Grade grade;

        /**

         @param id
         @param grade
         */
        public Course(CourseID id, Grade grade)
        {
            this.id = id;
            this.grade = grade;
        }

        /**

         @param id
         */
        public Course(CourseID id)
        {
            this.id = id;
            grade = null;
        }

        /**

         @return
         */
        public String encode()
        {
            StringBuilder r_val = new StringBuilder("COURSE(").append(id);
            if (grade != null)
                r_val.append(",").append(grade);
            return r_val.append(")").toString();
        }
    }

    /**

     */
    protected interface Encodable
    {
        /**

         @return
         */
        public String encode();
    }

    /**

     */
    protected static class CI implements Encodable
    {

        /**

         @return
         */
        public String encode()
        {
            return "CI";
        }

    }

    /**
     
     */
    public static class Builder
    {

        /**

         */
        public Type type;
        /**

         */
        protected Object data;

        private String encode()
        {
            switch(type)
            {
                case OPERATION:
                    return ((Operation)data).encode();
                case COURSE:
                    //return "COURSE(" + ((CourseID)data) + ")";
                    return ((Course)data).encode();
                case CI:
                    return "CI";
                default:
                    assert false;
                    return null;
            }
        }

        /**

         */
        protected enum Type
        {
            /**

             */
            OPERATION,
            /**
            
             */
            COURSE,
            /**

             */
            CI
        }

        /**

         @return
         */
        public Prerequisites build()
        {
            return new Prerequisites(encode());
        }

        /**

         @param type
         @param data
         */
        protected Builder(Type type, Object data)
        {
            this.type = type;
            this.data = data;
        }

        /**

         @param courseID
         @return
         */
        public static Builder course(CourseID courseID)
        {
            if (courseID == null) return null;
            return new Builder(Type.COURSE, new Course(courseID));
        }
        /**

         @param courseID
         @param grade
         @return
         */
        public static Builder course(CourseID courseID, Grade grade)
        {
            if (courseID == null) return null;
            return new Builder(Type.COURSE, new Course(courseID, grade));
        }

        /**

         @param op2
         @return
         */
        public Builder and(Builder op2)
        {
            return new Builder(Type.OPERATION, Operation.and(this, op2));
        }

        /**

         @param op2
         @return
         */
        public Builder or(Builder op2)
        {
            return new Builder(Type.OPERATION, Operation.or(this, op2));
        }

        /**

         @return
         */
        public Builder negate()
        {
            if (type == Type.OPERATION)
            {
                // Undo double-negations
                if (((Operation)data).type == Operation.Type.NOT)
                {
                    return ((Operation)data).operands[0];
                }
                if (!((Operation)data).isUnary())
                {
                    Builder group = Builder.group(this);
                    return new Builder(Type.OPERATION, Operation.not(group));
                }
            }
            return new Builder(Type.OPERATION, Operation.not(this));
        }

        /**

         @param op
         @return
         */
        public static Builder not(Builder op)
        {
            return op.negate();
            //return new Builder(Type.OPERATION, Operation.not(op));
        }

        /**

         @return
         */
        public Builder group()
        {
            return new Builder(Type.OPERATION, Operation.group(this));
        }
        /**

         @param op
         @return
         */
        public static Builder group(Builder op)
        {
            return op.group();
            //return new Builder(Type.OPERATION, Operation.group(op));
        }

        /**

         */
        public static class Operation
        {
            /**

             @param op1
             @param op2
             @return
             */
            public static Operation and(Builder op1, Builder op2)
            {
                return new Operation(Type.AND, op1, op2);
            }

            /**
             
             @param op1
             @param op2
             @return
             */
            public static Operation or(Builder op1, Builder op2)
            {
                return new Operation(Type.OR, op1, op2);
            }

            /**

             @param op
             @return
             */
            public static Operation not(Builder op)
            {
                return new Operation(Type.NOT, op);
            }

            /**

             @param op
             @return
             */
            public static Operation group(Builder op)
            {
                return new Operation(Type.GROUP, op);
            }

            /**

             @return
             */
            public String encode()
            {
                switch(type)
                {
                    case NOT:
                        return "NOT " + operands[0].encode();
                    case GROUP:
                        return "(" + operands[0].encode() + ")";
                    case AND:
                        return operands[0].encode() + " AND " + operands[1].encode();
                    case OR:
                        return operands[0].encode() + " OR " + operands[1].encode();
                    default:
                        assert false;
                        return null;
                }
            }

            /**

             @return
             */
            public boolean isUnary()
            {
                switch (type)
                {
                    case NOT:
                    case GROUP:
                        return true;
                }
                return false;
            }

            /**

             @param type
             @param operands
             */
            protected Operation(Type type, Builder... operands)
            {
                this.type = type;
                this.operands = operands;
            }

            /**

             */
            protected Type type;
            /**

             */
            protected Builder[] operands;
            /**

             */
            protected enum Type
            {

                /**
                 
                 */
                NOT,
                /**

                 */
                GROUP,
                /**
                
                 */
                AND,
                /**

                 */
                OR
            }
            //public static Operation and(Prerequisites)
        }
    }
}
