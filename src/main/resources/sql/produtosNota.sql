select O.prdno as codigo,  mfno_ref as referencia, TRIM(MID(P.name, 1, 38)) as descricao,
       0 as quant,
       O.grade, IFNULL(TRIM(B.barcode), '') as gtin,
       MID(grade_l, 1, 10)*1 > 0 OR MID(grade_l, 11, 10)*1 > 0 as temGrade
FROM inv AS I
  INNER JOIN inv2 AS I2
             ON I2.invno = I.l4
  INNER JOIN mblistord AS M
             ON M.groupno = I2.l3
  INNER JOIN oprd AS O
             ON  M.ordno = O.ordno
               AND I.storeno = O.storeno
  inner join iprd2 AS IP2
             ON  IP2.invno = I.l4
               AND IP2.prdno = O.prdno
  inner join prd AS P
             on P.no = O.prdno
  left join prdbar AS B
            ON  B.prdno = O.prdno
              AND B.grade = O.grade
              AND LENGTH(TRIM(B.barcode))  = 13
where I.invno = :invno
        AND P.dereg & POW(2, 2) = 0
GROUP BY O.prdno, O.grade
ORDER BY O.prdno, O.grade;