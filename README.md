# Gerenciador-de-Estoque
Trabalho da matéria de Desenvolvimento de Sistemas Corporativos do curso de Bacharelado em Sistemas de Informação do IFNMG<br>
Discentes: Bruno Lima e Thalles Maia

## Banco de Dados
O arquivo .sql está configurado para criar todo o banco de dados necessário. Mysql com usuario padrão "root" e sem senha "".<br>
No Banco de Dados já estará inserido dois usuários: Um com permissão total de administrador (Login: admin, Senha: admin), e outro com restrições: (Login: comum, Senha: comum)

## Como Usar
1. Acesso
  - Vá para `Gerenciador-de-Estoque/Gerenciador-de-Estoque/dist` e execute o .jar (Sistema desenvolvido em Java 17)
  - Logue com as credenciais de Administrador ou Usuário Comum
2. CRUDS's
  - Os CRUDS's de Usuários, Clientres, Fornecedores e Mercadorias estão nas suas respectivas abas do menu
  - É possivel preencher os dados (exceto ID) e salvar um novo.
  - Ao preencher o nome, pode-se clicar em "Buscar" e os campos se completarão
  - Na aba de consulta, se pode buscar por um registro, bem como o selecionar e enviar para a tela de cadastro
  - Com os dados preenchidos é possivel realizar a edição e exclusão
  - Também é possível limpar os campos clicando em "Novo"
3. Mercadorias -> Controle de Estoque
  - Selecione um produto, o sistema irá mostrar o estoque atual
  - Preencha o campo "Quantidade nova" e selecione se deseja acrescentar ou reduzir do estoque.
4. Vendas
  - Ponto de Venda
    - Complete o cpf de algum cliente e aperte "Buscar"
    - Selecione um produto, digite a quantidade a adicione ao carrinho. Faça isso pra tudo que quiser comprar
    - Coloque o desconto, ex: 50 = 50% de desconto
    - Clique em Pagar para o formulario de pagamento ou cancelar pra limpar o carrinho
    - Pagamento
      - Preencha o valor que irá pagar em dinheiro e o valor em cartão
      - Selecione Pagar
      - Se a soma for maior ou igual o preço a pagar, a compra é confirmada
  - Fluxo do dia
    - Preencha o (dia dd/mm/aa) e aperte Buscar
    - O valor do dia será informado
  - Histórico
    - Preencha a data de inicio e fim do intervalo a ser pesquisado (dia dd/mm/aa)
    - As vendas do período serão informadas
    - Ao selecionar uma venda, os detalhes dela aparecerão
5. Configurações
  - Relatórios
    - Decida entre o relatório de estoque, vendas ou financeiro
    - O relatório será gerado
  - Trocar Usuário
    - Voltará a tela de Login
6. Sair
  - Decida se irá sair do sistema
