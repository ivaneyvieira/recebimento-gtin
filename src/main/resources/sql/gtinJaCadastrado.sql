select COUNT(*) as quant
  from prdbar
where barcode = :gtin
  and bits    = 2
  and NOT (prdno = :prdno AND grade = :grade)
  and :gtin <> ""