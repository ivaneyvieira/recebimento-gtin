select I.prdno as codigo, TRIM(MID(P.name, 1, 38)) as descricao, I.qtty/100 as quant,
  I.grade, B.barcode48 as gtin
from sqldados.iprd AS I
  inner join sqldados.prd AS P
    on P.no = I.prdno
  inner join sqldados.prdbar AS B
    USING(prdno, grade)
where I.invno = :invno