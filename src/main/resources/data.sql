-- -----------------------------------------------------------
-- 1. REMOTE_CONTROLLERS (Master Data)
-- -----------------------------------------------------------
INSERT INTO remote_controllers (compatible_with, battery_type, name, brand, price, original_stock)
VALUES ('All Smart TV', 'AAA', 'Universal Premium Remote', 'Logitech', 49.99, 50),
       ('Samsung QLED Series', 'AA', 'SmartOne Voice Control', 'Samsung', 75.00, 30),
       ('Philips AmbiLight', 'AAA', 'AmbiControl Pro', 'Philips', 89.95, 20),
       ('LG WebOS OLED', 'AAA', 'Magic Motion Remote', 'LG', 60.00, 45),
       ('Basic LED / LCD', 'AA', 'Classic TV Control', 'Sony', 25.50, 60);

-- -----------------------------------------------------------
-- 2. WALLBRACKETS (Master Data)
-- -----------------------------------------------------------
INSERT INTO wallbrackets (size, adjustable, name, price)
VALUES ('Small (22"-40")', TRUE, 'Flexi-Mount Small', 29.99),
       ('Medium (40"-55")', TRUE, 'Pro-Tilt 55"', 49.50),
       ('Large (55"-75")', FALSE, 'Fixed-Mount Heavy', 65.00),
       ('Universal Max', TRUE, 'Omni-Swivel 8K', 99.00),
       ('Compact', FALSE, 'Slim Fit 32"', 15.99);

-- -----------------------------------------------------------
-- 3. CI_MODULES (Master Data, initially unassigned to a TV)
-- -----------------------------------------------------------
INSERT INTO ci_modules (name, type, price, television_id)
VALUES ('Ziggo CI+ Module v2', 'DVB-C', 49.99, NULL),
       ('Canal Digitaal CI+ HD', 'DVB-S', 59.50, NULL),
       ('Telenet CI Module', 'DVB-C', 45.00, NULL),
       ('Delta CI Module 4K', 'DVB-C', 42.99, NULL),
       ('Generic CAM Slot', 'DVB-T', 35.00, NULL);

-- -----------------------------------------------------------
-- 4. TELEVISIONS (Linked to RemoteControllers)
-- Corrected Ordinals: FIFTYFIVE_INCH=4, ONE_HUNDRED_HERTZ=1, OLED=1, FOUR_K=3, etc.
-- -----------------------------------------------------------
INSERT INTO televisions (type, brand, name, price, available_sizes, refresh_rate, screen_type, screen_quality,
                         smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light,
                         original_stock, sold, date_of_purchase, remote_controllers)
VALUES ('OLED 4K', 'LG', 'OLED C2 55 Inch', 1299.00, 4, 1, 1, 3,
        TRUE, TRUE, TRUE, TRUE, TRUE, FALSE, 25, 5, '2024-08-15 10:00:00', 4),       -- 55 Inch(4), 100Hz(1), OLED(1), 4K(3)
       ('QLED 8K', 'Samsung', 'Q900 60 Inch', 2500.00, 5, 2, 2, 4,
        TRUE, TRUE, TRUE, TRUE, TRUE, FALSE, 15, 2, '2024-07-20 15:30:00', 2),       -- 60 Inch(5), 200Hz(2), QLED(2), 8K(4)
       ('LED Full HD', 'Philips', 'The One 40 Inch', 499.99, 2, 0, 0, 1,
        TRUE, TRUE, FALSE, FALSE, FALSE, TRUE, 50, 15, '2024-09-01 09:10:00', 3),    -- 40 Inch(2), 50Hz(0), LED(0), FULL_HD(1)
       ('LCD HD', 'Sony', 'Bravia 32 Inch', 1299.00, 1, 0, 3, 0,
        FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, 70, 30, '2024-10-05 12:45:00', 5), -- 32 Inch(1), 50Hz(0), LCD(3), HD(0)
       ('QLED 4K Slim', 'Samsung', 'The Frame 50 Inch', 999.00, 3, 1, 2, 2,
        TRUE, TRUE, TRUE, TRUE, TRUE, FALSE, 35, 10, '2024-08-28 14:20:00', 1);       -- 50 Inch(3), 100Hz(1), QLED(2), UHD(2)

-- -----------------------------------------------------------
-- 5. RELATIONSHIP DATA (Many-to-Many: Televisions <-> Wallbrackets)
-- -----------------------------------------------------------
INSERT INTO televisions_wallbrackets (television_id, wallbracket_id)
VALUES (1, 2), -- TV 1 (LG) can use Wallbracket 2 (Pro-Tilt 55")
       (1, 4), -- TV 1 (LG) can also use Wallbracket 4 (Omni-Swivel 8K)
       (2, 4), -- TV 2 (Samsung 8K) needs Wallbracket 4 (Omni-Swivel 8K)
       (3, 1), -- TV 3 (Philips 40") can use Wallbracket 1 (Flexi-Mount Small)
       (5, 2); -- TV 5 (Samsung Frame 50") can use Wallbracket 2 (Pro-Tilt 55")
