#sql("fun001.01")
  select id,username,gender,salt,password, secret,
  phone,email,birthday,address,status,create_time from  user_account where username=? and status = 0;
#end
#sql("fun001.02")
   select * from user_account;
#end

#sql("fun001.03")
  select id,username,gender,salt,password,secret,
  phone,email,birthday,address,status,create_time from  user_account where id=? and status = 0;
#end

#sql("fun001.04")
  select id, username, gender, phone, email, birthday from user_account where username = ? and status = 0;
#end


#sql("fun001.05")
select secret FROM user_account where id = ?;
#end

#sql("fun001.06")
SELECT secret from user_account where username = ?;
#end