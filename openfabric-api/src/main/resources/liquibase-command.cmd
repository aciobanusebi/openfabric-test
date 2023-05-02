liquibase ^
--driver=org.postgresql.Driver ^
--classpath=liquibase-postgresql-42.6.0.jar ^
--url="jdbc:postgresql://localhost:5432/openfabric" ^
--changeLogFile=db/liquibase-changelog.xml ^
--username=postgres ^
--password=postgres ^
rollback-count-sql 1
:: https://stackoverflow.com/a/45313902/7947996 !!!!!! : db/liquibase-changelog.xml should be in column filename from table databasechangelog