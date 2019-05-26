select COUNT(*) as quant
  from sqldados.prdbar
where barcode48 = :gtin
  and prdno <> :prdno
  and grade <> :grade