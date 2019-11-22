#sql("fun001.01")
  SELECT id,page_index,width,height,lines_space,font_size,font_family,column_width,blob_id from pdf_page where blob_id = ?;
#end

#sql("fun001.02")
  SELECT id,page_index,width,height,lines_space,font_size,font_family,column_width,blob_id from pdf_page where id = ?;
#end