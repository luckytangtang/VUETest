#sql("fun002.01")
  SELECT id, create_time, user_id, status,
    file_size, checksum from pdf_blob where id = ?;
#end

#sql("fun002.02")
SELECT id, create_time, user_id, status,
  file_size, checksum from pdf_blob where checksum = ?;
#end