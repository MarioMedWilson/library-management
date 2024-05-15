INSERT INTO book (title, description, author, isbn, year)
VALUES
    ('The Catcher in the Rye', 'A novel by J. D. Salinger', 'J. D. Salinger', '978-0316769488', 1951),
    ('The Hitchhiker Guide to the Galaxy', 'A science fiction comedy by Douglas Adams', 'Douglas Adams', '978-0345391803', 1982),
    ('Pride and Prejudice', 'A novel by Jane Austen', 'Jane Austen', '978-0140435225', 1813),
    ('To Kill a Mockingbird', 'A novel by Harper Lee', 'Harper Lee', '978-0446310727', 1960),
    ('The Great Gatsby', 'A novel by F. Scott Fitzgerald', 'F. Scott Fitzgerald', '978-0743273565', 1925),
    ('The Hobbit', 'A fantasy novel by J. R. R. Tolkien', 'J. R. R. Tolkien', '978-0345534835', 1937),
    ('The Lord of the Rings', 'An epic high-fantasy novel by J. R. R. Tolkien', 'J. R. R. Tolkien', '978-0544003415', 1954),
    ('The Little Prince', 'A novella by Antoine de Saint-Exupéry', 'Antoine de Saint-Exupéry', '978-0156012195', 1943),
    ('The Chronicles of Narnia', 'A series of seven high fantasy novels by C. S. Lewis', 'C. S. Lewis', '978-0066238500', 1950);


INSERT INTO patron (name, phone, email, address)
VALUES
    ('Alice', '123-456-7890', 'alice@gmail.com', '123 Main St, Springfield, IL 62701'),
    ('Bob', '234-567-8901', 'bob@gmail.com', '234 Elm St, Springfield, IL 62702'),
    ('Charlie', '345-678-9012', 'charlie@gmail.com', '345 Oak St, Springfield, IL 62703'),
    ('David', '456-789-0123', 'david@gmail.com', '456 Pine St, Springfield, IL 62704'),
    ('Eve', '567-890-1234', 'eve@gmail.com', '567 Maple St, Springfield, IL 62705'),
    ('Frank', '678-901-2345', 'frank@gmail.com', '678 Cedar St, Springfield, IL 62706'),
    ('Grace', '789-012-3456', 'grace@gmail.com', '789 Walnut St, Springfield, IL 62707'),
    ('Heidi', '890-123-4567', 'heidi@gmail.com', '890 Birch St, Springfield, IL 62708'),
    ('Ivan', '901-234-5678', 'ivan@gmail.com', '901 Ash St, Springfield, IL 62709');

INSERT INTO borrowing_record (book_id, patron_id, borrow_date, return_date)
VALUES
    (1, 1, '2021-01-01 00:00:00', '2021-01-15 00:00:00'),
    (2, 2, '2021-01-02 00:00:00', '2021-01-16 00:00:00'),
    (3, 3, '2021-01-03 00:00:00', '2021-01-17 00:00:00'),
    (4, 4, '2021-01-04 00:00:00', '2021-01-18 00:00:00'),
    (5, 5, '2021-01-05 00:00:00', '2021-01-19 00:00:00'),
    (6, 6, '2021-01-06 00:00:00', '2021-01-20 00:00:00'),
    (7, 7, '2021-01-07 00:00:00', '2021-01-21 00:00:00'),
    (8, 8, '2021-01-08 00:00:00', '2021-01-22 00:00:00'),
    (9, 9, '2021-01-09 00:00:00', '2021-01-23 00:00:00');

