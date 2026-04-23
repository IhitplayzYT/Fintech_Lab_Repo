use mydb;
show tables;
desc cust_cntct_dtls;
insert into cust_cntct_dtls VALUES (1,'C','PAYMENT_SERVICE','2026-01-18 12:30:00','2026-01-18 07:00:00','Sample condition value','2026-12-31','2026-01-18 12:30:05','2026-01-18 18:00:05','2026-01-01','user_123','550e8400-e29b-41d4-a716-446655440000','WS_01',1001);
select * from cust_cntct_dtls;
UPDATE cstcndt SET cstcndt_crud_value = 'U',cstcndt_value = 'Updated condition value',cstcndt_end_dt = '2027-12-31',cstcndt_host_ts = NOW() WHERE cstcndt_id = 1;
drop table cust_cntct_dtls;
