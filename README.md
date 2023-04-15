Projeto consulta e cadastro de Clientes
======

# Baixar imagem docker:
- docker run wanderalvess/clients-back
- docker run wanderalvess/clients-front

# Pode ser usado o comando abaixo, para iniciar todos ao mesmo tempo
- docker-compose up


### ***Informações Sobre o Front-End:***

- Esse projeto foi feito com angular e node
- ***Versões:***
	- Angular CLI: 14.2.8
	- Angular: 14.2.9
	- Node: 14.15.0
	- Package Manager: npm 6.14.8
  
- Informações completas de Build no Readme dentro da pasta do projeto.


### ***Informações Sobre o Back-End:***

- Esse projeto foi feito com Java, SpringBot e Maven.
- ***Versões:***
	- Java: 11.0.9
	- Apache Maven: 3.8.6
	- SpringBot: 2.7.5
  
 - Informações completas de Build no Readme dentro da pasta do projeto.


Plano de Teste
======

### ***Listar Clientes:***
- Após build dos projetos, a tela inicial será mostrado os clientes cadastrados.
![initial](https://user-images.githubusercontent.com/74334991/200380891-933365e7-a184-4b58-a6a7-46d46c73e048.png)

### ***Ações:***
- No botão de adicionar (icone de +) do lado direito, será possível cadastrar novos clientes.
![register](https://user-images.githubusercontent.com/74334991/200381047-a3804d33-e325-420c-91fe-f2715174ee0e.png)

- No Botão de excluir (icone de lixeira) do lado direito das informações do cliente, será possível excluir um cadastro de cliente.

- No Botão de editar (icone de lápis) do lado direito das informações do cliente, será possível editar um cadastro de cliente.

- No botão de salvar, na tela de cadastro de cliente, será possível salvar um novo cadastro ou que está sendo editado.

- No botão de cancelar, na tela de cadastro de cliente, será possível voltar para a tela inicial.

### ***Validações:***

- Validações de conexão:
	- Se o front-end estiver em execução e não conseguir encontrar o back-end, mostrar a mensagem que não foi possível encontrar a lista de clientes.
	![err](https://user-images.githubusercontent.com/74334991/200382297-7623cc3d-5fd9-41c0-89f7-7591c0819825.png)

- Validações novos cadastros:
	- Nome:
		- Tamanho mínimo precisa ser de 4 caracteres.
		- Tamanho máximo excedido de 100 caracteres.
		- Campo obrigatório
	- Documento: 
		- Tamanho mínimo precisa ser de 11 caracteres.
		- Tamanho máximo excedido de 14 caracteres.
		- Campo obrigatório
	- Endereço:
		- Tamanho mínimo precisa ser de 5 caracteres.
		- Campo obrigatório
	- Longitude:
		- Tamanho mínimo precisa ser de 5 caracteres.
		- Campo obrigatório
	- Latitude:
		- Tamanho mínimo precisa ser de 5 caracteres.
		- Campo obrigatório

![validation](https://user-images.githubusercontent.com/74334991/200383148-c1ccbc1c-e1ce-4e5e-a17b-105cc44cb82b.png)



