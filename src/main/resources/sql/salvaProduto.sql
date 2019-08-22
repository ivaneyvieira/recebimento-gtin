delete from sqldados.prdbar
where prdno     = :prdno
  and grade     = :grade
  and :gtin     = :gtin
  and LENGTH(TRIM(barcode)) = 13
  and bits      = 0
  and barcode48 = '';

insert ignore into sqldados.prdbar(auxLong1, auxLong2, bits, bits2, prdno, grade, barcode, barcode48)
select 0, 0, 2, 0, :prdno, :grade, :gtin, ''
from dual
where LENGTH(TRIM(:gtin)) = 13
