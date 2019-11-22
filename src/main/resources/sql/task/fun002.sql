#sql("fun002.01")
    select id, status, error_info as errorInfo, doc_id as docId from task
        where status = ? order by id desc
#end


#sql("fun002.02")
    select id, status, error_info, doc_id from task order by id desc
#end