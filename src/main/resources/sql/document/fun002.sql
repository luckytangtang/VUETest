#sql("fun002.01")
    select id, create_time, user_id,
                      file_name, author, title, area_version, blob_id from document
        where user_id = ? and status=0 order by id desc limit ? ,?;
#end



#sql("fun002.02")
    select id, create_time, user_id,
                      file_name, author, title, area_version, blob_id from document
        where id = ? and status=0;
#end

