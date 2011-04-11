INSERT INTO COURSES(id, NAME, DESCRIPTION, CREDITSMIN, CREDITSMAX, PREREQUISITES)
VALUES(	'ENC1101',
	'Composition I',
	'Expository writing with emphasis on effective communication and critical thinking. Emphasizing the writing process writing topics are based on selected readings and on student experiences. The �NC� grading policy applies to this course',
	3,
	3,
	'NONE');
/*Fall/Spring (Both)*/
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'ENC1101',
        0,
        0);
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'ENC1101',
        0,
        1);
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'ENC1101',
        1,
        0);
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'ENC1101',
        1,
        1);
--------------------------------------------------------------------------------
INSERT INTO COURSES(id, NAME, DESCRIPTION, CREDITSMIN, CREDITSMAX, PREREQUISITES)
VALUES(	'ENC1102',
	'Composition II',
	'Focus on extensive research in analytical and argumentative writing based on a variety of readings from the humanities. Emphasis on developing critical thinking and diversity of perspective. The �NC� grading policy applies to this course.',
	3,
	3,
	'COURSE(ENC1101,C-)');
/*Fall/Spring (Both)*/
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'ENC1102',
        0,
        0);
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'ENC1102',
        0,
        1);
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'ENC1102',
        1,
        0);
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'ENC1102',
        1,
        1);
--------------------------------------------------------------------------------
INSERT INTO COURSES(id, NAME, DESCRIPTION, CREDITSMIN, CREDITSMAX, PREREQUISITES)
VALUES(	'SPC1608',
	'Fundamentals of Oral Communication',
	'Priority will be given to students whose major requires this course. Communication theory and its application to preparing and delivering public speeches',
	3,
	3,
	'COURSE(ENC1101,C-)');
/*Fall/Spring (Both)*/
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'SPC1608',
        0,
        0);
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'SPC1608',
        0,
        1);
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'SPC1608',
        1,
        0);
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'SPC1608',
        1,
        1);
--------------------------------------------------------------------------------
INSERT INTO COURSES(id, NAME, DESCRIPTION, CREDITSMIN, CREDITSMAX, PREREQUISITES)
VALUES(	'SPC1603',
	'Fundamentals of Technical Presentation',
	'Communication theory and its application to preparing and delivering technical information in public speaking situations',
	3,
	3,
	'NONE');
/*Fall/Spring (Both)*/
/*Fall/Spring (Both)*/
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'SPC1603',
        0,
        0);
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'SPC1603',
        0,
        1);
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'SPC1603',
        1,
        0);
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'SPC1603',
        1,
        1);
--------------------------------------------------------------------------------
INSERT INTO COURSES(id, NAME, DESCRIPTION, CREDITSMIN, CREDITSMAX, PREREQUISITES)
VALUES(	'COM1000',
	'Introduction to Communication',
	'Communication theory and its application to preparing and delivering technical information in public speaking situations',
	3,
	3,
	'NONE');
/*Fall/Spring (Both)*/
/*Fall/Spring (Both)*/
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'COM1000',
        0,
        0);
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'COM1000',
        0,
        1);
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'COM1000',
        1,
        0);
INSERT INTO CourseSchedules(courseID, term, yearParity)
VALUES( 'COM1000',
        1,
        1);