CREATE MATERIALIZED VIEW project_task_count AS
SELECT
    p.projet_id,
    p.name,
    COUNT(t.task_id) AS task_count
FROM
    projet p
        LEFT JOIN
    task t ON p.project_id = t.project_id
GROUP BY
    p.project_id, p.name;