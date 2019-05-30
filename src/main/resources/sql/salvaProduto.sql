update prdbar
set barcode48 = :gtin
where prdno = :prdno
  and grade = :grade