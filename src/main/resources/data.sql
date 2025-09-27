INSERT INTO pharmacy.categories (name, description) VALUES
        ('Painkillers', 'Medications for pain relief'),
        ('Antibiotics', 'Medications for bacterial infections'),
        ('Vitamins', 'Vitamins and supplements');

INSERT INTO pharmacy.medicines (name, code, price, stock, category_id) VALUES
        ('Paracetamol', 'P001', 2.5, 100, 1),
        ('Ibuprofen', 'I002', 3.0, 150, 1),
        ('Amoxicillin', 'A003', 5.0, 50, 2),
        ('Vitamin C', 'V004', 1.5, 200, 3);

INSERT INTO pharmacy.transactions (type, quantity, date, description, medicine_id) VALUES
        ('IN', 50, '2025-09-27 10:00:00', 'Initial stock', 1),
        ('OUT', 10, '2025-09-27 12:00:00', 'Sold to customer', 1),
        ('IN', 100, '2025-09-27 09:00:00', 'Restock', 2),
        ('OUT', 20, '2025-09-27 14:00:00', 'Sold to customer', 2),
        ('IN', 30, '2025-09-26 11:00:00', 'Initial stock', 3),
        ('IN', 200, '2025-09-26 08:00:00', 'Initial stock', 4);
