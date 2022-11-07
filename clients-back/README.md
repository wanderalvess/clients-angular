Projeto consulta e cadastro de Clientes Back
======

### ***Informações Sobre o Projeto:***

- Esse projeto foi feito com Java, SpringBot, Maven e criado os container com docker.
- ***Versões:***
	- Java: 11.0.9
	- Apache Maven: 3.8.6
	- SpringBot: 2.7.5

Build Docker
======
- ***Criar imagem e executar***
	- docker build -t wanderalvess/clients-back:1.0.0 .
	- docker run -p 8080:8080 wanderalvess/clients-back:1.0.0

Build TomCat
======
- ***Executar metódo principal (main)***
	- maven clean install
    - executar ou depurar
    - Tomcat será iniciado no caminho: http://localhost:8080/api/clients (http)


Serviços API
======


### Serviços Disponíveis:

### 1) ***Consulta de todos clientes cadastrados***
   - Nessa consulta, basta apenas fazer a requisição da url.


- ***Método:*** GET


- ***Url:*** `http://localhost:8080/api/clients`


- ***Exemplo:*** `http://localhost:8080/api/clients`


- ***Retorno:*** 
				{
					"name": "João",
					"document": "11.111.111/0001-11",
					"address": "Av. 136",
					"latitude": "-16.700884642335396",
					"longitude": "-49.25342831243458",
					"_id": 1
				}


### 2) ***Consulta de clientes cadastrados por id***
   - Nessa consulta, basta apenas passar o id do cliente na url.


- ***Método:*** GET


- ***Url:*** `http://localhost:8080/api/clients/{id}`


- ***Exemplo:*** `http://localhost:8080/api/clients/1`


- ***Retorno:*** 
				{
					"name": "João",
					"document": "11.111.111/0001-11",
					"address": "Av. 136",
					"latitude": "-16.700884642335396",
					"longitude": "-49.25342831243458",
					"_id": 1
				}


### 3) ***Cadastro de clientes***
   - Nesse serviço, é preciso informar todos os dados do cliente no corpo da requisição para o cadastro. É utilizado o método POST.


- ***Método:*** POST


- ***Url:*** `http://localhost:8080/api/clients`

- ***Parâmetros:*** 

  - **name:** Nome do Contribuinte
  - **document:** O documento pode ser informado o CNPJ ou CPF do cliente
  - **address:** Nesse parametro é informado o endereço do cliente
  - **latitude:** É informado a latitude do endereço do cliente
  - **longitude:** É informado a longitude do endereço do cliente


- ***Exemplo:*** 
				{
					"name": "Maria",
					"document": "22.222.222/0001-22",
					"address": "Av. 122",
					"latitude": "-16.700884642335396",
					"longitude": "-49.25342831243458"
				}


- ***Retorno:*** 
				{
					"name": "Maria",
					"document": "11.111.111/0001-11",
					"address": "Av. 136",
					"latitude": "-16.700884642335396",
					"longitude": "-49.25342831243458",
					"_id": 3
				}

### 4) ***Atualização de clientes***
   - Nesse serviço, será feito a atualização dos dados do cliente, é preciso informar as alterações do cliente no corpo da requisição para o cadastro.


- ***Método:*** PUT


- ***Url:*** `http://localhost:8080/api/clients/{id}`

- ***Parâmetros:*** 

  - **name:** Nome do Contribuinte
  - **document:** O documento pode ser informado o CNPJ ou CPF do cliente
  - **address:** Nesse parametro é informado o endereço do cliente
  - **latitude:** É informado a latitude do endereço do cliente
  - **longitude:** É informado a longitude do endereço do cliente


- ***Exemplo:*** 
				{
					"name": "Pedro",
					"document": "33.333.333/0001-33",
					"address": "Av. 133",
					"latitude": "-16.700884642335396",
					"longitude": "-49.25342831243458"
				}


- ***Retorno:*** 
				{
					"name": "Pedro",
					"document": "33.333.333/0001-33",
					"address": "Av. 133",
					"latitude": "-16.700884642335396",
					"longitude": "-49.25342831243458",
					"_id":3
				}

### 5) ***Exclusão de clientes***
   - Nessa consulta, basta apenas passar o id do cliente na url e será feito a exclusão do cliente.


- ***Método:*** DELETE


- ***Url:*** `http://localhost:8080/api/clients/{id}`


- ***Exemplo:*** `http://localhost:8080/api/clients/1`

