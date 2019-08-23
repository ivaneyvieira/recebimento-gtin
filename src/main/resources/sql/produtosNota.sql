select O.prdno as codigo,  mfno_ref as referencia, TRIM(MID(P.name, 1, 38)) as descricao,
       SUM(O.qtty - qttyCancel - qttyRcv) as quant,
       O.grade, IFNULL(TRIM(B.barcode), '') as gtin,
       MID(grade_l, 1, 10)*1 > 0 OR MID(grade_l, 11, 10)*1 > 0 as temGrade
from inv AS I
         inner join oprd AS O
                    USING(storeno, ordno)
         inner join iprd2 AS I2
                    ON I2.invno = I.l4
                        AND I2.prdno = O.prdno
         inner join prd AS P
                    on P.no = O.prdno
         left join prdbar AS B
                   ON  B.prdno = O.prdno
                       AND B.grade = O.grade
                       AND LENGTH(TRIM(B.barcode))  = 13
where I.invno = :invno
        AND P.dereg & POW(2, 2) = 0
GROUP BY O.prdno, O.grade
ORDER BY O.prdno, O.grade