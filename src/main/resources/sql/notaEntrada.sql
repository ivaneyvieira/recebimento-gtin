select I.invno, I.storeno, I.nfname as numero, I.invse as serie, vendno, V.sname as fornecedor, I.issue_date as dataEmissao
from sqldados.inv AS I
  inner join sqldados.invnfe AS N
    USING(invno)
  inner join sqldados.vend AS V
    ON V.no = I.vendno
WHERE N.nfekey = :nfekey