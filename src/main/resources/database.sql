CREATE DATABASE income_expense_tracker;
USE income_expense_tracker;

    CREATE TABLE expenses(
        expenseID INT AUTO_INCREMENT PRIMARY HEY,
        title VARCHAR(255) NOT NULL,
        category VARCHAT(255) NOT NULL,
        amount DOUBLE NOT NULL,
        dataIncurred DATE NOT NULL
    )

    CREATE TABLE income(
        incomeID INT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        amount DOUBLE NOT NULL,
        dateEarned DATE NOT NULL
    )

    -- Sample Data
    INSERT INTO expenses (title, category, amount, dateIncurred)
    VALUES
    ('Weekly shop','Groceries',47.50,'2025-01-07'),
    ('Gym membership','Fitness',30.00,'2025-01-09')

    INSERT INTO income (title, amount, dateEarned)
    VALUES
    ('Babysitting',60.00,'2025-01-05'),
    ('Store work',357.00,'2025-01-07')