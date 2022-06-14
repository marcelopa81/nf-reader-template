## Arquivos
As Notas fiscais usadas foram baixadas do [portal da transparencia](https://www.portaltransparencia.gov.br/download-de-dados/notas-fiscais)
em formato `csv`.

Somente é possível baixar um `.zip` com os arquivos de cada mês. Dentro desse `.zip` existem vários arquivos, mas iremo
s usa somente os de nome `...NFe_NotaFiscalItem.csv` pois o a maneira de serializar foi pensada somente para esse arquivo.

Para que os exemplos tenham um efeito mais didático, idealmente use pelo menos 5 arquivos ou mais (cada um tem aproximadamente 200mb)

## Diretório

Por padrão, o diretório de leitura está configurado para: `src/main/resources/nfe`. Esse diretório pode ser trocado na 
classe `Main`.

## Resultado
O resultado é gravado em outro `csv` que fica em `src/main/resources/resultado`
