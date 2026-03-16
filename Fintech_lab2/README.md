1.Connected mysqldb to mysql workbench
2.Went to new models and made the first 5 practise Er diagrams
3.Cloned the repo for MIT api
4.Changed the pom.xml and application.yaml to have valid details and use mysql version of database
5.Executed the script and it made a 10 tables using reverse engineer.
6.Some sample sql commands on lab2 table
    1)desc cust_cntct_dtls;
    2)insert into cust_cntct_dtls VALUES (1,'C','PAYMENT_SERVICE','2026-01-18 12:30:00','2026-01-18 07:00:00','Sample condition value','2026-12-31','2026-01-18 12:30:05','2026-01-18 18:00:05','2026-01-01','user_123','550e8400-e29b-41d4-a716-446655440000','WS_01',1001);
    3)select * from cust_cntct_dtls;
    4)UPDATE cstcndt SET cstcndt_crud_value = 'U',cstcndt_value = 'Updated condition value',cstcndt_end_dt = '2027-12-31',cstcndt_host_ts = NOW() WHERE cstcndt_id = 1;
    5)drop table cust_cntct_dtls;
