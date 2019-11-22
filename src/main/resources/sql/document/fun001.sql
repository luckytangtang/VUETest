#sql("fun001.01")
  insert into document (create_time, user_id, status,
                      file_name, author, title, blob_id, area_version)
  values (?, ?, ?, ?, ?, ?, ?, ?)
#end