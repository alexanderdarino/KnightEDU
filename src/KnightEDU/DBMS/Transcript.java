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
        public KnightEDU.Transcript.Entry addTranscriptEntry(int studentID, KnightEDU.CourseID courseID, int year, Term term, Grade.Type gradeType, Grade grade, int credits);
        public boolean containsTranscriptEntry(int studentID, KnightEDU.CourseID courseID, int year, Term term);
        public  KnightEDU.Transcript getTranscript(int studentID);
        public KnightEDU.Transcript.Entry getTranscriptEntry(int studentID, KnightEDU.CourseID courseID, int year, Term term);
        public void updateTranscriptEntry(int studentID, KnightEDU.Transcript.Entry transcriptEntry);
        public void removeTranscriptEntry(int studentID, KnightEDU.CourseID courseID, int year, Term term);
    }

}
