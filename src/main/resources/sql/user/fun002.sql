#sql("fun002.01")
update  user_account set
#if(address)
 address =  #para(address),
#end
#if(gender)
 gender =  #para(gender),
#end
#if(birthday)
 birthday =  #para(birthday),
#end
 status = 0 where username = #para(username);
#end



#sql("fun002.02")
update user_account set secret = ? where id = ? and status = 0;
#end