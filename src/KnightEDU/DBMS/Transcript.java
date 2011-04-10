/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package KnightEDU.DBMS;

import KnightEDU.Grade;
import KnightEDU.Term;

/**
 *
 * @author Alexander Darino
 */
public interface Transcript {
    public static interface Entry extends Transcript
    {
        public KnightEDU.DBMS.Query.Transcript queryTranscript();
        public KnightEDU.Transcript.Entry addTranscriptEntry(int studentID, CourseID courseID, int year, Term term, Grade grade, int credits);
        public boolean containsTranscriptEntry(int studentID, CourseID courseID, int year, Term term);
        public KnightEDU.Transcript.Entry getTranscriptEntry(int studentID, CourseID courseID, int year, Term term);
        public void updateTranscriptEntry(KnightEDU.Transcript transcript);
        public void removeTranscriptEntry(int studentID, CourseID courseID, int year, Term term);
    }

}
