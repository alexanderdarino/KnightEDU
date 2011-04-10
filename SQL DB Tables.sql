CREATE TABLE Courses (
    id  CHAR(8),
    name CHAR(254),
    description VARCHAR(1000),
    creditsMin INT,
    creditsMax INT,
    prerequisites VARCHAR(1000),
    PRIMARY KEY (id));

CREATE TABLE Components(
    id INT,
    componentType INT,
    PRIMARY KEY (id));

CREATE TABLE ComponentGroups(
	primaryComponentID INT NOT NULL,
	additionalComponentID INT NOT NULL,
	PRIMARY KEY (primaryComponentID, additionalComponentID),
	FOREIGN KEY (primaryComponentID) REFERENCES Components(id),
	FOREIGN KEY (additionalComponentID) REFERENCES Components(id));

CREATE TABLE CourseOfferings (	
    courseID CHAR(8),
    term INT,
    yearOffered INT,
	primaryComponentID INT NOT NULL,
    PRIMARY KEY (courseID, term, yearOffered),
    FOREIGN KEY (courseID) REFERENCES Courses(id),
	FOREIGN KEY (primaryComponentID) REFERENCES Components(id));


CREATE TABLE Sections (
    id INT,
    days CHAR (3),
    timeStart INT,
    timeFinish INT,
    location CHAR (254),
    PRIMARY KEY (id));

 
CREATE TABLE Classes (
    id INT,
    sectionID INT,
    sectionNum INT,
    PRIMARY KEY (id),
    FOREIGN KEY (sectionID) REFERENCES Sections(id));





CREATE TABLE ComponentClasses(
    classID INT,
    componentID INT,
    
    PRIMARY KEY (componentID, classID),
    FOREIGN KEY (componentID) REFERENCES Components(id),
    FOREIGN KEY (classID) REFERENCES Classes(id));

CREATE TABLE Employees (
    id INT NOT NULL,
    fname CHAR(254) NOT NULL,
    lname CHAR(254) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Transcript (
    studentID INT,
    courseID CHAR(8),
    yearOffered INT,
    term INT,
    grade INT,
    credits INT,
    PRIMARY KEY (studentID, courseID, yearOffered, term)
    FOREIGN KEY (studentID) REFERENCES Employees(id));

CREATE TABLE InstructorTeaches (
    instructorID INT,
    classID INT,
    PRIMARY KEY (instructorID)
    FOREIGN KEY (instructorID) REFERENCES Employees(id)
    FOREIGN KEY (classID) REFERENCES Classes(id)
);


