select I.prdno as codigo, TRIM(MID(P.name, 1, 38)) as descricao, I.qtty/100 as quant,
  I.grade, IFNULL(B.barcode48, '') as gtin,
  MID(grade_l, 1, 10)*1 > 0 OR MID(grade_l, 11, 10)*1 > 0 as temGrade
from sqldados.iprd AS I
  inner join sqldados.prd AS P
    on P.no = I.prdno
  left join sqldados.prdbar AS B
    USING(prdno, grade)
where I.invno = :invno