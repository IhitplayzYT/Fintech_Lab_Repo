-- 1. Total number of customers in the system
SELECT COUNT(DISTINCT customer_identifier) AS total_customers FROM customer_detail;

-- 2. Gender distribution of all customers
SELECT customer_gender, COUNT(*) AS count, ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER (), 2) AS percentage FROM customer_detail GROUP BY customer_gender;

-- 3. Customer count by country/region
SELECT customer_country, COUNT(*) AS total_customers FROM customer_detail GROUP BY customer_country ORDER BY total_customers DESC;

-- 4. Customer count by status (active, inactive, etc.)
SELECT customer_status, COUNT(*) AS count FROM customer_detail GROUP BY customer_status ORDER BY count DESC;

-- 5. Customer count by type (individual, corporate, etc.)
SELECT customer_type, COUNT(*) AS count FROM customer_detail GROUP BY customer_type ORDER BY count DESC;

-- 6. Customer count by preferred language
SELECT customer_lang, COUNT(*) AS count FROM customer_detail GROUP BY customer_lang ORDER BY count DESC;

-- 7. Gender distribution broken down by country
SELECT customer_country, customer_gender, COUNT(*) AS count FROM customer_detail GROUP BY customer_country, customer_gender ORDER BY customer_country, count DESC;

-- 8. Customer count by type and status combined
SELECT customer_type, customer_status, COUNT(*) AS count FROM customer_detail GROUP BY customer_type, customer_status ORDER BY customer_type, count DESC;

-- 9. New customers registered each year based on audit acceptance time
SELECT YEAR(audit_acceptance_time) AS year, COUNT(*) AS new_customers FROM customer_detail GROUP BY YEAR(audit_acceptance_time) ORDER BY year DESC;

-- 10. New customers registered each month in the last 3 years
SELECT YEAR(audit_acceptance_time) AS year, MONTH(audit_acceptance_time) AS month, COUNT(*) AS new_customers FROM customer_detail WHERE audit_acceptance_time >= DATE_SUB(NOW(), INTERVAL 3 YEAR) GROUP BY YEAR(audit_acceptance_time), MONTH(audit_acceptance_time) ORDER BY year DESC, month DESC;

-- 11. Customer age distribution (age brackets) based on date of birth
SELECT CASE WHEN TIMESTAMPDIFF(YEAR, customer_dob, CURDATE()) < 18 THEN 'Under 18' WHEN TIMESTAMPDIFF(YEAR, customer_dob, CURDATE()) BETWEEN 18 AND 25 THEN '18-25' WHEN TIMESTAMPDIFF(YEAR, customer_dob, CURDATE()) BETWEEN 26 AND 35 THEN '26-35' WHEN TIMESTAMPDIFF(YEAR, customer_dob, CURDATE()) BETWEEN 36 AND 45 THEN '36-45' WHEN TIMESTAMPDIFF(YEAR, customer_dob, CURDATE()) BETWEEN 46 AND 60 THEN '46-60' ELSE '60+' END AS age_group, COUNT(*) AS count FROM customer_detail WHERE customer_dob IS NOT NULL GROUP BY age_group ORDER BY age_group;

-- 12. Average customer age by country
SELECT customer_country, ROUND(AVG(TIMESTAMPDIFF(YEAR, customer_dob, CURDATE())), 1) AS avg_age FROM customer_detail WHERE customer_dob IS NOT NULL GROUP BY customer_country ORDER BY avg_age DESC;

-- 13. Average customer age by gender
SELECT customer_gender, ROUND(AVG(TIMESTAMPDIFF(YEAR, customer_dob, CURDATE())), 1) AS avg_age FROM customer_detail WHERE customer_dob IS NOT NULL GROUP BY customer_gender;

-- 14. Total customers added in the past 3 years vs before that
SELECT CASE WHEN audit_acceptance_time >= DATE_SUB(NOW(), INTERVAL 3 YEAR) THEN 'Last 3 Years' ELSE 'Before Last 3 Years' END AS period, COUNT(*) AS count FROM customer_detail GROUP BY period;

-- 15. Count of customers by identification type (passport, national ID, etc.)
SELECT customer_identification_type, COUNT(*) AS count FROM customer_identification GROUP BY customer_identification_type ORDER BY count DESC;

-- 16. Customers with multiple identification types registered
SELECT customer_identifier, COUNT(*) AS id_count FROM customer_identification GROUP BY customer_identifier HAVING id_count > 1 ORDER BY id_count DESC;

-- 17. Count of customers by proof of ID type
SELECT customer_proof_of_id_type, COUNT(*) AS count FROM customer_proof_of_id GROUP BY customer_proof_of_id_type ORDER BY count DESC;

-- 18. Customers whose proof of ID is currently active (within start and end date)
SELECT customer_proof_of_id_type, COUNT(*) AS active_count FROM customer_proof_of_id WHERE start_date <= CURDATE() AND (end_date IS NULL OR end_date >= CURDATE()) GROUP BY customer_proof_of_id_type ORDER BY active_count DESC;

-- 19. Customers with expired proof of ID
SELECT COUNT(DISTINCT customer_identifier) AS customers_with_expired_id FROM customer_proof_of_id WHERE end_date < CURDATE();

-- 20. Contact type distribution (email, phone, etc.)
SELECT customer_contact_type, COUNT(*) AS count FROM customer_contact_information GROUP BY customer_contact_type ORDER BY count DESC;

-- 21. Customers with active contact information currently
SELECT customer_contact_type, COUNT(*) AS active_contacts FROM customer_contact_information WHERE start_date <= CURDATE() AND (end_date IS NULL OR end_date >= CURDATE()) GROUP BY customer_contact_type;

-- 22. Customers with multiple contact types registered
SELECT customer_identifier, COUNT(DISTINCT customer_contact_type) AS contact_type_count FROM customer_contact_information GROUP BY customer_identifier HAVING contact_type_count > 1 ORDER BY contact_type_count DESC;

-- 23. Address type distribution across all customers
SELECT customer_address_type, COUNT(*) AS count FROM customer_address GROUP BY customer_address_type ORDER BY count DESC;

-- 24. Customers with multiple address types on record
SELECT customer_identifier, COUNT(DISTINCT customer_address_type) AS address_type_count FROM customer_address GROUP BY customer_identifier HAVING address_type_count > 1 ORDER BY address_type_count DESC;

-- 25. Classification type distribution across all customers
SELECT customer_classification_type, COUNT(*) AS count FROM customer_classification_type GROUP BY customer_classification_type ORDER BY count DESC;

-- 26. Classification value breakdown per classification type
SELECT customer_classification_type, customer_classification_value, COUNT(*) AS count FROM customer_classification_type GROUP BY customer_classification_type, customer_classification_value ORDER BY customer_classification_type, count DESC;

-- 27. Customer name type distribution (legal, preferred, alias, etc.)
SELECT customer_name_type, COUNT(*) AS count FROM customer_name GROUP BY customer_name_type ORDER BY count DESC;

-- 28. Customers with multiple name types registered
SELECT customer_identifier, COUNT(DISTINCT customer_name_type) AS name_type_count FROM customer_name GROUP BY customer_identifier HAVING name_type_count > 1 ORDER BY name_type_count DESC;

-- 29. Gender ratio per customer type
SELECT cd.customer_type, cd.customer_gender, COUNT(*) AS count FROM customer_detail cd GROUP BY cd.customer_type, cd.customer_gender ORDER BY cd.customer_type, count DESC;

-- 30. Country-wise gender breakdown with percentage
SELECT customer_country, customer_gender, COUNT(*) AS count, ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER (PARTITION BY customer_country), 2) AS pct_within_country FROM customer_detail GROUP BY customer_country, customer_gender ORDER BY customer_country, count DESC;

-- 31. Year-over-year customer growth rate for past 3 years
SELECT YEAR(audit_acceptance_time) AS year, COUNT(*) AS new_customers, LAG(COUNT(*)) OVER (ORDER BY YEAR(audit_acceptance_time)) AS prev_year_count, ROUND((COUNT(*) - LAG(COUNT(*)) OVER (ORDER BY YEAR(audit_acceptance_time))) * 100.0 / NULLIF(LAG(COUNT(*)) OVER (ORDER BY YEAR(audit_acceptance_time)), 0), 2) AS growth_pct FROM customer_detail WHERE audit_acceptance_time >= DATE_SUB(NOW(), INTERVAL 3 YEAR) GROUP BY YEAR(audit_acceptance_time) ORDER BY year;

-- 32. Customers registered per quarter in the past 3 years
SELECT YEAR(audit_acceptance_time) AS year, QUARTER(audit_acceptance_time) AS quarter, COUNT(*) AS new_customers FROM customer_detail WHERE audit_acceptance_time >= DATE_SUB(NOW(), INTERVAL 3 YEAR) GROUP BY YEAR(audit_acceptance_time), QUARTER(audit_acceptance_time) ORDER BY year, quarter;

-- 33. Top 10 countries by customer count
SELECT customer_country, COUNT(*) AS total FROM customer_detail GROUP BY customer_country ORDER BY total DESC LIMIT 10;

-- 34. Customers by status and country combined
SELECT customer_country, customer_status, COUNT(*) AS count FROM customer_detail GROUP BY customer_country, customer_status ORDER BY customer_country, count DESC;

-- 35. Percentage of customers by language preference per country
SELECT customer_country, customer_lang, COUNT(*) AS count, ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER (PARTITION BY customer_country), 2) AS pct_within_country FROM customer_detail GROUP BY customer_country, customer_lang ORDER BY customer_country, count DESC;

-- 36. Active vs inactive customers per country
SELECT customer_country, SUM(CASE WHEN customer_status = 'ACTIVE' THEN 1 ELSE 0 END) AS active, SUM(CASE WHEN customer_status != 'ACTIVE' THEN 1 ELSE 0 END) AS inactive, COUNT(*) AS total FROM customer_detail GROUP BY customer_country ORDER BY total DESC;

-- 37. Customers with no contact information on record
SELECT COUNT(*) AS customers_without_contact FROM customer_detail cd WHERE NOT EXISTS (SELECT 1 FROM customer_contact_information cci WHERE cci.customer_identifier = cd.customer_identifier);

-- 38. Customers with no address on record
SELECT COUNT(*) AS customers_without_address FROM customer_detail cd WHERE NOT EXISTS (SELECT 1 FROM customer_address ca WHERE ca.customer_identifier = cd.customer_identifier);

-- 39. Customers with no identification document recorded
SELECT COUNT(*) AS customers_without_identification FROM customer_detail cd WHERE NOT EXISTS (SELECT 1 FROM customer_identification ci WHERE ci.customer_identifier = cd.customer_identifier);

-- 40. Customer completeness score: count how many data entities each customer has filled
SELECT cd.customer_identifier, cd.customer_country, cd.customer_gender, cd.customer_status, (CASE WHEN cn.customer_identifier IS NOT NULL THEN 1 ELSE 0 END + CASE WHEN ca.customer_identifier IS NOT NULL THEN 1 ELSE 0 END + CASE WHEN cci.customer_identifier IS NOT NULL THEN 1 ELSE 0 END + CASE WHEN ci.customer_identifier IS NOT NULL THEN 1 ELSE 0 END + CASE WHEN cp.customer_identifier IS NOT NULL THEN 1 ELSE 0 END + CASE WHEN cct.customer_identifier IS NOT NULL THEN 1 ELSE 0 END) AS completeness_score FROM customer_detail cd LEFT JOIN customer_name cn ON cn.customer_identifier = cd.customer_identifier LEFT JOIN customer_address ca ON ca.customer_identifier = cd.customer_identifier LEFT JOIN customer_contact_information cci ON cci.customer_identifier = cd.customer_identifier LEFT JOIN customer_identification ci ON ci.customer_identifier = cd.customer_identifier LEFT JOIN customer_proof_of_id cp ON cp.customer_identifier = cd.customer_identifier LEFT JOIN customer_classification_type cct ON cct.customer_identifier = cd.customer_identifier GROUP BY cd.customer_identifier, cd.customer_country, cd.customer_gender, cd.customer_status ORDER BY completeness_score DESC;

-- 41. Distribution of completeness scores across the userbase
SELECT completeness_score, COUNT(*) AS customer_count FROM (SELECT cd.customer_identifier, (CASE WHEN cn.customer_identifier IS NOT NULL THEN 1 ELSE 0 END + CASE WHEN ca.customer_identifier IS NOT NULL THEN 1 ELSE 0 END + CASE WHEN cci.customer_identifier IS NOT NULL THEN 1 ELSE 0 END + CASE WHEN ci.customer_identifier IS NOT NULL THEN 1 ELSE 0 END + CASE WHEN cp.customer_identifier IS NOT NULL THEN 1 ELSE 0 END + CASE WHEN cct.customer_identifier IS NOT NULL THEN 1 ELSE 0 END) AS completeness_score FROM customer_detail cd LEFT JOIN customer_name cn ON cn.customer_identifier = cd.customer_identifier LEFT JOIN customer_address ca ON ca.customer_identifier = cd.customer_identifier LEFT JOIN customer_contact_information cci ON cci.customer_identifier = cd.customer_identifier LEFT JOIN customer_identification ci ON ci.customer_identifier = cd.customer_identifier LEFT JOIN customer_proof_of_id cp ON cp.customer_identifier = cd.customer_identifier LEFT JOIN customer_classification_type cct ON cct.customer_identifier = cd.customer_identifier GROUP BY cd.customer_identifier) AS scores GROUP BY completeness_score ORDER BY completeness_score DESC;

-- 42. Customers flagged with specific CRUD flags (e.g. deleted, updated records)
SELECT crud_flag, COUNT(*) AS count FROM customer_detail GROUP BY crud_flag ORDER BY count DESC;

-- 43. Monthly trend of address records added in the last 3 years
SELECT YEAR(audit_acceptance_time) AS year, MONTH(audit_acceptance_time) AS month, COUNT(*) AS addresses_added FROM customer_address WHERE audit_acceptance_time >= DATE_SUB(NOW(), INTERVAL 3 YEAR) GROUP BY YEAR(audit_acceptance_time), MONTH(audit_acceptance_time) ORDER BY year DESC, month DESC;

-- 44. Contact information records expiring within the next 90 days
SELECT customer_contact_type, COUNT(*) AS expiring_soon FROM customer_contact_information WHERE end_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 90 DAY) GROUP BY customer_contact_type ORDER BY expiring_soon DESC;

-- 45. Proof of ID records expiring within the next 90 days
SELECT customer_proof_of_id_type, COUNT(*) AS expiring_soon FROM customer_proof_of_id WHERE end_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 90 DAY) GROUP BY customer_proof_of_id_type ORDER BY expiring_soon DESC;
