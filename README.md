Projeto consulta e cadastro de Clientes
======

### O projeto "Consulta e Cadastro de Clientes" é uma aplicação web desenvolvida utilizando Angular, Spring Boot e Maven. Ele permite cadastrar, listar, editar e excluir clientes em um sistema.

# ***1° Forma de executar esse projeto no Docker***		
		
# Pode ser usado o Docker Compose, para iniciar todos ao mesmo tempo

## Baixar o projeto:
- Git clone:

		git clone https://github.com/wanderalvess/clients-angular-spring.git
		
## Criar as imagens:
- ir até a pasta clients-back: 

		cd ./clients-back

- executar no terminal: 

		docker build -t wanderalvess/clients-back:2.0.0 .

- ir até a pasta clients-front: 

		cd ./clients-front

- executar no terminal: 

		npm install

- executar no terminal: 

		docker build -t wanderalvess/clients-front:2.0.0 .

- voltar para pasta raiz:

		cd ..

- executar no terminal: 

		docker-compose up
		
- Container será iniciado no caminho:

		http://localhost:8081/clients
	
# ***2° Forma de executar esse projeto baixando o repositório***
	
## Baixar projeto:
- Git clone:

		git clone https://github.com/wanderalvess/clients-angular-spring.git
	
## Formas de Build no Back-End

### Build Docker
- ***Criar imagem e executar***
	- ir até a pasta clients-back: 
	
			cd ./clients-back

	- executar no terminal: 
	
			docker build -t wanderalvess/clients-back:2.0.0 .
		
	- executar no terminal: 
	
			docker run -p 8080:8080 wanderalvess/clients-back:2.0.0
  
    - Container será iniciado no caminho:

			http://localhost:8080/api/clients
		

### Build TomCat
- ***Executar metódo principal (main)***
	- ir até a pasta clients-front: 
	
			cd ./clients-back
			
	- executar no terminal:  
	
			maven clean install
			
    - executar ou depurar a aplicação
    - Tomcat será iniciado no caminho: 
	
			http://localhost:8080/api/clients 
    
## Formas de Build no Front-End

### Build Docker
- ***Criar imagem e executar***
	- ir até a pasta clients-front: 
	
			cd ./clients-front
			
	- executar no terminal: 
			
			docker build -t wanderalvess/clients-front:1.0.0 .
			
	- executar no terminal: 
	
			docker run -p 8081:8081 wanderalvess/clients-front:1.0.0
			
	
### Build Ng Serve

- ***Executar aplicação***
	- ir até a pasta clients-front:
	
		cd ./clients-front
			
    - executar no terminal:
	
		npm install
			
    - executar no terminal:
		
		npm run start

    - Serve será iniciado no caminho:
	
		http://localhost:4200


# ***Informações Sobre o Front-End:***

## Esse projeto foi feito com angular e node
- ***Versões:***
	- Angular CLI: 15.2.6
	- Angular: 15.2.7
	- Node: 14.20.0
	- Package Manager: npm 6.14.17


## Documentação via Compodoc

- ***Subir serve de documentação***
	- executar no terminal: 
	
			npm run serve-docs
			
	- Server Documentação no caminho: 
	
			http://127.0.0.1:9000


# ***Informações Sobre o Back-End:***

## Esse projeto foi feito com Java, SpringBot e Maven.
- ***Versões:***
	- Java: 11.0.9
	- Apache Maven: 3.8.6
	- SpringBot: 2.7.5
  

Plano de Teste
======

### ***Listar Clientes:***
- Após build dos projetos, a tela inicial será mostrado os clientes cadastrados.
![clients](https://github.com/wanderalvess/clients-angular/assets/74334991/51985494-a7a4-4642-b28f-3b4e4d28ca46)

### ***Ações:***
- No botão de adicionar (icone de +) do lado direito, será possível cadastrar novos clientes.
![register](https://github.com/wanderalvess/clients-angular/assets/74334991/ff3fe30e-6239-4009-8fae-b4fd29d3ca40)

- No Botão de excluir (icone de lixeira) do lado direito das informações do cliente, será possível excluir um cadastro de cliente.
![delete](https://github.com/wanderalvess/clients-angular/assets/74334991/b74e85b6-88f7-4f0a-823a-575ea2300014)

- No Botão de editar (icone de lápis) do lado direito das informações do cliente, será possível editar um cadastro de cliente.
![edit](https://github.com/wanderalvess/clients-angular/assets/74334991/6ca2ffe9-37ec-4230-b0c6-e3ec494b870c)

- No botão de salvar, na tela de cadastro de cliente, será possível salvar um novo cadastro ou que está sendo editado.

- No botão de cancelar, na tela de cadastro de cliente, será possível voltar para a tela inicial.

- No botão de buscar localização, na tela de cadastro de cliente, será possível adicionar uma marcador com a localização do cliente (Campo latitude e Longitude é obrigatório)
![localization](https://user-images.githubusercontent.com/74334991/232457796-254afb32-20b7-4cf6-aae0-ff5e655c6566.png)

### ***Validações:***

- Validações de conexão:
	- Se o front-end estiver em execução e não conseguir encontrar o back-end, mostrar a mensagem que não foi possível encontrar a lista de clientes.
	![connection](https://user-images.githubusercontent.com/74334991/232464352-9268507e-a64e-4b50-af43-adc5d1551162.png)

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

![validation](https://user-images.githubusercontent.com/74334991/232464084-25f37b47-5613-4fe3-ba09-d1ca8d5bac76.png)

- OS testes unitários para o backend estão localizados na pasta src/test e pode ser acessado através de uma ide:
![teste](https://github.com/wanderalvess/clients-angular/assets/74334991/24254b0b-5726-4ae1-9e24-6ef8ff5b6077)



