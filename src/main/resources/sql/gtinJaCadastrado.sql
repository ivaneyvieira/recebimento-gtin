select COUNT(*) as quant
  from prdbar
where barcode48 = :gtin
  and NOT (prdno = :prdno AND grade = :grade)