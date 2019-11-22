#sql("fun001.01")
  select id,block_order,type,checksum,text_body,font_family,font_size,x1,y1,x2,y2,page_id
  from pdf_block where page_id=?;
#end

#sql("fun001.02")
   select  id,block_order,type,checksum,text_body,font_family,font_size,x1,y1,x2,y2,page_id
   from pdf_block where id=?;
#end


#sql("fun001.03")
  select id,block_order,type,checksum,text_body,font_family,font_size,x1,y1,x2,y2,page_id
  from pdf_block where id in (
    select pb.pdf_block_id from pdf_page_block pb inner join pdf_page p on p.id = pb.page_id
    where pb.area_version = ? and p.blob_id = ?) ORDER BY id asc limit ? ,?;
#end