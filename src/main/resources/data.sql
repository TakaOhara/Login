INSERT INTO task_type VALUES 
(1,'緊急','最優先で取り掛かるべきタスク'),
(2,'重要','期限に間に合わせるべきタスク'),
(3,'できれば','今後やってみたいアイデア');

INSERT INTO task VALUES 
(NULL,1,1,'JUnitを学習','テストの仕方を学習する','2020-07-07 15:00:00'),
(NULL,1,3,'サービスの自作','マイクロサービスを作ってみる','2020-09-13 17:00:00'),
(NULL,2,2,'Githubを学習','プルリクしてみる','2020-09-18 19:00:00');

INSERT INTO user_info VALUES (NULL, 'suzuki','suzuki@example.com','$2a$10$2sf0TbkGtlBqEZoFzdVi.OaNVFOSJnYL3dcB./5IJQTtEOja6fozq',true,'ROLE_USER'),
(NULL, 'tanaka','tanaka@example.com','$2a$10$2sf0TbkGtlBqEZoFzdVi.OaNVFOSJnYL3dcB./5IJQTtEOja6fozq',true,'ROLE_USER');

INSERT INTO role VALUES (1, 'ROLE_ADMIN', '管理者権限'),
(2, 'ROLE_USER', 'ユーザー権限');

INSERT INTO permission VALUES (NULL,1,1),
(NULL,1,2),
(NULL,2,2);
