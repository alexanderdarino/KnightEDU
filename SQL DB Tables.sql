CREATE TABLE Course (
    id  CHAR(8),
    name CHAR(254),
    description VARCHAR(1000),
    creditsMin INT,
    creditsMax INT,
    prerequisites VARCHAR(1000)
    PRIMARY KEY (id));

CREATE TABLE CourseOffered (
    courseID CHAR(8),
    term CHAR(16),
    yearOffered INT,
    PRIMARY KEY (courseID, term, yearOffered)
    FOREIGN KEY (courseID) REFERENCES Course(id));

CREATE TABLE Sections (
    id INT,
    days CHAR (3),
    timeStart INT,
    timeFinish INT,
    location CHAR (254)
    PRIMARY KEY (id));

 
CREATE TABLE Classes (
    id INT,
    sectionID INT,
    sectionNum INT
    PRIMARY KEY (id),
    FOREIGN KEY (sectionID) REFERENCES Sections(id));

CREATE TABLE Component(
    id INT,
    componentType INT
    PRIMARY KEY (id));

CREATE TABLE ComponentClasses(
    classID INT,
    componentID INT,
    
    PRIMARY KEY (componentID, classID),
    FOREIGN KEY (componentID) REFERENCES Component(id),
    FOREIGN KEY (classID) REFERENCES Classes(id)));