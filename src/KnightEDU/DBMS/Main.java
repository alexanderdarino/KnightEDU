package KnightEDU.DBMS;

import KnightEDU.DBMS.SQL.DB;


public class Main {

    public static void main (String args[])
    {
        DB db = new DB(3,4,1);
        db.queryCourse().descriptionContains("virus").build();
    }
}
