# DebateLeagueBackend
Backend code for Debate League

Steps to create MySQL database -

Open MySQL through root login and type the below. 

mysql > create database debateleague;
mysql > create user 'springuser'@'localhost' identified by 'password';
mysql > grant all on debateleague.* to 'springuer'@'localhost';