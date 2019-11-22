#sql("fun001.01")
  insert into pdf_blob (create_time, user_id, status,
                      file_size, checksum)
  values (?, ?, ?, ?, ?)
#end