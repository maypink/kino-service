INSERT INTO recommendations_table(id, user_id, film_id, score, date_time)
VALUES  (gen_random_uuid(), '6b774484-fd4f-40f3-a366-139ff3c971ca', 'cb3b6bd8-f2ae-4ee3-9992-212b4ea6e602', 0.5, NOW()),
        (gen_random_uuid(), 'b6866277-2278-460e-b6ff-fd88d8d6c2ec', '93e4b1a4-3fef-458c-94bd-c2bfab687bba', 0.2, NOW()),
        (gen_random_uuid(), '6b774484-fd4f-40f3-a366-139ff3c971ca', '823610d4-4a50-4801-a9f5-786fabdce42f', 0.1, NOW());