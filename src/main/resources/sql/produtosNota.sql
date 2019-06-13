select I.prdno as codigo, TRIM(MID(P.name, 1, 38)) as descricao, I.qtty/1000 as quant,
  I.grade, IFNULL(TRIM(B.barcode), '') as gtin,
  MID(grade_l, 1, 10)*1 > 0 OR MID(grade_l, 11, 10)*1 > 0 as temGrade
from iprd AS I
  inner join prd AS P
    on P.no = I.prdno
  left join prdbar AS B
    ON  B.prdno = I.prdno
    AND B.grade = I.grade
    AND B.bits  = 2
where I.invno = :invno