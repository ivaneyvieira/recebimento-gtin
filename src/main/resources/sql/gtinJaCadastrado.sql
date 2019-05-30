select COUNT(*) as quant
  from prdbar
where barcode48 = :gtin
  and prdno <> :prdno
  and grade <> :grade