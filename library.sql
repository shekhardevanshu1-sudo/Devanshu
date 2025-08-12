-- Create Database
CREATE DATABASE LibraryDB;
USE LibraryDB;

-- Create Tables
CREATE TABLE Books (
    BookID INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(100),
    Author VARCHAR(100),
    Genre VARCHAR(50),
    Available BOOLEAN
);

CREATE TABLE Members (
    MemberID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100),
    Email VARCHAR(100)
);

CREATE TABLE IssuedBooks (
    IssueID INT PRIMARY KEY AUTO_INCREMENT,
    BookID INT,
    MemberID INT,
    IssueDate DATE,
    ReturnDate DATE,
    FOREIGN KEY (BookID) REFERENCES Books(BookID),
    FOREIGN KEY (MemberID) REFERENCES Members(MemberID)
);

-- Sample Data
INSERT INTO Books (Title, Author, Genre, Available) VALUES
('The Alchemist', 'Paulo Coelho', 'Fiction', TRUE),
('Atomic Habits', 'James Clear', 'Self-Help', TRUE),
('Clean Code', 'Robert C. Martin', 'Programming', TRUE);

INSERT INTO Members (Name, Email) VALUES
('Alice Smith', 'alice@example.com'),
('Bob Johnson', 'bob@example.com');

-- Issue a Book
INSERT INTO IssuedBooks (BookID, MemberID, IssueDate, ReturnDate)
VALUES (1, 1, '2025-08-12', '2025-08-20');

-- Query: List all issued books with member name
SELECT i.IssueID, b.Title, m.Name, i.IssueDate, i.ReturnDate
FROM IssuedBooks i
JOIN Books b ON i.BookID = b.BookID
JOIN Members m ON i.MemberID = m.MemberID;

-- Query: Available Books
SELECT * FROM Books WHERE Available = TRUE;