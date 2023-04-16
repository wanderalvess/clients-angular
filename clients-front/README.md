Projeto consulta e cadastro de Clientes Front
======

### ***Informações Sobre o Projeto:***

- Esse projeto foi feito com angular e node
- ***Versões:***
	- Angular CLI: 15.2.6
	- Angular: 15.2.7
	- Node: 14.20.0
	- Package Manager: npm 6.14.17

### Build Docker
- ***Criar imagem e executar***
	- ir até a pasta clients-front: 
	
			cd ./clients-front
			
	- executar no terminal: 
	
			npm install
			
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

Documentação via Compodoc
======
- ***Subir serve de documentação***
  - npm run serve-docs
  - Server Documentação no caminho: http://127.0.0.1:9000
